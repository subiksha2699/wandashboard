import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { SdwanApiService } from '../../core/sdwan-api.service';
import { EdgeDevice, SiteSummary } from '../../core/models';

@Component({
  selector: 'app-site',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './site.component.html',
  styleUrls: ['./site.component.css']
})
export class SiteComponent implements OnInit, OnDestroy {
  private readonly api = inject(SdwanApiService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  private uptimeInterval: ReturnType<typeof setInterval> | null = null;

  siteId = '';
  siteName = '';
  site: SiteSummary | null = null;
  devices: EdgeDevice[] = [];
  loading = true;
  error: string | null = null;

  totalDevices = 0;
  totalOnline = 0;
  totalOffline = 0;
  totalMaintenance = 0;

  // Map of device id -> formatted uptime string
  uptimeMap: Record<string, string> = {};

  ngOnInit(): void {
    this.siteId = this.route.snapshot.paramMap.get('siteId') || '';

    const navState = this.router.getCurrentNavigation()?.extras.state
      ?? history.state;
    if (navState?.['site']) {
      this.site = navState['site'] as SiteSummary;
      this.siteName = this.site.name;
    }

    this.api.getEdgeDevices(this.siteId).subscribe({
      next: (devices) => {
        this.devices = devices;
        this.calculateTotals(devices);
        this.calculateUptimes();
        this.startUptimeTimer();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load edge devices';
        this.loading = false;
        console.error(err);
      }
    });
  }

  ngOnDestroy(): void {
    if (this.uptimeInterval !== null) {
      clearInterval(this.uptimeInterval);
      this.uptimeInterval = null;
    }
  }

  private startUptimeTimer(): void {
    this.uptimeInterval = setInterval(() => {
      this.calculateUptimes();
    }, 60000);
  }

  private calculateUptimes(): void {
    const now = new Date().getTime();
    for (const device of this.devices) {
      if (device.lastUpTime && device.status !== "DOWN") {
        const upTime = new Date(device.lastUpTime).getTime();
        const diffMs = now - upTime;
        this.uptimeMap[device.id] = this.formatUptime(diffMs);
      } else {
        this.uptimeMap[device.id] = '—';
      }
    }
  }

  private formatUptime(ms: number): string {
    if (ms < 0) return '0d 0h 00m';
    const totalSeconds = Math.floor(ms / 1000);
    const days = Math.floor(totalSeconds / 86400);
    const hours = Math.floor((totalSeconds % 86400) / 3600);
    const minutes = Math.floor((totalSeconds % 3600) / 60);
    return `${days}d ${hours}h ${minutes.toString().padStart(2, '0')}m`;
  }

  private calculateTotals(devices: EdgeDevice[]): void {
    this.totalDevices = devices.length;
    this.totalOnline = devices.filter(d => d.status === 'HEALTHY' || d.status === 'DEGRADED').length;
    this.totalOffline = devices.filter(d => d.status === 'DOWN' || d.status === 'MAINTENANCE').length;
    this.totalMaintenance = devices.filter(d => d.status === 'MAINTENANCE').length;
  }

  getStatusClass(status: string): string {
    return `status--${status.toLowerCase()}`;
  }

  navigateToDevice(device: EdgeDevice): void {
    this.router.navigate(['/edge', device.id], {
      state: { siteName: this.siteName, siteId: this.siteId }
    });
  }
}

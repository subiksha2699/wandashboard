import { CommonModule } from '@angular/common';
import { Component, inject, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { SdwanApiService } from '../../core/sdwan-api.service';
import { EdgeDeviceDetail, DevicePort } from '../../core/models';

@Component({
  selector: 'app-edge-detail',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './edge-detail.component.html',
  styleUrls: ['./edge-detail.component.css']
})
export class EdgeDetailComponent implements OnInit, OnDestroy {
  private readonly api = inject(SdwanApiService);
  private readonly route = inject(ActivatedRoute);
  private readonly router = inject(Router);

  private uptimeInterval: ReturnType<typeof setInterval> | null = null;

  deviceId = '';
  device: EdgeDeviceDetail | null = null;
  loading = true;
  error: string | null = null;
  uptime = '—';

  // Passed via router state
  siteName = '';
  siteId = '';

  ngOnInit(): void {
    this.deviceId = this.route.snapshot.paramMap.get('deviceId') || '';

    const navState = this.router.getCurrentNavigation()?.extras.state ?? history.state;
    if (navState?.['siteName']) {
      this.siteName = navState['siteName'];
    }
    if (navState?.['siteId']) {
      this.siteId = navState['siteId'];
    }

    this.api.getEdgeDeviceDetail(this.deviceId).subscribe({
      next: (device) => {
        this.device = device;
        this.siteId = this.siteId || device.siteId;
        this.calculateUptime();
        this.startUptimeTimer();
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load edge device details';
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
      this.calculateUptime();
    }, 1000);
  }

  private calculateUptime(): void {
    if (this.device?.lastUpTime) {
      const now = new Date().getTime();
      const upTime = new Date(this.device.lastUpTime).getTime();
      const diffMs = now - upTime;
      this.uptime = this.formatUptime(diffMs);
    } else {
      this.uptime = '—';
    }
  }

  private formatUptime(ms: number): string {
    if (ms < 0) return '0D 0H 00M';
    const totalSeconds = Math.floor(ms / 1000);
    const days = Math.floor(totalSeconds / 86400);
    const hours = Math.floor((totalSeconds % 86400) / 3600);
    const minutes = Math.floor((totalSeconds % 3600) / 60);
    return `${days}D ${hours}H ${minutes.toString().padStart(2, '0')}M`;
  }

  getStatusClass(status: string): string {
    return `status--${status.toLowerCase()}`;
  }

  getPortStatusClass(status: string): string {
    return status === 'UP' ? 'port--up' : 'port--down';
  }

  getWanPortsUp(): number {
    return this.device?.wanPorts.filter(p => p.status === 'UP').length || 0;
  }

  getLanPortsUp(): number {
    return this.device?.lanPorts.filter(p => p.status === 'UP').length || 0;
  }
}

import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SdwanApiService } from '../../core/sdwan-api.service';
import { SiteSummary } from '../../core/models';

interface SummaryCard {
  title: string;
  value: string | number;
  icon: string;
  color: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private readonly api = inject(SdwanApiService);

  sites: SiteSummary[] = [];
  summaryCards: SummaryCard[] = [];
  loading = true;
  error: string | null = null;

  ngOnInit(): void {
    this.api.getSites('org-1').subscribe({
      next: (sites) => {
        this.sites = sites;
        this.buildSummaryCards(sites);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load site data';
        this.loading = false;
        console.error(err);
      }
    });
  }

  private buildSummaryCards(sites: SiteSummary[]): void {
    const totalSites = sites.length;
    const totalEdges = sites.reduce((sum, s) => sum + s.totalEdges, 0);
    const healthySites = sites.filter(s => s.healthStatus === 'HEALTHY').length;
    const degradedSites = sites.filter(s => s.healthStatus === 'DEGRADED').length;
    const downSites = sites.filter(s => s.healthStatus === 'DOWN').length;

    this.summaryCards = [
      { title: 'Total Sites', value: totalSites, icon: 'pi pi-map-marker', color: '#3b82f6' },
      { title: 'Total Edge Devices', value: totalEdges, icon: 'pi pi-server', color: '#6366f1' },
      { title: 'Healthy Sites', value: healthySites, icon: 'pi pi-check-circle', color: '#22c55e' },
      { title: 'Degraded Sites', value: degradedSites, icon: 'pi pi-exclamation-triangle', color: '#f59e0b' },
      { title: 'Down Sites', value: downSites, icon: 'pi pi-times-circle', color: '#ef4444' }
    ];
  }

  getHealthClass(status: string): string {
    return `health--${status.toLowerCase()}`;
  }

  getOnlineDevices(site: SiteSummary): number {
    return site.healthyDevices;
  }

  getOfflineDevices(site: SiteSummary): number {
    return site.downDevices;
  }
}

import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { RouterLink } from '@angular/router';
import { SdwanApiService } from '../../core/sdwan-api.service';
import { SiteSummary } from '../../core/models';

@Component({
  selector: 'app-organization',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './organization.component.html',
  styleUrls: ['./organization.component.css']
})
export class OrganizationComponent implements OnInit {
  private readonly api = inject(SdwanApiService);

  sites: SiteSummary[] = [];
  loading = true;
  error: string | null = null;

  // Org-level totals derived from site data
  totalSites = 0;
  totalHealthy = 0;
  totalDegraded = 0;
  totalDown = 0;
  totalEdgeDevices = 0;

  ngOnInit(): void {
    this.api.getSites('org-1').subscribe({
      next: (sites) => {
        this.sites = sites;
        this.calculateTotals(sites);
        this.loading = false;
      },
      error: (err) => {
        this.error = 'Failed to load organization data';
        this.loading = false;
        console.error(err);
      }
    });
  }

  private calculateTotals(sites: SiteSummary[]): void {
    this.totalSites = sites.length;
    this.totalHealthy = sites.filter(s => s.healthStatus === 'HEALTHY').length;
    this.totalDegraded = sites.filter(s => s.healthStatus === 'DEGRADED').length;
    this.totalDown = sites.filter(s => s.healthStatus === 'DOWN').length;
    this.totalEdgeDevices = sites.reduce((acc,s) => s.totalEdges + acc , 0);
  }

  getHealthClass(status: string): string {
    return `health--${status.toLowerCase()}`;
  }
}

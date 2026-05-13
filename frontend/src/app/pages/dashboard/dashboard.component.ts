import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { CardModule } from 'primeng/card';
import { SdwanApiService } from '../../core/sdwan-api.service';
import { catchError, map, of, startWith } from 'rxjs';

interface SummaryCard {
  title: string;
  value: string | number;
  icon: string;
  color: string;
  trend?: string;
}

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, CardModule],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  private readonly route = inject(ActivatedRoute);
  private readonly api = inject(SdwanApiService);

  pageTitle = 'Dashboard Overview';
  currentPage = 'dashboard';

  summaryCards: SummaryCard[] = [
    {
      title: 'Total Sites',
      value: 12,
      icon: 'pi pi-map-marker',
      color: '#3b82f6',
      trend: '+2 this month'
    },
    {
      title: 'Active Devices',
      value: 48,
      icon: 'pi pi-server',
      color: '#10b981',
      trend: '96% online'
    },
    {
      title: 'Healthy Sites',
      value: 9,
      icon: 'pi pi-check-circle',
      color: '#22c55e',
      trend: '75% healthy'
    },
    {
      title: 'Degraded Sites',
      value: 2,
      icon: 'pi pi-exclamation-triangle',
      color: '#f59e0b',
      trend: 'Needs attention'
    },
    {
      title: 'Down Sites',
      value: 1,
      icon: 'pi pi-times-circle',
      color: '#ef4444',
      trend: 'Critical'
    },
    {
      title: 'Avg Latency',
      value: '12.4ms',
      icon: 'pi pi-clock',
      color: '#8b5cf6',
      trend: '-2.1ms vs last week'
    }
  ];

  recentAlerts = [
    { severity: 'critical', message: 'Site "Branch-NYC" is DOWN - all devices offline', time: '2 min ago' },
    { severity: 'warning', message: 'Device "edge-router-05" high packet loss (4.2%)', time: '15 min ago' },
    { severity: 'warning', message: 'Site "DC-West" degraded - 1 device offline', time: '32 min ago' },
    { severity: 'info', message: 'WAN failover completed on "edge-router-12"', time: '1 hr ago' },
    { severity: 'info', message: 'Firmware update available for 3 devices', time: '3 hr ago' }
  ];

  siteHealthSummary = [
    { name: 'Headquarters', status: 'HEALTHY', devices: 8, onlineDevices: 8 },
    { name: 'DC-East', status: 'HEALTHY', devices: 6, onlineDevices: 6 },
    { name: 'DC-West', status: 'DEGRADED', devices: 5, onlineDevices: 4 },
    { name: 'Branch-Chicago', status: 'HEALTHY', devices: 4, onlineDevices: 4 },
    { name: 'Branch-LA', status: 'HEALTHY', devices: 4, onlineDevices: 4 },
    { name: 'Branch-NYC', status: 'DOWN', devices: 3, onlineDevices: 0 }
  ];

  apiStatus$ = this.api.getHealth().pipe(
    map(() => ({ connected: true, label: 'API Connected' })),
    startWith({ connected: false, label: 'Connecting...' }),
    catchError(() => of({ connected: false, label: 'API Offline' }))
  );

  ngOnInit(): void {
    const page = this.route.snapshot.data['page'];
    if (page) {
      this.currentPage = page;
      this.pageTitle = this.getPageTitle(page);
    }
  }

  private getPageTitle(page: string): string {
    const titles: Record<string, string> = {
      dashboard: 'Dashboard Overview',
      organization: 'Organization',
      sites: 'Sites',
      devices: 'Devices',
      'wan-metrics': 'WAN Metrics',
      alerts: 'Alerts',
      settings: 'Settings'
    };
    return titles[page] || 'Dashboard';
  }

  getSeverityClass(severity: string): string {
    return `alert--${severity}`;
  }

  getHealthClass(status: string): string {
    return `health--${status.toLowerCase()}`;
  }
}

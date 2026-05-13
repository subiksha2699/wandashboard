import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive } from '@angular/router';
import { TooltipModule } from 'primeng/tooltip';

interface NavItem {
  label: string;
  icon: string;
  route: string;
}

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [CommonModule, RouterLink, RouterLinkActive, TooltipModule],
  templateUrl: './sidebar.component.html',
  styleUrls: ['./sidebar.component.css']
})
export class SidebarComponent {
  collapsed = false;

  navItems: NavItem[] = [
    { label: 'Dashboard', icon: 'pi pi-th-large', route: '/dashboard' },
    { label: 'Organization', icon: 'pi pi-building', route: '/organization' },
    { label: 'Sites', icon: 'pi pi-map-marker', route: '/sites' },
    { label: 'Devices', icon: 'pi pi-server', route: '/devices' },
    { label: 'WAN Metrics', icon: 'pi pi-chart-line', route: '/wan-metrics' },
    { label: 'Alerts', icon: 'pi pi-bell', route: '/alerts' },
    { label: 'Settings', icon: 'pi pi-cog', route: '/settings' }
  ];

  toggleSidebar(): void {
    this.collapsed = !this.collapsed;
  }
}

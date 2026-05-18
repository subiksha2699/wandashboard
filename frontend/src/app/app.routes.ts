import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    redirectTo: 'dashboard',
    pathMatch: 'full'
  },
  {
    path: 'dashboard',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent)
  },
  {
    path: 'organization',
    loadComponent: () =>
      import('./pages/organization/organization.component').then(m => m.OrganizationComponent)
  },
  {
    path: 'site/:siteId',
    loadComponent: () =>
      import('./pages/site-detail/site.component').then(m => m.SiteComponent)
  },
  {
    path: 'edge/:deviceId',
    loadComponent: () =>
      import('./pages/edge-detail/edge-detail.component').then(m => m.EdgeDetailComponent)
  },
  {
    path: 'sites',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    data: { page: 'sites' }
  },
  {
    path: 'devices',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    data: { page: 'devices' }
  },
  {
    path: 'wan-metrics',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    data: { page: 'wan-metrics' }
  },
  {
    path: 'alerts',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    data: { page: 'alerts' }
  },
  {
    path: 'settings',
    loadComponent: () =>
      import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent),
    data: { page: 'settings' }
  }
];

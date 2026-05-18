import { HttpClient } from '@angular/common/http';
import { Injectable, inject } from '@angular/core';
import { HealthResponse, Organisation, SiteSummary } from './models';

@Injectable({ providedIn: 'root' })
export class SdwanApiService {
  private readonly http = inject(HttpClient);
  private readonly baseUrl = 'http://localhost:8080/api/v1';

  getHealth() {
    return this.http.get<HealthResponse>(`${this.baseUrl}/health`);
  }

  getOrganizations() {
    return this.http.get<Organisation[]>(`${this.baseUrl}/organizations`);
  }

  getSites(organisationId: string) {
    return this.http.get<SiteSummary[]>(`${this.baseUrl}/sites`, {
      params: { organisationId }
    });
  }
}

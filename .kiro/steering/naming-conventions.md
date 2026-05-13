---
inclusion: always
---

# Naming Conventions and Code Standards

## Java Backend Naming Conventions

### Package Structure
```
com.example.sdwan
├── api/          # REST controllers
├── model/        # DTOs and entities
├── service/      # Business logic services
├── repository/   # Data access layer
├── config/       # Configuration classes
└── exception/    # Custom exceptions and error handling
```

### Class Naming
- **Controllers**: `{Resource}Controller` (e.g., `OrganizationController`, `SiteController`)
- **Services**: `{Resource}Service` (e.g., `OrganizationService`, `DeviceService`)
- **DTOs**: `{Resource}DTO` or `{Resource}Response` (e.g., `SiteDTO`, `DeviceResponse`)
- **Repositories**: `{Resource}Repository` (e.g., `SiteRepository`)
- **Exceptions**: `{Type}Exception` (e.g., `ResourceNotFoundException`)

### Method Naming
- **REST Endpoints**: Use HTTP verbs with resource names
  - `GET /api/organizations/{id}` → `getOrganization()`
  - `GET /api/sites/{id}/devices` → `getDevicesBySite()`
- **Service Methods**: Use descriptive action verbs
  - `calculateSiteHealth()`, `aggregateWanMetrics()`, `findDevicesByStatus()`

## Angular Frontend Naming Conventions

### File Structure
```
src/app/
├── core/                    # Singleton services, guards, interceptors
├── shared/                  # Reusable components, pipes, directives
├── pages/                   # Feature modules and page components
│   ├── dashboard/
│   ├── organization/
│   ├── site-detail/
│   └── device-detail/
└── models/                  # TypeScript interfaces and types
```

### Component Naming
- **Components**: `{Feature}Component` (e.g., `DashboardComponent`, `SiteDetailComponent`)
- **Services**: `{Resource}Service` (e.g., `SdwanApiService`, `DeviceService`)
- **Models**: `{Resource}` interface (e.g., `Organization`, `Device`, `WanMetrics`)
- **Files**: Use kebab-case (e.g., `site-detail.component.ts`, `wan-metrics.model.ts`)

### CSS Class Naming
- Use BEM methodology: `block__element--modifier`
- PrimeNG classes: Leverage existing PrimeNG CSS classes
- Custom classes: `sdwan-{component}__{element}--{modifier}`

## API Endpoint Naming

### Resource-Oriented URLs
```
GET    /api/organizations/{id}                    # Get organization details
GET    /api/organizations/{id}/sites              # Get sites for organization
GET    /api/sites/{id}                           # Get site details
GET    /api/sites/{id}/devices                   # Get devices for site
GET    /api/devices/{id}                         # Get device details
GET    /api/devices/{id}/wan-metrics             # Get WAN metrics for device
```

### Query Parameters
- Use camelCase: `?startTime=...&endTime=...`
- Keep to maximum 2 query parameters for POST/PUT/PATCH
- Use descriptive names: `?timeRange=1h&resolution=5m`

## Database/Model Naming

### Entity Properties
- Use camelCase in Java: `deviceId`, `siteHealth`, `wanBandwidth`
- Use snake_case in JSON responses: `device_id`, `site_health`, `wan_bandwidth`
- Boolean properties: Use `is` prefix: `isOnline`, `isHealthy`

### Enum Values
- Use UPPER_SNAKE_CASE: `HEALTHY`, `DEGRADED`, `DOWN`
- Device status: `ONLINE`, `OFFLINE`, `MAINTENANCE`
- Interface types: `WAN_PRIMARY`, `WAN_BACKUP`, `LAN`

## Constants and Configuration

### Java Constants
```java
public static final String API_BASE_PATH = "/api";
public static final int DEFAULT_PAGE_SIZE = 20;
public static final Duration WAN_METRICS_RETENTION = Duration.ofDays(30);
```

### Angular Constants
```typescript
export const API_ENDPOINTS = {
  ORGANIZATIONS: '/api/organizations',
  SITES: '/api/sites',
  DEVICES: '/api/devices'
} as const;
```

## Error Handling Naming

### Exception Classes
- `ResourceNotFoundException`: When requested resource doesn't exist
- `InvalidRequestException`: For validation errors
- `ServiceUnavailableException`: For external service failures

### Error Response Format
```json
{
  "error_code": "RESOURCE_NOT_FOUND",
  "message": "Site with ID 123 not found",
  "timestamp": "2026-05-13T10:30:00Z",
  "path": "/api/sites/123"
}
```

## Testing Naming Conventions

### Test Classes
- Unit tests: `{ClassName}Test` (e.g., `SiteServiceTest`)
- Integration tests: `{ClassName}IntegrationTest`
- Component tests: `{ComponentName}ComponentSpec`

### Test Methods
- Use descriptive names: `shouldReturnHealthyWhenAllDevicesOnline()`
- Follow pattern: `should{ExpectedBehavior}When{Condition}()`


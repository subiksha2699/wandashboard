---
inclusion: always
---

# REST API Standards for SD-WAN Dashboard

## API Design Principles

### Resource-Oriented Design
- Design APIs around resources (organizations, sites, devices) not actions
- Use nouns for resource names, not verbs
- Maintain consistent resource hierarchies: `/organizations/{id}/sites/{id}/devices`
- Use plural nouns for collections: `/sites`, `/devices`

### API Versioning
- Use URL path versioning: `/api/v1/`, `/api/v2/`
- Increment major version only for breaking changes
- Maintain backward compatibility within a version for at least 2 minor releases
- Include API version in response headers: `API-Version: 1.0.0`
- Deprecate endpoints with 6-month notice before removal
- Document migration guides for version upgrades

### HTTP Methods Usage
- **GET**: Retrieve resources (idempotent, cacheable)
- **POST**: Create new resources or complex queries
- **PUT**: Replace entire resource (idempotent)
- **PATCH**: Partial resource updates (preferred for updates)
- **DELETE**: Remove resources (idempotent)

### Standard HTTP Status Codes
```
200 OK          - Successful GET, PUT, PATCH
201 Created     - Successful POST with resource creation
204 No Content  - Successful DELETE or PUT with no response body
400 Bad Request - Invalid request format or parameters
401 Unauthorized - Authentication required
403 Forbidden   - Access denied
404 Not Found   - Resource doesn't exist
409 Conflict    - Resource state conflict
422 Unprocessable Entity - Validation errors
500 Internal Server Error - Server-side errors
```

## API Endpoint Standards

### Organization Endpoints
```
GET    /api/organizations/{id}           # Get organization details
GET    /api/organizations/{id}/sites     # Get sites for organization
GET    /api/organizations/{id}/summary   # Get organization health summary
```

### Site Endpoints
```
GET    /api/sites/{id}                   # Get site details
GET    /api/sites/{id}/devices           # Get devices for site
PATCH  /api/sites/{id}                   # Update site configuration
GET    /api/sites/{id}/health            # Get site health status
```

### Device Endpoints
```
GET    /api/devices/{id}                 # Get device details
GET    /api/devices/{id}/wan-metrics     # Get WAN telemetry data
PATCH  /api/devices/{id}                 # Update device configuration
GET    /api/devices/{id}/interfaces      # Get interface status
```

## Request/Response Standards

### JSON Format Requirements
- Use camelCase for property names in requests
- Use snake_case for property names in responses (for consistency with monitoring tools)
- Include proper Content-Type headers: `application/json`
- Use ISO 8601 format for timestamps: `2026-05-13T10:30:00Z`

### Query Parameter Limits
- **Maximum 2 query parameters** for POST, PUT, PATCH requests
- Use request body for complex parameters
- Common query parameters:
  - `?start_time=2026-05-13T00:00:00Z&end_time=2026-05-13T23:59:59Z`
  - `?page=1&size=20`

### Request Examples
```json
// PATCH /api/devices/{id}
{
  "name": "Edge Router 01",
  "location": "Building A"
}

// POST /api/devices/{id}/wan-metrics (complex query)
{
  "timeRange": {
    "start": "2026-05-13T00:00:00Z",
    "end": "2026-05-13T23:59:59Z"
  },
  "resolution": "5m"
}
```

### Response Examples
```json
// GET /api/sites/{id}
{
  "site_id": "site-123",
  "name": "Headquarters",
  "health_status": "HEALTHY",
  "device_count": 5,
  "online_devices": 5,
  "last_updated": "2026-05-13T10:30:00Z"
}

// GET /api/devices/{id}/wan-metrics
{
  "device_id": "device-456",
  "metrics": [
    {
      "timestamp": "2026-05-13T10:30:00Z",
      "bandwidth_utilization": 75.5,
      "latency_ms": 12.3,
      "packet_loss_percent": 0.1
    }
  ],
  "total_count": 288
}
```

## Security Standards

### HTTPS Requirements
- **HTTPS Only**: All API endpoints must use HTTPS in production
- Include security headers:
  ```
  Strict-Transport-Security: max-age=31536000; includeSubDomains
  X-Content-Type-Options: nosniff
  X-Frame-Options: DENY
  ```

### Authentication Headers
```
Authorization: Bearer <jwt-token>
X-API-Key: <api-key>  # For service-to-service calls
```

## Error Handling Standards

### Global Error Handler Implementation
Create a `GlobalErrorHandler` class that handles:
- `ResourceNotFoundException` → 404 Not Found
- `ValidationException` → 422 Unprocessable Entity
- `IllegalArgumentException` → 400 Bad Request
- `RuntimeException` → 500 Internal Server Error

### Error Response Format
```json
{
  "error": {
    "code": "RESOURCE_NOT_FOUND",
    "message": "Site with ID 'site-123' not found",
    "details": "The requested site does not exist or has been deleted",
    "timestamp": "2026-05-13T10:30:00Z",
    "path": "/api/sites/site-123"
  }
}
```

### Validation Error Format
```json
{
  "error": {
    "code": "VALIDATION_FAILED",
    "message": "Request validation failed",
    "validation_errors": [
      {
        "field": "name",
        "message": "Site name is required"
      },
      {
        "field": "device_count",
        "message": "Device count must be positive"
      }
    ],
    "timestamp": "2026-05-13T10:30:00Z",
    "path": "/api/sites"
  }
}
```

## API Documentation Standards

### README API Documentation
Maintain `backend/API_README.md` with:
- Complete endpoint list with examples
- Request/response schemas
- Error code definitions
- Authentication requirements
- Rate limiting information

### OpenAPI Specification
- Generate OpenAPI 3.0 specification from code annotations
- Include example requests and responses
- Document all error scenarios
- Provide clear parameter descriptions

## Performance Standards

### Response Time Targets
- Simple GET requests: < 100ms
- Complex aggregations: < 500ms
- WAN metrics queries: < 1000ms
- Bulk operations: < 2000ms

### Pagination Standards
```json
// GET /api/sites?page=1&size=20
{
  "data": [...],
  "pagination": {
    "page": 1,
    "size": 20,
    "total_pages": 5,
    "total_elements": 100,
    "has_next": true,
    "has_previous": false
  }
}
```

### Caching Headers
```
Cache-Control: public, max-age=300  # For relatively static data
Cache-Control: no-cache             # For real-time metrics
ETag: "version-hash"                # For conditional requests
```
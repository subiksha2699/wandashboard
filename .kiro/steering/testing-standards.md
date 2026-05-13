---
inclusion: always
---

# Testing Standards for SD-WAN Dashboard

## Testing Philosophy
- **Test-Driven Development**: Write tests to define expected behavior
- **Pyramid Testing**: More unit tests, fewer integration tests, minimal E2E tests
- **Deterministic Tests**: Use predictable mock data for consistent results
- **Fast Feedback**: Tests should run quickly and provide clear failure messages

## Java Backend Testing

### Unit Testing Standards
- **Framework**: JUnit 5 with Mockito for mocking
- **Coverage Target**: Minimum 80% code coverage for service layer
- **Test Structure**: Follow Arrange-Act-Assert pattern

```java
@ExtendWith(MockitoExtension.class)
class SiteServiceTest {
    
    @Mock
    private SiteRepository siteRepository;
    
    @InjectMocks
    private SiteService siteService;
    
    @Test
    void shouldReturnHealthyWhenAllDevicesOnline() {
        // Arrange
        Site site = createSiteWithDevices(ONLINE, ONLINE, ONLINE);
        when(siteRepository.findById("site-1")).thenReturn(Optional.of(site));
        
        // Act
        SiteHealth health = siteService.calculateSiteHealth("site-1");
        
        // Assert
        assertThat(health.getStatus()).isEqualTo(HealthStatus.HEALTHY);
    }
}
```

### Integration Testing
- **Framework**: Spring Boot Test with TestContainers (if needed)
- **Scope**: Test API endpoints with mock data
- **Database**: Use in-memory H2 for integration tests

```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SiteControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    void shouldReturnSiteDetails() {
        ResponseEntity<SiteDTO> response = restTemplate.getForEntity(
            "/api/sites/site-1", SiteDTO.class);
        
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody().getName()).isEqualTo("Headquarters");
    }
}
```

### Test Data Management
- **Mock Data**: Create deterministic test data in `TestDataFactory`
- **Builders**: Use builder pattern for complex test objects
- **Constants**: Define test constants in separate class

```java
public class TestDataFactory {
    public static Organization createTestOrganization() {
        return Organization.builder()
            .id("org-1")
            .name("Test Organization")
            .sites(List.of(createTestSite()))
            .build();
    }
    
    public static Site createSiteWithHealth(HealthStatus status) {
        List<Device> devices = switch(status) {
            case HEALTHY -> List.of(onlineDevice(), onlineDevice());
            case DEGRADED -> List.of(onlineDevice(), offlineDevice());
            case DOWN -> List.of(offlineDevice(), offlineDevice());
        };
        
        return Site.builder()
            .id("site-1")
            .name("Test Site")
            .devices(devices)
            .build();
    }
}
```

## Angular Frontend Testing

### Component Testing Standards
- **Framework**: Jasmine with Karma for unit tests
- **Testing Utilities**: Angular Testing Library for component testing
- **Coverage Target**: Minimum 70% code coverage for components

```typescript
describe('SiteDetailComponent', () => {
  let component: SiteDetailComponent;
  let fixture: ComponentFixture<SiteDetailComponent>;
  let mockSdwanService: jasmine.SpyObj<SdwanApiService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('SdwanApiService', ['getSiteDetails']);
    
    TestBed.configureTestingModule({
      imports: [SiteDetailComponent],
      providers: [
        { provide: SdwanApiService, useValue: spy }
      ]
    });
    
    fixture = TestBed.createComponent(SiteDetailComponent);
    component = fixture.componentInstance;
    mockSdwanService = TestBed.inject(SdwanApiService) as jasmine.SpyObj<SdwanApiService>;
  });

  it('should display site health status', () => {
    const mockSite = { id: 'site-1', name: 'Test Site', health: 'HEALTHY' };
    mockSdwanService.getSiteDetails.and.returnValue(of(mockSite));
    
    fixture.detectChanges();
    
    expect(component.site).toEqual(mockSite);
    expect(fixture.nativeElement.querySelector('.health-status')).toHaveTextContent('HEALTHY');
  });
});
```

### Service Testing
- **HTTP Testing**: Use HttpClientTestingModule for API service tests
- **Mock Responses**: Create realistic mock API responses
- **Error Handling**: Test error scenarios and retry logic

```typescript
describe('SdwanApiService', () => {
  let service: SdwanApiService;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [SdwanApiService]
    });
    
    service = TestBed.inject(SdwanApiService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  it('should fetch site details', () => {
    const mockSite = { id: 'site-1', name: 'Test Site' };
    
    service.getSiteDetails('site-1').subscribe(site => {
      expect(site).toEqual(mockSite);
    });
    
    const req = httpMock.expectOne('/api/sites/site-1');
    expect(req.request.method).toBe('GET');
    req.flush(mockSite);
  });

  it('should handle 404 errors gracefully', () => {
    service.getSiteDetails('invalid-id').subscribe({
      next: () => fail('Should have failed'),
      error: (error) => expect(error.status).toBe(404)
    });
    
    const req = httpMock.expectOne('/api/sites/invalid-id');
    req.flush('Site not found', { status: 404, statusText: 'Not Found' });
  });
});
```

## Test Data Standards

### Mock Data Consistency
- **Deterministic IDs**: Use predictable IDs like `org-1`, `site-1`, `device-1`
- **Realistic Values**: Use believable names, metrics, and timestamps
- **Relationship Integrity**: Ensure parent-child relationships are valid

### WAN Metrics Test Data
```typescript
export const mockWanMetrics: WanMetrics[] = [
  {
    timestamp: '2026-05-13T10:00:00Z',
    bandwidth_utilization: 75.5,
    latency_ms: 12.3,
    packet_loss_percent: 0.1,
    interface_name: 'wan0'
  },
  {
    timestamp: '2026-05-13T10:05:00Z',
    bandwidth_utilization: 78.2,
    latency_ms: 11.8,
    packet_loss_percent: 0.0,
    interface_name: 'wan0'
  }
];
```

## Performance Testing

### Load Testing Guidelines
- **Tool**: Use JMeter or Artillery for API load testing
- **Targets**: Test with 100+ concurrent users for WAN metrics endpoints
- **Scenarios**: Test typical user flows (org → site → device navigation)

### Frontend Performance Testing
- **Bundle Size**: Monitor Angular bundle size with `ng build --stats-json`
- **Rendering Performance**: Test chart rendering with large datasets
- **Memory Usage**: Monitor for memory leaks in long-running sessions

## Test Automation

### CI/CD Integration
- **Backend Tests**: Run on every commit with `mvn test`
- **Frontend Tests**: Run with `npm test -- --watch=false --browsers=ChromeHeadless`
- **Coverage Reports**: Generate and publish coverage reports

### Test Commands
```bash
# Backend testing
cd backend
mvn test                           # Run unit tests
mvn verify                         # Run integration tests
mvn jacoco:report                  # Generate coverage report

# Frontend testing
cd frontend
npm test                           # Run tests in watch mode
npm run test:ci                    # Run tests once for CI
npm run test:coverage              # Generate coverage report
```

## Testing Best Practices

### Do's
- ✅ Write tests before implementing features (TDD)
- ✅ Use descriptive test names that explain the scenario
- ✅ Test both happy path and error scenarios
- ✅ Mock external dependencies consistently
- ✅ Keep tests independent and isolated
- ✅ Use realistic test data that matches production patterns

### Don'ts
- ❌ Don't test implementation details, test behavior
- ❌ Don't write tests that depend on external services
- ❌ Don't ignore flaky tests - fix or remove them
- ❌ Don't test framework code (Angular, Spring Boot internals)
- ❌ Don't write overly complex test setups
- ❌ Don't skip testing error handling and edge cases

## Test Documentation

### Test Reports
- Generate HTML coverage reports for both frontend and backend
- Include test results in pull request reviews
- Track coverage trends over time

### Test Maintenance
- Review and update tests when requirements change
- Remove obsolete tests for deprecated features
- Refactor test code to maintain readability
- Document complex test scenarios and their purpose
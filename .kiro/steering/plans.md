---
inclusion: always
---

# SD-WAN Dashboard Development Plan

## Development Philosophy
- **Minimal Viable Product**: Keep the initial implementation simple and runnable
- **Iterative Development**: Build core functionality first, then enhance
- **Clean Architecture**: Maintain clear separation between frontend and backend concerns
- **Deterministic Data**: Use predictable mock data for consistent testing

## Recommended Build Order

### Phase 1: Foundation (Priority 1)
1. **Define API Contracts**
   - Create DTOs for Organization, Site, Device, and WAN metrics
   - Design RESTful endpoints following resource-oriented patterns
   - Implement basic CRUD operations with proper HTTP methods

2. **Create Mock Data Layer**
   - Build deterministic in-memory data structures
   - Implement data relationships (Organization → Sites → Devices)
   - Create realistic WAN telemetry data with time series

3. **Basic Backend Services**
   - Implement service layer for business logic
   - Add site health calculation logic
   - Create data access layer with mock repositories

### Phase 2: Core Views (Priority 2)
4. **Organization Summary Implementation**
   - Build organization overview endpoint
   - Create Angular organization component with PrimeNG
   - Implement basic navigation structure

5. **Site Detail Implementation**
   - Build site detail endpoints with device aggregation
   - Create site detail component with device listings
   - Implement site health status display

### Phase 3: Device Details (Priority 3)
6. **Device Detail Routes**
   - Create device detail endpoints with WAN metrics
   - Build device detail component with telemetry views
   - Implement WAN-focused charts using PrimeNG charts

7. **WAN History and Controls**
   - Add time-range selection functionality
   - Implement historical data endpoints
   - Create interactive charts for WAN metrics

### Phase 4: Polish (Priority 4)
8. **Error Handling and Edge Cases**
   - Implement global error handling
   - Add loading states and empty state handling
   - Create proper 404 and validation responses

9. **Performance and UX**
   - Optimize chart rendering for large datasets
   - Add responsive design considerations
   - Implement proper navigation breadcrumbs

## Implementation Constraints

### Code Quality
- **Readable and Simple**: Prioritize clarity over cleverness
- **Consistent Patterns**: Use the same patterns across similar components
- **Proper Separation**: Keep business logic in services, not components

### Navigation Requirements
- **Clear Hierarchy**: Maintain Organization → Site → Device flow
- **Breadcrumb Navigation**: Show current location in hierarchy
- **Deep Linking**: Support direct URLs to specific views

### Data Rules
- **Site Health Calculation**: Implement the three-state health model
- **WAN-Centric Focus**: Emphasize WAN interfaces in device views
- **Deterministic Behavior**: Ensure consistent data for testing

## Validation Checkpoints
- After each phase, verify both frontend and backend start successfully
- Test the complete user flow from organization to device level
- Validate that site health rules are correctly implemented
- Ensure WAN telemetry displays meaningful data

## Technical Debt Management
- Document any shortcuts taken for time constraints
- Identify areas that would need enhancement for production use
- Keep a list of potential performance improvements
- Note any security considerations for future implementation

---
inclusion: always
---

# Technology Stack Guidelines

## Core Technologies
- **Java 17**: Use for Spring Boot backend development
- **Angular 17**: Use for frontend development with standalone components
- **Spring Boot 3.x**: Latest stable version compatible with Java 17
- **Maven**: Build tool for Java backend (use `/tmp/sdwan-m2` as local repo)

## Frontend Libraries
- **PrimeNG**: Primary UI component library for Angular
  - Use for data tables, charts, buttons, forms, and layout components
  - Leverage PrimeNG themes for consistent styling
  - Use p-chart for WAN telemetry visualization
- **PrimeIcons**: Icon library that integrates with PrimeNG
- **@fortawesome/angular-fontawesome**: (Font Awesome) for fonts.
- **@ngx-charts**: Chart utility library (import this only if required and chart not already present in PrimeNg)
- **Tailwind**: CSS utility library for responsive layouts

## Development Tools
- **Node.js 20+**: Required for Angular development
- **npm 10+**: Package manager for frontend dependencies
- **Maven 3.9+**: Build tool for Java backend

## Code Organization
- Use Angular standalone components (Angular 17 feature)
- Implement lazy loading for route modules
- Use Angular services for API communication
- Follow Angular style guide for file naming and structure

## Build Commands
- Backend: `mvn -Dmaven.repo.local=/tmp/sdwan-m2 spring-boot:run`
- Frontend: `npm start` (development) or `npm run build` (production)

## Performance Considerations
- Use OnPush change detection strategy for components
- Implement virtual scrolling for large datasets
- Use Angular's built-in HTTP interceptors for error handling
- Leverage PrimeNG's lazy loading features for data tables


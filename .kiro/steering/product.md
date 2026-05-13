---
inclusion: always
---

# SD-WAN Dashboard Product Requirements

## Project Overview
Build a comprehensive SD-WAN monitoring dashboard with a Java Spring Boot backend and Angular frontend that provides real-time network visibility and management capabilities.

## User Journey Flow
The application must support a hierarchical navigation pattern:

1. **Organization View** (Landing Page)
   - Display organization-wide health summary
   - Show aggregate statistics across all sites
   - Provide quick access to critical alerts
   - Include high-level KPIs and metrics

2. **Site View** (Site Detail Page)
   - List all sites within the organization
   - Display site health status (HEALTHY/DEGRADED/DOWN)
   - Show device count and status per site
   - Provide site-level performance metrics

3. **Device View** (Device Detail Page)
   - Show individual device information
   - Display WAN-focused telemetry and metrics
   - Include interface status and configuration
   - Provide historical performance data

## Core Features

### Dashboard Requirements
- **Clean and Intuitive UI Design**: Use PrimeNG components for consistency
- **Data Aggregation**: Implement proper rollup of metrics from device → site → organization
- **WAN Metrics Visualization**: Focus on bandwidth, latency, packet loss, and availability
- **Real-time Updates**: Simulate live data updates for monitoring experience

### Site Health Rules
- **HEALTHY**: All devices in the site are online and functioning
- **DEGRADED**: At least one device is offline or experiencing issues
- **DOWN**: All devices in the site are offline

### WAN-Centric Focus
- Emphasize WAN interface monitoring over LAN interfaces
- Include metrics like: bandwidth utilization, latency, jitter, packet loss
- Provide historical trending for WAN performance
- Support time-range selection for historical data

## User Experience Goals
- Create a cohesive monitoring tool, not disconnected screens
- Ensure smooth navigation between organizational levels
- Provide meaningful context at each level of detail
- Include proper loading states and error handling
- Support responsive design for different screen sizes

## Success Criteria
- Users can quickly identify network issues at any level
- Navigation feels natural and purposeful
- Data presentation is clear and actionable
- The tool feels like a professional monitoring solution


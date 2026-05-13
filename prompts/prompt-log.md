# Prompt Log

Use this file to document meaningful AI interactions during the assignment.

## Entry Template

### Date

`YYYY-MM-DD`

### Goal

Short statement of what you were trying to achieve.

### Exact Prompt

```text
Paste the exact prompt here.
```

### Files / Context Provided

- `path/to/file`
- `path/to/other-file`

### Output Summary

Short summary of what AI returned.

### What I Kept

- Item kept from the AI response

### What I Changed Manually

- Manual improvement or correction

### What AI Missed Or Got Wrong

- Missing edge case
- Weak design choice
- Incorrect assumption

### Reusable?

`yes` or `no`, with a short reason.

---

## First prompt

### Date

`2026-05-13`

### Goal

Setting up steering files for kiro's spec-driven developement 

### Exact Prompt

```text
define all the steering files for kiro. I have mentioned certain guidelines and rules specified in my natural language in each of these file. Update these md files to keep kiro in context of the current project.
```

### Files / Context Provided

- `boilerplate/.kiro`

### Output Summary

The AI updated the tech.md , product.md, plans.md, naming-conventions.md using the initial guideline I provided for each of these files.

### What I Kept

- All the files that includes practical examples and clear guidelines 

### What I Changed Manually

- I used another prompt to include versioning in apis

### What AI Missed Or Got Wrong

- AI missed adding api contracts with versioning

### Reusable?

`yes` because this sets the basic context for kiro and all it's model to build this proect.

---

## Create Dashboard prompt

### Date

`2026-05-13`

### Goal

Setting up dashboard with sidebar by connecting to figma power

### Exact Prompt

```text
Let's create a SD-WAN dashboard with figma design as given here. https://www.figma.com/design/mfIfvp5RyCL2DN3JeCK57r/Untitled?node-id=1-2&t=iNKy26Ojpn5J7wed-4.

The dashboard should be collapsible with icons visible when collapsed. Hovering over the icons should show a pop up of the name of the page. Let's have the dashboard page under /src/app/pages/dashboard. 
```

### Files / Context Provided

- `wandashboard/frontend`
- `wandashboard/frontend/dashboard`
- `wandashboard/.kiro`

### Output Summary

A dashborad page with following is created
Summary cards with KPIs (sites, devices, health status, latency)
Site health overview table with availability bars
Recent alerts panel with severity indicators
API connection status indicator
Responsive layout

### What I Kept

- The sidebar and dashboard component

### What I Changed Manually

- I added a button the sidebar which is on it's border.

### What AI Missed Or Got Wrong

- It missed adding the header component

### Reusable?

`yes` it has created a shared folder that holds side-bar component
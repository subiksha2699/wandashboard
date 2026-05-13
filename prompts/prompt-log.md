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
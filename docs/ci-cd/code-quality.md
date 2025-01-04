# Code Quality Checks

This document describes the automated code quality checks implemented in our GitHub Actions workflow.

## Overview

The code quality workflow (`code-quality-check.yml`) runs automated style and quality checks on our Java codebase using Maven's Checkstyle plugin.

## Workflow Details

**File Location**: `.github/workflows/code-quality-check.yml`

### Triggers
- Runs on push to main branch
- Runs on pull requests targeting main branch

### What It Does

1. **Checkstyle Analysis**
   - Uses custom Checkstyle configuration (`config/checkstyle/custom_checks.xml`)
   - Enforces project-specific code style rules
   - Checks formatting, naming conventions, and code structure
   - Generates reports for code quality violations

### Configuration

The workflow uses two main Checkstyle configurations:
- Custom checks (`custom_checks.xml`)
- Google style checks (`google_code_style_checks.xml`)

### Reports and Results

- Results appear in the GitHub Actions tab for each run
- Fails the build if violations are found (failOnViolation=true)
- Console output shows detailed violation information

### Maven Configuration

```xml
<plugin>
    <groupId>org.apache.maven.plugins</groupId>
    <artifactId>maven-checkstyle-plugin</artifactId>
    <version>3.2.0</version>
    <!-- ... configuration details ... -->
</plugin>
```

## Common Issues and Solutions

1. **Build Failures**
   - Check the GitHub Actions log for specific violation details
   - Look for line numbers and file names in the error report
   - Fix style violations in the indicated locations

2. **False Positives**
   - If you believe a rule is triggering incorrectly, review the custom_checks.xml
   - Discuss with team before modifying rule configurations

## Local Execution

To run these checks locally before committing:
```bash
mvn checkstyle:check
```

This will use the same configuration as the CI pipeline.

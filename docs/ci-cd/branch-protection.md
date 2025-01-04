# Branch Protection and Required Checks

This document describes how branch protection rules are configured in our repository to ensure code quality and security standards.

## Branch Protection Configuration

### Accessing Branch Protection Settings
1. Navigate to repository on GitHub.com
2. Go to Settings → Branches
3. Under "Branch protection rules" click "Add rule" or edit existing rules

### Required Status Checks
Current mandatory checks before merging:
- `Code Quality Check` - Checkstyle analysis
- `Analyze (java-kotlin)` - CodeQL security scan

### Configuration Steps
1. In "Branch name pattern" enter `main`
2. Check "Require status checks to pass before merging"
3. Search and enable each required check by exact name:
   - `Code Quality Check`
   - `Analyze (java-kotlin)`
4. Enable "Require branches to be up to date before merging"

## Managing Required Checks

### Temporarily Disabling Checks
There are several ways to manage these checks:

1. **Repository-wide Disable**
   - Go to Settings → Branches
   - Edit the branch protection rule
   - Uncheck specific status checks
   - Click "Save changes"
   
2. **Skip Checks for Specific Commit**
   - Add `[skip ci]` or `[skip actions]` to commit message
   - Note: This won't bypass branch protection rules
   
3. **Emergency Override**
   - Repository admins can temporarily disable branch protection
   - Go to Settings → Branches
   - Edit protection rule
   - Uncheck "Require status checks to pass before merging"
   - Re-enable after emergency merge

### Re-enabling Checks
1. Return to branch protection settings
2. Re-check previously disabled checks
3. Save changes

## Best Practices

1. **Documentation**
   - Document any temporary disabling of checks
   - Include reason and duration in team communication
   - Create issue to track re-enabling of checks

2. **Emergency Process**
   - Define what constitutes an emergency
   - Document approval process for bypassing checks
   - Keep audit trail of bypassed checks

3. **Monitoring**
   - Regularly verify all required checks are enabled
   - Review bypass history periodically
   - Ensure temporary disables are re-enabled

## Required Checks Reference

### Code Quality Check
- **Purpose**: Style and format verification
- **Configuration**: `.github/workflows/code-quality-check.yml`
- **Documentation**: `docs/ci-cd/code-quality.md`

### CodeQL Analysis
- **Purpose**: Security vulnerability scanning
- **Configuration**: `.github/workflows/codeql.yml`
- **Documentation**: `docs/ci-cd/security-scans.md`

## Troubleshooting

1. **Check Not Appearing**
   - Verify workflow file exists and is properly named
   - Ensure workflow has run at least once
   - Check exact name matches in branch protection

2. **False Positives**
   - Document in issue tracker
   - Consider adjusting check configurations
   - Don't disable checks without team review

## Questions and Support
- For configuration issues: Contact repository administrators
- For check failures: Refer to specific check documentation
- For emergency bypasses: Follow team escalation process

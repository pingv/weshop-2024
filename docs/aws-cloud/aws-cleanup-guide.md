# AWS Resource Cleanup Commands Quick Reference

## Check Resources Across Regions
```bash
# List EC2 instances in all regions
for region in $(aws ec2 describe-regions --query 'Regions[].RegionName' --output text); do
    echo "Region: $region"
    aws ec2 describe-instances --region $region \
    --query 'Reservations[].Instances[?State.Name!=`terminated`].[InstanceId,InstanceType,State.Name]' \
    --output table
done

# Check specific region
aws ec2 describe-instances --region us-east-1
```

## Auto Scaling Groups (ASG)
```bash
# List ASGs
aws autoscaling describe-auto-scaling-groups

# Set ASG capacity to 0
aws autoscaling update-auto-scaling-group \
    --auto-scaling-group-name YOUR_ASG_NAME \
    --desired-capacity 0

# Delete ASG
aws autoscaling delete-auto-scaling-group \
    --auto-scaling-group-name YOUR_ASG_NAME
```

## EC2 & Related Resources
```bash
# Terminate EC2 instances
aws ec2 terminate-instances --instance-ids i-1234567890abcdef0

# List EBS volumes
aws ec2 describe-volumes \
    --query 'Volumes[?State!=`deleted`].[VolumeId,Size,State]' \
    --output table

# Check volume attachments
aws ec2 describe-volumes \
    --volume-ids vol-1234567890abcdef0 \
    --query 'Volumes[*].{VolumeId:VolumeId,State:State,InstanceId:Attachments[0].InstanceId}'

# List Elastic IPs
aws ec2 describe-addresses --output table

# Release Elastic IP
aws ec2 release-address --allocation-id eipalloc-1234567890abcdef0
```

## Elastic Beanstalk
```bash
# List environments
aws elasticbeanstalk describe-environments --output table

# Terminate environment
aws elasticbeanstalk terminate-environment \
    --environment-name YOUR_ENV_NAME

# Delete application
aws elasticbeanstalk delete-application \
    --application-name YOUR_APP_NAME
```

## S3 Buckets
```bash
# List buckets
aws s3 ls

# List bucket contents
aws s3 ls s3://bucket-name

# Delete empty bucket
aws s3 rb s3://bucket-name

# Delete non-empty bucket
aws s3 rb s3://bucket-name --force

## Unable to delete bucket due to policies that prevent deletion
# 1. Check bucket policy first
aws s3api get-bucket-policy --bucket <bucket-name>

# 2. Remove bucket policy to allow deletion
aws s3api delete-bucket-policy --bucket <bucket-name>

# 3. Remove all objects from bucket
aws s3 rm s3://<bucket-name> --recursive

# 4. Delete the empty bucket
aws s3 rb s3://<bucket-name>
```

## Load Balancers
```bash
# List load balancers
aws elbv2 describe-load-balancers \
    --query 'LoadBalancers[].[LoadBalancerArn,LoadBalancerName,State.Code]' \
    --output table
```

## RDS Instances
```bash
# List RDS instances
aws rds describe-db-instances \
    --query 'DBInstances[].[DBInstanceIdentifier,DBInstanceStatus]' \
    --output table
```

## Best Practices
1. Check resources across all regions
2. Start with application-level resources (Elastic Beanstalk, ASG)
3. Then clean up individual resources (EC2, EBS, EIP)
4. Leave system buckets (elasticbeanstalk-*) if planning to use the service again
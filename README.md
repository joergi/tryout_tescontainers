# tryout_testcontainers

## tryout for yourself:

### AWS (Amazon Web Service)
- AWS: creating S3 bucket, S3 IAM role, S3 user & add it to S3 role
- you have to change the accesskey + secretkey in AWSConfig.java to the one of the S3 user (soon: application.yml)
- the first test run, will take a little bit longer because of downloading the ~400MB container

### GCP (Google Cloud Plattform)
- Create a bucket on [GCP](https://console.cloud.google.com/storage/create-bucket)
- create service account as described [here](https://www.baeldung.com/java-google-cloud-storage) 

# fee-mgmt-svc
Repository for Fee Management microservice , responsible for fee related activities

Steps
1. This microservice need to be run second
2. Compile the Project using gradle build
   API first approach has been used, OpenAPI tool  will create the FeeApi controller interface stub
   fee-api --> build -->classes --> com.skiply.fee.api --> FeeApi
3. Run the application , it will start on port 8081
4. Import the Postman collection and execute the curl requests
5. H2 db console can be connected in browser
   JDBC URL : jdbc:h2:mem:feedb
   userName : sa
6.OpenAPI Spec file is located at
      fee-mgmt-svc/fee-api/src/main/resources/fee-openapi-spec-file.yaml

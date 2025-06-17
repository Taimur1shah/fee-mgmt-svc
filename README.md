# fee-mgmt-svc
1. Repository for Fee Management microservice , responsible for fee CRUD related activities
2. Fee will be payed using this service API http://127.0.0.1:8081/fee-service/api/fees/pay
   and it will call the Payment api to execute the transaction

## Techonology Stack
1. Java 17
2. Spring boot 3.2.5
3. Gradle
4. gradle-7.5
5. H2 db
6. Openapitools
7. 7. openapi: 3.0.3

## Steps to run the application
1. This microservice need to be run second
2. Compile the Project using gradle build by running command in cmd

   ./gradlew clean build
3. 
   API first approach has been used, OpenAPI tool  will create the FeeApi controller interface stub
   fee-api --> build -->classes --> com.skiply.fee.api --> FeeApi
4. Run the application , it will start on port 8081
5. Import the Postman collection and execute the curl requests
6. H2 db console can be connected in browser
   JDBC URL : jdbc:h2:mem:feedb
   userName : sa
6.OpenAPI Spec file is located at
      fee-mgmt-svc/fee-api/src/main/resources/fee-openapi-spec-file.yaml
7. Performance, Scalability, and Reliability Targets
   health checks and readiness
   http://localhost:8081/actuator/metrics
   http://localhost:8081/actuator/health/readiness
   http://localhost:8081/actuator/health/liveness
# Weather Forecast Service

Example project using Spring Boot, Spring Cloud, Open Feign and Java 11.

Spring Boot and Spring Cloud were chosen as they make really easy to stand up a new project.
Java 11 is chosen because Java 8 is no longer supported by Oracle plus the libraries chosen 
already support Java 11.

Open Feign makes a good choice to consume an API. This services consumes the Open Weather 
Map API. There is an ErrorDecoder to catch any non 200 errors from the API.

Spring Validation is used to ensure the city name size is between 1 and 100. These limits 
were chosen arbitrarily. I would've also used a regular expression, but I haven't checked
which characters are valid in the Open Weather Map API.

The service is divided in the following layers:
* **client** For Feign clients.
* **controller** For endpoints and user input validation.
* **dto** To pass information between the service layer, controller layer and final API user.
* **model** Maps the objects return by the API in this case.
* **service** _Business logic_. In this case it transforms the results we get from the
Open Weather Map API

There is a similar division for the tests. Most classes that end with *Tests* are Unit Tests.
`ForecastControllerIntegrationTests` contains Integration tests.

Swagger is chosen to document the API and it can be found in 
http://localhost:8080/forecasts/swagger-ui.html when the application is running. It can also 
be used to test the API

## How do I run and test this project?

The easiest way to run this project is with Gradle Wrapper.

1. Replace the value `<insert-api-key-here>` of `service.open-weather-map.api-key` 
in the file `src/main/resources/application.yml`.
Alternatively you can provide a `SERVICE_OPEN-WEATHER-MAP_API-KEY` environment variable.  
2. Open the terminal
3. Navigate to the folder in which the project is located
4. Execute `gradlew.bat bootRun` in Windows or `gradlew bootRun` in MacOS/Linux.
5. Execute the following command in your terminal in MacOS/Linux or equivalent in Windows

       curl 'http://localhost:8080/forecasts/data?cityName=Berlin' 

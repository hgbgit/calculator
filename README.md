# Loan Pro Calculator
A backend with Spring Boot with Login and Registration that supports JWT with Http Cookie or Authorization Header.
Calculator capabilities included in this backend service:

- ADDITION
- SUBTRACTION
- MULTIPLICATION
- DIVISION
- SQUARE_ROOT
- RANDOM_STRING

All users start with an initial balance of $10.0, eacho operation have a specific cost as shown in table below:


| Operation      | Cost |
|----------------|------|
| ADDITION       | 1.00 |   
| SUBTRACTION    | 1.00 |   
| MULTIPLICATION | 2.00 |   
| DIVISION       | 2.00 |   
| SQUARE_ROOT    | 2.50 |   
| RANDOM_STRING  | 3.50 |   
Api operation call example:
```bash 
curl --request POST \
  --url http://localhost:8080/api/calculation \
  --header 'Authorization: eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZ2IiLCJpYXQiOjE2OTYzNTU2NTYsImV4cCI6MTY5NjM2NTY1Nn0.lIQQC7dM6MT5jtBTGV-Dkn5ol-isdW7gmHVVaqQ7l9Q' \
  --header 'Content-Type: application/json' \
  --header 'accept: application/json, text/plain, */*' \
  --cookie loanpro=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJoZ2IiLCJpYXQiOjE2OTY1MzkyNDAsImV4cCI6MTY5NjU0OTI0MH0.QgdPiP_Ih_kUyImd0wesb43C8DIdGJPFSMpriweiKa0 \
  --data '{
	"a": 5,
	"b": 7,
	"operation": "ADDITION"
}' 

```

Api response example:

```json
{
	"result": "12"
}
```


## User Registration, Login and Authorization process.

![flow](flow.png)

## Spring Boot Server Architecture with Spring Security
You can have an overview of our Spring Boot Server with the diagram below:

![architecture](architecture.png)

# Tech Stack

Selecting the Ideal Tech Stack: A Strategic Choice Driven by Time and Requirement. Due to the requirements i selected the tech stack below because of more know how to accomplish desired features.  

## Java 17 and Spring 3.1.4

Java 17 and Spring Framework 3.1.4 are significant updates in the world of Java development.

Java 17, released in September 2021, is a long-term support (LTS) version, making it a stable choice for enterprises and developers. It brings several new features and enhancements to the Java platform, including improved performance, enhanced security, and new language features like pattern matching and sealed classes. Developers can take advantage of Java 17's capabilities to build robust and efficient applications.

Spring Framework 3.1.4, on the other hand, is a release from the Spring Framework 3.x series, which was widely used in the past. While Spring 3.1.4 is not the latest version (as of my last knowledge update in September 2021, the latest version was Spring Framework 5.x), it's important to note that the Spring ecosystem has evolved considerably. Spring Framework 5.x and later versions have introduced many enhancements, including better support for reactive programming, improved performance, and simplified configuration.

If you are using Spring Framework 3.1.4, it might be worth considering an upgrade to a more recent version like Spring Framework 5.x or Spring Boot to take advantage of the latest features, improvements, and ongoing support from the Spring community.

In summary, while Java 17 is a noteworthy LTS release with various enhancements, Spring Framework 3.1.4 is a version from the past. It's advisable for developers to explore the newer versions of the Spring Framework to benefit from the latest advancements in Java and Spring technology for their applications.

## MDC - Filter

The MDC (Mapped Diagnostic Context) filter offers several valuable benefits in the world of logging and application monitoring:

- Contextual Logging: MDC allows developers to associate contextual information, such as user IDs, session IDs, or request-specific data, with log entries. This makes it easier to understand the circumstances surrounding each log message.

- Thread Safety: In multi-threaded applications, MDC ensures that each thread has its own isolated context. This prevents context data from being mixed up between threads, providing accurate and reliable logging.

- Correlation: MDC is particularly useful in distributed systems and microservices architectures. It enables the correlation of log entries across multiple services, making it simpler to trace the flow of a request or transaction as it moves through various components.

- Debugging and Troubleshooting: With context-rich logs, developers can quickly pinpoint issues and troubleshoot problems. MDC-filtered logs provide essential details to identify the root causes of errors and failures.

- Security and Auditing: MDC can be used to include security-related information in logs, aiding in security audits and compliance. For instance, you can log user authentication details or access control decisions.

- Customization: MDC allows for flexible customization, enabling developers to define and include context data that aligns with their application's specific needs. In case of this project we use mdc filter to print user id as well as request id to enable a easier tracing when trying to solve problems.

## Feign Clients

Feign clients are a crucial component in modern microservices architecture and API communication. These clients are a part of the Spring Cloud ecosystem, a framework that simplifies the development of cloud-native applications.

In essence, a Feign client is a declarative HTTP client that allows developers to make HTTP requests to other services as if they were invoking local methods or functions. It abstracts away much of the boilerplate code typically associated with HTTP requests, making it easier to consume RESTful APIs or interact with other microservices.

## BDD Tests - Cucumber

Behavior-Driven Development (BDD) is a software development methodology that focuses on aligning the goals and activities of various stakeholders in a software project, including developers, testers, and non-technical business stakeholders. BDD encourages collaboration and communication among these groups to ensure that the software being developed meets the desired behavior and functionality.

At the heart of BDD is the idea that software should be defined and developed based on its expected behavior or outcomes, rather than just technical specifications. This approach helps ensure that the software is not only technically sound but also fulfills the business and user requirements effectively.

Cucumber is a popular BDD framework that facilitates the implementation of BDD principles in software development. It allows teams to define and document the desired behavior of a software application using plain text scenarios written in a human-readable language, typically known as Gherkin. These scenarios describe the expected behavior from the user's perspective.

Here's how Cucumber works:

- Feature Files: Scenarios are defined in feature files written in Gherkin. These files contain high-level descriptions of application features and their expected behavior.

- Step Definitions: Developers write step definitions in code (typically Java, Ruby, or other supported languages) to map the Gherkin steps in feature files to actual code that implements the behavior. Each step corresponds to an action or validation in the software.

- Test Execution: Cucumber executes the feature files and matches the steps to their corresponding step definitions. It reports the results, indicating whether the software behavior aligns with the expectations outlined in the feature files.

Cucumber offers several benefits in the BDD approach:

- Clarity: Gherkin scenarios are easy to understand by non-technical stakeholders, promoting clear communication about requirements.

- Collaboration: BDD encourages collaboration between developers, testers, product owners, and business analysts to ensure everyone has a shared understanding of the software's behavior.

- Test Automation: Cucumber facilitates the automation of acceptance tests, allowing for continuous testing and regression testing as the software evolves.

- Living Documentation: Feature files serve as living documentation, helping teams maintain an up-to-date and comprehensive record of the application's behavior.

- Behavior-Driven: Cucumber encourages teams to focus on the desired behavior and outcomes, which can lead to more user-centric and effective software.

# Mysql database

This project uses a Mysql 8 database.

# Running Local

## With Docker

```bash 
docker-compose up
```

## With local java instalation

 
```bash
mvn clean install 
cd target 
java -jar loan_pro_calculator.jar 
```

## Running tests locally

``` mvn verify ```
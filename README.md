# E-Store:  Wooden Home Decor


An online E-store system built in Java 8=>11 and Angular
  
## Team

- Khaled Aldasouki
- Hrishikesh Kumar
- Mohammad Hadi Elabed
- Maksim Rashchupkin
- Aya Alqawasmi


## Prerequisites

- Java 8=>11 
- Maven
- Angular
- Spring Boot


## How to run it

1. Clone the repository and go to the root directory.
2. Execute `mvn compile exec:java` in the estore-api directory
3. Execute `ng serve` in the estore-ui directory (You can also use `ng serve --open` so that the website automatically opens in your browser once it's ready)
4. If you didn't use `ng serve --open`, Open `http://localhost:4200/` in your browser. 

## Known bugs and disclaimers

1. When logging in the user is asked to confirm them logging in or creating an account, however if the user changes the username after they pressed `login` for the first time it will continue with the login process without asking to confirm again. A similar outcome occurs if the user tries to press login without entering a username, once they enter one they will instantly login without being prompted to confirm.

2. If a user removes an item from their cart the product stock does not go back up.

3. A user can create the order without selecting a payment method or entering an address.

4. If a user has 2 or more of the same product in their cart with the same woodtype and quantity, removing one wil remove all of them.

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory


## License

MIT License

See LICENSE for details.

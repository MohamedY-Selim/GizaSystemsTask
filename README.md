# GizaSystemsTask

This project automates a set of UI scenarios for the **XYZ Bank** website using Selenium WebDriver with Cucumber, and generates interactive reports using Allure Reports. The tests are executed automatically via GitHub Actions, and the reports are published to GitHub Pages.

## Project Overview

This project is designed to:

* Automate bank manager scenarios:

  * Add a new customer (with Data-Driven support).
  * Open a new account for a customer.
  * Search for an existing customer by first name.
  * Sort customers by postcode (ascending/descending).
  * Delete a customer.
* Automate customer scenarios:

  * Log in as a customer.
  * Deposit money and verify balance increase.
  * Verify that the deposit transaction appears in the Transactions page within the selected date range.
* Use Selenium WebDriver to interact with UI elements.
* Organize code using the Page Object Model (POM) design pattern with Cucumber BDD.
* Generate Allure reports to monitor and review test results.
* Configure GitHub Actions to run tests automatically and publish Allure reports to GitHub Pages.

## Project Structure

```
GizaSystemsTask
├── .github/workflows/main.yml         # GitHub Actions workflow to run tests and publish reports
├── src
│   ├── main/java/com/globalsqa/banking
│   │   ├── base                       # BasePage class: shared utility methods for all pages
│   │   ├── factory                    # DriverFactory + EndPoint constants
│   │   ├── model                      # Enums/Models like CustomerSection, ManagerSection
│   │   ├── pages
│   │   │   ├── customer               # Customer pages (DepositPage, TransactionsPage)
│   │   │   └── manager                # Manager pages (AddCustomerPage, CustomersPage, OpenAccountPage)
│   │   ├── utils                      # ConfigUtils, PropertiesUtils
│   │
│   └── test/java/com/globalsqa/banking
│       ├── hooks                      # Hooks for setup/teardown
│       ├── runners                    # RunnerTest for executing Cucumber features
│       ├── steps                      # Step Definitions for each feature
│       └── resources/features         # Feature files (Gherkin syntax)
│
├── pom.xml                            # Maven configuration and dependencies
└── README.md                          # Project documentation
```

## Setup Instructions

### Prerequisites

Ensure the following are installed:

* **Java (v22 or later)**
* **Maven**
* **Allure Commandline** (`brew install allure` on macOS)

### Cloning the Repository

```bash
git clone https://github.com/MohamedY-Selim/GizaSystemsTask.git
cd GizaSystemsTask
```

### Running the Tests

```bash
mvn clean test
```

This will execute all tests and generate Allure results in `target/allure-results`.

### Generating the Allure Report Locally

```bash
allure serve target/allure-results
```

This will open the Allure report in your default browser.

## Continuous Integration with GitHub Actions

GitHub Actions workflow is set up to:

* Run tests on every push to the `main` branch.
* Generate the Allure report.
* Deploy the report to GitHub Pages:
  [📊 View Allure Report](https://mohamedy-selim.github.io/GizaSystemsTask)

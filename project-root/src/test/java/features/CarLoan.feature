Feature: Car Loan EMI Calculation

  Scenario: Valid Car Loan EMI
    Given I open the Car Loan EMI Calculator
    When I enter loan amount "1500000", tenure "1", and interest rate "9.5"
    Then I should see the monthly EMI displayed


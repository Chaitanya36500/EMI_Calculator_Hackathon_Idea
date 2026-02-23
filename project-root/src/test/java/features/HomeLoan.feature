Feature: Home Loan EMI Calculation

  Scenario: Valid Home Loan EMI
    Given I open the Home Loan EMI Calculator
    When I enter home loan amount "1500000", tenure "1", and interest rate "9.5"
    Then I should see the monthly EMI for home loan displayed
    And I should store EMI details in Excel

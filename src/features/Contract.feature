Feature: dogovor check

  #1 проверка создания и проведения Договора
  Scenario: open contract creation
      Given main page is opened
      And i have HSE_UID_Customer and HSE_Contract_EOL roles
      When i click +Create button
      Then modal window is open
      And there are valid creation dialogs

  Scenario: fill required fields
    Given creation dialog opened
    When i type name <autotest> and number <autotest>
    And i set job type <Добыча>
    And i set start date at 01.01.2020
    And i set jobstart date at 02.01.2020
    And i set finish date at 01.04.2020
    And i check if all required fields are filled
    Then Save button is active

  Scenario: save the dogovor
    Given creation dialog opened
    And all required fields are filled
    When i press Save Button
    Then window is closed
    And dogovor is saved and selected

  Scenario: check saved dogovor
    Given dogovor is created
    When i check active dogovor
    Then i see right data
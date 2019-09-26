Feature: Prod Check

  # 1 проверка глагны
  Scenario: main page check
    Given open main page
    Then check page title
    And title is not 500 or 400
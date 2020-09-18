#language:en

Feature: Validate Otus partnership functions

  Scenario Outline: I can sent partnership request
    Given I navigate to page 'https://otus.ru/'
    When I can navigate to partnership page
    When I can open modal partnership windows
    Then I can enter teachers '<fullName>' and '<email>' and '<phone>'
    Then I see success modal window
    Examples:
      | fullName            | email                    | phone |
      | Mr Cucumber Teacher | MrCucumberTester@mail.ru | +77777777777 |

  Scenario Outline: I can sent partnership request
    Given I navigate to page 'https://otus.ru/'
    When I can navigate to partnership page
    When I can open modal partnership windows
    Then I can enter teachers '<fullName>' and '<email>' and '<phone>'
    Then I cant see success modal window
    Examples:
      | fullName            | email                    | phone |
      | Mr123 | MrCucumberTester@mail.ru | +77777777777 |















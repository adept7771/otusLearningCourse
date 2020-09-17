#language:en

Feature: Validate Otus personal cabinet functions

  Scenario Outline: I can change my personal data in personal cabinet
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to about myself information
    Then I can change username to '<username>'
    Then I can change latinUserName to '<latinUserName>'
    Then I save new personal data
    Then I can navigate to about myself information
    Then I check personal data of '<username>' and '<latinUserName>' at personal data page
    Examples:
      | username            | latinUserName
      | userNameForCucumber | latinUserCucumberName

  
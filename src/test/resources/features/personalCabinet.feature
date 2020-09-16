#language:en

Feature: Validate Otus personal cabinet functions

  Scenario Outline: I can change my personal data in personal cabinet
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    Then I can change username to '<studentsAmount>'
    Then I can change latinuserName to '<studentsAmount>'
    Then I save new personal data
    Then I check personal data of '<studentsAmount>' and '<studentsAmount>' at personal data page
    Examples:
      | studentsAmount | groupsAmount | teachersAmount |
      | 150            | 100          | 50             |

  
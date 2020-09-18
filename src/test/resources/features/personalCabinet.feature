#language:en

Feature: Validate Otus personal cabinet functions

  Scenario Outline: I can change my username and latin username in personal cabinet
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to about myself information
    Then I can change username to '<username>'
    Then I can change latinUserName to '<latinUserName>'
    Then I save new personal data
    Then I can navigate to about myself information
    Then I check personal data of '<username>' and '<latinUserName>' at personal data page
    Examples:
      | username            | latinUserName         |
      | userNameForCucumber | latinUserCucumberName |

  Scenario Outline: I cant change my username and latin username with wrong data in personal cabinet
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to about myself information
    Then I can change username to '<username>'
    Then I can change latinUserName to '<latinUserName>'
    Then I save new personal data
    Then I see error message in personal data page field validation
    Examples:
      | username   | latinUserName  |
      | wr0ng1Name | wr0nGLatiNname |

  Scenario: Can't authorize with wrong credentials
    Given I navigate to page 'https://otus.ru/'
    When I cant login with incorrect login and password
    Then I see authorisation error

  Scenario: Does Anna already checked out my pageObject home task?
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to my courses
    Then I can choose java qa course
    Then I can choose second month lessons
    Then I check status of page object lesson

  Scenario: I can set avatar in profile and can check avatar is properly uploaded
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to about myself information
    Then I can change avatar to test avatar
    Then I save new personal data
    Then I can navigate to about myself information
    Then I download avatar from personal cabinet
    Then I compare current avatar with uploaded one include file difference

  Scenario: I can approve that course was payed
    Given I navigate to page 'https://otus.ru/'
    When I can login with correct login and password
    When I can navigate to about myself information
    Then I can navigate to payment page
    Then I check current course was already payed

















#language:en

Feature: Validate Otus statistic

  Scenario: I can create single user with valid data and get success response code
    Given I sent post user request with valid data to pet store
    Then Server response code is 200

  Scenario: I can't create single user with invalid data and get error response code
    Given I sent post user request with valid data to pet store with custom base api path 'https://petstore.swagger.io/wrong'
    Then Server response code is 404

  Scenario: I can get user with pre generated random user name
    Given I sent post user request with data and random name: 'firstName', 'lastName', 'email@mail.ru', 'pass', 'phone'
    When I get pre generated user
    Then Server response code is 200
    Then User with random name contains data in response: 'firstName', 'lastName', 'email@mail.ru', 'pass', 'phone'

  Scenario: I can't get user with non-existing user name
    When I get user by username: 'nonExistedUserForTests'
    Then Server response code is 404
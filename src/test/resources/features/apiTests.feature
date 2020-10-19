#language:en

Feature: Validate Otus statistic

  Scenario: I can create single user with valid data and get success response code
    Given I sent post user request with valid data to pet store
    Then Server response code is 200

  Scenario: I cant create single user with invalid data and get error response code
    Given I sent post user request with valid data to pet store with custom base api path 'test'
    Then Server response code is 200


#language:en

Feature: Validate Otus statistic

  Scenario Outline: As a user a can navigate to Development stream page
    Given I navigate to page 'https://otus.ru/'
    When I see statistics blocks
    Then I should values greater then '<studentsAmount>' studentStatistic '<groupsAmount>' groupStatistic '<teachersAmount>' teacherStatistic in statistic blocks
    Examples:
      | studentsAmount | groupsAmount | teachersAmount |
      | 150            | 100          | 50             |

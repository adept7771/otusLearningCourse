#language:en

Feature: Validate Otus statistic

  @smoke
  Scenario Outline: As a user i can check statistics block on main page
    Given I navigate to page 'https://otus.ru/'
    When I see statistics blocks
    Then I should values greater then '<studentsAmount>' studentStatistic '<groupsAmount>' groupStatistic '<teachersAmount>' teacherStatistic in statistic blocks
    Examples:
      | studentsAmount | groupsAmount | teachersAmount |
      | 150            | 100          | 50             |

  @smoke
  Scenario: Find best teacher at course of Java QA
    Given I navigate to page 'https://otus.ru/'
    When I navigate to qa java course
    Then i should see the best teacher at course


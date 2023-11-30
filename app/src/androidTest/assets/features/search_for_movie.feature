Feature: Can you search for movie?
  Scenario: Type in the search field
    Given I am on the Search page
    When I type in a movie
    Then I am shown a movie of that name


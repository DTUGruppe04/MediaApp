Feature: Can you search for movie?
  you need to be able to search for a movie

  Scenario: Type in the search field
    Given I am on the Search page
    When I type in a movie
    Then I am shown a movie of that name


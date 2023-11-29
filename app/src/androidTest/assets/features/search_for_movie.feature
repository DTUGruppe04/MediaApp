Feature: Can you search for movie?
  you need to be able to search for a movie

  Scenario: Type in the search field
    Given I am on the Search page
    When I enter "<string>" in the search field
    Then I am shown a movie of that name


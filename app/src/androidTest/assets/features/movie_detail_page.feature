Feature:I search for a movie, click on it and see the details

  Scenario: See movie details page
    Given I open the search page
    When I search for Oppenheimer
    When I click on the movie
    Then I see the movie details
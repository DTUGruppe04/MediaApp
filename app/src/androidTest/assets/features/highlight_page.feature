Feature: There shouldn't be an empty list on the top of the page

  Scenario: There shouldn't be an empty list on the top of the highlight page
    Given I am on the highlight page
    When I look at the top of the page
    Then I should not see an empty list
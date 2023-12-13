Feature: As a developer user, I can manage issues
  # login in as developer
  # three issues has been created
  # comment has been left by John (team leader)

  Scenario: Login in as a developer user and manage issues
    Given I login in as a developer user
    When I check all issues on me "Adam Smith"
    Then I verify the status code of checking being 200
    When I add a message "new message 12 12 " to an issue "PROJ-5"
    Then I verify the status code of adding message being 201
    When I edit a message "edit message" to an issue "PROJ-5"
    Then I verify the status code of editing message being 200

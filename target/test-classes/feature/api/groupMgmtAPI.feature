Feature: As an admin user, I can create groups and assign existing users to different groups
  #1.login in as an admin
  Scenario Outline: Login in as an admin user and create new groups
    Given I login in as an admin
    When I create new <groupName> group
    Then I verify the status code of creating group being 201

    Examples:
      | groupName    |
      | "Jira-group-1" |
      | "Jira-group-2" |
      | "Jira-group-3" |
      | "group-4" |

  Scenario Outline: Assign users to different groups
    When I assign user <userName> to group <group>
    Then I verify the status code of adding user to group being 201
    Examples:
      | userName     |  group |
      | Adam Smith   |Jira-group-1|
      | Diana West   |Jira-group-1|
      | Adam Smith   |group-4     |
      | Diana West   |group-4     |
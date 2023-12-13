Feature: As a team leader user, I can manage issues
  # login in as team leader
  # different epics created:  Frontend dev, Backend dev, UI/UX design, Testing
  # issue type id:   task: 10101 ;    epic: 10001   ;  bug:10102   story:10100
  # priority id: highest: 1   high:2    medium:3  low:4   lowest:5
  Scenario Outline: As a team leader user, I can create issues
    Given I login in as a team leader
    When I Create issues with <issueType> and <priority> and <summary> and <description>
    Then I verify the status code of creating issue 201
    When I link <link> the current issue <IssueKey> to a specific <EpicKey>
    Then I verify the status code of linking issue 201

    Examples:
    |issueType | priority | summary         | description      |link             |IssueKey | EpicKey    |
    |"Task"   |   "3"    | "task1 by John" | "task1 by John"  |"Epic-Issue Link" |"PROJ-5" | "PROJ-1"   |
    |"Story"   |   "2"    | "story1 by John"|"story1 by John"  |"Epic-Issue Link" |"PROJ-6" | "PROJ-2"   |
    |"Task"   |   "3"    | "task2 by John" | "task2 by John"  |"Epic-Issue Link" |"PROJ-7" | "PROJ-1"   |

  Scenario: as a team leader user, I can specify blocking link and assign issue
    When I create a "Blocks" link between issue "PROJ-5" and issue "PROJ-6"
    Then I verify the status code of creating a Blocks link being 201
    When I check the link between issue "PROJ-5" and issue "PROJ-6"
    Then I verify the status code of link between issues being 200

  Scenario Outline:
    When I assign issue <issueKey> to <user>
    Then I verify the status code of assigning being 204

    Examples:
      |issueKey |user       |
      |"PROJ-5"|"Adam Smith"|
      |"PROJ-6"|"Bob Dylan" |
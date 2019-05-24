Feature:
  Verify GET

  Scenario: Verify Get
 #   Given I perform GET "url"
    And I perform id "2180" and checking if first name "qwerty" exist

  Scenario: Verify Post
   # Given I perform Post "url"
    And I'm Posting: email , first_name , last_name ,gender
      | email      | zxc@sdd.com |
      | first_name | 12345       |
      | last_name  | 12345       |
      | gender     | male        |
    And Verify data
      | email      | zxc@sdd.com |
      | first_name | 12345       |
      | last_name  | 12345       |
      | gender     | male        |

  Scenario: Verify Delete
    And I perform id "1672" and checking if first name "qwerty" delete
  #And I perform id "2180" and checking if first name "qwerty" exist


  Scenario: Verify Put
    And I perform id "2051"
      | email      | qweee@sdd.com |
      | first_name | 123           |
      | last_name  | 123           |
      | gender     | male          |

  Scenario: Verify all data
    And I'm Posting: email , first_name , last_name ,gender
      | email      | old@email.com |
      | first_name | oldFName      |
      | last_name  | oldLName      |
      | gender     | male          |
    And Verify data
      | email      | old@email.com |
      | first_name | oldFName      |
      | last_name  | oldLName      |
      | gender     | male          |
    And Getting data
    And Changing data
      | email      | new@email.com |
      | first_name | newFName      |
      | last_name  | newLName      |
      | gender     | male          |
    And Getting new data
    And Delete all data
    Then Verify status
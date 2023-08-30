Feature: Validating place API

  @AddPlace,@Regression
  Scenario Outline: Verify if placed is added successfully using add place API.
    Given Add place payload with "<name>" "<language>" "<address>"
    When user calls "addPlaceAPI" API using http "post" method.
    Then API call got successfully with status code 200.
    And In the response body "status" is "OK".
    And verify the place id created maps to "<name>" using "getPlaceAPI".
    Examples:
      | name  | language | address  |
      | Farha | English  | Tarikere |
#      |Mayu  |Freanch   |Banglore   |

  @`DeletePlace🤣`, @Regression
  Scenario:
    Given Delete place payload
    When user calls "deletePlaceAPI" API using http "post" method.
    Then API call got successfully with status code 200.
    And In the response body "status" is "OK".

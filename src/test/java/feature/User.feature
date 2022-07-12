Feature: Validating Place API's

  Scenario Outline: Verify if place is being Succesfully added using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When user calls "AddPlaceAPI" with "post" hhtps resquest
    Then then API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify the added "place_id" have the same "<name>" with "GetPlaceAPI" for "get"



    Examples:-
      | name       | language | address             |
      | Delhui     | enlish   | streat 29, ramapark |
    #  | food centre| hindi    | patel nagar         |

Scenario: verify the delete place functionality is working
  Given Delete place payload
  When user calls "DeletePlaceAPI" with "post" hhtps resquest
  Then then API call got success with status code 200
  And "status" in response body is "OK"

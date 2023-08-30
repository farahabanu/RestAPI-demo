package stepdefinations;


import io.cucumber.java.Before;

import java.io.IOException;

public class Hooks {
    @Before("@DeletePlace")

    public void runBeforeScenario() throws IOException
//         Execute this code only when place id is null
//         write a code that will give you place id
    {
        if (StepDefinition.placeId == null) {
            StepDefinition sd = new StepDefinition();
            sd.add_place_payload("mayu", "urdu", "banglore");
            sd.callApiRequest("addPlaceAPI", "post");
            sd.verifyThePlaceIdCreatedMapsToUsing("mayu", "addPlaceAPI");
        }


    }
}

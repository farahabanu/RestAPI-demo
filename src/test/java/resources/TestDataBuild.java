package resources;

import PojoClasses.Location;
import PojoClasses.PojoCla;

import java.util.ArrayList;
import java.util.List;

public class TestDataBuild {
    public PojoCla AddPlacePayload(String name, String language, String address) {
        PojoCla p1 = new PojoCla();

        p1.setAccuracy(50);
        p1.setName(name);
        p1.setPhone_number("(+91) 983 893 3937");
        p1.setAddress(address);
        List<String> mylist = new ArrayList<String>();
        mylist.add("shop");
        mylist.add("shoe park");
        p1.setTypes(mylist);

        Location l1 = new Location();
        l1.setLat(-38.383494);
        l1.setLng(33.427362);
        p1.setLocation(l1);
        p1.setWebsite("http://google.com");
        p1.setLanguage(language);
        return p1;
    }

    public String deletePlacePayload(String placeId) {
        return "{\n    \"place_id\":\"" + placeId + "\"\n}";

    }
}

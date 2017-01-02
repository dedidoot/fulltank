package com.fulltank.model.pojo;

import java.util.List;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class PojoPlace {

    public Response response;

    public class Response {

        public List<Venues> venues;

          public  class Venues {

            public String name;
            public Contact contact;
            public Location location;

              class Contact {
                public String phone;
            }

              class Location {
                public String address;
                public String city;
                public double lat;
                public double lng;
            }

        }
    }

}

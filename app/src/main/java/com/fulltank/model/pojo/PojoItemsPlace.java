package com.fulltank.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class PojoItemsPlace {


    @SerializedName("venue")
    @Expose
    public Venue venue;

    public class Venue {

        @SerializedName("id")
        @Expose
        public String id;
        @SerializedName("name")
        @Expose
        public String name;
        @SerializedName("contact")
        @Expose
        public Contact contact;
        @SerializedName("location")
        @Expose
        public Location location;
        @SerializedName("categories")
        @Expose
        public List<Category> categories = null;
        @SerializedName("verified")
        @Expose
        public Boolean verified;
        @SerializedName("stats")
        @Expose
        public Stats stats;
        @SerializedName("rating")
        @Expose
        public Float rating;
        @SerializedName("ratingColor")
        @Expose
        public String ratingColor;
        @SerializedName("ratingSignals")
        @Expose
        public Integer ratingSignals;
        @SerializedName("venueRatingBlacklisted")
        @Expose
        public Boolean venueRatingBlacklisted;
        @SerializedName("beenHere")
        @Expose
        public BeenHere beenHere;
        @SerializedName("hours")
        @Expose
        public Hours hours;
        @SerializedName("photos")
        @Expose
        public Photos photos;
        @SerializedName("allowMenuUrlEdit")
        @Expose
        public Boolean allowMenuUrlEdit;
        @SerializedName("url")
        @Expose
        public String url;
        @SerializedName("price")
        @Expose
        public Price price;
        @SerializedName("venuePage")
        @Expose
        public VenuePage venuePage;
        @SerializedName("storeId")
        @Expose
        public String storeId;

        public class VenuePage {

            @SerializedName("id")
            @Expose
            public String id;

        }

        public class Price {

            @SerializedName("tier")
            @Expose
            public Integer tier;
            @SerializedName("message")
            @Expose
            public String message;
            @SerializedName("currency")
            @Expose
            public String currency;

        }


        public class Photos {

            @SerializedName("count")
            @Expose
            public Integer count;
            @SerializedName("groups")
            @Expose
            public List<Object> groups = null;

        }

        public class Hours {

            @SerializedName("status")
            @Expose
            public String status;
            @SerializedName("isOpen")
            @Expose
            public Boolean isOpen;
            @SerializedName("isLocalHoliday")
            @Expose
            public Boolean isLocalHoliday;

        }

        public class BeenHere {

            @SerializedName("count")
            @Expose
            public Integer count;
            @SerializedName("marked")
            @Expose
            public Boolean marked;
            @SerializedName("lastCheckinExpiredAt")
            @Expose
            public Integer lastCheckinExpiredAt;

        }

        public class Stats {

            @SerializedName("checkinsCount")
            @Expose
            public Integer checkinsCount;
            @SerializedName("usersCount")
            @Expose
            public Integer usersCount;
            @SerializedName("tipCount")
            @Expose
            public Integer tipCount;

        }

        public class Category {

            @SerializedName("id")
            @Expose
            public String id;
            @SerializedName("name")
            @Expose
            public String name;
            @SerializedName("pluralName")
            @Expose
            public String pluralName;
            @SerializedName("shortName")
            @Expose
            public String shortName;
            @SerializedName("icon")
            @Expose
            public Icon icon;
            @SerializedName("primary")
            @Expose
            public Boolean primary;

            public class Icon {

                @SerializedName("prefix")
                @Expose
                public String prefix;
                @SerializedName("suffix")
                @Expose
                public String suffix;

            }

        }

        public class Contact {

            @SerializedName("phone")
            @Expose
            public String phone;
            @SerializedName("formattedPhone")
            @Expose
            public String formattedPhone;
            @SerializedName("twitter")
            @Expose
            public String twitter;

        }

        public class Location {

            @SerializedName("address")
            @Expose
            public String address;
            @SerializedName("crossStreet")
            @Expose
            public String crossStreet;
            @SerializedName("lat")
            @Expose
            public Float lat;
            @SerializedName("lng")
            @Expose
            public Float lng;
            @SerializedName("distance")
            @Expose
            public Integer distance;
            @SerializedName("postalCode")
            @Expose
            public String postalCode;
            @SerializedName("cc")
            @Expose
            public String cc;
            @SerializedName("city")
            @Expose
            public String city;
            @SerializedName("state")
            @Expose
            public String state;
            @SerializedName("country")
            @Expose
            public String country;
            @SerializedName("formattedAddress")
            @Expose
            public List<String> formattedAddress = null;
            @SerializedName("labeledLatLngs")
            @Expose
            public List<LabeledLatLng> labeledLatLngs = null;

            public class LabeledLatLng {

                @SerializedName("label")
                @Expose
                public String label;
                @SerializedName("lat")
                @Expose
                public Float lat;
                @SerializedName("lng")
                @Expose
                public Float lng;

            }

        }

    }


}

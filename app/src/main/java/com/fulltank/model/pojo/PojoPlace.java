package com.fulltank.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class PojoPlace {

    @SerializedName("response")
    @Expose
    public Response response;

    public class Response {

        @SerializedName("groups")
        @Expose
        public List<Group_> groups = null;

        public class Group_ {

            @SerializedName("items")
            @Expose
            public List<PojoItemsPlace> items = null;

        }
    }

}

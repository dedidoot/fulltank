package com.fulltank.model.helper;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * Created by TEAM on 1/2/2017.
 * That's it
 */

public class Utils {

    public static RequestBody requestBody(String value) {
        return RequestBody.create(MediaType.parse("multipart/form-data"), value);
    }
}

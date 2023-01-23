package com.util;

import org.json.JSONObject;

public class TasksCacheEntry {

    JSONObject data;
    long timestamp;

    TasksCacheEntry(JSONObject data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }
}

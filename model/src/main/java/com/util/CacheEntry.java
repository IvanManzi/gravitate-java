package com.util;
import org.json.JSONObject;
public class CacheEntry {

    JSONObject data;
    long timestamp;

    CacheEntry(JSONObject data, long timestamp) {
        this.data = data;
        this.timestamp = timestamp;
    }
}

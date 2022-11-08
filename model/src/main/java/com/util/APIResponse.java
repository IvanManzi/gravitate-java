package com.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Map;

@SuperBuilder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse<T> {
    private final int statusCode;
    private final Map<String, List<String>> headers;
    private final Object data;

    /**
     * @param statusCode The status code of HTTP response
     * @param headers The headers of HTTP response
     */
    public APIResponse(int statusCode, Map<String, List<String>> headers) {
        this(statusCode, headers, null);
    }

    /**
     * @param statusCode The status code of HTTP response
     */
    public APIResponse(int statusCode) {
        this(statusCode, "Operation failure");
    }

    /**
     * @param statusCode The status code of HTTP response
     * @param data The object deserialized from response bod
     */
    public APIResponse(int statusCode, Object data) {
        this(statusCode, null, data);
    }

    /**
     * @param statusCode The status code of HTTP response
     * @param headers The headers of HTTP response
     * @param data The object deserialized from response bod
     */
    public APIResponse(int statusCode, Map<String, List<String>> headers, Object data) {
        this.statusCode = statusCode;
        this.headers = headers;
        this.data = data;
    }
}


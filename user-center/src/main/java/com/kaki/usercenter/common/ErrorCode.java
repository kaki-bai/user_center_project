package com.kaki.usercenter.common;

/**
 * error code
 *
 * @author kaki
 */
public enum ErrorCode {

    SUCCESS(0, "ok", ""),
    PARAMS_ERROR(40000, "Request parameter error", ""),
    NULL_ERROR(40001, "Request data is null", ""),
    NOT_LOGIN(40100, "Not logged in", ""),
    NO_AUTH(40101, "No permission", ""),
    SYSTEM_ERROR(50000, "Internal system error", "");

    private final int code;

    /**
     * error message
     */
    private final String message;

    /**
     * error description
     */
    private final String description;

    ErrorCode(int code, String message, String description) {
        this.code = code;
        this.message = message;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }
}

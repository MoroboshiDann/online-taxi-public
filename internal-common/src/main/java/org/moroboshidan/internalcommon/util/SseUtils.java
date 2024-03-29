package org.moroboshidan.internalcommon.util;

public class SseUtils {
    public static final String separator = "$";

    public static String generateSseKey(Long userId, String identity) {
        return userId + separator + identity;
    }
}

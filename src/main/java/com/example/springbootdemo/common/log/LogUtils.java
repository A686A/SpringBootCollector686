package com.example.springbootdemo.common.log;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogUtils {

    private static final Pattern ESCAPE_TARGET_PATTERN;

    static {
        ESCAPE_TARGET_PATTERN = Pattern.compile("[\r\n\t\\p{Cntrl}\"&'<>]");
    }

    public static String doEscape(String src) throws UnsupportedEncodingException {
        char[] charSrc = src.toCharArray();
        String result = "";
        for (char targeyChar : charSrc) {
            String targetString = String.valueOf(targeyChar);
            Matcher targetMatcher = ESCAPE_TARGET_PATTERN.matcher(targetString);
            if (targetMatcher.find()) {
                result += URLEncoder.encode(targetString, StandardCharsets.UTF_8.toString());
            } else {
                result += targetString;
            }
        }
        return result;
    }
}

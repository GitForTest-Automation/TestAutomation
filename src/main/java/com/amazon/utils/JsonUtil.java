package com.amazon.utils;

import com.amazon.enums.*;
import com.fasterxml.jackson.databind.*;

import java.io.*;
import java.util.*;

/**
 * The JsonUtil
 */
public class JsonUtil {

    /**
     * Gets the object.
     *
     * @param <T> the
     * @param jsonString the json string
     * @param className the class name
     * @return the object
     */
    public static <T> T getObject(String jsonString, Class<T> className) {

        final ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, className);
        } catch (NullPointerException ex) {

            LogUtils.log(jsonString == null ? "The File for the environment is not loaded" : "The required data " + jsonString
                    + " is not in property file", LogLevel.MEDIUM);
        } catch (IOException e) {
            LogUtils.log("Exception in JSON parsing. Cause: " + e, LogLevel.MEDIUM);
        }
        return null;
    }

    /**
     * Gets the list object.
     *
     * @param <T> the
     * @param jsonString the json string
     * @param className the class name
     * @return the list object
     */
    public static <T> List<T> getListObject(String jsonString, Class<T> className) {

        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, className));
        } catch (IOException e) {
            LogUtils.log("Exception in JSON parsing. Cause: " + e, LogLevel.MEDIUM);
        }
        return null;
    }

}

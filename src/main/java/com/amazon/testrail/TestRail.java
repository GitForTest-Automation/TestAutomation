package com.amazon.testrail;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static com.amazon.utils.PropertyUtils.*;

public class TestRail {

    public static String TEST_RUN_ID = "1";
    public static String TEST_RAIL_USERNAME = getTestRailUserName();
    public static String TEST_RAIL_PASSWORD = getTestRailPassword();
    public static String TEST_RAIL_ENGINE_URL = getTestRailUrl();
    public static int TEST_CASE_PASS_STATUS = 1;
    public static int TEST_CASE_FAIL_STATUS = 5;

    public static void addResultsForTestCase(String testCase, int status, String error) {

        String testRunId = TEST_RUN_ID;
        APIClient apiClient = new APIClient(TEST_RAIL_ENGINE_URL);
        apiClient.setUser(TEST_RAIL_USERNAME);
        apiClient.setPassword(TEST_RAIL_PASSWORD);

        Map<String, Object> data = new HashMap<String, Object>();
        data.put("status_id", status);
        data.put("comment", "" + error);

        try {
            apiClient.sendPost("add_result_for_case/" + testRunId + "/" + testCase, data);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (APIException e) {
            throw new RuntimeException(e);
        }

    }
}

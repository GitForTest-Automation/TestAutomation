package testListeners;

import com.amazon.PageObjectModel.ReusableMethods;
import com.amazon.enums.*;
import com.amazon.utils.*;
import org.joda.time.*;
import org.joda.time.format.*;
import org.testng.*;

import java.util.*;

public class Listeners extends ReusableMethods implements ITestListener, ISuiteListener {


    String logStart = "";

    public static final String LARGE_FORMAT = "EEEE, MMMM dd, yyyy HH:mm:ss aa";
    @Override
    public void onTestStart(ITestResult arg0) {
        LogUtils.setNoOfSteps(0);
        LogUtils.setNoOfSections(0);

        LogUtils.commonConsoleLog(new StringBuilder("").toString());
        LogUtils.commonConsoleLog(new StringBuilder("").toString());

        long time = Calendar.getInstance().getTimeInMillis();
        String startTime = formatToZone(time, DateTimeZone.forID("Asia/Kolkata"), LARGE_FORMAT);
        String content = "<p style=\"text-decoration:underline;font-weight:bold\">START TIME : " + startTime + "</p>";
        logStart = content;
        String methodName = arg0.getMethod().getMethodName();
        LogUtils.commonConsoleLog(new StringBuilder("Started Test " + methodName).toString());
        LogUtils.commonConsoleLog(new StringBuilder("START TIME : " + startTime).toString());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        LogUtils.log("<b style=\"color:green\">TEST PASSED.</b>", LogLevel.LOW);
    }

    @Override
    public void onTestFailure(ITestResult result) {

        String methodName = result.getMethod().getMethodName();
        LogUtils.log("<b style=\"color:red\">TEST FAILED.</b>", LogLevel.LOW);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        LogUtils.log("<font color=\"red\"> Test case Skipped in MAC.</font>", LogLevel.LOW);
        result.getThrowable().printStackTrace();
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
        System.out.println("onTestFailedWithTimeout");
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestResult context) {
        LogUtils.setNoOfSteps(0);
        LogUtils.setNoOfSections(0);

        LogUtils.commonConsoleLog(new StringBuilder("").toString());
        LogUtils.commonConsoleLog(new StringBuilder("").toString());

        long time = Calendar.getInstance().getTimeInMillis();
        String startTime = formatToZone(time, DateTimeZone.forID("Asia/Kolkata"), LARGE_FORMAT);
        String content = "<p style=\"text-decoration:underline;font-weight:bold\">START TIME : " + startTime + "</p>";
        logStart = content;
        String methodName = context.getMethod().getMethodName();
        LogUtils.commonConsoleLog(new StringBuilder("End Test " + methodName).toString());
        LogUtils.commonConsoleLog(new StringBuilder("END TIME : " + startTime).toString());
    }

    public static String formatToZone(Long milliseconds, DateTimeZone zone, String pattern) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern(pattern);
        fmt = fmt.withZone(zone);
        String strDate = fmt.print(new DateTime(milliseconds));
        return strDate;
    }
}

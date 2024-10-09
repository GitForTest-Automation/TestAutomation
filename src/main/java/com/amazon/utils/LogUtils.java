package com.amazon.utils;


import com.amazon.enums.*;
import org.apache.logging.log4j.LogManager;
import org.slf4j.*;
import org.testng.*;

import java.util.*;

public class LogUtils {

    private static ThreadLocal<Integer> noOfSections = new ThreadLocal<Integer>();
    private static final Logger LOGGER = LoggerFactory.getLogger(LogUtils.class);
    protected static ThreadLocal<Integer> noOfSteps = new ThreadLocal<Integer>();
    private static ThreadLocal<Boolean> stepsRunning = new ThreadLocal<Boolean>();

    public static Integer getNoOfSteps() {

        if (noOfSteps.get() == null) {
            noOfSteps.set(0);
        }
        return noOfSteps.get();
    }

    private static void printSteps(Steps steps, String log) {

        String space = StringUtil.addIndent(getNoOfSections() + 1);
        if (steps == Steps.START) {
            int currentStep = getNoOfSteps() + 1;
            stepsRunning.set(true);
            LOGGER.info(new StringBuilder(space).append("STEP  " + currentStep + " :" + log).toString());
            space = space.replaceAll(" ", "&nbsp;");
            Reporter.log(new StringBuilder(space).append(
                    "<font style=\"background-color:#ffff88\">STEP  " + currentStep + " :" + log + "</font>").toString());
            space = StringUtil.addIndent(getNoOfSections() + 2);
            space = space.replaceAll(" ", "&nbsp;");
            setNoOfSteps(getNoOfSteps() + 1);
        } else if (steps == Steps.END) {
            stepsRunning.set(false);
            LOGGER.info(new StringBuilder(space).append(log).toString());
            LOGGER.info("");
            space = space.replaceAll(" ", "&nbsp;");
            Reporter.log(new StringBuilder(space).append(log).toString());
            Reporter.log("");
        }
    }

    public static void log(String s, final LogLevel logLevel) {

        if (getLogLevel().getValue() >= logLevel.getValue()) {
            printSections(null, s);
            commonConsoleLog(s);
        }
    }

    public static void log(Steps steps, String s) {

        printSteps(steps, s);
    }

    private static void printSections(LogSection logSection, String log) {

        String space = StringUtil.addIndent(getNoOfSections());

        if (logSection == LogSection.START) {
            LOGGER.info(new StringBuilder(space).append(log).toString());
            LOGGER.info(new StringBuilder(space).append(StringUtil.addUnderline(log.length())).toString());
            space = space.replaceAll(" ", "&nbsp;");
            Reporter.log(new StringBuilder(space).append("<b>" + log + "</b>").toString(), false);
            Reporter.log(new StringBuilder(space).append(StringUtil.addUnderline(log.length())).toString(), false);
            setNoOfSections(getNoOfSections() + 1);
        } else if (logSection == LogSection.END) {
            setNoOfSections(getNoOfSections() - 1);
            space = StringUtil.addIndent(getNoOfSections());
            LOGGER.info(new StringBuilder(space).append(log).toString());
            LOGGER.info(new StringBuilder(space).append(StringUtil.addUnderline(log.length())).toString());
            space = space.replaceAll(" ", "&nbsp;");
            Reporter.log(new StringBuilder(space).append("<b>" + log + "</b>").toString(), false);
            Reporter.log(new StringBuilder(space).append(StringUtil.addUnderline(log.length())).toString(), false);
        }
    }

    public static void commonConsoleLog(String log) {

        if (stepsRunning == null || stepsRunning.get() == null) {
            stepsRunning.set(false);
        }
        String space = StringUtil.addIndent(stepsRunning.get() ? getNoOfSections() + 2 : getNoOfSections());
        LOGGER.info(new StringBuilder(space).append(StringUtil.changeHtmlToPlain(log)).toString());
        space = space.replaceAll(" ", "&nbsp;");
        Reporter.log(new StringBuilder(space).append(log).toString(), false);
    }

    public static LogLevel getLogLevel() {

        final String logLevel = System.getProperty("logLevel");
        return logLevel == null ? LogLevel.HIGH : LogLevel.valueOf(logLevel.toUpperCase(Locale.getDefault()));
    }

    public static int getNoOfSections() {

        if (noOfSections.get() == null) {
            noOfSections.set(new Integer(0));
        }
        return noOfSections.get();
    }

    public static void setNoOfSections(int sections) {

        noOfSections.set(sections);
    }

    public static void setNoOfSteps(Integer steps) {

        noOfSteps.set(steps);
    }

}

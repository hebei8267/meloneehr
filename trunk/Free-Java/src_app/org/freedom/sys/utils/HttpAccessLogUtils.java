/*
 * Copyright 2009 by hebei, All rights reserved.
 */
package org.freedom.sys.utils;

import org.apache.log4j.Logger;

/**
 * @author 何贝
 * @since JDK1.5
 */
public class HttpAccessLogUtils {
    private static final Logger LOGGER = Logger.getLogger(HttpAccessLogUtils.class.getName());

    private HttpAccessLogUtils() {
        System.out.println(HttpAccessLogUtils.class.getName());
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    public static void debug(Object message) {
        getLogger().debug(message);
    }

    public static void debug(Object message1, Object message2) {
        getLogger().debug(message1 + "\n" + message2);
    }

    public static void debug(Object message1, Object message2, Object message3) {
        getLogger().debug(message1 + "\n" + message2 + "\n" + message3);
    }

    public static void error(Object message) {
        getLogger().error(message);
    }

    public static void fatal(Object message) {
        getLogger().fatal(message);
    }

    public static void info(Object message) {
        getLogger().info(message);
    }

    public static void debug(Object message, Throwable t) {
        getLogger().debug(message, t);
    }

    public static void error(Object message, Throwable t) {
        getLogger().error(message, t);
    }

    public static void fatal(Object message, Throwable t) {
        getLogger().fatal(message, t);
    }

    public static void info(Object message, Throwable t) {
        getLogger().info(message, t);
    }
}

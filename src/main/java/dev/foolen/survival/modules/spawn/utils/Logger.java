package dev.foolen.survival.modules.spawn.utils;

public class Logger {
    private static final String PREFIX = "[Survival]";

    public static void info(String message) {
        System.out.println(PREFIX + "[Info] " + message);
    }

    public static void warning(String message) {
        System.out.println(PREFIX + "[Warning] " + message);
    }

    public static void error(String message) {
        System.out.println(PREFIX + "[Error] " + message);
    }
}

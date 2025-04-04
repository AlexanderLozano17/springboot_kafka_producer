package com.demo.utils;

/**
 * La clase LogHelper es una utilidad diseñada para generar mensajes de log
 * estructurados de manera uniforme en toda la aplicación.
 */
public final class LogHelper {

    private LogHelper() {}

    public static String start(Class<?> clazz, String method) {
        return String.format("→ START | %s::%s()", clazz.getSimpleName(), method);
    }

    public static String end(Class<?> clazz, String method) {
        return String.format("← END | %s::%s()", clazz.getSimpleName(), method);
    }

    public static String success(Class<?> clazz, String method, String detail) {
        return String.format("✓ SUCCESS | %s::%s() - %s", clazz.getSimpleName(), method, detail);
    }

    public static String warn(Class<?> clazz, String method, String detail) {
        return String.format("⚠ WARNING | %s::%s() - %s", clazz.getSimpleName(), method, detail);
    }

    public static String error(Class<?> clazz, String method, String detail) {
        return String.format("✖ ERROR | %s::%s() - %s", clazz.getSimpleName(), method, detail);
    }
}

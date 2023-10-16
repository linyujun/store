package com.kinzhan.dev.platform.config.plugins;

/**
 * 用于忽略自动关联
 * try {
 *     IgnoreRelationHelper.init();
 * }finally {
 *     IgnoreRelationHelper.release();
 * }
 */
public class IgnoreRelationHelper {

    private static final ThreadLocal<Boolean> ignoreHolder =
            new ThreadLocal<>();

    public static void init() {
        ignoreHolder.set(true);
    }

    public static void release() {
        ignoreHolder.remove();
    }

    public static Boolean get() {
        return ignoreHolder.get();
    }
}

package com.didi.soda.andy.tools;

import android.annotation.SuppressLint;
import android.util.Log;

/**
 * 日志控制
 */
@SuppressLint("Log")
public final class LogUtils {

    public static boolean S_OPEN_LOG = true;
    static volatile long sCurrentForSpendTimeLog = System.currentTimeMillis();
    static volatile int sSpendTimeIndex = 0;
    private static final String TAG = "_AndyTest_";

    private LogUtils() {
    }

    public static void d(String logKey, CharSequence msg) {
        d(logKey, msg, 2);
    }

    public static void d(String logKey, final CharSequence msg, final Object... args) {
        d(logKey, msg.toString() + args, 2);
    }

    /**
     * @param stackIndex 1:当前位置，2：上级栈位置，0：logcat 的位置（没有意义）
     */
    public static void d(String logKey, CharSequence msg, int stackIndex) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
            String log = build(msg, ste);
//            Log.d(logKey, log);
            Log.d(TAG, "[" + logKey + "]" + log);
        }
    }

    public static void d(CharSequence msg) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            String log = build(msg, ste);
            Log.d(TAG, log);

        }
    }

    public static void i(String logKey, CharSequence msg, int stackIndex) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
            String log = build(msg, ste);
            Log.i(TAG, "[" + logKey + "]" + log);
//            Log.i(logKey, log);

        }
    }

    public static void i(String logKey, CharSequence msg) {
        i(logKey, msg, 2);
    }

    public static void v(String logKey, CharSequence msg, int stackIndex) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
            String log = build(msg, ste);
            Log.v(TAG, "[" + logKey + "]" + log);
        }
    }

    public static void v(String logKey, CharSequence msg) {
        v(logKey, msg, 2);
    }

    public static void w(String logKey, CharSequence msg) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            String log = build(logKey, msg, ste);
            Log.w(TAG, "[" + logKey + "]" + log);
        }
    }

    /**
     * 打印error级别的log
     *
     * @param tag tag标签
     */
    public static void e(String tag, Throwable tr) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            String log = build(tag, "", ste, tr);
            Log.e(tag, log, tr);
//            Log.e(tag, "[" + Thread.currentThread().getId() + "]" + tr.getMessage(), tr);
        }
    }

    public static void e(String logKey, CharSequence msg) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            String log = build(logKey, msg, ste);
            Log.e(logKey, log);

        }
    }

    public static void e(String logKey, CharSequence msg, Throwable e) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[1];
            String log = build(logKey, msg, ste);
            Log.e(TAG, log, e);
        }
    }

    /**
     * 打印调用栈信息
     *
     * @param tag tag标签
     * @param str 内容
     */
    public static void t(String tag, CharSequence str) {
        if (S_OPEN_LOG) {
            LogUtils.i(tag, "DebugInfo: " + str, 2);
            Throwable e = new Throwable(tag);
            e.printStackTrace();
        }
    }

    public static void timeSinceLast() {
        LogUtils.d(TAG, "⚠️" + (sSpendTimeIndex++) + "spend:" + (System.currentTimeMillis() - sCurrentForSpendTimeLog) + " ", 2);
        sCurrentForSpendTimeLog = System.currentTimeMillis();
    }


    public static String getLineText(String msg, int stackIndex) {
        if (S_OPEN_LOG) {
            StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
            String log = build(msg, ste);
            return log;
        } else {
            return msg;
        }
    }

    public static String getLineText(String msg) {
        if (S_OPEN_LOG) {
            int stackIndex = 1;
            StackTraceElement ste = new Throwable().getStackTrace()[stackIndex];
            String log = build(msg, ste);
            return log;
        } else {
            return msg;
        }
    }

    /**
     * 制作打log位置的文件名与文件行号详细信息
     */
    private static String build(CharSequence log, StackTraceElement ste) {
        StringBuilder buf = new StringBuilder();

        buf.append("[").append(Thread.currentThread().getId()).append("]");

        if (ste.isNativeMethod()) {
            buf.append("(Native Method)");
        } else {
            CharSequence fileName = ste.getFileName();

            if (fileName == null) {
                buf.append("(Unknown Source)");
            } else {
                int lineNum = ste.getLineNumber();
                buf.append('(');
                buf.append(fileName);
                if (lineNum >= 0) {
                    buf.append(':');
                    buf.append(lineNum);
                }
                buf.append("):");
            }
        }
        buf.append(log);
        return buf.toString();
    }

    private static String build(String logKey, CharSequence msg, StackTraceElement ste) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(logKey).append("]").append(build(msg, ste));
        return sb.toString();
    }

    private static String build(String logKey, CharSequence msg, StackTraceElement ste, Throwable e) {
        StringBuilder sb = new StringBuilder();
        sb.append("[").append(logKey).append("]").append(ste.toString()).append(":").append(msg).append("\r\n").append("e:").append(e.getMessage());
        return sb.toString();
    }
}

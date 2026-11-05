package com.github.syren_dev_tech.scylla.mobs.client;

public final class CustomNbtTintUtil {

    private CustomNbtTintUtil() {
    }

    public static int tintFromRange(int value, int minValue, int maxValue, int minColor, int maxColor) {
        float t = normalize(value, minValue, maxValue);
        return lerpRgba(minColor, maxColor, t);
    }

    public static int tintFromBoolean(boolean value, int falseColor, int trueColor) {
        return value ? trueColor : falseColor;
    }

    public static float normalize(int value, int minValue, int maxValue) {
        if (maxValue <= minValue) {
            return 1.0f;
        }

        float normalized = (value - minValue) / (float) (maxValue - minValue);
        if (normalized < 0.0f) {
            return 0.0f;
        }
        if (normalized > 1.0f) {
            return 1.0f;
        }

        return normalized;
    }

    public static int lerpRgba(int startColor, int endColor, float t) {
        float clamped = clamp01(t);

        int startRed = (startColor >> 24) & 0xFF;
        int startGreen = (startColor >> 16) & 0xFF;
        int startBlue = (startColor >> 8) & 0xFF;
        int startAlpha = startColor & 0xFF;

        int endRed = (endColor >> 24) & 0xFF;
        int endGreen = (endColor >> 16) & 0xFF;
        int endBlue = (endColor >> 8) & 0xFF;
        int endAlpha = endColor & 0xFF;

        int outRed = lerpChannel(startRed, endRed, clamped);
        int outGreen = lerpChannel(startGreen, endGreen, clamped);
        int outBlue = lerpChannel(startBlue, endBlue, clamped);
        int outAlpha = lerpChannel(startAlpha, endAlpha, clamped);

        return (outRed << 24) | (outGreen << 16) | (outBlue << 8) | outAlpha;
    }

    private static int lerpChannel(int start, int end, float t) {
        return Math.round(start + ((end - start) * t));
    }

    private static float clamp01(float value) {
        if (value < 0.0f) {
            return 0.0f;
        }
        if (value > 1.0f) {
            return 1.0f;
        }
        return value;
    }
}

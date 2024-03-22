package org.moroboshidan.internalcommon.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalUtils {
    public static double add(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.add(b2).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double divide(int v1, int v2) throws IllegalAccessException {
        if (v2 <= 0) {
            throw new IllegalAccessException("除数非法");
        }
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.divide(b2, RoundingMode.HALF_UP).doubleValue();
    }

    public static double subtract(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.subtract(b2).doubleValue();
    }

    public static double multiply(double v1, double v2) {
        BigDecimal b1 = BigDecimal.valueOf(v1);
        BigDecimal b2 = BigDecimal.valueOf(v2);
        return b1.multiply(b2).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}

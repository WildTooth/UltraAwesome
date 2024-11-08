package com.github.wildtooth.awesome.core.util;

import java.text.NumberFormat;
import java.util.Locale;

public class StringUtil {
  private final static NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);

  private StringUtil() {
    throw new IllegalStateException("Utility class");
  }

  static {
    numberFormat.setMaximumFractionDigits(5);
  }

  public static String formatNum(double input) {
    return numberFormat.format(input);
  }
}

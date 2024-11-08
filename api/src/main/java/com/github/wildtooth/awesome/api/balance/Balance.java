package com.github.wildtooth.awesome.api.balance;

public interface Balance {
  double getNumericValue();

  String getFormattedValue();

  void refresh();
}

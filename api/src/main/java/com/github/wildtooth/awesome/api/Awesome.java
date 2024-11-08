package com.github.wildtooth.awesome.api;

import com.github.wildtooth.awesome.api.generated.ReferenceStorage;

public class Awesome {
  private static ReferenceStorage references;

  private Awesome() {
    throw new IllegalStateException("Awesome should not be instantiated");
  }

  public static ReferenceStorage getReferences() {
    return references;
  }

  public static void init(ReferenceStorage references) {
    if (Awesome.references != null) {
      throw new IllegalStateException("Awesome is already initialized");
    } else {
      Awesome.references = references;
    }
  }

  public static void refresh() {
    Awesome.references.balanceService().refresh();
  }

}

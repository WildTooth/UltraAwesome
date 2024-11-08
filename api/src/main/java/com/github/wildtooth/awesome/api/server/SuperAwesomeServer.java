package com.github.wildtooth.awesome.api.server;

/**
 * Represents official SuperAwesome servers.
 */
public enum SuperAwesomeServer {
  LIMBO("Limbo"),
  LOUD_LOBBY("Larmelobby"),
  SHOPPING_LOBBY("Shoppylobby"),
  MACHINE_ROOM("Maskinrummet"),
  CREEPY_LOBBY("Creepylobby"),

  NONE,

  /**
   * Represents an "unrecognized" server, that is, on superawesome but not officially recognized.
   */
  UNRECOGNIZED
  ;

  private final String displayName;

  SuperAwesomeServer() {
    this.displayName = "";
  }

  SuperAwesomeServer(String displayName) {
    this.displayName = displayName;
  }

  public String getDisplayName() {
    return this.displayName;
  }

  public static SuperAwesomeServer getFromDisplayName(String displayName) {
    for (SuperAwesomeServer server : values()) {
      if (server.getDisplayName().equalsIgnoreCase(displayName)) {
        return server;
      }
    }
    return null;
  }
}

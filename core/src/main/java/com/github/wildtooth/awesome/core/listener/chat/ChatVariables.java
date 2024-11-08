package com.github.wildtooth.awesome.core.listener.chat;

import java.util.HashMap;
import java.util.Map;

/**
 * A class to store variables from chat messages.
 *
 * <p>Variables are stored in a map with a key and a value. The key is an enum that represents the
 * variable type, and the value is the variable itself.
 *
 * <p>Variables are extracted from chat messages by checking if the message starts with the prefix
 * of the variable and ends with the suffix of the variable. If the message does not contain a
 * variable, the message is ignored.
 */
public class ChatVariables {

  private static final Map<Variable, String> variables = new HashMap<>();

  ChatVariables() {

  }

  void setVariable(Variable key, String value) {
    ChatVariables.variables.put(key, value);
  }

  public static String getVariable(Variable key) {
    return ChatVariables.variables.get(key);
  }

  public enum Variable {
    BALANCE("", ""),
    CURRENT_SERVER("", ""),
    ;

    /**
     * The beginning part of the message, before the variable.
     */
    private final String prefix;

    /**
     * The ending part of the message, after the variable.
     */
    private final String suffix;

    Variable(String prefix, String suffix) {
      this.prefix = prefix;
      this.suffix = suffix;
    }

    public String getPrefix() {
      return this.prefix;
    }

    public String getSuffix() {
      return this.suffix;
    }

    public static ChatVariables.Variable getFromChatMessage(String message) {
      for (ChatVariables.Variable variable : ChatVariables.Variable.values()) {
        if (message.startsWith(variable.getPrefix()) && message.endsWith(variable.getSuffix())) {
          return variable;
        }
        if (variable.getSuffix().isEmpty()) {
          if (message.startsWith(variable.getPrefix())) {
            return variable;
          }
        }
      }
      return null;
    }
  }
}

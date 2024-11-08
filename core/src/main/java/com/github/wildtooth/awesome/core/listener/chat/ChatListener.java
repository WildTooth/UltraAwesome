package com.github.wildtooth.awesome.core.listener.chat;

import com.github.wildtooth.awesome.core.listener.Listener;
import net.labymod.api.event.EventBus;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.chat.ChatReceiveEvent;
import net.labymod.api.util.logging.Logging;

public class ChatListener implements Listener {

  private final Logging logger = Logging.create(ChatListener.class);

  private final EventBus eventBus;
  private final ChatVariables chatVariables;

  public ChatListener(EventBus eventBus) {
    this.eventBus = eventBus;
    this.chatVariables = new ChatVariables();
  }

  @Subscribe
  public void onChatMessageReceived(ChatReceiveEvent event) {
    String message = event.chatMessage().getPlainText().trim();
    ChatVariables.Variable variable = ChatVariables.Variable.getFromChatMessage(message);
    if (variable == null) {
      return;
    }
    String variableValue = getVariableFromString(variable, message);
    if (variableValue.isEmpty()) {
      return;
    }
    this.logger.debug("Setting variable {} to value {}", variable, variableValue);
    this.chatVariables.setVariable(variable, variableValue);
  }

  private String getVariableFromString(ChatVariables.Variable varType, String message) {
    String variable = "";
    if (message.startsWith(varType.getPrefix()) && message.endsWith(varType.getSuffix())) {
      variable = message.substring(varType.getPrefix().length() + 1, message.length() - varType.getSuffix().length());
    }
    return variable;
  }

  @Override
  public void enable() {
    this.logger.info("Enabling ChatListener");
    this.eventBus.registerListener(this);
  }

  @Override
  public void disable() {
    this.logger.info("Disabling ChatListener");
    this.eventBus.unregisterListener(this);
  }
}

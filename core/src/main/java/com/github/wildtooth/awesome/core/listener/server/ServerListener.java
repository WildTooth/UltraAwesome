package com.github.wildtooth.awesome.core.listener.server;

import com.github.wildtooth.awesome.api.Awesome;
import com.github.wildtooth.awesome.api.client.ClientService;
import com.github.wildtooth.awesome.api.server.SuperAwesomeServer;
import com.github.wildtooth.awesome.core.client.DefaultClientService;
import com.github.wildtooth.awesome.core.listener.Listener;
import net.labymod.api.event.EventBus;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.network.server.ServerDisconnectEvent;
import net.labymod.api.event.client.network.server.ServerJoinEvent;
import net.labymod.api.event.client.network.server.ServerSwitchEvent;
import net.labymod.api.event.client.network.server.SubServerSwitchEvent;
import net.labymod.api.util.logging.Logging;

public class ServerListener implements Listener {

  private final Logging logger = Logging.create(ServerListener.class);

  private final EventBus eventBus;
  private final ClientService clientService;

  public ServerListener(EventBus eventBus) {
    this.eventBus = eventBus;
    this.clientService = Awesome.getReferences().clientService();
  }

  @Subscribe
  public void onServerChange(ServerJoinEvent event) {
    handle(true);
  }

  @Subscribe
  public void onServerChange(ServerDisconnectEvent event) {
    handle();
  }

  @Subscribe
  public void onServerChange(SubServerSwitchEvent event) {
    handle();
  }

  @Subscribe
  public void onServerChange(ServerSwitchEvent event) {
    handle();
  }

  private boolean isRelevantServer(SuperAwesomeServer server) {
    return server != SuperAwesomeServer.NONE && server != SuperAwesomeServer.UNRECOGNIZED;
  }

  private void handle() {
    handle(false);
  }

  private void handle(boolean isJoin) {
    if (isJoin) {
      DefaultClientService clientService = (DefaultClientService) this.clientService;
      clientService.setServer(SuperAwesomeServer.LIMBO);
      this.logger.debug("Setting server to Limbo");
      return;
    }
    this.logger.debug("Refreshing client service");
    this.clientService.refresh();
    if (isRelevantServer(this.clientService.getServer())) {
      this.logger.debug("Refreshing Awesome");
      Awesome.refresh();
    }
  }

  @Override
  public void enable() {
    this.logger.info("Enabling ServerListener");
    this.eventBus.registerListener(this);
  }

  @Override
  public void disable() {
    this.logger.info("Disabling ServerListener");
    this.eventBus.unregisterListener(this);
  }
}

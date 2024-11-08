package com.github.wildtooth.awesome.core.client;

import com.github.wildtooth.awesome.api.client.ClientService;
import com.github.wildtooth.awesome.api.server.SuperAwesomeServer;
import com.github.wildtooth.awesome.core.listener.chat.ChatVariables;
import com.github.wildtooth.awesome.core.listener.chat.ChatVariables.Variable;
import net.labymod.api.Laby;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.client.network.server.ServerController;
import net.labymod.api.models.Implements;
import net.labymod.api.util.logging.Logging;
import javax.inject.Singleton;
import java.util.Objects;
import java.util.Optional;

@Singleton
@Implements(ClientService.class)
public class DefaultClientService implements ClientService {
  private final Logging logger = Logging.create(ClientService.class);

  private ClientPlayer clientPlayer;
  private SuperAwesomeServer server;

  @Override
  public Optional<ClientPlayer> getClientPlayer() {
    return Optional.ofNullable(this.clientPlayer);
  }

  @Override
  public SuperAwesomeServer getServer() {
    return this.server;
  }

  @Override
  public void refresh() {
    this.clientPlayer = Laby.labyAPI().minecraft().getClientPlayer();
    this.server = SuperAwesomeServer.getFromDisplayName(ChatVariables.getVariable(Variable.CURRENT_SERVER));
    if (this.server == null && isConnected()) {
      this.server = SuperAwesomeServer.UNRECOGNIZED;
    } else if (this.server == null) {
      this.server = SuperAwesomeServer.NONE;
    }
    this.logger.debug("Refreshed client service, found server {}", this.server);
  }

  public void setServer(SuperAwesomeServer server) {
    this.server = server;
  }

  private boolean isConnected() {
    ServerController serverController = Laby.labyAPI().serverController();
    if (!serverController.isConnected()) return false;
    return isValidServerAddress(
        Objects.requireNonNull(serverController.getCurrentServerData()).address().getHost());
  }

  private boolean isValidServerAddress(String address) {
    address = address.toLowerCase();
    return address.equals("superawesome.dk") || address.endsWith(".superawesome.dk");
  }
}

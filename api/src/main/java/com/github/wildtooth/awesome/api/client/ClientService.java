package com.github.wildtooth.awesome.api.client;

import com.github.wildtooth.awesome.api.server.SuperAwesomeServer;
import net.labymod.api.client.entity.player.ClientPlayer;
import net.labymod.api.reference.annotation.Referenceable;
import java.util.Optional;

@Referenceable
public interface ClientService {
  Optional<ClientPlayer> getClientPlayer();

  SuperAwesomeServer getServer();

  void refresh();
}

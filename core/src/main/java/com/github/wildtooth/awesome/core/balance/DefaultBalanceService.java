package com.github.wildtooth.awesome.core.balance;

import com.github.wildtooth.awesome.api.balance.Balance;
import com.github.wildtooth.awesome.api.balance.BalanceService;
import net.labymod.api.Laby;
import net.labymod.api.models.Implements;
import net.labymod.api.util.logging.Logging;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
@Implements(BalanceService.class)
public class DefaultBalanceService implements BalanceService {
  private final Logging logger = Logging.create(BalanceService.class);

  private Balance balance;

  private UUID currentUUID;

  @Override
  public Optional<Balance> getBalance() {
    return Optional.ofNullable(this.balance);
  }

  @Override
  public void refresh() {
    if (this.currentUUID == null || !this.currentUUID.equals(Laby.labyAPI().getUniqueId())) {
      this.logger.debug("Switching to overseeing the balance of (UUID) {}", Laby.labyAPI().getUniqueId());
      this.currentUUID = Laby.labyAPI().getUniqueId();
      this.balance = new DefaultBalance(0.0);
    }
    this.logger.debug("Refreshing balance");
    this.balance.refresh();
  }
}

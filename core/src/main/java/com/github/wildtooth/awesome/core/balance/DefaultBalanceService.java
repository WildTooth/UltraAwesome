package com.github.wildtooth.awesome.core.balance;

import com.github.wildtooth.awesome.api.balance.Balance;
import com.github.wildtooth.awesome.api.balance.BalanceService;
import net.labymod.api.Laby;
import net.labymod.api.models.Implements;
import javax.inject.Singleton;
import java.util.Optional;
import java.util.UUID;

@Singleton
@Implements(BalanceService.class)
public class DefaultBalanceService implements BalanceService {

  private Balance balance;

  private UUID currentUUID;

  @Override
  public Optional<Balance> getBalance() {
    return Optional.ofNullable(this.balance);
  }

  @Override
  public void refresh() {
    if (this.currentUUID == null || !this.currentUUID.equals(Laby.labyAPI().getUniqueId())) {
      this.currentUUID = Laby.labyAPI().getUniqueId();
      this.balance = new DefaultBalance(0.0);
    }
    this.balance.refresh();
  }
}

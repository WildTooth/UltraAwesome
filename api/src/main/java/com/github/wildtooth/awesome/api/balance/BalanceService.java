package com.github.wildtooth.awesome.api.balance;

import net.labymod.api.reference.annotation.Referenceable;
import java.util.Optional;

@Referenceable
public interface BalanceService {
  Optional<Balance> getBalance();

  void refresh();
}

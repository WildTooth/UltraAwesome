package com.github.wildtooth.awesome.core.widget;

import com.github.wildtooth.awesome.api.Awesome;
import com.github.wildtooth.awesome.api.balance.BalanceService;
import net.labymod.api.Textures.SpriteCommon;
import net.labymod.api.client.gui.icon.Icon;
import java.util.concurrent.atomic.AtomicReference;

public class BalanceWidget extends AwesomeWidget {

  private final BalanceService balanceService;

  public BalanceWidget() {
    super("balance", "Balance", Icon.texture(
        SpriteCommon.LOOTBOX.getResourceLocation()
    ));
    this.balanceService = Awesome.getReferences().balanceService();
  }

  @Override
  protected String getDisplayText() {
    AtomicReference<String> formattedBalance = new AtomicReference<>(" ");
    this.balanceService.getBalance().ifPresent(balance -> {
      formattedBalance.set(balance.getFormattedValue());
    });
    return formattedBalance.get();
  }
}

package com.github.wildtooth.awesome.core;

import com.github.wildtooth.awesome.api.Awesome;
import com.github.wildtooth.awesome.core.util.Listeners;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import javax.inject.Singleton;

@Singleton
@AddonMain
public class AwesomeAddon extends LabyAddon<AwesomeConfiguration> {

  private Listeners listeners;

  @Override
  protected void enable() {
    this.registerSettingCategory();

    Awesome.init(this.referenceStorageAccessor());

    this.listeners = new Listeners(labyAPI().eventBus());

    this.listeners.enableAll();

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<AwesomeConfiguration> configurationClass() {
    return AwesomeConfiguration.class;
  }

  public Listeners getListeners() {
    return this.listeners;
  }
}

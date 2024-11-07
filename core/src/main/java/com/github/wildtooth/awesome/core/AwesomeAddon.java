package com.github.wildtooth.awesome.core;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AwesomeAddon extends LabyAddon<AwesomeConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<AwesomeConfiguration> configurationClass() {
    return AwesomeConfiguration.class;
  }
}

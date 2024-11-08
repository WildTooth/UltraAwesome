package com.github.wildtooth.awesome.core;

import com.github.wildtooth.awesome.api.Awesome;
import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;

@AddonMain
public class AwesomeAddon extends LabyAddon<AwesomeConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    Awesome.init(this.referenceStorageAccessor());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<AwesomeConfiguration> configurationClass() {
    return AwesomeConfiguration.class;
  }
}

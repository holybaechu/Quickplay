package xyz.holyb.quickplay;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import xyz.holyb.quickplay.listener.HotkeyListener;

@AddonMain
public class QuickplayAddon extends LabyAddon<QuickplayConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerListener(new HotkeyListener(this));

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<QuickplayConfiguration> configurationClass() {
    return QuickplayConfiguration.class;
  }
}

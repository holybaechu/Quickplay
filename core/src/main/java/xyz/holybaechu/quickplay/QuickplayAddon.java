package xyz.holybaechu.quickplay;

import net.labymod.api.addon.LabyAddon;
import net.labymod.api.models.addon.annotation.AddonMain;
import xyz.holybaechu.quickplay.commands.QuickplayCommand;

@AddonMain
public class QuickplayAddon extends LabyAddon<QuickplayConfiguration> {

  @Override
  protected void enable() {
    this.registerSettingCategory();

    this.registerCommand(new QuickplayCommand());

    this.logger().info("Enabled the Addon");
  }

  @Override
  protected Class<QuickplayConfiguration> configurationClass() {
    return QuickplayConfiguration.class;
  }
}

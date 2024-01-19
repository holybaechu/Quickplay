package xyz.holybaechu.quickplay.commands;

import net.labymod.api.client.chat.command.Command;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.TextComponent;
import net.labymod.api.client.component.format.NamedTextColor;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.util.Color;
import org.w3c.dom.Text;
import xyz.holybaechu.quickplay.activity.QuickplayActivity;
import xyz.holybaechu.quickplay.servers.Hypixel;

import java.util.Objects;

public class QuickplayCommand extends Command {
  Hypixel hypixel = new Hypixel();

  public QuickplayCommand() {
    super("quickplay");
  }

  @Override
  public boolean execute(String prefix, String[] arguments) {
    // Check if connected to hypixel.net
//    if(
//        !labyAPI.serverController().isConnected() &&
//        !Objects.equals(Objects.requireNonNull(labyAPI.serverController().getCurrentServerData()).address().getHost(), "hypixel.net")
//    ) {
//      this.displayMessage(Component.text("You are not connected to hypixel.net!", TextColor.color(255, 0 ,0)));
//
//      return false;
//    }

    labyAPI.minecraft().executeNextTick(() -> labyAPI.minecraft().minecraftWindow().displayScreen(new QuickplayActivity(hypixel)));

    return true;
  }
}

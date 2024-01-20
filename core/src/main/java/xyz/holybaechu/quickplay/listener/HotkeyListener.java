package xyz.holybaechu.quickplay.listener;

import net.labymod.api.Laby;
import net.labymod.api.client.component.Component;
import net.labymod.api.client.component.format.TextColor;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import xyz.holybaechu.quickplay.QuickplayAddon;
import xyz.holybaechu.quickplay.activity.QuickplayActivity;
import xyz.holybaechu.quickplay.servers.Hypixel;
import java.util.Objects;

public class HotkeyListener {
  private final QuickplayAddon addon;
  private final Hypixel hypixel = new Hypixel();

  public HotkeyListener(QuickplayAddon addon){
    this.addon = addon;
  }

  @Subscribe
  public void onKeyPress(KeyEvent event){
    if (event.key() != addon.configuration().hotKey().get()) return;

    if (!addon.configuration().enabled().get()) return;
    if (event.state() != State.PRESS) return;

    if (Laby.labyAPI().minecraft().minecraftWindow().isScreenOpened()) return;

    // Check if connected to hypixel.net
    if(
        !Laby.labyAPI().serverController().isConnected() ||
        !Objects.equals(Objects.requireNonNull(Laby.labyAPI().serverController().getCurrentServerData()).address().getHost(), "hypixel.net")
    ) return;

    Laby.labyAPI().minecraft().executeNextTick(() -> Laby.labyAPI().minecraft().minecraftWindow().displayScreen(new QuickplayActivity(hypixel)));
  }
}

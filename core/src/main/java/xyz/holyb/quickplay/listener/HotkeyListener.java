package xyz.holyb.quickplay.listener;

import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.util.io.web.request.Request;
import xyz.holyb.quickplay.QuickplayAddon;
import xyz.holyb.quickplay.activity.QuickplayActivity;
import xyz.holyb.quickplay.utils.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HotkeyListener {
  private final QuickplayAddon addon;

  private final List<Server> servers = new ArrayList<>();

  public HotkeyListener(QuickplayAddon addon){
    this.addon = addon;

    servers.add(
        Request.ofGson(Server.class)
          .url("https://raw.githubusercontent.com/holybaechuLabyAddons/QuickplayServers/main/Hypixel.json")
          .executeSync().get()
    );

  }

  @Subscribe
  public void onKeyPress(KeyEvent event){
    if (event.key() != addon.configuration().hotKey().get()) return;

    if (!addon.configuration().enabled().get()) return;
    if (event.state() != State.PRESS) return;

    if (Laby.labyAPI().minecraft().minecraftWindow().isScreenOpened()) return;

    // addon.logger().info("isConnected(): "+ (Laby.labyAPI().serverController().isConnected() ? "true" : "false"));
    // if (Laby.labyAPI().serverController().isConnected()) addon.logger().info("getHost(): "+ Laby.labyAPI().serverController().getCurrentServerData().address().getHost());

    // Check if connected to supported server
    if (!Laby.labyAPI().serverController().isConnected()) return;

    for (Server value : this.servers) {
      if (!Objects.requireNonNull(Laby.labyAPI().serverController().getCurrentServerData()).address().getHost().endsWith(value.ip)) continue;

      Laby.labyAPI().minecraft().executeNextTick(() -> Laby.labyAPI().minecraft().minecraftWindow().displayScreen(new QuickplayActivity(value)));
    }
  }
}

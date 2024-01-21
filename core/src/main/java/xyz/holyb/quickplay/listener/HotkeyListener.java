package xyz.holyb.quickplay.listener;

import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.util.io.web.request.Request;
import xyz.holyb.quickplay.QuickplayAddon;
import xyz.holyb.quickplay.activity.QuickplayActivity;
import xyz.holyb.quickplay.utils.GithubFile;
import xyz.holyb.quickplay.utils.GithubTree;
import xyz.holyb.quickplay.utils.Server;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HotkeyListener {
  private final QuickplayAddon addon;

  private final List<Server> servers = new ArrayList<>();
  private final String serversRepo = "holybaechuLabyAddons/QuickplayServers";

  public HotkeyListener(QuickplayAddon addon){
    this.addon = addon;

    GithubTree serversRepoTree = Request.ofGson(GithubTree.class)
        .url(String.format("https://api.github.com/repos/%s/git/trees/main?recursive=1", serversRepo))
        .executeSync().get();

    for (GithubFile value : serversRepoTree.tree) {
      if (!value.path.endsWith(".json")) continue;

      servers.add(
          Request.ofGson(Server.class)
              .url(String.format("https://raw.githubusercontent.com/%s/main/%s", serversRepo, value.path))
              .executeSync().get()
      );
    }
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
      for (String ip : value.ip){
        if (!Objects.requireNonNull(Laby.labyAPI().serverController().getCurrentServerData()).address().getHost().endsWith(ip)) continue;

        Laby.labyAPI().minecraft().executeNextTick(() -> Laby.labyAPI().minecraft().minecraftWindow().displayScreen(new QuickplayActivity(value)));

        return;
      }
    }
  }
}

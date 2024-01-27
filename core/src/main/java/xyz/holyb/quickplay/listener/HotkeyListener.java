package xyz.holyb.quickplay.listener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.util.JsonFileCache;
import net.labymod.api.util.io.web.request.Request;
import xyz.holyb.quickplay.QuickplayAddon;
import xyz.holyb.quickplay.activity.QuickplayActivity;
import xyz.holyb.quickplay.utils.GithubFile;
import xyz.holyb.quickplay.utils.GithubTree;
import xyz.holyb.quickplay.utils.Server;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HotkeyListener {
  private final QuickplayAddon addon;

  private final List<Server> servers = new ArrayList<>();
  private final String serversRepo = "holybaechuLabyAddons/QuickplayServers";
  private final Gson gson = new Gson();

  public HotkeyListener(QuickplayAddon addon){
    this.addon = addon;

    Request<JsonElement> req = Request.ofGson(JsonElement.class)
        .url(String.format("https://api.github.com/repos/%s/git/trees/main?recursive=1", serversRepo))
        .async();

    JsonFileCache<JsonElement> treeCache = JsonFileCache.create(Path.of("./labymod-neo/cache/quickplay/", "tree.json"), req, "tree.json");

    treeCache.read(false, (_result) -> {
          for (GithubFile value : gson.fromJson(treeCache.getJsonObject().get().get("tree.json"), GithubTree.class).tree) {
            if (!value.path.endsWith(".json")) continue;

            Request<JsonElement> request = Request.ofGson(JsonElement.class)
                .url(String.format("https://raw.githubusercontent.com/%s/main/%s", serversRepo, value.path))
                .async();

            JsonFileCache<JsonElement> serverCache = JsonFileCache.create(Path.of("./labymod-neo/cache/quickplay/servers", value.path), request, value.path);

            serverCache.read(false, (__result) -> {
              servers.add(gson.fromJson(serverCache.getJsonObject().get().get(value.path), Server.class));
            });
          }
    });
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

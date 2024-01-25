package xyz.holyb.quickplay.listener;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import net.labymod.api.Laby;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.util.JsonFileCache;
import net.labymod.api.util.io.web.request.Request;
import net.labymod.api.util.logging.Logging;
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



    Request.ofGson(GithubTree.class)
        .url(String.format("https://api.github.com/repos/%s/git/trees/main?recursive=1", serversRepo))
        .execute((response) -> {
          for (GithubFile value : response.get().tree) {
            if (!value.path.endsWith(".json")) continue;

            Request<JsonElement> request = Request.ofGson(JsonElement.class)
                .url(String.format("https://raw.githubusercontent.com/%s/main/%s", serversRepo, value.path));

            JsonFileCache<JsonElement> cache = JsonFileCache.create(Path.of("./labymod-neo/cache/quickplay/", value.path), request, value.path);

            cache.read(true, (result) -> {
              servers.add(gson.fromJson(result.get(), Server.class));
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

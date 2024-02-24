package xyz.holyb.quickplay.listener;

import com.google.gson.Gson;
import net.labymod.api.Laby;
import net.labymod.api.client.resources.ResourceLocation;
import net.labymod.api.event.Subscribe;
import net.labymod.api.event.client.input.KeyEvent.State;
import net.labymod.api.event.client.input.KeyEvent;
import net.labymod.api.labynet.models.GameMode;
import net.labymod.api.labynet.models.ServerGroup;
import xyz.holyb.quickplay.QuickplayAddon;
import xyz.holyb.quickplay.activity.QuickplayActivity;
import xyz.holyb.quickplay.utils.Gamemode;
import xyz.holyb.quickplay.utils.Server;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class HotkeyListener {
  private final QuickplayAddon addon;

  private final List<Server> servers = new ArrayList<>();
  private final List<String> serverFiles = List.of("hypixel.json", "skydinse.json");
  private final String serversDirectory = "servers";
  private final Gson gson = new Gson();

  public HotkeyListener(QuickplayAddon addon){
    this.addon = addon;

    // Load server data
    for (String serverFile : serverFiles){
      try {
        InputStream in = ResourceLocation.create("quickplay",  String.join("/", serversDirectory, serverFile)).openStream();
        InputStreamReader reader = new InputStreamReader(in, StandardCharsets.UTF_8);

        servers.add(gson.fromJson(reader, Server.class));
      } catch (Exception e) {
        addon.logger().warn("Could not load server: "+serverFile+". Skipping "+serverFile);

//        e.printStackTrace();
      }
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

    // Laby.net server
    if (Laby.labyAPI().labyNetController().getCurrentServer().isPresent()) {
      ServerGroup serverGroup = Laby.labyAPI().labyNetController().getCurrentServer().get();
      Server newServer = new Server();

      // gamemodes
      Map<String, Gamemode> gameModes = new HashMap<>();
      for (GameMode gameMode : serverGroup.getAllGameModes().values()) {
        Gamemode newGamemode = new Gamemode();

        if (Objects.isNull(gameMode.getCommand())) continue;
        newGamemode.command = gameMode.getCommand();

        gameModes.put(gameMode.getName(), newGamemode);
      }
      if (gameModes.isEmpty()) return;
      newServer.gameModes = gameModes;

      if (serverGroup.getAttachment("icon").isPresent()) {
        newServer.baseImageURL = serverGroup.getAttachment("icon").get().getUrl();
      }

      Laby.labyAPI().minecraft().executeNextTick(() -> Laby.labyAPI().minecraft().minecraftWindow().displayScreen(new QuickplayActivity(newServer)));
    }
  }
}

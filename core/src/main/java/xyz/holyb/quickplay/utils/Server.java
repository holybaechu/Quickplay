package xyz.holyb.quickplay.utils;

import com.google.gson.annotations.SerializedName;
import java.util.List;
import java.util.Map;

public class Server {
  public List<String> ip;

  @SerializedName("gamemodes")
  public Map<String, Gamemode> gameModes;

  public String baseImageURL;
}

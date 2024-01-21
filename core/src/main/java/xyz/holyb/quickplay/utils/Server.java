package xyz.holyb.quickplay.utils;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Server{
  public String ip;

  @SerializedName("gamemodes")
  public Map<String, Gamemode> gameModes;

  public String baseImageURL;
}

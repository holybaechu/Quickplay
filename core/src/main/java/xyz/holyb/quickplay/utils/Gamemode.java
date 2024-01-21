package xyz.holyb.quickplay.utils;

import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Gamemode{
  public String command;
  public String imageURL;

  @SerializedName("gamemodes")
  public Map<String, Gamemode> gameModes;
}

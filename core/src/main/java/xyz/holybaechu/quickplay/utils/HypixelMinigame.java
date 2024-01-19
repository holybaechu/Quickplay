package xyz.holybaechu.quickplay.utils;

import java.util.Map;

public class HypixelMinigame {
    public String name;
    public String imageURL;
    public Map<String, String> modes;

    public HypixelMinigame setName(String name) {
      this.name = name;
      this.imageURL = "https://hypixel.net/styles/hypixel-uix/hypixel/game-icons/"+name.replace(" ","")+"-64.png";

      return this;
    }

    public HypixelMinigame setModes(Map<String, String> modes) {
      this.modes = modes;

      return this;
    }

    public HypixelMinigame setImageURL(String imageURL) {
      this.imageURL = imageURL;

      return this;
    }
}

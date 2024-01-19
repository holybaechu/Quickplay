package xyz.holybaechu.quickplay.activity;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.attributes.bounds.BoundsType;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.GridWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import net.labymod.api.client.resources.ResourceLocation;
import xyz.holybaechu.quickplay.servers.Hypixel;
import xyz.holybaechu.quickplay.utils.HypixelMinigame;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@AutoActivity
@Link("quickplay.lss")
public class QuickplayActivity extends SimpleActivity {

  Hypixel hypixel;

  public QuickplayActivity(Hypixel hypixel){
    this.hypixel = hypixel;
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    FlexibleContentWidget windowWidget = new FlexibleContentWidget().addId("window");
    GridWidget<Widget> content = new GridWidget<>().addId("content");
    content.rows().set((int) Math.ceil((double) 10/3));

    for (int i = 0; i < hypixel.minigames.size(); i++) {
      HypixelMinigame minigame = hypixel.minigames.get(i);

      HorizontalListWidget minigameWidget = new HorizontalListWidget().addId("minigame");

      IconWidget icon = new IconWidget(Icon.url(minigame.imageURL)).addId("icon");
      minigameWidget.addEntry(icon);

      ButtonWidget button = new ButtonWidget().addId("button");
      button.updateComponent(Component.text(minigame.name));

      button.setActionListener(() -> {
        if(minigame.modes.size() == 1){
          labyAPI.minecraft().chatExecutor().chat("/play "+minigame.modes.values().toArray()[0]);
        }else {
          Hypixel newHypixel = new Hypixel();
          List<HypixelMinigame> newMinigames = new ArrayList<>();

          minigame.modes.forEach((key, mode) -> {
            newMinigames.add(new HypixelMinigame().setName(key).setModes(
                Map.ofEntries(Map.entry("Standard", mode))).setImageURL(minigame.imageURL));
          });

          newHypixel.minigames = newMinigames;

          labyAPI.minecraft().executeNextTick(() -> labyAPI.minecraft().minecraftWindow().displayScreen(new QuickplayActivity(newHypixel)));
        }
      });
      minigameWidget.addEntry(button);

      content.addChild(minigameWidget);
    }

    windowWidget.addContent(content);
    this.document().addChild(windowWidget);
  }
}

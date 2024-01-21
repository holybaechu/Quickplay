package xyz.holyb.quickplay.activity;

import net.labymod.api.client.component.Component;
import net.labymod.api.client.gui.icon.Icon;
import net.labymod.api.client.gui.screen.Parent;
import net.labymod.api.client.gui.screen.activity.AutoActivity;
import net.labymod.api.client.gui.screen.activity.Link;
import net.labymod.api.client.gui.screen.activity.types.SimpleActivity;
import net.labymod.api.client.gui.screen.widget.Widget;
import net.labymod.api.client.gui.screen.widget.widgets.input.ButtonWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.FlexibleContentWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.GridWidget;
import net.labymod.api.client.gui.screen.widget.widgets.layout.list.HorizontalListWidget;
import net.labymod.api.client.gui.screen.widget.widgets.renderer.IconWidget;
import xyz.holyb.quickplay.utils.Gamemode;
import xyz.holyb.quickplay.utils.Server;
import java.util.ArrayList;
import java.util.Map;

@AutoActivity
@Link("quickplay.lss")
public class QuickplayActivity extends SimpleActivity {

  Server server;

  public QuickplayActivity(Server server){
    this.server = server;
  }

  private String getImageURL(Server server, Gamemode gameMode, String name) {
    return gameMode.imageURL != null ? gameMode.imageURL : String.format(server.baseImageURL, name.replace(" ", ""));
  }

  @Override
  public void initialize(Parent parent) {
    super.initialize(parent);

    FlexibleContentWidget windowWidget = new FlexibleContentWidget().addId("window");
    GridWidget<Widget> content = new GridWidget<>().addId("content");
    content.rows().set((int) Math.ceil((double) 10/3));


    server.gameModes.forEach((name, gameMode) -> {
      HorizontalListWidget gameModeWidget = new HorizontalListWidget().addId("gamemode");

      IconWidget icon = new IconWidget(Icon.url(getImageURL(server, gameMode, name))).addId("icon");
      gameModeWidget.addEntry(icon);

      ButtonWidget button = new ButtonWidget().addId("button");
      button.updateComponent(Component.text(name));

      button.setActionListener(() -> {
        if (gameMode.gameModes == null){
          labyAPI.minecraft().chatExecutor().chat(gameMode.command);

          return;
        }

        if(gameMode.gameModes.size() == 1){
          labyAPI.minecraft().chatExecutor().chat(((Gamemode) gameMode.gameModes.values().toArray()[0]).command);
        }else {
          Server newServer = new Server();
          Map<String, Gamemode> gameModes = gameMode.gameModes;

          gameModes.forEach((key, value) -> {
            value.imageURL = getImageURL(server, gameMode, name);
          });

          newServer.gameModes = gameModes;

          labyAPI.minecraft().executeNextTick(() -> labyAPI.minecraft().minecraftWindow().displayScreen(new QuickplayActivity(newServer)));
        }
      });
      gameModeWidget.addEntry(button);

      content.addChild(gameModeWidget);
    });

    windowWidget.addContent(content);
    this.document().addChild(windowWidget);
  }
}

package com.github.wildtooth.awesome.core.widget;

import com.github.wildtooth.awesome.api.Awesome;
import com.github.wildtooth.awesome.api.server.SuperAwesomeServer;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidget;
import net.labymod.api.client.gui.hud.hudwidget.text.TextHudWidgetConfig;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine;
import net.labymod.api.client.gui.hud.hudwidget.text.TextLine.State;
import net.labymod.api.client.gui.icon.Icon;

public abstract class AwesomeWidget extends TextHudWidget<TextHudWidgetConfig> {

  private TextLine textLine;
  private final String widgetDisplayName;
  private final Icon associatedIcon;

  public AwesomeWidget(String id, String widgetDisplayName, Icon associatedIcon) {
    super(id);
    this.widgetDisplayName = widgetDisplayName;
    this.associatedIcon = associatedIcon;

    // this.bindCategory();
  }

  @Override
  public void load(TextHudWidgetConfig config) {
    super.load(config);
    this.textLine = super.createLine(this.widgetDisplayName, getDisplayText());
    if (shouldShow()) {
      this.textLine.setState(State.VISIBLE);
    } else {
      this.textLine.setState(State.HIDDEN);
    }
    this.setIcon(this.associatedIcon);
  }

  @Override
  public void onUpdate() {
    super.onUpdate();
    if (getDisplayText().equals(" ")) {
      this.textLine.setState(State.HIDDEN);
    } else {
      this.textLine.setState(State.VISIBLE);
    }
  }

  protected boolean shouldShow() {
    SuperAwesomeServer server = Awesome.getReferences().clientService().getServer();
    return server != SuperAwesomeServer.NONE;
  }

  protected abstract String getDisplayText();

}

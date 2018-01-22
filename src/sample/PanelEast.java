package sample;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

public class PanelEast  extends Pane {

    private int width;
    private int height;
    private float fontSize;

    public PanelEast(int wid,int hei, float fonts)
    {
        width=wid;
        height=hei;
        fontSize=fonts;
    }

    public void init()
    {
        this.setPadding(new Insets(height/100,height/100,height/100,height/100));
        this.setPrefHeight(82*height/100);
        this.setPrefWidth((88*width/100)-(2*height/100));
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/leftBackground.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }
}

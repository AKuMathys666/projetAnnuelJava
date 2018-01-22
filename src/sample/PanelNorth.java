package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PanelNorth extends FlowPane{

    private int width;
    private int height;
    private float fontSize;

    private Label logged = new Label();
    private Label labelLogin = new Label();
    private TextArea login = new TextArea();
    private Label labelPassword = new Label();
    private PasswordField password = new PasswordField();
    private Button submit = new Button();
    private TextArea dateEtHeure = new TextArea();
    private Label erreurLogged = new Label();
    private Button createAccount = new Button();

    protected PanelEast panelEast;

    public PanelNorth(int wid,int hei, float fonts)
    {
        width=wid;
        height=hei;
        fontSize=fonts;
    }

    public void init()
    {
        this.setPadding(new Insets(height/100,height/100,height/100,height/100));
        this.setPrefHeight(15*height/100);
        this.setPrefWidth(width-(2*height/100));
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/header-background-1.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }

    class ClockListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //actualisation/redefinition de la date et de l'heure
            SimpleDateFormat df = new SimpleDateFormat("EEEE, d MMM yyyy HH:mm:ss");
            dateEtHeure.setText(df.format(Calendar.getInstance().getTime()));
        }
    }
}

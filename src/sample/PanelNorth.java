package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PanelNorth extends HBox{

    private int width;
    private int height;
    private float fontSize;

    private Label logged = new Label();
    private Label labelLogin = new Label();
    private TextArea login = new TextArea();
    private Label labelPassword = new Label();
    private PasswordField password = new PasswordField();
    private Label timeLabel = new Label();
    private Button submit = new Button();
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
        Pane datePane = new Pane();
        datePane.setPrefWidth(50*width/100-(7*height/100));
        datePane.setPrefHeight(7*height/100);

        FlowPane thisPane = new FlowPane();
        thisPane.setVgap(height/100);
        thisPane.setHgap(height/100);
        thisPane.setPadding(new Insets(height/100,height/100,height/100,height/100));
        thisPane.setPrefWidth(width-(2*height/100));
        thisPane.setPrefHeight(15*height/100);

        this.setPrefWidth(width-(2*height/100));
        this.setPrefHeight(15*height/100);
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/header-background-1.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        timeLabel = new Label();
        timeLabel.setFont(Font.font("Arial",(int)fontSize/30));
        DateFormat timeFormat = new SimpleDateFormat( "EEEE, d MMM yyyy HH:mm:ss" );
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis( 500 ),
                        new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent e) {
                                final long diff =System.currentTimeMillis();
                                if (diff < 0) {
                                    //  timeLabel.setText( "00:00:00" );
                                    timeLabel.setText(timeFormat.format(0));
                                } else {
                                    timeLabel.setText(timeFormat.format(diff));
                                }
                            }
                        }
                )
        );
        timeline.setCycleCount( Animation.INDEFINITE );
        timeline.play();


        logged = new Label("Logged with username ");
        logged.setPrefWidth((12*width/100));
        logged.setPrefHeight(5*height/100);
        logged.setFont(Font.font("Arial",(int)fontSize/40));
        logged.setVisible(false);
        logged.setManaged(false);

        erreurLogged = new Label("Erreur: login ou mot de passe incorrect. ");
        erreurLogged.setPrefWidth((12*width/100));
        erreurLogged.setPrefHeight(5*height/100);
        erreurLogged.setFont(Font.font("Arial",(int)fontSize/40));
        erreurLogged.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        erreurLogged.setVisible(false);
        erreurLogged.setManaged(false);

        labelLogin = new Label("Login : ");
        labelLogin.setPrefWidth((5*width/100));
        labelLogin.setPrefHeight(5*height/100);
        labelLogin.setFont(Font.font("Arial",(int)fontSize/60));

        login = new TextArea();
        login.setPrefWidth((13*width/100));
        login.setPrefHeight(5*height/100);
        //login.setMargin(new Insets(0,height/100,0,0));
        //login.setLineWrap(true);
        //login.setWrapStyleWord(true);
        //login.setBackground(new Color(222,222,222));
        login.setFont(Font.font("Arial",(int)fontSize/60));

        labelPassword = new Label("Password : ");
        labelPassword.setPrefWidth((9*width/100));
        labelPassword.setPrefHeight(5*height/100);
        labelPassword.setFont(Font.font("Arial",(int)fontSize/60));

        password = new PasswordField();
        password.setPrefWidth((13*width/100));
        password.setPrefHeight(5*height/100);
        //password.setMargin(new Insets(0,height/100,0,0));
        //password.setBackground(new Color(222,222,222));
        password.setFont(Font.font("Arial",(int)fontSize/60));

        submit = new Button("Submit");
        //submit.setContentAreaFilled(false);
        submit.setPrefWidth(10*width/100);
        submit.setPrefHeight(5*height/100);
        //submit.setMargin(new Insets(0,height/100,0,0));
        submit.setFont(Font.font("Arial",(int)fontSize/60));
        //submit.setFocusPainted(false);
        //submit.setMargin(new Insets(1,1,1,1));
        //submit.setBackground(new Color(222,222,222));
        //submit.addActionListener(new SubmitListener());

        createAccount = new Button("Créer un compte");
        //createAccount.setContentAreaFilled(false);
        createAccount.setPrefWidth(20*width/100);
        createAccount.setPrefHeight(5*height/100);
        //createAccount.setMargin(new Insets(0,height/100,0,0));
        createAccount.setFont(Font.font("Arial",(int)fontSize/60));
        // createAccount.setFocusPainted(false);
        //createAccount.setMargin(new Insets(1,1,1,1));
        //createAccount.setBackground(new Color(222,222,222));
        //createAccount.addActionListener(new CreateListener());

        thisPane.getChildren().add(logged);
        thisPane.getChildren().add(labelLogin);
        thisPane.getChildren().add(login);
        thisPane.getChildren().add(labelPassword);
        thisPane.getChildren().add(password);
        thisPane.getChildren().add(submit);
        thisPane.getChildren().add(createAccount);
        thisPane.getChildren().add(erreurLogged);

        this.getChildren().add(thisPane);
        this.getChildren().add(datePane);
        datePane.getChildren().add(timeLabel);
        this.setHgrow(timeLabel,Priority.ALWAYS);

        //submit.addActionListener(new SubmitListener());
    }
}

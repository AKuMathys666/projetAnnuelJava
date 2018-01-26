package sample;

import javafx.scene.control.*;
import org.json.JSONObject;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class PanelNorth extends HBox{

    private int width;
    private int height;
    private float fontSize;

    private Label logged = new Label();
    private Label labelLogin = new Label();
    private TextField login = new TextField();
    private Label labelPassword = new Label();
    private PasswordField password = new PasswordField();
    private Label timeLabel = new Label();
    private Button submit = new Button();
    private Label erreurLogged = new Label();
    private Button createAccount = new Button();

    private String token;

    protected PanelEast panelEast;

    public PanelNorth(int wid,int hei, float fonts)
    {
        width=wid;
        height=hei;
        fontSize=fonts;
    }

    public void init(PanelEast aPanelEast)
    {
        panelEast = aPanelEast;

        Pane datePane = new Pane();
        datePane.setPrefWidth(40*width/100-(7*height/100));
        datePane.setPrefHeight(7*height/100);

        FlowPane thisPane = new FlowPane();
        thisPane.setVgap(height/100);
        thisPane.setHgap(height/100);
        thisPane.setPadding(new Insets(height/100,height/100,height/100,height/100));
        thisPane.setPrefWidth(60*width/100+(8*height/100));
        thisPane.setPrefHeight(15*height/100);

        this.setPrefWidth(width-(2*height/100));
        this.setPrefHeight(15*height/100);
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/header-background-1.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        timeLabel = new Label();
        timeLabel.setPrefWidth(50*width/100-(7*height/100));
        timeLabel.setPrefHeight(7*height/100);
        timeLabel.setFont(Font.font("Arial",(int)fontSize/35));
        DateFormat timeFormat = new SimpleDateFormat( "EEEE, d MMM yyyy HH:mm:ss" );
        Timeline timeline = new Timeline(
                new KeyFrame(
                        Duration.millis( 500 ),
                        new EventHandler<ActionEvent>(){
                            @Override
                            public void handle(ActionEvent e) {
                                final long diff =System.currentTimeMillis();
                                if (diff < 0) {
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
        logged.setPrefWidth((50*width/100));
        logged.setPrefHeight(5*height/100);
        logged.setFont(Font.font("Arial",(int)fontSize/40));
        logged.setVisible(false);
        logged.setManaged(false);

        erreurLogged = new Label("Error: invalid login or password.");
        erreurLogged.setPrefWidth((30*width/100));
        erreurLogged.setPrefHeight(5*height/100);
        erreurLogged.setFont(Font.font("Arial",(int)fontSize/60));
        erreurLogged.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        erreurLogged.setVisible(false);
        erreurLogged.setManaged(false);

        labelLogin = new Label("Login : ");
        labelLogin.setPrefWidth((5*width/100));
        labelLogin.setPrefHeight(5*height/100);
        labelLogin.setFont(Font.font("Arial",(int)fontSize/60));

        login = new TextField();
        login.setPrefWidth((13*width/100));
        login.setPrefHeight(5*height/100);
        login.setFont(Font.font("Arial",(int)fontSize/60));

        labelPassword = new Label("Password : ");
        labelPassword.setPrefWidth((9*width/100));
        labelPassword.setPrefHeight(5*height/100);
        labelPassword.setFont(Font.font("Arial",(int)fontSize/60));

        password = new PasswordField();
        password.setPrefWidth((13*width/100));
        password.setPrefHeight(5*height/100);
        password.setFont(Font.font("Arial",(int)fontSize/60));

        submit = new Button("Submit");
        submit.setPrefWidth(10*width/100);
        submit.setPrefHeight(5*height/100);
        submit.setFont(Font.font("Arial",(int)fontSize/60));

        createAccount = new Button("Create account");
        createAccount.setPrefWidth(20*width/100);
        createAccount.setPrefHeight(5*height/100);
        createAccount.setFont(Font.font("Arial",(int)fontSize/60));
        createAccount.setOnAction(new CreateListener());

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

        submit.setOnAction(new SubmitListener());
    }

    class SubmitListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            String tryLogin=login.getText();
            String tryPassword=password.getText();
            login.setText("");
            password.setText("");

            try
            {
                URL url=new URL("http://localhost:8080/auth/login");
                HttpURLConnection co =(HttpURLConnection) url.openConnection();
                co.setRequestProperty("Content-Type", "application/json");
                co.setRequestProperty("Accept", "application/json");
                co.setDoOutput(true);
                co.setRequestMethod("POST");

                org.json.JSONObject cred   = new JSONObject();
                cred.put("email",tryLogin);
                cred.put("password", tryPassword);
                OutputStreamWriter wr = new OutputStreamWriter(co.getOutputStream());
                wr.write(cred.toString());
                wr.flush();

                StringBuilder sb = new StringBuilder();
                int HttpResult = co.getResponseCode();
                if (HttpResult == HttpURLConnection.HTTP_OK)
                {
                    BufferedReader br = new BufferedReader(new InputStreamReader(co.getInputStream(), "utf-8"));
                    String line = null;
                    while ((line = br.readLine()) != null)
                    {
                        sb.append(line + "\n");
                    }
                    br.close();
                    token=sb.toString().replace("\n","");
                    panelEast.token=token;

                    logged.setText("Logged with username "+tryLogin);
                    logged.setVisible(true);
                    logged.setManaged(true);
                    login.setVisible(false);
                    login.setManaged(false);
                    labelLogin.setVisible(false);
                    labelLogin.setManaged(false);
                    labelPassword.setVisible(false);
                    labelPassword.setManaged(false);
                    password.setVisible(false);
                    password.setManaged(false);
                    submit.setVisible(false);
                    submit.setManaged(false);
                    erreurLogged.setText("");
                    erreurLogged.setVisible(false);
                    erreurLogged.setManaged(false);
                    createAccount.setVisible(false);
                    createAccount.setManaged(false);
                }
                else
                {
                    erreurLogged.setVisible(true);
                    erreurLogged.setManaged(true);
                }
            }
            catch(Exception exc)
            {
                exc.printStackTrace();
            }
            panelEast.displayProjects();
        }
    }
    class CreateListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.createAccount();
        }
    }
}

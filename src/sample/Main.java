package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.*;
import javafx.geometry.*;

public class Main extends Application {

    private Rectangle2D tailleFenetre = Screen.getPrimary().getVisualBounds();

    private int width = (int)tailleFenetre.getWidth();
    private int height = (int)tailleFenetre.getHeight();

    private float ratio;
    private float fontSize;

    private PanelNorth panelNorth;
    private PanelEast panelEast;
    private PanelWest panelWest;

    @Override
    public void start(Stage primaryStage) throws Exception{
        ratio = this.monRatio(width,height);
        fontSize=(float)width*ratio;

        primaryStage.initStyle(StageStyle.UNDECORATED);
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        BorderPane mainPane = new BorderPane();
        mainPane.setPadding(new Insets(height/100,height/100,height/100,height/100));


        panelNorth 		= new PanelNorth(width,height,fontSize);
        panelEast 		= new PanelEast(width,height,fontSize);
        panelWest 		= new PanelWest(width,height,fontSize);

        panelNorth.init(panelEast);
        panelEast.init();
        panelWest.init();

        mainPane.setTop(panelNorth);
        mainPane.setLeft(panelWest);
        mainPane.setRight(panelEast);

        mainPane.setMargin(panelNorth,new Insets(0,0,height/100,0));
        mainPane.setBackground(new Background(new BackgroundFill(Color.rgb(48,77,95),CornerRadii.EMPTY,Insets.EMPTY)));


        Scene myScene = new Scene(mainPane, width, height, Color.rgb(48,77,95));
        myScene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());
        primaryStage.setScene(myScene);
        primaryStage.setTitle("Hello World");
        primaryStage.show();
    }

    private float monRatio(int width,int height){
        float ratio = (float)width/height;
        float mult;
        if (ratio <= 1.2){
            mult=(float)1;
        }
        else if(ratio <=1.5){
            mult=(float)95/100;
        }
        else if(ratio <=1.6){
            mult=(float)90/100;
        }
        else if(ratio <=1.71){
            mult=(float)85/100;
        }
        else if(ratio <=1.8){
            mult=(float)80/100;
        }
        else if(ratio <=1.9){
            mult=(float)80/100;
        }
        else {
            mult=(float)75/100;
        }
        return mult;
    }

    public static void main(String[] args) {
        launch(args);
    }
}

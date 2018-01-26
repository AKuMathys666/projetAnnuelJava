package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class PanelWest extends VBox {

    private ArrayList<Button> listButton = new ArrayList<Button>();

    private int width;
    private int height;
    private float fontSize;

    private Button timer;
    private Button tasks;
    private Button projects;
    private Button clients;
    private Button team;
    private Button workspaces;

    protected PanelEast panelEast;

    public PanelWest(int wid,int hei, float fonts)
    {
        width=wid;
        height=hei;
        fontSize=fonts;
    }

    public void init(PanelEast aPanelEast)
    {
        panelEast = aPanelEast;

        this.setSpacing(height/100);
        this.setPadding(new Insets(height/100,height/100,height/100,height/100));
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/blue_background_extra_long.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                    BackgroundSize.DEFAULT)));

        //this.setStyle("-fx-background-image: url('../img/blue_background_extra_long.jpg');" +
        //        "-fx-background-size: cover;");
        this.setPrefHeight(82*height/100);
        this.setPrefWidth((12*width/100)-(2*height/100));
        timer = new Button("Timer");
        timer.setOnAction(new TimerListener());

        tasks = new Button("Tasks");
        tasks.setOnAction(new TasksListener());

        projects = new Button("Projects");
        projects.setOnAction(new ProjectsListener());

        clients = new Button("Clients");
        clients.setOnAction(new ClientsListener());

        team = new Button("Team");
        team.setOnAction(new TeamListener());

        workspaces = new Button("Workspaces");
        workspaces.setOnAction(new WorkspacesListener());

        listButton.add(timer);
        listButton.add(tasks);
        listButton.add(projects);
        listButton.add(clients);
        listButton.add(team);
        listButton.add(workspaces);

        for(Button item : listButton)
        {
            item.setPrefWidth((12*width/100)-(4*height/100));
            item.setPrefHeight(7*height/100);
            item.setFont(Font.font("Arial",(int)fontSize/65));
            this.getChildren().add(item);
        }

    }

    class TimerListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayTimer();
        }
    }

    class TasksListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayTasks();
        }
    }

    class ProjectsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayProjects();
        }
    }

    class ClientsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayClients();
        }
    }

    class TeamListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayTeam();
        }
    }

    class WorkspacesListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            panelEast.displayWorkspaces();
        }
    }
}

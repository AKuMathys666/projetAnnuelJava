package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class PanelWest extends VBox {

    private ArrayList<Button> listButton = new ArrayList<Button>();

    private int width;
    private int height;
    private float fontSize;

    private Button timer;
    private Button dashboard;
    private Button reports;
    private Button insights;
    private Button savedReports;
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

    public void init()
    {
        String monImage = "img/blue_background_extra_long.jpg";
        //ImagePanel panelDate = new ImagePanel(monImage,(12*width/100)-(2*height/100), 82*height/100);

        //panelDate.setPreferredSize(new Dimension((12*width/100)-(2*height/100),82*height/100));

        //Ajout de ces derniers au panel nord
        //this.add(panelDate);
        //this.setVgap(height/100);
        //this.getLayout().setHgap(0);

        timer = new Button("Timer");
        timer.setOnAction(new TimerListener());

        dashboard = new Button("Dashboard");
        dashboard.setOnAction(new DashboardListener());

        reports = new Button("Reports");
        reports.setOnAction(new ReportsListener());

        insights = new Button("Insights");
        insights.setOnAction(new InsightsListener());

        savedReports = new Button("Saved Reports");
        savedReports.setOnAction(new SavedReportsListener());

        projects = new Button("Projects");
        projects.setOnAction(new ProjectsListener());

        clients = new Button("Clients");
        clients.setOnAction(new ClientsListener());

        team = new Button("Team");
        team.setOnAction(new TeamListener());

        workspaces = new Button("Workspaces");
        workspaces.setOnAction(new WorkspacesListener());

        listButton.add(timer);
        listButton.add(dashboard);
        listButton.add(reports);
        listButton.add(insights);
        listButton.add(savedReports);
        listButton.add(projects);
        listButton.add(clients);
        listButton.add(team);
        listButton.add(workspaces);

        for(Button item : listButton)
        {
            //item.setOpaqueInsets();
            //item.setContentAreaFilled(false);
            item.setPrefWidth((12*width/100)-(4*height/100));
            item.setPrefHeight(7*height/100);
            //item.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
            //item.setFocusPainted(false);
            //item.setMargin(new Insets(1,1,1,1));
            this.getChildren().add(item);
        }

    }

    class TimerListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayTimer();
        }
    }
    class DashboardListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayDashboard();
        }
    }
    class ReportsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayReports();
        }
    }
    class InsightsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayInsights();
        }
    }
    class SavedReportsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            // panelEast.displaySavedReports();
        }
    }
    class ProjectsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //try {
            //    panelEast.displayProjects();
            //} catch (IOException | JSONException e1) {
            //    // TODO Auto-generated catch block
            //    e1.printStackTrace();
            //}
        }
    }
    class ClientsListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayClients();
        }
    }
    class TeamListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayTeam();
        }
    }
    class WorkspacesListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            //panelEast.displayWorkspaces();
        }
    }
}

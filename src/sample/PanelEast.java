package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class PanelEast  extends StackPane {

    private int width;
    private int height;
    private float fontSize;

    private Label labelLogin;
    private TextArea login;
    private Label labelPassword;
    private PasswordField password;
    private Label labelFirstName;
    private TextArea firstName;
    private Label labelLastName;
    private TextArea lastName;
    private Button submit;
    private Button submitProject;
    private Label erreurLogged;

    private Label labelTitre;
    private TextArea titre;

    private ArrayList<CheckBox> headerCheckbox = new ArrayList<CheckBox>();
    private ArrayList<String> headerIdProject = new ArrayList<String>();
    private ArrayList<Label> headerProject = new ArrayList<Label>();
    private ArrayList<Label> headerCreator = new ArrayList<Label>();
    private ArrayList<String> headerIdTeam = new ArrayList<String>();
    private ArrayList<Label> headerTeam = new ArrayList<Label>();
    private ArrayList<Label> headerStatus = new ArrayList<Label>();
    private ArrayList<Label> headerIdProjectInTeam = new ArrayList<Label>();
    private ArrayList<Label> headerRolesInTeam = new ArrayList<Label>();
    private ArrayList<Label> headerMemberInTeam = new ArrayList<Label>();
    private ArrayList<Label> headerMember = new ArrayList<Label>();
    private ArrayList<String> headerIdMember = new ArrayList<String>();

    private Button deleteProject = new Button();
    private Button selectProject = new Button();
    private Button addProject = new Button();

    private Button deleteMember = new Button();
    private Button addMember = new Button();
    private Button postAddMember = new Button();

    private String selectedProjet;
    private String selectedTeam;

    public ScrollPane[] panelArray= new ScrollPane[10];

    protected String token;

    public PanelEast(int wid,int hei, float fonts)
    {
        width=wid;
        height=hei;
        fontSize=fonts;
    }
    public void clearAllArray()
    {
        headerCheckbox.clear();
        headerIdProject.clear();
        headerProject.clear();
        headerCreator.clear();
        headerIdTeam.clear();
        headerTeam.clear();
        headerStatus.clear();
        headerIdProjectInTeam.clear();
        headerRolesInTeam.clear();
        headerMemberInTeam.clear();
        headerMember.clear();
        headerIdMember.clear();
    }

    public void init()
    {
        this.setPadding(new Insets(height/100,height/100,height/100,height/100));
        this.setPrefHeight(82*height/100);
        this.setPrefWidth((88*width/100)-(2*height/100));
        this.setBackground(new Background(new BackgroundImage(new Image(getClass().getResource("../img/leftBackground.jpg").toExternalForm()),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));

        for (int i=0;i<10;i++)
        {
            panelArray[i]= new ScrollPane();
            panelArray[i].setPrefWidth((88*width/100)-(3*height/100));
            panelArray[i].setPrefHeight(81*height/100);
            this.getChildren().add(panelArray[i]);
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
            panelArray[i].setVbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            panelArray[i].setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
            //panelArray[i].setOpaque(false);
        }
    }

    public void createAccount()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }

        GridPane grid = new GridPane();//5,2,height/100,height/100
        grid.setPadding(new Insets(5*height/100, 50*width/100, 39*height/100, 5*width/100));
        panelArray[0].setVisible(true);
        panelArray[0].setManaged(true);
        //panelArray[0].setBorder(BorderFactory.createEmptyBorder(5*height/100, 5*height/100, 30*height/100, 40*width/100));
        //panelArray[0].setOpaque(false);
        grid.setVgap(height/100);
        grid.setHgap(height/100);
        panelArray[0].setContent(grid);

        labelLogin = new Label("Email : ");
        labelLogin.setPrefWidth(10*width/100);
        labelLogin.setPrefHeight(5*height/100);
        labelLogin.setFont(Font.font("Arial",(int)fontSize/60));

        login = new TextArea();
        login.setPrefWidth(20*width/100);
        login.setPrefHeight(5*height/100);
        //login.setMargin(new Insets(0,height/100,0,0));
        //login.setLineWrap(true);
        //login.setWrapStyleWord(true);
        login.setFont(Font.font("Arial",(int)fontSize/60));

        labelPassword = new Label("Password : ");
        labelPassword.setPrefWidth(10*width/100);
        labelPassword.setPrefHeight(5*height/100);
        labelPassword.setFont(Font.font("Arial",(int)fontSize/60));

        password = new PasswordField();
        password.setPrefWidth(15*width/100);
        password.setPrefHeight(5*height/100);
        //password.setMargin(new Insets(0,height/100,0,0));
        //password.setBackground(new Color(222,222,222));
        password.setFont(Font.font("Arial",(int)fontSize/60));

        labelFirstName = new Label("First Name : ");
        labelFirstName.setPrefWidth(10*width/100);
        labelFirstName.setPrefHeight(5*height/100);
        labelFirstName.setFont(Font.font("Arial",(int)fontSize/60));

        firstName = new TextArea();
        firstName.setPrefWidth(20*width/100);
        firstName.setPrefHeight(5*height/100);
        //firstName.setMargin(new Insets(0,height/100,0,0));
        //firstName.setLineWrap(true);
        //firstName.setWrapStyleWord(true);
        //firstName.setBackground(new Color(222,222,222));
        firstName.setFont(Font.font("Arial",(int)fontSize/60));

        labelLastName = new Label("Last Name : ");
        labelLastName.setPrefWidth(10*width/100);
        labelLastName.setPrefHeight(5*height/100);
        labelLastName.setFont(Font.font("Arial",(int)fontSize/60));

        lastName = new TextArea();
        lastName.setPrefWidth(20*width/100);
        lastName.setPrefHeight(5*height/100);
        //lastName.setMargin(new Insets(0,height/100,0,0));
        //lastName.setLineWrap(true);
        //lastName.setWrapStyleWord(true);
        //lastName.setBackground(new Color(222,222,222));
        lastName.setFont(Font.font("Arial",(int)fontSize/60));

        submit = new Button("Submit");
        //submit.setContentAreaFilled(false);
        submit.setPrefWidth(10*width/100);
        submit.setPrefHeight(5*height/100);
        //submit.setMargin(new Insets(0,height/100,0,0));
        submit.setFont(Font.font("Arial",(int)fontSize/60));
        //submit.setFocusPainted(false);
        //submit.setMargin(new Insets(1,1,1,1));
        //submit.setBackground(new Color(222,222,222));
        //submit.addActionListener(new SubmitCreationListener());
        submit.setOnAction(new SubmitCreationListener());

        erreurLogged = new Label("");
        erreurLogged.setPrefWidth(30*width/100);
        erreurLogged.setPrefHeight(5*height/100);
        erreurLogged.setWrapText(true);
        erreurLogged.setFont(Font.font("Arial",(int)fontSize/70));

        grid.add(labelLogin,1,1);
        grid.add(login,2,1);
        grid.add(labelPassword,1,2);
        grid.add(password,2,2);
        grid.add(labelFirstName,1,3);
        grid.add(firstName,2,3);
        grid.add(labelLastName,1,4);
        grid.add(lastName,2,4);
        grid.add(submit,1,5);
        grid.add(erreurLogged,2,5);
    }

    public void displayTimer()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[1].setVisible(true);
        panelArray[1].setManaged(true);
        TextArea mytext = new TextArea("displayTimer In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[1].setContent(mytext);
    }

    public void displayCreateTask()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[2].setVisible(true);
        panelArray[2].setManaged(true);
        TextArea mytext = new TextArea("displayDashboard In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[2].setContent(mytext);
    }

    public void displayAddMember() throws IOException, JSONException
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[3].setVisible(true);
        panelArray[3].setManaged(true);

        GridPane grid = new GridPane();

        URL url=new URL("http://localhost:8080/users");
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        headerCheckbox.add(new CheckBox());
        headerMember.add(new Label("users"));
        headerIdMember.add("idMember");

        grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,1);
        grid.add(headerMember.get(headerMember.size()-1),2,1);

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        JSONArray jsonArray = new JSONArray(response.toString());
        int i;
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);

            headerCheckbox.add(new CheckBox());
            headerMember.add(new Label(explrObject.getString("email")));
            headerIdMember.add(explrObject.getString("_id"));

            grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,2+i);
            grid.add(headerMember.get(headerMember.size()-1),2,2+i);

        }

        for(int j = 0; j < headerCheckbox.size(); j++)
        {

            headerCheckbox.get(j).setPrefWidth(5*width/100);
            headerCheckbox.get(j).setPrefHeight(5*height/100);
            headerCheckbox.get(j).setWrapText(true);

            headerMember.get(j).setPrefWidth(15*width/100);
            headerMember.get(j).setPrefHeight(5*height/100);
            headerMember.get(j).setWrapText(true);
            headerMember.get(j).setFont(Font.font("Arial",(int)fontSize/70));

        }
        grid.setVgap(height/100);
        grid.setHgap(height/100);

        postAddMember = new Button("Add Member");

        postAddMember.setFont(Font.font("Arial",(int)fontSize/65));
        postAddMember.setPrefWidth(10*width/100);
        postAddMember.setPrefHeight(7*height/100);
        postAddMember.setOnAction(new SubmitFormAddMemberListener());

        grid.add(postAddMember,1,2+i);

        panelArray[3].setContent(grid);

        headerCheckbox.get(0).setOnAction(new AllBoxesListener());




    }

    public void displayInsights()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[4].setVisible(true);
        panelArray[4].setManaged(true);
        //panelArray[4].removeAll();
        TextArea mytext = new TextArea("displayInsights In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[4].setContent(mytext);
    }

    public void displayCreateProject()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(5*height/100, 50*width/100, 39*height/100, 5*width/100));
        panelArray[0].setVisible(true);
        panelArray[0].setManaged(true);
        grid.setVgap(height/100);
        grid.setHgap(height/100);
        panelArray[0].setContent(grid);

        labelTitre = new Label("Nom du projet : ");
        labelTitre.setPrefWidth(10*width/100);
        labelTitre.setPrefHeight(5*height/100);
        labelTitre.setFont(Font.font("Arial",(int)fontSize/60));

        titre = new TextArea();
        titre.setPrefWidth(20*width/100);
        titre.setPrefHeight(5*height/100);
        titre.setFont(Font.font("Arial",(int)fontSize/60));


        submitProject = new Button("Submit");
        //submit.setContentAreaFilled(false);
        submitProject.setPrefWidth(10*width/100);
        submitProject.setPrefHeight(5*height/100);
        //submit.setMargin(new Insets(0,height/100,0,0));
        submitProject.setFont(Font.font("Arial",(int)fontSize/60));
        //submit.setFocusPainted(false);
        //submit.setMargin(new Insets(1,1,1,1));
        //submit.setBackground(new Color(222,222,222));
        //submit.addActionListener(new SubmitCreationListener());
        submitProject.setOnAction(new SubmitFormAddProjectListener());

        erreurLogged = new Label("");
        erreurLogged.setPrefWidth(30*width/100);
        erreurLogged.setPrefHeight(5*height/100);
        erreurLogged.setWrapText(true);
        erreurLogged.setFont(Font.font("Arial",(int)fontSize/70));

        grid.add(labelTitre,1,1);
        grid.add(titre,2,1);
        grid.add(submitProject,1,5);
        grid.add(erreurLogged,2,5);
    }

    public void displayProjects() throws IOException, JSONException
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[6].setVisible(true);
        panelArray[6].setManaged(true);
        //panelArray[6].removeAll();

        GridPane grid = new GridPane();

        URL url=new URL("http://localhost:8080/projects");
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        headerCheckbox.clear();

        headerCheckbox.add(new CheckBox());
        headerIdProject.add("IdProject");
        headerProject.add(new Label("Project"));
        headerCreator.add(new Label("Creator"));
        headerIdTeam.add("IdTeam");
        headerTeam.add(new Label("Team"));
        headerStatus.add(new Label("Status"));

        grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,1);
        grid.add(headerProject.get(headerProject.size()-1),2,1);
        grid.add(headerCreator.get(headerCreator.size()-1),3,1);
        grid.add(headerTeam.get(headerTeam.size()-1),4,1);
        grid.add(headerStatus.get(headerStatus.size()-1),5,1);

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        JSONArray jsonArray = new JSONArray(response.toString());
        int i;
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);

            headerCheckbox.add(new CheckBox());
            headerIdProject.add(explrObject.getString("_id"));
            headerProject.add(new Label(explrObject.getString("title")));
            headerCreator.add(new Label(this.getUserById(explrObject.getString("creator"))));
            headerIdTeam.add(explrObject.getString("equipe"));
            headerTeam.add(new Label(this.getTeamById(explrObject.getString("equipe"))));
            headerStatus.add(new Label("Status"));

            grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,2+i);
            grid.add(headerProject.get(headerProject.size()-1),2,2+i);
            grid.add(headerCreator.get(headerCreator.size()-1),3,2+i);
            grid.add(headerTeam.get(headerTeam.size()-1),4,2+i);
            grid.add(headerStatus.get(headerStatus.size()-1),5,2+i);

            for(int j = 0; j < headerCheckbox.size(); j++)
            {

                headerCheckbox.get(j).setPrefWidth(5*width/100);
                headerCheckbox.get(j).setPrefHeight(5*height/100);
                headerCheckbox.get(j).setWrapText(true);

                headerProject.get(j).setPrefWidth(15*width/100);
                headerProject.get(j).setPrefHeight(5*height/100);
                headerProject.get(j).setWrapText(true);
                headerProject.get(j).setFont(Font.font("Arial",(int)fontSize/70));

                headerCreator.get(j).setPrefWidth(15*width/100);
                headerCreator.get(j).setPrefHeight(5*height/100);
                headerCreator.get(j).setWrapText(true);
                headerCreator.get(j).setFont(Font.font("Arial",(int)fontSize/70));

                headerTeam.get(j).setPrefWidth(15*width/100);
                headerTeam.get(j).setPrefHeight(5*height/100);
                headerTeam.get(j).setWrapText(true);
                headerTeam.get(j).setFont(Font.font("Arial",(int)fontSize/70));

                headerStatus.get(j).setPrefWidth(15*width/100);
                headerStatus.get(j).setPrefHeight(5*height/100);
                headerStatus.get(j).setWrapText(true);
                headerStatus.get(j).setFont(Font.font("Arial",(int)fontSize/70));
            }
            //System.out.println(this.getUserById(creator)+" "+title+" "+this.getTeamById(equipe));
            //String[] user = {email,last_name,first_name};
            //listuser.add(user);
        }


        //GridPane grid = new GridPane();//5,2,height/100,height/100
        grid.setVgap(height/100);
        grid.setHgap(height/100);

        TextArea mytext = new TextArea("Vous devez être connecté pour avoir accès aux projets.");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));

        deleteProject = new Button("Delete project");
        selectProject = new Button("Select project");
        addProject = new Button("Add project");

        deleteProject.setFont(Font.font("Arial",(int)fontSize/65));
        deleteProject.setPrefWidth(10*width/100);
        deleteProject.setPrefHeight(7*height/100);
        deleteProject.setOnAction(new DeleteProjectListener());

        selectProject.setFont(Font.font("Arial",(int)fontSize/65));
        selectProject.setPrefWidth(10*width/100);
        selectProject.setPrefHeight(7*height/100);
        selectProject.setOnAction(new SelectProjectListener());

        addProject.setFont(Font.font("Arial",(int)fontSize/65));
        addProject.setPrefWidth(10*width/100);
        addProject.setPrefHeight(7*height/100);
        addProject.setOnAction(new GetFormAddProjectListener());

        grid.add(deleteProject,1,2+i);
        grid.add(selectProject,2,2+i);
        grid.add(addProject,3,2+i);

        if(token.length()!=0)
        {
            panelArray[6].setContent(grid);
        }
        else{
            panelArray[6].setContent(mytext);
        }

        headerCheckbox.get(0).setOnAction(new AllBoxesListener());
    }

    public String getUserById(String userId) throws IOException, JSONException
    {
        URL url=new URL("http://localhost:8080/users/"+userId);
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        JSONArray jsonArray = new JSONArray("["+response.toString()+"]");
        for (int i = 0; i < jsonArray.length(); i++)
        {
            JSONObject explrObject = jsonArray.getJSONObject(i);
            String email = explrObject.getString("email");
            return email;
        }
        return "";
    }

    public String getTeamById(String teamId)throws IOException, JSONException
    {
        URL url=new URL("http://localhost:8080/teams/"+teamId);
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", token);
        //System.out.println(token);
        con.setRequestMethod("GET");

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        JSONObject explrObject = new JSONObject(response.toString());
        String members="";
        JSONArray arrayMemberID = explrObject.getJSONArray("members");
        for (int i=0;i<arrayMemberID.length();i++)
        {
            if (i==0)
            {
                members+=this.getUserById(arrayMemberID.getString(i));
            }
            else
            {
                members+=", "+this.getUserById(arrayMemberID.getString(i));
            }
        }
        return members;
    }

    public void displayClients()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[7].setVisible(true);
        panelArray[7].setManaged(true);
        TextArea mytext = new TextArea("displayClients In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[7].setContent(mytext);
    }

    public void displayTeam()throws IOException, JSONException
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[8].setVisible(true);
        panelArray[8].setManaged(true);

        GridPane grid = new GridPane();

        URL url=new URL("http://localhost:8080/teams/"+selectedTeam);
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestProperty("Authorization", token);
        con.setRequestMethod("GET");

        headerCheckbox.clear();

        headerCheckbox.add(new CheckBox());
        headerMemberInTeam.add(new Label("Members"));
        headerRolesInTeam.add(new Label("Roles"));
        headerIdProjectInTeam.add(new Label("Project"));

        grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,1);
        grid.add(headerMemberInTeam.get(headerMemberInTeam.size()-1),2,1);
        grid.add(headerRolesInTeam.get(headerRolesInTeam.size()-1),3,1);

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        JSONArray jsonArray = new JSONArray("["+response.toString()+"]");
        int i;
        int k=0;
        for (i = 0; i < jsonArray.length(); i++) {
            JSONObject explrObject = jsonArray.getJSONObject(i);


            headerIdProjectInTeam.add(new Label(explrObject.getString("project")));
            JSONArray arrayRoleID = explrObject.getJSONArray("roleNumbers");
            JSONArray arrayMemberID = explrObject.getJSONArray("members");
            for (k=0;k<arrayMemberID.length();k++)
            {
                headerCheckbox.add(new CheckBox());
                headerRolesInTeam.add(new Label(this.getRoleById(arrayRoleID.getString(k))));
                headerMemberInTeam.add(new Label(this.getUserById(arrayMemberID.getString(k))));

                grid.add(headerCheckbox.get(headerCheckbox.size()-1),1,2+k);
                grid.add(headerMemberInTeam.get(headerMemberInTeam.size()-1),2,2+k);
                grid.add(headerRolesInTeam.get(headerRolesInTeam.size()-1),3,2+k);
            }

            for(int j = 0; j < headerCheckbox.size(); j++)
            {

                headerCheckbox.get(j).setPrefWidth(5*width/100);
                headerCheckbox.get(j).setPrefHeight(5*height/100);
                headerCheckbox.get(j).setWrapText(true);

                headerMemberInTeam.get(j).setPrefWidth(15*width/100);
                headerMemberInTeam.get(j).setPrefHeight(5*height/100);
                headerMemberInTeam.get(j).setWrapText(true);
                headerMemberInTeam.get(j).setFont(Font.font("Arial",(int)fontSize/70));

                headerRolesInTeam.get(j).setPrefWidth(15*width/100);
                headerRolesInTeam.get(j).setPrefHeight(5*height/100);
                headerRolesInTeam.get(j).setWrapText(true);
                headerRolesInTeam.get(j).setFont(Font.font("Arial",(int)fontSize/70));
            }
        }


        grid.setVgap(height/100);
        grid.setHgap(height/100);

        TextArea mytext = new TextArea("You need to select a project to have acces here.");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        mytext.setFont(Font.font("Arial",(int)fontSize/60));

        deleteMember = new Button("Delete member");
        addMember = new Button("Add member");

        deleteMember.setFont(Font.font("Arial",(int)fontSize/65));
        deleteMember.setPrefWidth(10*width/100);
        deleteMember.setPrefHeight(7*height/100);
        deleteMember.setOnAction(new DeleteMemberListener());

        addMember.setFont(Font.font("Arial",(int)fontSize/65));
        addMember.setPrefWidth(10*width/100);
        addMember.setPrefHeight(7*height/100);
        addMember.setOnAction(new GetFormAddMemberListener());

        grid.add(deleteMember,1,2+k);
        grid.add(addMember,2,2+k);

        if(token.length()!=0)
        {
            panelArray[8].setContent(grid);
        }
        else{
            panelArray[8].setContent(mytext);
        }

        headerCheckbox.get(0).setOnAction(new AllBoxesListener());
    }

    public String getRoleById(String roleId)throws IOException, JSONException
    {
        URL url=new URL("http://localhost:8080/roles/"+roleId);
        HttpURLConnection con =(HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", token);
        //System.out.println(token);
        con.setRequestMethod("GET");

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
        String line;
        while ((line = rd.readLine()) != null)
        {
            response.append(line);
            response.append('\r');
        }
        rd.close();

        JSONObject explrObject = new JSONObject(response.toString());
        String roles=explrObject.getString("title");
        return roles;
    }

    public void displayWorkspaces()
    {
        clearAllArray();
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[9].setVisible(true);
        panelArray[9].setManaged(true);
        //panelArray[9].removeAll();
        TextArea mytext = new TextArea("displayWorkspaces In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/65));
        panelArray[9].setContent(mytext);
    }

    class AllBoxesListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            if (headerCheckbox.get(0).isSelected()) {
                for (int incr = 0; incr < headerCheckbox.size(); incr++) {
                    headerCheckbox.get(incr).setSelected(true);
                }
            } else {
                for (int index = 0; index < headerCheckbox.size(); index++) {
                    headerCheckbox.get(index).setSelected(false);
                }
            }
        }
    }

    class SelectProjectListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            int nbrSelected=0;
            for (int incr = 0; incr < headerCheckbox.size(); incr++)
            {
                if (headerCheckbox.get(incr).isSelected())
                {
                    nbrSelected++;
                    selectedProjet=headerIdProject.get(incr);
                    selectedTeam=headerIdTeam.get(incr);
                }
            }
            if(nbrSelected !=1)
            {
                selectedProjet="";
                selectedTeam="";
            }
            else
            {
                displayTimer();
            }
        }
    }

    class GetFormAddProjectListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e) {
            displayCreateProject();
        }
    }

    class DeleteProjectListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e){
            for(int i=1;i<headerCheckbox.size();i++)
            {
                if(headerCheckbox.get(i).isSelected()==true){
                    try {
                        URL url=new URL("http://localhost:8080/projects/"+headerIdProject.get(i));
                        HttpURLConnection con =(HttpURLConnection) url.openConnection();
                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Accept", "application/json");
                        con.setRequestProperty("Authorization", token);
                        //System.out.println(token);
                        con.setRequestMethod("DELETE");

                        //Get Response
                        InputStream is = con.getInputStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                        String line;
                        while ((line = rd.readLine()) != null)
                        {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();
                        displayProjects();
                    }
                    catch(Exception exc)
                    {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }

    class SubmitCreationListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            String tryLogin=login.getText();
            String tryPassword=password.getText();
            String tryFirstName=firstName.getText();
            String tryLastName=lastName.getText();
            erreurLogged.setText("");
            if (tryLogin.length()==0)
            {
                erreurLogged.setText(erreurLogged.getText()+"Login is empty. ");
            }
            if (tryPassword.length()==0)
            {
                erreurLogged.setText(erreurLogged.getText()+"Password is empty. ");
            }
            if (tryFirstName.length()==0)
            {
                erreurLogged.setText(erreurLogged.getText()+"First name is empty. ");
            }
            if (tryLastName.length()==0)
            {
                erreurLogged.setText(erreurLogged.getText()+"Last name is empty. ");
            }
            System.out.println(erreurLogged.getText().length()+erreurLogged.getText());
            if(erreurLogged.getText().length() == 0)
            {
                try
                {
                    URL url=new URL("http://localhost:8080/users");
                    HttpURLConnection co =(HttpURLConnection) url.openConnection();
                    co.setRequestProperty("Content-Type", "application/json");
                    co.setRequestProperty("Accept", "application/json");
                    co.setDoOutput(true);
                    co.setRequestMethod("POST");

                    JSONObject cred   = new JSONObject();
                    cred.put("email",tryLogin);
                    cred.put("password", tryPassword);
                    cred.put("first_name",tryFirstName);
                    cred.put("last_name", tryLastName);
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
//	       		 		System.out.println("" + sb.toString());
                        //submit.getParent().setVisible(false);
                        //submit.getParent().setManaged(false);
                    }
                    else
                    {
                        if (HttpResult == 403)
                        {
                            erreurLogged.setText("Cet utilisateur existe déjà.");
                        }

                        System.out.println(co.getResponseMessage());
                    }
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
            }
        }
    }

    class SubmitFormAddProjectListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            String tryTitre=titre.getText();
            erreurLogged.setText("");
            if (tryTitre.length()==0)
            {
                erreurLogged.setText(erreurLogged.getText()+"Last name is empty. ");
            }
            System.out.println(erreurLogged.getText().length()+erreurLogged.getText());
            if(erreurLogged.getText().length() == 0)
            {
                try
                {
                    URL url=new URL("http://localhost:8080/projects");
                    HttpURLConnection co =(HttpURLConnection) url.openConnection();
                    co.setRequestProperty("Content-Type", "application/json");
                    co.setRequestProperty("Accept", "application/json");
                    co.setRequestProperty("Authorization", token);
                    co.setDoOutput(true);
                    co.setRequestMethod("POST");

                    JSONObject cred   = new JSONObject();
                    cred.put("title",tryTitre);
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
                    }
                    else
                    {
                        if (HttpResult == 403)
                        {
                            erreurLogged.setText("Name already taken.");
                        }

                        System.out.println(co.getResponseMessage());
                    }
                    displayProjects();
                }
                catch(Exception exc)
                {
                    exc.printStackTrace();
                }
            }
        }
    }

    class GetFormAddMemberListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e) {
            try {
                displayAddMember();
            } catch (IOException e1) {
                e1.printStackTrace();
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    class DeleteMemberListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e){
            for(int i=1;i<headerCheckbox.size();i++)
            {
                if(headerCheckbox.get(i).isSelected()==true){
                    try {
                        URL url=new URL("http://localhost:8080/projects/"+headerIdProject.get(i));
                        HttpURLConnection con =(HttpURLConnection) url.openConnection();
                        con.setRequestProperty("Content-Type", "application/json");
                        con.setRequestProperty("Accept", "application/json");
                        con.setRequestProperty("Authorization", token);
                        //System.out.println(token);
                        con.setRequestMethod("DELETE");

                        //Get Response
                        InputStream is = con.getInputStream();
                        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
                        StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
                        String line;
                        while ((line = rd.readLine()) != null)
                        {
                            response.append(line);
                            response.append('\r');
                        }
                        rd.close();
                        displayTeam();
                    }
                    catch(Exception exc)
                    {
                        exc.printStackTrace();
                    }
                }
            }
        }
    }

    class SubmitFormAddMemberListener implements EventHandler<ActionEvent>
    {
        @Override
        public void handle(ActionEvent e)
        {
            System.out.println("checkbox size "+headerCheckbox.size());
            for(int i=1;i<headerCheckbox.size();i++) {
                System.out.println("i="+i+"; headerCheckbox.size()="+headerCheckbox.size());
                if (headerCheckbox.get(i).isSelected() == true) {
                    System.out.println("checkbox "+i+" checked");
                    try {
                        URL url = new URL("http://localhost:8080/teams/"+selectedTeam+"/"+headerIdMember.get(i));
                        HttpURLConnection co = (HttpURLConnection) url.openConnection();
                        co.setRequestProperty("Content-Type", "application/json");
                        co.setRequestProperty("Accept", "application/json");
                        co.setRequestProperty("Authorization", token);
                        co.setDoOutput(true);
                        co.setRequestMethod("POST");

                        StringBuilder sb = new StringBuilder();
                        int HttpResult = co.getResponseCode();
                        if (HttpResult == HttpURLConnection.HTTP_OK) {
                            BufferedReader br = new BufferedReader(new InputStreamReader(co.getInputStream(), "utf-8"));
                            String line = null;
                            while ((line = br.readLine()) != null) {
                                sb.append(line + "\n");
                            }
                            br.close();
                        } else {
                            if (HttpResult == 403) {
                                erreurLogged.setText("Name already taken.");
                            }

                            System.out.println(co.getResponseMessage());
                        }
                        displayTeam();
                    } catch (Exception exc) {
                        exc.printStackTrace();
                    }
                }
                else
                {
                    System.out.println("checkbox "+i+" not checked");
                }
            }
        }
    }
}

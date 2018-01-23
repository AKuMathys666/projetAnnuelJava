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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

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
    private Label erreurLogged;

    public Pane[] panelArray= new Pane[10];

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

        for (int i=0;i<10;i++)
        {
            panelArray[i]= new Pane();
            panelArray[i].setPrefWidth((88*width/100)-(3*height/100));
            panelArray[i].setPrefHeight(81*height/100);
            this.getChildren().add(panelArray[i]);
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
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
        panelArray[0].getChildren().add(grid);

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
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[1].setVisible(true);
        panelArray[1].setManaged(true);
        //panelArray[1].removeAll();
        TextArea mytext = new TextArea("displayTimer In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[1].getChildren().add(mytext);
    }

    public void displayDashboard()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[2].setVisible(true);
        panelArray[2].setManaged(true);
        //panelArray[2].removeAll();
        TextArea mytext = new TextArea("displayDashboard In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[2].getChildren().add(mytext);
    }

    public void displayReports()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[3].setVisible(true);
        panelArray[3].setManaged(true);
        //panelArray[3].removeAll();
        TextArea mytext = new TextArea("displayReports In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[3].getChildren().add(mytext);
    }

    public void displayInsights()
    {
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
        panelArray[4].getChildren().add(mytext);
    }

    public void displaySavedReports()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[5].setVisible(true);
        panelArray[5].setManaged(true);
        //panelArray[5].removeAll();
        TextArea mytext = new TextArea("displaySavedReports In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[5].getChildren().add(mytext);
    }

    public void displayProjects() throws IOException, JSONException
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[6].setVisible(true);
        panelArray[6].setManaged(true);
        //panelArray[6].removeAll();
        TextArea mytext = new TextArea("displayProjects In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[6].getChildren().add(mytext);

//		JButton projects = new JButton("Create Projects");
//		projects.addActionListener(new CreareProjectsListener());
//		projects.setOpaque(false);
//		projects.setContentAreaFilled(false);
//		projects.setPreferredSize(new Dimension((12*width/100)-(4*height/100),7*height/100));
//		projects.setFont(new Font("Arial", Font.PLAIN, (int)fontSize/60));
//		projects.setFocusPainted(false);
//		projects.setMargin(new Insets(1,1,1,1));
//		panelArray[6].add(projects);
//		URL urll=new URL("http://localhost:8080/projects");
//		HttpURLConnection con =(HttpURLConnection) urll.openConnection();
//		con.setRequestMethod("GET");
//
//		//Get Response
//		InputStream is = con.getInputStream();
//		BufferedReader rd = new BufferedReader(new InputStreamReader(is));
//		StringBuilder response = new StringBuilder(); // or StringBuffer if Java version 5+
//		String line;
//		while ((line = rd.readLine()) != null)
//		{
//			response.append(line);
//			response.append('\r');
//		}
//		rd.close();
//		JSONArray jsonArray = new JSONArray(response.toString());
//
//		JPanel panelProject = new JPanel();
//		panelProject.setPreferredSize(new Dimension((50*width/100)-(2*height/100),5*jsonArray.length()*height/100));
//		GridLayout grid = new GridLayout(jsonArray.length(),5,height/100,height/100);
//		panelProject.setLayout(grid);
//
//		for (int i = 0; i < jsonArray.length(); i++)
//		{
//			JSONObject explrObject = jsonArray.getJSONObject(i);
//			String team=explrObject.getString("team");
//			String creator=explrObject.getString("creator");
//			String title=explrObject.getString("title");
//			String task=explrObject.getString("task");
//			panelProject.add(new JTextArea(team));
//			panelProject.add(new JTextArea(creator));
//			panelProject.add(new JTextArea(title));
//			panelProject.add(new JTextArea(task));
//		}
//
//		panelArray[6].add(panelProject);
    }

    public void displayClients()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[7].setVisible(true);
        panelArray[7].setManaged(true);
        //panelArray[7].removeAll();
        TextArea mytext = new TextArea("displayClients In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[7].getChildren().add(mytext);
    }

    public void displayTeam()
    {
        for (int i=0;i<10;i++)
        {
            panelArray[i].setVisible(false);
            panelArray[i].setManaged(false);
        }
        panelArray[8].setVisible(true);
        panelArray[8].setManaged(true);
        //panelArray[8].removeAll();
        TextArea mytext = new TextArea("displayTeam In progress");
        mytext.setPrefWidth(25*width/100);
        mytext.setPrefHeight(5*height/100);
        //mytext.setMargin(new Insets(0,height/100,0,0));
        //mytext.setLineWrap(true);
        //mytext.setWrapStyleWord(true);
        //mytext.setBackground(new Color(222,222,222));
        mytext.setFont(Font.font("Arial",(int)fontSize/60));
        panelArray[8].getChildren().add(mytext);
    }

    public void displayWorkspaces()
    {
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
        panelArray[9].getChildren().add(mytext);
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
                        submit.getParent().setVisible(false); //not working, WHY?
                        submit.getParent().setManaged(false);
                    }
                    else
                    {
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
}

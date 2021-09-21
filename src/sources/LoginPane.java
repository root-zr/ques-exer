package sources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class LoginPane {
	private Label label= null;
	private TextField tfId= null;
	private PasswordField tfPassword= null;
	RadioButton btS= null;
	RadioButton btT= null;
	private Statement s= null;
	private Stage primaryStage = null;
	
	
	public LoginPane(Statement s,Stage primaryStage) {
		this.s=s;
		this.primaryStage=primaryStage;
	}
	
	
	public VBox getPane() {
		VBox pane = new VBox();
		//添加背景图片
		BackgroundImage background=new BackgroundImage(new Image("img/background1.jpg"),BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT,BackgroundPosition.DEFAULT,BackgroundSize.DEFAULT);
		pane.setBackground(new Background(background));
		
		
		HBox hBox1 = new HBox();
		label = new Label("欢迎使用题库管理系统");
		label.setStyle("-fx-font-size:40");
		label.setPrefSize(400, 70);
		label.setAlignment(Pos.CENTER);
		hBox1.getChildren().add(label);
		hBox1.setAlignment(Pos.CENTER);

		GridPane pane0 = new GridPane();
		pane0.setPadding(new Insets(15));
		pane0.setHgap(10);
		pane0.setVgap(10);

		tfId = new TextField();
		tfId.setPrefWidth(340);
		tfPassword = new PasswordField();
		tfPassword.setPrefWidth(340);
		Label labelId = new Label("账号：");
		Label labelPassword = new Label("密码：");
		pane0.add(labelId, 0, 0);
		pane0.add(labelPassword, 0, 1);
		pane0.add(tfId, 1, 0);
		pane0.add(tfPassword, 1, 1);
		pane0.setAlignment(Pos.CENTER);

		HBox hBox2 = new HBox(40);
		Button btOn = new Button("登录");
		btOn.setOnAction(e -> on());
		Button btRe = new Button("注册");
		btRe.setOnAction(e -> register());
		hBox2.setAlignment(Pos.CENTER);
		hBox2.setPadding(new Insets(15, 0, 30, 0));
		hBox2.getChildren().addAll(btOn, btRe);
		
		HBox hBox3=new HBox(40);
		hBox3.setPadding(new Insets(10));
		ToggleGroup group=new ToggleGroup();
		
		//创建单选框区分用户和管理员，默认选中用户
		btS=new RadioButton("用户");
		btT=new RadioButton("管理员");
		btS.setToggleGroup(group);
		btT.setToggleGroup(group);
		btS.setUserData("userPassword");
		btT.setUserData("managePassword");
		btS.setSelected(true);
		hBox3.getChildren().addAll(btS,btT);
		hBox3.setAlignment(Pos.CENTER);
		
		pane.getChildren().addAll(hBox1, pane0, hBox3,hBox2);
		pane.setPadding(new Insets(10, 20, 10, 20));
		pane.setAlignment(Pos.CENTER);
		pane.setStyle("-fx-font-size:23");
		return pane;
	}
	
    //登录功能实现
	public void on() {
		
		String table= btS.isSelected() ? (String) btS.getUserData() : (String)btT.getUserData();
		try {
			
			
			ResultSet rs = s.executeQuery("select * from "+ table+" where Id='"+tfId.getText().trim()
			+"' and Password ='"+tfPassword.getText().trim()+"'");
			
			
			if (rs.next()) {	
				label.setText("登录成功！");
				primaryStage.close();
				//根据单选按钮判断是用户还是管理员登录
				if(btS.isSelected()) { //用户
	                UserGui ug = new UserGui(tfId.getText());
					Stage stage = new Stage();
					try {
						ug.start(stage);
					} catch (Exception e) {
						
						e.printStackTrace();
					}
				}
				
				else { 
					ManageGui mg = new ManageGui(tfId.getText());
					Stage stage = new Stage();
					try {
						mg.start(stage);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}else {
				label.setText("登录失败！");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//实现注册功能
	public void register() {
		
		String table= btS.isSelected() ? (String) btS.getUserData() : (String)btT.getUserData();
		
		String r = "insert into "+ table+" values('" + tfId.getText() 
		+ "','" + tfPassword.getText() +"')";
		try {
			ResultSet rs = s.executeQuery("select Id from "+table+" where Id ='"+tfId.getText()+"'");
			//判断之前是否已经注册
			if(rs.next()) {
				label.setText("此用户已经存在");
			}else {
				s.executeUpdate(r);
				label.setText("注册成功");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
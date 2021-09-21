package sources;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.*;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ManageGui extends Application {

	TextArea ta;
	Statement s = Startup.s;
	String id;
	int currentPro = 1; // 表示当前显示的是第几题
	String answer = " "; //题目的答案
	
	TextField taA;
	TextField taB;
	TextField taC;
	TextField taD;  //表示题目的四个选项
	
	Label labAlert = new Label();

	public ManageGui(String id) {
		this.id = id;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane pane = new BorderPane();

		// 添加背景图片
		BackgroundImage background = new BackgroundImage(new Image("img/background1.jpg"), BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		pane.setBackground(new Background(background));

		ta = new TextArea();
		ScrollPane sp = new ScrollPane(ta);

		TextField tf = new TextField();
		tf.setPrefHeight(20);
		tf.setPrefWidth(400);

		pane.setTop(getTop());
		pane.setCenter(sp);
		pane.setBottom(getBottemPane());

		showText(currentPro);

		Scene scene = new Scene(pane, 600, 450);

		primaryStage.setTitle("管理员登录");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
     
	
	//显示题目选项的pane
		private BorderPane getBottemPane() {
			
			BorderPane bgBorderPane = new BorderPane();
			
			//分别创建A,B,C,D选项的标签及其文本显示区域
			Label lbA = new Label("A:  ");
			taA= new TextField();
			taA.setPrefWidth(500);
			HBox hBox1 = new HBox();
			hBox1.getChildren().addAll(lbA,taA);
			hBox1.setAlignment(Pos.CENTER_LEFT);
			
			Label lbB = new Label("B:  ");
			taB = new TextField();
			taB.setPrefWidth(500);
			HBox hBox2 = new HBox();
			hBox2.getChildren().addAll(lbB,taB);
			hBox2.setAlignment(Pos.CENTER_LEFT);
			
			Label lbC = new Label("C:  ");
			taC = new TextField();
			taC.setPrefWidth(500);
			HBox hBox3 = new HBox();
			hBox3.getChildren().addAll(lbC,taC);
			hBox3.setAlignment(Pos.CENTER_LEFT);
			
			Label lbD = new Label("D:  ");
			taD = new TextField();
			taD.setPrefWidth(500);
			HBox hBox4 = new HBox();
			hBox4.getChildren().addAll(lbD,taD);
			hBox4.setAlignment(Pos.CENTER_LEFT);
			
			VBox vb = new VBox(10);
			vb.getChildren().addAll(hBox1,hBox2,hBox3,hBox4);
			
			bgBorderPane.setCenter(vb);
			bgBorderPane.setBottom(getBottom());
			return bgBorderPane;
			
		}
		
	// 在gui上显示题目
	private void showText(int num) throws SQLException {
		int count = 0;
		ResultSet rsPro1 = s.executeQuery("select * from java ");
		while (rsPro1.next()) {
			count++;
		}

		ResultSet rsPro2 = s.executeQuery("select * from java ");
		if (num <= count) { // 要查询的题目所在位置小于总的题目数
			for (int i = 0; i < num; i++) {
				rsPro2.next();
			}
			ta.setText(rsPro2.getString(2));
			
			taA.setText(rsPro2.getString(4));
			taB.setText(rsPro2.getString(5));
			taC.setText(rsPro2.getString(6));
			taD.setText(rsPro2.getString(7));
		}
	}

	// 查找题目对应的答案
	private String showAnswer(int num) throws SQLException {
		int count = 0;
		ResultSet rsPro1 = s.executeQuery("select answer from java ");
		while (rsPro1.next()) {
			count++;
		}

		ResultSet rsPro2 = s.executeQuery("select answer from java ");
		if (num <= count) {
			for (int i = 0; i < num; i++) {
				rsPro2.next();
			}
			return rsPro2.getString(1).trim();
		}

		return "";
	}
    
	//返回题目的总数
	public int getCount() throws SQLException {
		int count = 0;
		ResultSet rsPro1 = s.executeQuery("select * from java ");
		while (rsPro1.next()) {
			count++;
		}
		
		return count;
	}
	// 创建头像，签名等功能
	private HBox getTop() throws SQLException {
		HBox hbox = new HBox();

		Image image = new Image("img/headPortrait.jpg");
		ImageView imageView = new ImageView(image);
		imageView.setFitHeight(50);
		imageView.setFitWidth(50);

		Label label = new Label();
		label.setText("欢迎回来：" + id);
		label.setFont(new Font(20));

		hbox.getChildren().addAll(imageView, label);
		return hbox;
	}

	private HBox getBottom() {
        Label lb = new Label();
        
		Button bt1 = new Button("上一题");
		bt1.setPrefHeight(20);
		bt1.setPrefWidth(100);
		bt1.setStyle("  -fx-background-color:blue;");
		bt1.setOnAction(e -> {
			try {
				showText(--currentPro);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		Button bt2 = new Button("下一题");
		bt2.setPrefHeight(20);
		bt2.setPrefWidth(100);
		bt2.setStyle("  -fx-background-color:blue;");
		bt2.setOnAction(e -> {
			try {
				showText(++currentPro);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
        
		Button bt3 = new Button("修改题目");
		bt3.setPrefHeight(20);
		bt3.setPrefWidth(100);
		bt3.setStyle("  -fx-background-color:blue;");
		bt3.setOnAction(e -> {
			 
			String update= " UPDATE java SET  problem = '" + ta.getText() + "' WHERE id = '" + currentPro + "';";
			try {
				s.execute(update);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			labAlert.setText("修改成功");
			getAlert();
		});
		
		Button bt4 = new Button("增加题目");
		bt4.setPrefHeight(20);
		bt4.setPrefWidth(100);
		bt4.setStyle("  -fx-background-color:blue;");
		bt4.setOnAction(e -> {
			
			String str = getNewPane();
			String insert = " ";
			try {
				insert = " INSERT INTO java VALUES ('" + (getCount() + 1) + "' ,'" + ta.getText() + "',' " + 
			      str + "','"  +  taA.getText() + "','"+ taB.getText() + "','" + taC.getText()+ "','" + taD.getText() + "');";
				s.execute(insert);
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			

		});
		
		Button bt5 = new Button("删除题目");
		bt5.setPrefHeight(20);
		bt5.setPrefWidth(100);
		bt5.setStyle("  -fx-background-color:blue;");
		bt5.setOnAction(e -> {
			String delete = " DELETE FROM java WHERE id = '" + currentPro + "';";
			try {
				s.execute(delete);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
			labAlert.setText("删除成功");
			getAlert();
		});
		
		HBox hb = new HBox(30);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.getChildren().addAll(bt1, bt2,bt3,bt4,bt5);
		return hb;
	}
    
	// 设置弹出框
	public void getAlert() {
		BorderPane pane = new BorderPane();
		
		labAlert.setAlignment(Pos.CENTER);
		Button bt1 = new Button("下一题");
		bt1.setStyle("  -fx-background-color:blue;");
		Button bt2 = new Button("返回");
		bt2.setStyle("  -fx-background-color:red;");
		HBox hb = new HBox(40);
		hb.getChildren().addAll(bt1, bt2);
		hb.setAlignment(Pos.CENTER);

		pane.setTop(labAlert);
		pane.setCenter(hb);

		Scene scene = new Scene(pane, 300, 70);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

		bt1.setOnAction(e -> {
			try {
				showText(++currentPro);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			stage.close();
		});

		bt2.setOnAction(e -> {
			stage.close();
		});
	}
	
	public String getNewPane() {
	
		BorderPane pane = new BorderPane();
		Label lab = new Label();
		lab.setAlignment(Pos.CENTER);
		lab.setText("请输入题目的答案:");
        
		
		TextField tf = new TextField();
		tf.setPrefHeight(20);
		tf.setPrefWidth(300);
		Button bt = new Button("确定");
		bt.setPrefHeight(20);
		bt.setPrefWidth(100);
		bt.setStyle("  -fx-background-color:blue;");
		
		HBox hb = new HBox(40);
		hb.getChildren().addAll(tf, bt);
		hb.setAlignment(Pos.CENTER);
    
		pane.setTop(lab);
		pane.setCenter(hb);
		Scene scene = new Scene(pane, 500, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

		bt.setOnAction(e -> {
			stage.close();
			answer = tf.getText().trim();	
		});
		
		return answer;	
	}

}

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

public class UserGui extends Application {

	TextArea ta;
	Statement s = Startup.s;
	String id;
	int currentPro = 1; // 表示当前显示的是第几题
	
	TextField taA;
	TextField taB;
	TextField taC;
	TextField taD;  //表示题目的四个选项

	public UserGui(String id) {
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
		pane.setRight(getRight());

		showText(currentPro);

		Scene scene = new Scene(pane, 600, 450);

		primaryStage.setTitle("用户登录");
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
		if (num <= count && num >0) { // 要查询的题目所在位置小于总的题目数
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

		HBox hb = new HBox(50);
		hb.setAlignment(Pos.BASELINE_CENTER);
		hb.getChildren().addAll(bt1, bt2);
		return hb;
	}

	// 创建右边面板
	private VBox getRight() {
		VBox vb = new VBox(10);

		Button bta = new Button("A");
		bta.setPrefHeight(50);
		bta.setPrefWidth(50);
		bta.setOnAction(e -> {
			try {
				if (bta.getText().equals(showAnswer(currentPro))) {
					getNewPane(true);
				}

				else {
					getNewPane(false);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		Button btb = new Button("B");
		btb.setPrefHeight(50);
		btb.setPrefWidth(50);
		btb.setOnAction(e -> {
			try {
				if (btb.getText().equals(showAnswer(currentPro))) {
					getNewPane(true);
				}

				else {
					getNewPane(false);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		Button btc = new Button("C");
		btc.setPrefHeight(50);
		btc.setPrefWidth(50);
		btc.setOnAction(e -> {
			try {
				if (btc.getText().equals(showAnswer(currentPro))) {
					getNewPane(true);
				}

				else {
					getNewPane(false);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		Button btd = new Button("D");
		btd.setPrefHeight(50);
		btd.setPrefWidth(50);
		btd.setOnAction(e -> {
			try {
				if (btd.getText().equals(showAnswer(currentPro)) ) {
					getNewPane(true);
				}

				else {
					getNewPane(false);
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});
		
		vb.getChildren().addAll(bta, btb, btc, btd);
		return vb;
	}

	public void getNewPane(boolean flag) {
		BorderPane pane = new BorderPane();
		Label lab = new Label();
		lab.setAlignment(Pos.CENTER);
		if (flag) {
			lab.setText("恭喜你答对啦！");
		}

		else {
			lab.setText("很遗憾答错了！");
		}

		Button bt1 = new Button("继续答题");
		bt1.setStyle("  -fx-background-color:blue;");
		Button bt2 = new Button("返回");
		bt2.setStyle("  -fx-background-color:red;");
		HBox hb = new HBox(40);
		hb.getChildren().addAll(bt1, bt2);
		hb.setAlignment(Pos.CENTER);

		pane.setTop(lab);
		pane.setCenter(hb);

		Scene scene = new Scene(pane, 400, 100);
		Stage stage = new Stage();
		stage.setScene(scene);
		stage.show();

		bt1.setOnAction(e -> {
			stage.close();
			try {
				showText(++currentPro);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		});

		bt2.setOnAction(e -> {
			stage.close();
		});
	}

}
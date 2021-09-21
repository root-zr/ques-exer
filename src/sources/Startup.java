package sources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Startup extends Application {

	// MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
    static final String DB_URL = "jdbc:mysql://localhost:3306/question";
    
    // 数据库的用户名与密码
    static final String USER = "root";
    static final String PASS = "123456";
    
    static Connection conn = null;
    static Statement s = null;
	
	public void start(Stage primaryStage) {
		LoginPane gt = new LoginPane(s, primaryStage);
		Scene scene = new Scene(gt.getPane(), 680, 420);
		primaryStage.setResizable(false);
		primaryStage.setTitle("题库管理系统");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		 
		try {

			// 注册 JDBC 驱动
            Class.forName(JDBC_DRIVER);

		} catch (java.lang.ClassNotFoundException e) {
			System.out.println("ForName :" + e.getMessage());
		}

		try {
			// 打开链接
            System.out.println("连接数据库...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
			s = conn.createStatement();
		} catch (SQLException e) {
			System.out.println("SQLException:" + e.getMessage());
		}
       
		Application.launch(args);
	}
}

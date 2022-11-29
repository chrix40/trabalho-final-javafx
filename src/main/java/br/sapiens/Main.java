package br.sapiens;



import br.sapiens.configs.ConnectionSingleton;
import br.sapiens.configs.CriaEntidades;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.SQLOutput;
import java.sql.Statement;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException, SQLException {
        ConnectionSingleton conn = new ConnectionSingleton();
        Connection connect = conn.getConn();
        System.out.println(connect);
        CriaEntidades enti = new CriaEntidades(connect);
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/layout/main.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 700);
        stage.setTitle("Index");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws SQLException{
        launch();
    }

}
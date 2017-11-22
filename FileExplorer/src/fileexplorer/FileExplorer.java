/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fileexplorer;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.stage.Stage;

/**
 *
 * @author TIS
 */
public class FileExplorer extends Application {
    
    Stage window;
    TreeView<String> tree;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        
        
        Parent root_ = FXMLLoader.load(getClass().getResource("treeView.fxml"));
        Scene scene;
        scene=new Scene(root_);
        primaryStage.setTitle("File Explorar");
        primaryStage.setScene(scene);
        primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    
    
}

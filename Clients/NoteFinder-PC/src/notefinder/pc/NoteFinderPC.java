/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notefinder.pc;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author jasper
 */
public class NoteFinderPC extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        
        Scene scene = new Scene(new VBox(), 800, 600);
        scene.setFill(Color.OLDLACE);
        
        MenuBar menuBar = new MenuBar();
        
        Menu mf = new Menu("Import +");
        Menu mfo = new Menu("Open image file");
        Menu mfs = new Menu("Scan image");
        mf.getItems().addAll(mfo, mfs);
        
        Menu me = new Menu("Export +");
        Menu me3 = new Menu("To mp3");
        Menu mem = new Menu("To midi");
        me.getItems().addAll(me3, mem);
        
        
        
        Menu ms = new Menu("Settings");
        
        menuBar.getMenus().addAll(mf, me, ms);
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
        
        primaryStage.setTitle("Hello World!");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notefinder.pc;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

/**
 *
 * @author jasper
 */
public class Programma extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Thread mp3Thread;
        Scene scene = new Scene(new VBox(), 800, 600);
        
        MenuBar menuBar = new MenuBar();
        
        Menu mf = new Menu("Import", new ImageView(new Image("img/camera104.png")));
        MenuItem mfo = new MenuItem("Open image file");
        MenuItem mfs = new MenuItem("Scan image");
        mf.getItems().addAll(mfo, mfs);
        
        Menu me = new Menu("Export", new ImageView(new Image("img/note54.png")));
        MenuItem me3 = new MenuItem("To mp3");
        MenuItem mem = new MenuItem("To midi");
        me.getItems().addAll(me3, mem);
        
        Menu ms = new Menu("Settings", new ImageView(new Image("img/repair17.png")));
        
        menuBar.getMenus().addAll(mf, me, ms);
        
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event)
            {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        
                    }
                });
                        
                
            }
        });
        
        gc(scene).addAll(menuBar);
        gc(scene).add(btn);
        
        primaryStage.setTitle("Play mp3");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
    
    private ObservableList<Node> gc (Scene scene)
    {
        return ((VBox) scene.getRoot()).getChildren();
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

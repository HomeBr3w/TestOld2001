package notefinder.pc;

import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebView;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

/**
 *
 * @author jasper
 */
public class NoteFinderPC extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        showSplash(primaryStage);
    }
    
    private Pane splashLayout;
    private ProgressBar loadProgress;
    private Label progressText;
    private WebView webView;
    private Stage mainStage;
    private static final int SPLASH_WIDTH = 400;
    private static final int SPLASH_HEIGHT = 400; 
    
    @Override public void init() {
        ImageView splash = new ImageView(new Image("img/LogoSmall.png"));
        loadProgress = new ProgressBar();
        loadProgress.setPrefWidth(SPLASH_WIDTH);
        progressText = new Label("Loading hobbits with pie . . .");
        splashLayout = new VBox();
        splashLayout.getChildren().addAll(splash, loadProgress, progressText);
        progressText.setAlignment(Pos.CENTER);
        splashLayout.setEffect(new DropShadow());
    } 
    
    private void showMainStage() {
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
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        ((VBox) scene.getRoot()).getChildren().addAll(menuBar);
    }
    
    private void hideSplash(Stage initStage)
    {
         if (initStage.isShowing()) {
            loadProgress.progressProperty().unbind();
            loadProgress.setProgress(1);
            progressText.setText("All hobbits are full.");
            initStage.setIconified(false);
            initStage.toFront();
            FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
            fadeSplash.setFromValue(1.0);
            fadeSplash.setToValue(0.0);
            fadeSplash.setOnFinished(new EventHandler<ActionEvent>() {
                @Override public void handle(ActionEvent actionEvent) {
                    initStage.hide();
                    showMainStage();
                }
            });
            fadeSplash.play();
        } 
    }

    private void showSplash(Stage initStage) {
        Scene splashScene = new Scene(splashLayout);
        initStage.initStyle(StageStyle.TRANSPARENT);
        final Rectangle2D bounds = Screen.getPrimary().getBounds();
        initStage.setScene(splashScene);
        initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
        initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
        initStage.setTitle("NoteFinder");
        initStage.show();
        
        new java.util.Timer().schedule( 
            new java.util.TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            hideSplash(initStage);
                        }
                   });
                }
            }, 
            5000 
        );
    } 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

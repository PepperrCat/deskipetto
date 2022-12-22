package application;


import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;

public class PianoFrame {
    /*
     * 弹琴游戏类
    */
    private static ImageView imageView;
    private static Button button;
    private static PianoFrame piano;
    private AnchorPane pane;
    public static PianoFrame getInstance(AnchorPane pane) {
        if(piano == null){
            piano = new PianoFrame(pane);
        }
        return piano;
    }
    PianoFrame(AnchorPane pane) {
        Image piano=new Image(ResourcesImage.class.getResource("/"+"Piano.png").toExternalForm());
        imageView = new ImageView(piano);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(200);
        imageView.setFitHeight(200);
        imageView.setVisible(false);
        button=new Button();
        this.pane=pane;
        button.setLayoutX(50);
        button.setLayoutY(-100);
        pane.getChildren().add(imageView);
        pane.getChildren().add(button);
    }
    public void start() {
        imageView.setVisible(true);
        button.setVisible (true);
        button.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                String key = event.getCode().getName();
                System.out.println(key);
                if (key.equals(KeyCode.A.getName()))
                {
                    //String path=PianoFrame.class.getResource("/"+"music_piano"+"/"+"piano_sound_do.wav").toExternalForm();
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_do.wav");
                    ppt.start();
                }

                else if (key.equals(KeyCode.S.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_re.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.D.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_mi.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.F.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_fa.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.G.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_sol.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.H.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_la.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.J.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_xi.wav");
                    ppt.start();
                }
                else if (key.equals(KeyCode.K.getName())) {
                    PianoPlayThread ppt = new PianoPlayThread("./music_piano/piano_sound_do2.wav");
                    ppt.start();
                }
                else if(key.equals(KeyCode.ENTER.getName())) {
                    stop();
                }
                else if(key.equals(KeyCode.ESCAPE.getName())) {
                    stop();
                }
            }
        });
    }
    public void stop() {
        imageView.setVisible(false);
        button.setVisible(false);
    }
}
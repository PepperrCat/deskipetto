package application;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;


public class MenuBar {
    private ImageView imageView;
    private EventListener listen;
    private Stage primaryStage;
    private AnchorPane pane;
    private boolean flag;
    private static VBox menuBox;
    public MenuBar(ImageView imageView,EventListener listen,AnchorPane pane) {
        this.imageView = imageView;
        this.listen = listen;
        this.pane = pane;
        menuBox=new VBox();
        flag=false;
        pane.getChildren().add(menuBox);
        menuBox.setLayoutX(165);
        menuBox.setLayoutY(50);
        menuBox.setSpacing(10);
        menuBox.setVisible(false);
        Button b1=new Button("Skin resurfacing");
        Button b2=new Button("Conversation");
        Button b3=new Button("Feeding");
        Button b4=new Button("Wait for the addition");
        menuBox.getChildren().addAll(b1,b2,b3,b4);
    }
    public void change(){
        //如果flag=false，打开菜单，否则关闭菜单
        if(flag) menuBox.setVisible(false);
        else menuBox.setVisible(true);
        flag =!flag;
    }
}

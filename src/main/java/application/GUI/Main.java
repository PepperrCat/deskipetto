package application.GUI;

import application.Listener.EventListener;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * the class that initial the stage
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-18 18:11:05
 **/
public class Main extends Application {
    private static ImageView imageView;
    EventListener listen;
    static Stage primaryStage;
    private static String petName = "002_amiya";
    private static String petSkin = "default";
    double xOffset = 0;
    double yOffset = 0;
    private static UI ui;
    private static MenuBar menuBar;

    public void start(Stage Stage) {
        try {
            /*
             * 创建初始的图
             * 加载相对路径的图片要用class.getResource，不然运行jar包时会报错：找不到文件路径！
             * 路径中第一个“/”是必需的，它表示类的根目录，类文件夹在此项目中与lxh和biu在同一级
             */
            primaryStage = new Stage();

            imageView = new ImageView();
            imageView.setX(0);
            imageView.setY(0);
            imageView.setLayoutX(-300);
            imageView.setLayoutY(-100);
            //设置图片显示的大小
//	      	imageView.setFitHeight(1000);
            imageView.setFitWidth(800);
            //添加图片的点击事件
            listen = new EventListener(imageView);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, listen);

            imageView.setPreserveRatio(true); //保留 width：height的比例
            imageView.setStyle("-fx-background:transparent;");//容器背景设为透明

            ui = new UI(imageView, listen, primaryStage);
            ui.addMessageBox("博士，欢迎回来!");
            ui.setMsg(ui.getDialogAnalysis().getDialog(42));
            imageView.setImage(ui.getImage("Relax"));

            AnchorPane pane = new AnchorPane(ui.getMessageBox(), ui.getImageView());
            menuBar = new MenuBar(imageView, listen, pane);
            pane.setStyle("-fx-background:transparent;");
            //使窗体能拖动。先获取按下鼠标时的坐标p1，再将窗体坐标设为p1加拖动的位移量
            pane.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });
            pane.setOnMouseDragged(event -> {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            });




            Scene scene = new Scene(pane, 400, 400);
            scene.setFill(null);
            scene.getStylesheets().add(getClass().getResource("/application.css").toExternalForm());

            primaryStage.setScene(scene);
            //设置窗体的初始位置
            primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());

            primaryStage.setY(Screen.getPrimary().getVisualBounds().getMaxY() - 200);
            primaryStage.setAlwaysOnTop(true);//窗口总显示在最前
            //修改任务栏图标
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));

            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.initStyle(StageStyle.TRANSPARENT);//背景透明
            /*
             * 点击任务栏的“关闭窗口”时，播放告别动画，同时使托盘的图标也关闭.
             * event.consume()是必需的，这样才能真正阻止Window Close事件的默认处理。
             * 如果仅仅使用System.exit(0);则不需要event.consume();
             */
//			primaryStage.setOnCloseRequest( event ->{event.consume(); ui.end();});
            removeTaskbar(primaryStage);
            primaryStage.show();

            ui.setTray(primaryStage);//添加系统托盘
            Thread thread = new Thread(ui);
            thread.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static void removeTaskbar(Stage stage) {
        final Stage transparentStage = new Stage();
        transparentStage.initStyle(StageStyle.UTILITY);
        transparentStage.setOpacity(0);
        transparentStage.show();
        stage.initOwner(transparentStage);
    }
    public static ImageView getImageView(){return imageView;}
    public static String getPetName() {
        return petName;
    }

    public static void setPetName(String petName) {
        Main.petName = petName;
    }

    public static String getPetSkin() {
        return petSkin;
    }

    public static void setPetSkin(String petSkin) {
        Main.petSkin = petSkin;
    }

    public static MenuBar getMenuBar() {
        return menuBar;
    }

    public static UI getUi() {
        return ui;
    }
}

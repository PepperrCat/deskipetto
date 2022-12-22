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
             * ������ʼ��ͼ
             * �������·����ͼƬҪ��class.getResource����Ȼ����jar��ʱ�ᱨ���Ҳ����ļ�·����
             * ·���е�һ����/���Ǳ���ģ�����ʾ��ĸ�Ŀ¼�����ļ����ڴ���Ŀ����lxh��biu��ͬһ��
             */
            primaryStage = new Stage();

            imageView = new ImageView();
            imageView.setX(0);
            imageView.setY(0);
            imageView.setLayoutX(-300);
            imageView.setLayoutY(-100);
            //����ͼƬ��ʾ�Ĵ�С
//	      	imageView.setFitHeight(1000);
            imageView.setFitWidth(800);
            //���ͼƬ�ĵ���¼�
            listen = new EventListener(imageView);
            imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, listen);

            imageView.setPreserveRatio(true); //���� width��height�ı���
            imageView.setStyle("-fx-background:transparent;");//����������Ϊ͸��

            ui = new UI(imageView, listen, primaryStage);
            ui.addMessageBox("��ʿ����ӭ����!");
            ui.setMsg(ui.getDialogAnalysis().getDialog(42));
            imageView.setImage(ui.getImage("Relax"));

            AnchorPane pane = new AnchorPane(ui.getMessageBox(), ui.getImageView());
            menuBar = new MenuBar(imageView, listen, pane);
            pane.setStyle("-fx-background:transparent;");
            //ʹ�������϶����Ȼ�ȡ�������ʱ������p1���ٽ�����������Ϊp1���϶���λ����
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
            //���ô���ĳ�ʼλ��
            primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());

            primaryStage.setY(Screen.getPrimary().getVisualBounds().getMaxY() - 200);
            primaryStage.setAlwaysOnTop(true);//��������ʾ����ǰ
            //�޸�������ͼ��
            primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/icon.png")));

            primaryStage.initStyle(StageStyle.UTILITY);
            primaryStage.initStyle(StageStyle.TRANSPARENT);//����͸��
            /*
             * ����������ġ��رմ��ڡ�ʱ�����Ÿ�𶯻���ͬʱʹ���̵�ͼ��Ҳ�ر�.
             * event.consume()�Ǳ���ģ���������������ֹWindow Close�¼���Ĭ�ϴ���
             * �������ʹ��System.exit(0);����Ҫevent.consume();
             */
//			primaryStage.setOnCloseRequest( event ->{event.consume(); ui.end();});
            removeTaskbar(primaryStage);
            primaryStage.show();

            ui.setTray(primaryStage);//���ϵͳ����
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

package application;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.util.Objects;
import java.util.Optional;


public class MenuBar {
    private ImageView imageView;
    private EventListener listen;
    private Stage primaryStage;
    private AnchorPane pane;
    private boolean flag;
    private static VBox menuBox;

    public MenuBar(ImageView imageView, EventListener listen, AnchorPane pane) {
        this.imageView = imageView;
        this.listen = listen;
        this.pane = pane;
        menuBox = new VBox();
        flag = false;
        pane.getChildren().add(menuBox);
        menuBox.setLayoutX(165);
        menuBox.setLayoutY(50);
        menuBox.setSpacing(10);
        menuBox.setVisible(false);
        Button b1 = new Button("Skin resurfacing");
        Button b2 = new Button("Conversation");
        Button b3 = new Button("Feeding");
        Button b4 = new Button("Wait for the addition");
        Button b5 = new Button("Talk");

        TextInputDialog td = new TextInputDialog();

        Stage stage = (Stage) td.getDialogPane().getScene().getWindow();
//        stage.getIcons().add()

        stage.getIcons().add(new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png"))));
        /**T
         *可以试着把对话框样式换一换
         */
//        System.out.println( stage.getScene());


        b5.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * b5按钮：启用ai对话
             * 已知bug：第一个字符为空格时候会报错
             * 请使用GBK
             */

            @Override
            public void handle(ActionEvent event) {
                //event.getSource()获取一个Object对象 实际就是这个button 这里我们需要强制转行
                td.setHeaderText("你好啊");
                Optional<String> result = td.showAndWait();
//                String str= td.getEditor().getText().trim();
                if (result.isPresent()) {
//                    System.out.println(result.get());
                    try {
                        String speak = HttpClient.sendGet(result.get());
                        System.out.println(speak);
                        Platform.runLater(() -> Main.getUi().setMsg(speak, 5));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }


                //打印button的text文本信息可以验证


            }
        });
        menuBox.getChildren().addAll(b1, b2, b3, b4, b5);
    }

    public void change() {
        //如果flag=false，打开菜单，否则关闭菜单
        if (flag) menuBox.setVisible(false);
        else menuBox.setVisible(true);
        flag = !flag;
    }
}

package application.GUI;

import application.Dialog.DialogAnalysis;
import application.Listener.Desuki;
import application.Listener.EventListener;
import application.Listener.TimelinePool;
import application.Music.MusicPlayer;
import application.Net.HttpClient;
import application.Timer.Drink;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.DialogEvent;
import javafx.scene.control.TextInputDialog;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.util.Objects;
import java.util.Optional;

/**
 * build the menu
 *
 * @author ppcat
 * @version 0.1.0
 * @date 2022-12-23 15:59:09
 * @since 0.1.0
 **/
public class MenuBar {
    public static ImageView likeImageView;
    private boolean like;
    private ImageView imageView;
    private EventListener listen;
    private Stage primaryStage;
    private static AnchorPane pane;
    private boolean flag;
    private static VBox menuBox;
    private static VBox menuBox2;
    private static TimelinePool likeImagePool = new TimelinePool();
    private static MusicPlayer musicPlayer = new MusicPlayer();

    /*
     *
     *
     */
    public static void init() {
        Button b1 = new Button();
        Button b2 = new Button();
        Button b3 = new Button();
        Button b4 = new Button();
        Button b5 = new Button();
        Button b6 = new Button();
        Button b7 = new Button();
        Button b8 = new Button();
        TextInputDialog td = new TextInputDialog();
        Stage stage = (Stage) td.getDialogPane().getScene().getWindow();
//        stage.getIcons().add()

        // 监控关闭事件，将文本输入置空
        td.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                td.getEditor().setText("");
            }
        });
        /*
         *功能列表目前的图标采用的是播出传的一张很丑的图标，可以替换，目前button的样式是我试了很多种感觉看起来比较正常的
         */
        System.out.println(2);
        stage.getIcons().add(new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png"))));
        System.out.println(1);
        Image Image1 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/skin_button.png")));
        ImageView b1Image = new ImageView(Image1);
        Image Image2 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/role_button.png")));
        ImageView b2Image = new ImageView(Image2);
        Image Image3 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/piano_button.png")));
        ImageView b3Image = new ImageView(Image3);
        Image Image4 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/language_button.png")));
        ImageView b4Image = new ImageView(Image4);
        Image Image5 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/chat_button.png")));
        Image Image7 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/like_button.png")));
        Image Image8 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/music_button.png")));
        ImageView b8Image = new ImageView(Image8);
        ImageView b7Image = new ImageView(Image7);
        ImageView b5Image = new ImageView(Image5);

        b1Image.setFitWidth(20);
        b1Image.setFitHeight(20);
        b2Image.setFitWidth(20);
        b2Image.setFitHeight(20);
        b3Image.setFitWidth(20);
        b3Image.setFitHeight(20);
        b4Image.setFitWidth(20);
        b4Image.setFitHeight(20);
        b5Image.setFitWidth(20);
        b5Image.setFitHeight(20);
        b7Image.setFitWidth(20);
        b7Image.setFitHeight(20);
        b8Image.setFitWidth(20);
        b8Image.setFitHeight(20);
        Image Image6 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/drink_button.png")));
        ImageView b6Image = new ImageView(Image6);
        b6Image.setFitWidth(20);
        b6Image.setFitHeight(20);

        b1.setGraphic(b1Image);
        b2.setGraphic(b2Image);
        b3.setGraphic(b3Image);
        b4.setGraphic(b4Image);
        b5.setGraphic(b5Image);
        b6.setGraphic(b6Image);
        b7.setGraphic(b7Image);
        b8.setGraphic(b8Image);
        DropShadow shadow = new DropShadow();
        b1.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b2.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b3.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b4.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b5.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });

        b6.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b7.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b8.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                b1.setEffect(shadow);
            }
        });
        b1.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                        "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                        "-fx-border-radius:10;" +         //设置边框圆角
                        "-fx-border-color:#7fbfc7;" +     //设置边框颜色
                        "-fx-border-style:solid;" +      //设置边框样式
                        "-fx-border-width:3;" +           //设置边框宽度
                        "-fx-border-insets:0"           //设置边框插入值
        );
        b2.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#82b4c2;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );
        b3.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#86aabc;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );
        b4.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#8c95b1;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );
        b5.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#908aac;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );

        b6.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#937fa7;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );

        b7.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#899fb7;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );
        b8.setStyle("-fx-background-radius:10;" +     //设置背景圆角
                "-fx-text-fill:#1C1C1C;" +        //设置字体颜色
                "-fx-border-radius:10;" +         //设置边框圆角
                "-fx-border-color:#9675a1;" +     //设置边框颜色
                "-fx-border-style:solid;" +      //设置边框样式
                "-fx-border-width:3;" +           //设置边框宽度
                "-fx-border-insets:0"           //设置边框插入值
        );
        /**T
         *可以试着把对话框样式换一换
         */
//        System.out.println( stage.getScene());


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("切换皮肤");
                Main.getMenuBar().change();
                Main.getUi().switchSkin();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("切换人物");
                Main.getMenuBar().change();
                Main.getUi().switchPet(); // 加入美术资源后此条命令可以使用
            }
        });
        /*
         *第三个功能：弹钢琴
         * 按ESC或者ENTER键退出，写个存留3秒的对话框（cyx目前不会对话框）
         */
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("弹钢琴");
                Main.getMenuBar().change();
                PianoFrame pianoFrame = PianoFrame.getInstance(getPane());
                pianoFrame.start();
            }
        });
        /*
         *第四个功能尝试做一个娱乐性功能，看能否套用凡÷的五子棋
         */
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                switch (DialogAnalysis.getLanguage()) {
                    case "simplifiedChinese":
                        DialogAnalysis.setLanguage("Japanese");
                        break;
                    case "Japanese":
                        DialogAnalysis.setLanguage("Korean");
                        break;
                    case "Korean":
                        DialogAnalysis.setLanguage("traditionalChinese");
                        break;
                    default:
                        DialogAnalysis.setLanguage("simplifiedChinese");
                        break;
                }
                System.out.println(DialogAnalysis.getLanguage());
                Main.getMenuBar().change();
            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * b5按钮：启用ai对话
             * 已知bug：第一个字符为空格时候会报错
             * 请使用GBK
             */
            @Override
            public void handle(ActionEvent event) {
                Main.getMenuBar().change();
                //event.getSource()获取一个Object对象 实际就是这个button 这里我们需要强制转行
                td.setHeaderText("你好啊");
                Optional<String> result = td.showAndWait();
                if (result.isPresent()) {
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

        b6.setOnAction(new EventHandler<ActionEvent>() {
            //            /**
//             * b5按钮：启用ai对话
//             * 已知bug：第一个字符为空格时候会报错
//             * 请使用GBK
//             */
            @Override
            public void handle(ActionEvent event) {
                Main.getMenuBar().change();
                //event.getSource()获取一个Object对象 实际就是这个button 这里我们需要强制转行
                td.setHeaderText("设置喝水间隔");
                Optional<String> result = td.showAndWait();
                if (result.isPresent()) {
                    try {
                        int time = Integer.parseInt(result.get());
                        Drink.drink(time);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                //打印button的text文本信息可以验证
            }
        });
        b7.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Main.getMenuBar().change();
                boolean like = Main.getMenuBar().getLike();
                likeImagePool.stopAll();
                if (like) {
                    MenuBar.likeImageView.setVisible(false);
                    pane.getChildren().remove(MenuBar.likeImageView);
                } else {
                    try {
                        MenuBar.likeImageView = new ImageView(Draw.getLoveBar(Desuki.getInstance().getLikeGrade(), 200));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    MenuBar.likeImageView.setPreserveRatio(true);
                    MenuBar.likeImageView.setFitWidth(200);
                    MenuBar.likeImageView.setFitHeight(200);
                    MenuBar.likeImageView.setLayoutY(210);
                    //imageView.setVisible(true);
                    pane.getChildren().add(MenuBar.likeImageView);
                    Timeline tl = new Timeline(new KeyFrame(Duration.seconds(5), ae -> {
                        pane.getChildren().remove(MenuBar.likeImageView);
                        MenuBar.likeImageView.setVisible(false);
                        Main.getMenuBar().setLike(false);
                    }));
                    tl.play();
                    likeImagePool.addTimeLine(tl);
                }
                Main.getMenuBar().setLike(!like);
            }
        });
        b8.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                musicPlayer.setMedia(musicPlayer.randomize());
                musicPlayer.play();
            }
        });
        menuBox.getChildren().addAll(b1, b2, b3, b7);
        menuBox2.getChildren().addAll(b4, b5, b6, b8);
    }

    public MenuBar(ImageView imageView, EventListener listen, AnchorPane pane) {
        this.imageView = imageView;
        this.listen = listen;
        this.pane = pane;
        flag = false;
        menuBox = new VBox();
        pane.getChildren().add(menuBox);
        menuBox.setLayoutX(155);
        menuBox.setLayoutY(60);
        menuBox.setSpacing(10);
        menuBox.setVisible(false);

        menuBox2 = new VBox();
        pane.getChildren().add(menuBox2);
        menuBox2.setLayoutX(205);
        menuBox2.setLayoutY(60);
        menuBox2.setSpacing(10);
        menuBox2.setVisible(false);

        init();
    }

    public void change() {
        //如果flag=false，打开菜单，否则关闭菜单
        if (flag) {
            menuBox.setVisible(false);
            menuBox2.setVisible(false);
        } else {
            menuBox.setVisible(true);
            menuBox2.setVisible(true);
        }
        flag = !flag;
    }

    public void setInvisible() {
        menuBox.setVisible(false);
        menuBox2.setVisible(false);

    }

    public static AnchorPane getPane() {
        return pane;
    }

    public void setLike(boolean f) {
        like = f;
    }

    public boolean getLike() {
        return like;
    }
}

package application.GUI;

import java.awt.AWTException;
import java.awt.CheckboxMenuItem;
import java.awt.Font;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.util.Random;

import javax.imageio.ImageIO;

import application.Dialog.Dialog;
import application.Dialog.DialogAnalysis;
import application.Listener.Desuki;
import application.Listener.EventListener;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * gui
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-23 16:00:18
 **/
public class UI implements Runnable {
    private ImageView imageView;
    private EventListener listen;
    private VBox messageBox;
    private CheckboxMenuItem itemWalkable;
    private CheckboxMenuItem autoPlay;
    private CheckboxMenuItem itemSay;
    private MenuItem itemSwitch;
    private Stage primaryStage;
    Thread thread;
    private ResourcesImage resourcesImage;
    private DialogAnalysis dialogAnalysis;
    private MediaPlayer mediaPlayer;

    public UI(ImageView view, EventListener el, Stage s) {
        imageView = view;
        listen = el;
        primaryStage = s;
        dialogAnalysis = new DialogAnalysis();
        resourcesImage = new ResourcesImage();
        try {
            dialogAnalysis.startAnalyse();
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    //添加系统托盘
    public void setTray(Stage stage) {
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage image;//托盘图标
        try {
            // 为托盘添加一个右键弹出菜单
            PopupMenu popMenu = new PopupMenu();
            popMenu.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

            itemSwitch = new MenuItem("switch");
            itemSwitch.addActionListener(e -> switchPet());

            itemWalkable = new CheckboxMenuItem("walk");
            autoPlay = new CheckboxMenuItem("play");
            itemSay = new CheckboxMenuItem("dialog");
            //令"自行走动"、"自娱自乐"和"碎碎念"不能同时生效
            itemWalkable.addItemListener(il -> {
                if (itemWalkable.getState()) {
                    autoPlay.setState(false);
                    itemSay.setState(false);
                }

            });
            autoPlay.addItemListener(il -> {
                if (autoPlay.getState()) {
                    itemWalkable.setState(false);
                    itemSay.setState(false);
                }

            });
            itemSay.addItemListener(il -> {
                if (itemSay.getState()) {
                    itemWalkable.setState(false);
                    autoPlay.setState(false);
                }

            });

            MenuItem itemShow = new MenuItem("appear");
            itemShow.addActionListener(e -> Platform.runLater(() -> stage.show()));

            MenuItem itemHide = new MenuItem("hide");
            //要先setImplicitExit(false)，否则stage.hide()会直接关闭stage
            //stage.hide()等同于stage.close()
            itemHide.addActionListener(e -> {
                Platform.setImplicitExit(false);
                Platform.runLater(() -> stage.hide());
            });

            MenuItem itemExit = new MenuItem("exit");
//            itemExit.setFont(new Font("黑体",Font.PLAIN,16));
            itemExit.addActionListener(e -> end());

            popMenu.add(itemSwitch);
            popMenu.addSeparator();
            popMenu.add(itemWalkable);
            popMenu.add(autoPlay);
            popMenu.add(itemSay);
            popMenu.addSeparator();
            popMenu.add(itemShow);
            popMenu.add(itemHide);
            popMenu.add(itemExit);
            //设置托盘图标
            image = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
            TrayIcon trayIcon = new TrayIcon(image, "桌面宠物", popMenu);
            trayIcon.setToolTip("桌面宠物");
            trayIcon.setImageAutoSize(true);//自动调整图片大小。这步很重要，不然显示的是空白
            tray.add(trayIcon);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    //切换宠物

    /*
        *尝试写菜单栏第一个功能：切换皮肤，不改变人物，只改变着装
        *可能产生的bug，如果此时桌宠有动作，会与之产生冲突
     */
    public void switchSkin(){
        System.out.println("尝试切换皮肤");
        setMsg("");
        resourcesImage.switchSkin();
    }
    /*
        *菜单栏第二个功能，切换人物，与第一个类似
     */
    public void switchPet(){
        setMsg("");
        resourcesImage.switchPet();
    }
    //退出程序时展示动画
    void end() {
        double time = 3;
        Platform.runLater(() -> setMsg("博士，还会再见嘛T.T", time));
        try {
            File file=new File("love");
            if (!file.exists())file.createNewFile();
            FileOutputStream fileOut = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(Desuki.getInstance());
            out.close();
            fileOut.close();
        } catch (Exception e) {

        }
        //动画结束后执行退出
        new Timeline(new KeyFrame(
                Duration.seconds(time),
                ae -> System.exit(0)))
                .play();
    }

    //添加聊天气泡
    public void addMessageBox(String message) {
        Label bubble = new Label(message);
        //设置气泡的宽度。如果没有这句，就会根据内容多少来自适应宽度
        bubble.setPrefWidth(150);
        bubble.setWrapText(true);//自动换行
        bubble.setStyle("-fx-background-color: DarkTurquoise; -fx-background-radius: 8px;");
        bubble.setPadding(new Insets(7));//标签的内边距的宽度
        bubble.setFont(new javafx.scene.text.Font(14));
        Polygon triangle = new Polygon(
                0.0, 0.0,
                8.0, 10.0,
                16.0, 0.0);//分别设置三角形三个顶点的X和Y
        triangle.setFill(Color.DARKTURQUOISE);
        messageBox = new VBox();
//      VBox.setMargin(triangle, new Insets(0, 50, 0, 0));//设置三角形的位置，默认居中
        messageBox.getChildren().addAll(bubble, triangle);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);
        messageBox.setStyle("-fx-background:transparent;");
        //设置相对于父容器的位置
        messageBox.setLayoutX(50);
        messageBox.setLayoutY(0);
    }

    //用多线程来实现 经过随机时间间隔执行“自动行走”“自娱自乐”“碎碎念”的功能
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Random rand = new Random();
//            随机发生自动事件
            long time = (rand.nextInt(10) + 30) * 1000;
//            long time = 5000;
            int op = rand.nextInt(3);
//            int op = 0;
            if ("Relax".equals(listen.getBehavior())) {
                switch (op) {
                    case 1:
                        System.out.println("play");
                        play();
                        break;
                    case 2:
                        System.out.println("dialog");
                        Platform.runLater(() -> setMsg(dialogAnalysis.randomDialog()));
                        break;
                    default:
                        System.out.println("walk");
                        walk();
                        break;
                }
            }
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 执行"碎碎念"的功能——在宠物上方显示对话气泡
     * 不默认开启是考虑到用户可能不想被打扰
     */
    public void setMsg(String msg) {
        if (msg == null || msg.length() == 0) {
            EventListener.getMsgTimelinePool().stopAll();
            messageBox.setVisible(false);
            return;
        }
        Label lbl = (Label) messageBox.getChildren().get(0);
        lbl.setText(msg);
        messageBox.setVisible(true);
        EventListener.getMsgTimelinePool().stopAll();
        //设置气泡的显示时间
        Timeline tl = new Timeline(new KeyFrame(
                Duration.seconds(3),
                ae -> {
                    messageBox.setVisible(false);
                }));
        EventListener.getMsgTimelinePool().addTimeLine(tl);
        tl.play();
    }
/*
setMsg可能存在线程锁死的情况，我在某次运行时卡死并且报错在257行
当然不排除我电脑跑不动的情况
 */
    public void setMsg(String msg, double time) {
        if (msg == null || msg.length() == 0) {
            EventListener.getMsgTimelinePool().stopAll();
            messageBox.setVisible(false);
            return;
        }
        Label lbl = (Label) messageBox.getChildren().get(0);
        lbl.setText(msg);
        messageBox.setVisible(true);
        EventListener.getMsgTimelinePool().stopAll();
        //设置气泡的显示时间
        Timeline tl = new Timeline(new KeyFrame(
                Duration.seconds(time),
                ae -> {
                    messageBox.setVisible(false);
                }));
        EventListener.getMsgTimelinePool().addTimeLine(tl);
        tl.play();
    }

    public void setMsg(Dialog dialog) {
        if (dialog == null)
            return;
        Label lbl = (Label) messageBox.getChildren().get(0);
        lbl.setText(dialog.getDetail(DialogAnalysis.getLanguage()));
        messageBox.setVisible(true);
        EventListener.getMsgTimelinePool().stopAll();
        if (mediaPlayer != null)
            mediaPlayer.stop();
        mediaPlayer = new MediaPlayer(new Media(this.getClass().getResource("/" + Main.getPetName() + "/dialog/" + dialog.getVoiceFilename()).toExternalForm()));
        mediaPlayer.play();
        mediaPlayer.setOnEndOfMedia(() -> {
            messageBox.setVisible(false);
        });
    }

    /*
     * 执行"自行走动"的功能——在水平方向上走动
     * 不默认开启是考虑到用户可能只想宠物安静呆着
     */
    void walk() {
        Random rand = new Random();
        //如果将要到达屏幕边缘就停下
        //随机决定移动的时间，单位微秒ms
        long time = (rand.nextInt(4) + 3) * 1000;
//        System.out.println("Walking time:" + time);
        int direID = rand.nextInt(2);//随机决定方向，0为左，1为右
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                //切换至对应方向的行走图
                if (direID == 1)
                    imageView.setImage(resourcesImage.getImage("Move"));
                else
                    imageView.setImage(resourcesImage.getImage("MoveF"));
                Platform.runLater(() -> setMsg(dialogAnalysis.randomDialog()));
                //移动
                listen.setBehavior("Move");
                Move move = new Move(time, imageView, direID, primaryStage, listen);
                thread = new Thread(move);
                thread.start();
                return null;
            }
        };
        new Thread(task).start();
    }

    /*
     * 执行"自娱自乐"的功能——空闲时随机做动作
     * 这样就不用受部位数量的限制，也不会让宠物显得呆板
     * 不默认开启是考虑到用户可能只想宠物安静呆着
     */
    void play() {
        String behavior = randomAction();
        listen.loadImg(behavior);
    }

    String randomAction() {
        Random rand = new Random();
        int id = rand.nextInt(2);
        switch (id) {
            case 0:
                return "Sit";
            case 1:
                return "Sleep";
            default:
                return "Relax";
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }

    public VBox getMessageBox() {
        return messageBox;
    }

    public void setMessageBox(VBox messageBox) {
        this.messageBox = messageBox;
    }

    public void stopMedia() {
        if (mediaPlayer != null)
            mediaPlayer.stop();
    }

    public DialogAnalysis getDialogAnalysis() {
        return dialogAnalysis;
    }
    public Image getImage(String behavior) {
        return resourcesImage.getImage(behavior);
    }
}

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

    //����ϵͳ����
    public void setTray(Stage stage) {
        SystemTray tray = SystemTray.getSystemTray();
        BufferedImage image;//����ͼ��
        try {
            // Ϊ��������һ���Ҽ������˵�
            PopupMenu popMenu = new PopupMenu();
            popMenu.setFont(new Font("Microsoft YaHei", Font.PLAIN, 18));

            itemSwitch = new MenuItem("�л�����");
            itemSwitch.addActionListener(e -> switchPet());

            itemWalkable = new CheckboxMenuItem("�����߶�");
            autoPlay = new CheckboxMenuItem("��������");
            itemSay = new CheckboxMenuItem("������");
            //��"�����߶�"��"��������"��"������"����ͬʱ��Ч
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

            MenuItem itemShow = new MenuItem("��ʾ");
            itemShow.addActionListener(e -> Platform.runLater(() -> stage.show()));

            MenuItem itemHide = new MenuItem("����");
            //Ҫ��setImplicitExit(false)������stage.hide()��ֱ�ӹر�stage
            //stage.hide()��ͬ��stage.close()
            itemHide.addActionListener(e -> {
                Platform.setImplicitExit(false);
                Platform.runLater(() -> stage.hide());
            });

            MenuItem itemExit = new MenuItem("�˳�");
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
            //��������ͼ��
            image = ImageIO.read(getClass().getResourceAsStream("/icon.png"));
            TrayIcon trayIcon = new TrayIcon(image, "�������", popMenu);
            trayIcon.setToolTip("�������");
            trayIcon.setImageAutoSize(true);//�Զ�����ͼƬ��С���ⲽ����Ҫ����Ȼ��ʾ���ǿհ�
            tray.add(trayIcon);
        } catch (IOException | AWTException e) {
            e.printStackTrace();
        }
    }

    //�л�����

    /*
        *����д�˵�����һ�����ܣ��л�Ƥ�������ı����ֻ�ı���װ
        *���ܲ�����bug�������ʱ�����ж���������֮������ͻ
     */
    public void switchSkin(){
        System.out.println("�����л�Ƥ��");
        setMsg("");
        resourcesImage.switchSkin();
    }
    /*
        *�˵����ڶ������ܣ��л�������һ������
     */
    public void switchPet(){
        setMsg("");
        resourcesImage.switchPet();
    }
    //�˳�����ʱչʾ����
    void end() {
        double time = 3;
        Platform.runLater(() -> setMsg("��ʿ�������ټ���T.T", time));
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
        //����������ִ���˳�
        new Timeline(new KeyFrame(
                Duration.seconds(time),
                ae -> System.exit(0)))
                .play();
    }

    //������������
    public void addMessageBox(String message) {
        Label bubble = new Label(message);
        //�������ݵĿ��ȡ����û����䣬�ͻ�������ݶ���������Ӧ����
        bubble.setPrefWidth(150);
        bubble.setWrapText(true);//�Զ�����
        bubble.setStyle("-fx-background-color: DarkTurquoise; -fx-background-radius: 8px;");
        bubble.setPadding(new Insets(7));//��ǩ���ڱ߾�Ŀ���
        bubble.setFont(new javafx.scene.text.Font(14));
        Polygon triangle = new Polygon(
                0.0, 0.0,
                8.0, 10.0,
                16.0, 0.0);//�ֱ��������������������X��Y
        triangle.setFill(Color.DARKTURQUOISE);
        messageBox = new VBox();
//      VBox.setMargin(triangle, new Insets(0, 50, 0, 0));//���������ε�λ�ã�Ĭ�Ͼ���
        messageBox.getChildren().addAll(bubble, triangle);
        messageBox.setAlignment(Pos.BOTTOM_CENTER);
        messageBox.setStyle("-fx-background:transparent;");
        //��������ڸ�������λ��
        messageBox.setLayoutX(50);
        messageBox.setLayoutY(0);
    }

    //�ö��߳���ʵ�� �������ʱ����ִ�С��Զ����ߡ����������֡���������Ĺ���
    public void run() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while (true) {
            Random rand = new Random();
//            ��������Զ��¼�
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
     * ִ��"������"�Ĺ��ܡ����ڳ����Ϸ���ʾ�Ի�����
     * ��Ĭ�Ͽ����ǿ��ǵ��û����ܲ��뱻����
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
        //�������ݵ���ʾʱ��
        Timeline tl = new Timeline(new KeyFrame(
                Duration.seconds(3),
                ae -> {
                    messageBox.setVisible(false);
                }));
        EventListener.getMsgTimelinePool().addTimeLine(tl);
        tl.play();
    }
/*
setMsg���ܴ����߳����������������ĳ������ʱ�������ұ�����257��
��Ȼ���ų��ҵ����ܲ��������
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
        //�������ݵ���ʾʱ��
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
     * ִ��"�����߶�"�Ĺ��ܡ�����ˮƽ�������߶�
     * ��Ĭ�Ͽ����ǿ��ǵ��û�����ֻ����ﰲ������
     */
    void walk() {
        Random rand = new Random();
        //�����Ҫ������Ļ��Ե��ͣ��
        //��������ƶ���ʱ�䣬��λ΢��ms
        long time = (rand.nextInt(4) + 3) * 1000;
//        System.out.println("Walking time:" + time);
        int direID = rand.nextInt(2);//�����������0Ϊ��1Ϊ��
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                //�л�����Ӧ���������ͼ
                if (direID == 1)
                    imageView.setImage(resourcesImage.getImage("Move"));
                else
                    imageView.setImage(resourcesImage.getImage("MoveF"));
                Platform.runLater(() -> setMsg(dialogAnalysis.randomDialog()));
                //�ƶ�
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
     * ִ��"��������"�Ĺ��ܡ�������ʱ���������
     * �����Ͳ����ܲ�λ���������ƣ�Ҳ�����ó����Եô���
     * ��Ĭ�Ͽ����ǿ��ǵ��û�����ֻ����ﰲ������
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
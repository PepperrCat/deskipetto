package application.GUI;

import application.Listener.EventListener;
import application.Net.HttpClient;
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


import java.util.Objects;
import java.util.Optional;


public class MenuBar {
    private ImageView imageView;
    private EventListener listen;
    private Stage primaryStage;
    private static AnchorPane pane;
    private boolean flag;
    private static VBox menuBox;
    /*
        *init ��ʼ��������ť��style�Լ����� �������ص�û�ú������Ӹ����Ծ���Ϊ�����Ӵ������������޸ģ��ύǰ�ǵ�ɾ����ע�ͣ���������
        *չʾ����֮ǰ��һ����۵�����
     */
    public static void init(){
        Button b1 = new Button("Skin");
        Button b2 = new Button("Roles");
        Button b3 = new Button("Piano");
        Button b4 = new Button("Add");
        Button b5 = new Button("Talk");
        TextInputDialog td = new TextInputDialog();
        Stage stage = (Stage) td.getDialogPane().getScene().getWindow();
//        stage.getIcons().add()

        // ��عر��¼������ı������ÿ�
        td.setOnCloseRequest(new EventHandler<DialogEvent>() {
            @Override
            public void handle(DialogEvent dialogEvent) {
                td.getEditor().setText("");
            }
        });
/*
    *�����б�Ŀǰ��ͼ����õ��ǲ�������һ�źܳ��ͼ�꣬�����滻��Ŀǰbutton����ʽ�������˺ܶ��ָо��������Ƚ�������
 */
        stage.getIcons().add(new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png"))));
        Image Image1 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png")));
        ImageView b1Image=new ImageView(Image1);
        Image Image2 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png")));
        ImageView b2Image=new ImageView(Image2);
        Image Image3 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png")));
        ImageView b3Image=new ImageView(Image3);
        Image Image4 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png")));
        ImageView b4Image=new ImageView(Image4);
        Image Image5 = new Image(Objects.requireNonNull(ResourcesImage.class.getResourceAsStream(
                "/icon.png")));
        ImageView b5Image=new ImageView(Image5);
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
        b1.setGraphic(b1Image);
        b2.setGraphic(b2Image);
        b3.setGraphic(b3Image);
        b4.setGraphic(b4Image);
        b5.setGraphic(b5Image);
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
        b1.setStyle(    "-fx-background-radius:10;"+     //���ñ���Բ��
                "-fx-text-fill:#1C1C1C;"+        //����������ɫ
                "-fx-border-radius:10;"+         //���ñ߿�Բ��
                "-fx-border-color:#98F5FF;"+     //���ñ߿���ɫ
                "-fx-border-style:solid;"+      //���ñ߿���ʽ
                "-fx-border-width:3;"+           //���ñ߿���
                "-fx-border-insets:0"           //���ñ߿����ֵ
        );
        b2.setStyle(    "-fx-background-radius:10;"+     //���ñ���Բ��
                "-fx-text-fill:#1C1C1C;"+        //����������ɫ
                "-fx-border-radius:10;"+         //���ñ߿�Բ��
                "-fx-border-color:#98F5FF;"+     //���ñ߿���ɫ
                "-fx-border-style:solid;"+      //���ñ߿���ʽ
                "-fx-border-width:3;"+           //���ñ߿���
                "-fx-border-insets:0"           //���ñ߿����ֵ
        );
        b3.setStyle(    "-fx-background-radius:10;"+     //���ñ���Բ��
                "-fx-text-fill:#1C1C1C;"+        //����������ɫ
                "-fx-border-radius:10;"+         //���ñ߿�Բ��
                "-fx-border-color:#98F5FF;"+     //���ñ߿���ɫ
                "-fx-border-style:solid;"+      //���ñ߿���ʽ
                "-fx-border-width:3;"+           //���ñ߿���
                "-fx-border-insets:0"           //���ñ߿����ֵ
        );
        b4.setStyle(    "-fx-background-radius:10;"+     //���ñ���Բ��
                "-fx-text-fill:#1C1C1C;"+        //����������ɫ
                "-fx-border-radius:10;"+         //���ñ߿�Բ��
                "-fx-border-color:#98F5FF;"+     //���ñ߿���ɫ
                "-fx-border-style:solid;"+      //���ñ߿���ʽ
                "-fx-border-width:3;"+           //���ñ߿���
                "-fx-border-insets:0"           //���ñ߿����ֵ
        );
        b5.setStyle(    "-fx-background-radius:10;"+     //���ñ���Բ��
                "-fx-text-fill:#1C1C1C;"+        //����������ɫ
                "-fx-border-radius:10;"+         //���ñ߿�Բ��
                "-fx-border-color:#98F5FF;"+     //���ñ߿���ɫ
                "-fx-border-style:solid;"+      //���ñ߿���ʽ
                "-fx-border-width:3;"+           //���ñ߿���
                "-fx-border-insets:0"           //���ñ߿����ֵ
        );
        /**T
         *�������ŰѶԻ�����ʽ��һ��
         */
//        System.out.println( stage.getScene());


        b1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("�л�Ƥ��");
                Main.getMenuBar().change();
                Main.getUi().switchSkin();
            }
        });
        b2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("�л�����");
                Main.getMenuBar().change();
                Main.getUi().switchPet(); // ����������Դ������������ʹ��
            }
        });
        /*
            *���������ܣ�������
            * ��ESC����ENTER���˳���д������3��ĶԻ���cyxĿǰ����Ի���
         */
        b3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("������");
                Main.getMenuBar().change();
                PianoFrame pianoFrame= PianoFrame.getInstance(getPane());
                pianoFrame.start();
            }
        });
        /*
            *���ĸ����ܳ�����һ�������Թ��ܣ����ܷ����÷��µ�������
         */
        b4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println("�л�Ƥ��");
                Main.getMenuBar().change();
            }
        });
        b5.setOnAction(new EventHandler<ActionEvent>() {
            /**
             * b5��ť������ai�Ի�
             * ��֪bug����һ���ַ�Ϊ�ո�ʱ��ᱨ��
             * ��ʹ��GBK
             */
            @Override
            public void handle(ActionEvent event) {
                Main.getMenuBar().change();
                //event.getSource()��ȡһ��Object���� ʵ�ʾ������button ����������Ҫǿ��ת��
                td.setHeaderText("��ð�");
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
                //��ӡbutton��text�ı���Ϣ������֤
            }
        });
        menuBox.getChildren().addAll(b1, b2, b3, b4, b5);
    }

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
        init();
    }

    public void change() {
        //���flag=false���򿪲˵�������رղ˵�
        if (flag) menuBox.setVisible(false);
        else menuBox.setVisible(true);
        flag = !flag;
    }
    public void setInvisible() {
        menuBox.setVisible(false);
    }
    public static AnchorPane getPane(){
        return pane;
    }
}

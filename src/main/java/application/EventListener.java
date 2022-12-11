package application;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class EventListener implements EventHandler<MouseEvent> {
    private ImageView imageView;
    String behavior = "Relax";

    public EventListener(ImageView imgView) {
        imageView = imgView;
    }

    public void handle(MouseEvent e) {
        if (!"Relax".equals(behavior)) return;    //�������û���꣬�Ͳ����������µĶ���
        double x = e.getX();
        double y = e.getY();
        System.out.println(x + " " + y);//�����۾��Ȳ�λ��λ��
        //ѡ����
        String behavior = Behavior(x, y);
        loadImg(behavior, 1.18);
    }

    private String Behavior(double x, double y) {
        if (x > 370 && x < 420 && y > 170 && y < 200)
            return "Interact";
        if (x > 370 && x < 410 && y > 260 && y < 310)
            return "Sit";
        if (x > 370 && x < 410 && y > 220 && y < 250)
            return "Sleep";
        return "Relax";
    }

    //�����λ�����ͼƬ
    public void loadImg(String behavior, double time) {
        this.behavior = behavior;
        if (!"Relax".equals(behavior)) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    imageView.setImage(ResourcesImage.getImage(behavior));
                    new Timeline(new KeyFrame(Duration.seconds(getTime()), ae -> mainImg())).play();
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    //��ͼ������ȴ�ʱ���˳�ʱ�Ķ���
    public void mainImg() {
        behavior = "Relax";
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                imageView.setImage(ResourcesImage.getImage(behavior));
                return null;
            }
        };
        new Thread(task).start();
    }

    private double getTime() {
        switch (behavior) {
            case "Relax":
                return 0;
            case "Interact":
                return 0.3;
            case "Sit":
                return 5;
            case "Sleep":
                return 5;
            case "Special":
                return 1;
        }
        return 0;
    }
}

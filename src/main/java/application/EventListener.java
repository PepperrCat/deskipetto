package application;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class EventListener implements EventHandler<MouseEvent> {
    private ImageView imageView;
    String behavior = "Relax";
    private static TimelinePool behaviorTimelinePool = new TimelinePool();
    private static TimelinePool msgTimelinePool = new TimelinePool();

    public EventListener(ImageView imgView) {
        imageView = imgView;
    }

    public void handle(MouseEvent e) {
        //
        if (e.getButton().name().equals("PRIMARY")) {
            if ("Sleep".equals(behavior) || "Sit".equals(behavior)) {
                loadImg("Interact");
                return;
            }
            if (!"Relax".equals(behavior)) return;    //如果动作没做完，就不允许再做新的动作
            double x = e.getX();
            double y = e.getY();
//        System.out.println(x + " " + y);//测试眼睛等部位的位置
            //选择动作
            String behavior = Behavior(x, y);
            loadImg(behavior);
        }
        else {//右键事件
            Main.getMenuBar().change();
        }
    }

    private String Behavior(double x, double y) {
        Desuki desuki = Desuki.getInstance();
        if (x > 370 && x < 420 && y > 170 && y < 200) {
            desuki.addGrade(2);
            return "Interact";
        }
//        if (x > 370 && x < 410 && y > 260 && y < 310)
//            return "Sit";
//        if (x > 370 && x < 410 && y > 220 && y < 250)
//            return "Sleep";
        desuki.addGrade(1);
        return "Relax";
    }

    //点击部位后加载图片
    public void loadImg(String behavior) {
        this.behavior = behavior;
        if (!"Relax".equals(behavior)) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    imageView.setImage(ResourcesImage.getImage(behavior));
                    switch (behavior) {
                        case "Interact":
                            Platform.runLater(() -> Main.getUi().setMsg(Main.getUi().getDialogAnalysis().getDialog(34)));
                            break;
                        case "Sit":
                            Platform.runLater(() -> Main.getUi().setMsg("嗯哼~嗯哼~", 5));
                            break;
                        case "Sleep":
                            Platform.runLater(() -> Main.getUi().setMsg("zzz", 5));
                            break;
                    }
                    behaviorTimelinePool.stopAll();
                    Timeline tl = new Timeline(new KeyFrame(Duration.seconds(getTime()), ae -> mainImg()));
                    behaviorTimelinePool.addTimeLine(tl);
                    tl.play();
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    public void loadImg(String behavior, double time) {
        this.behavior = behavior;
        if (!"Relax".equals(behavior)) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    imageView.setImage(ResourcesImage.getImage(behavior));
                    switch (behavior) {
                        case "Interact":
                            Platform.runLater(() -> Main.getUi().setMsg(Main.getUi().getDialogAnalysis().getDialog(34)));
                            break;
                        case "Sit":
                            Platform.runLater(() -> Main.getUi().setMsg("嗯哼~嗯哼~", 5));
                            break;
                        case "Sleep":
                            Platform.runLater(() -> Main.getUi().setMsg("zzz", 5));
                            break;
                    }
                    behaviorTimelinePool.stopAll();
                    System.out.println(time);
                    Timeline tl = new Timeline(new KeyFrame(Duration.seconds(time), ae -> mainImg()));
                    behaviorTimelinePool.addTimeLine(tl);
                    tl.play();
                    return null;
                }
            };
            new Thread(task).start();
        }
    }

    //主图，负责等待时和退出时的动作
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
                return 15;
            case "Sleep":
                return 15;
            case "Special":
                return 1;
        }
        return 0;
    }

    public static TimelinePool getBehaviorTimelinePool() {
        return behaviorTimelinePool;
    }

    public static TimelinePool getMsgTimelinePool() {
        return msgTimelinePool;
    }
}

package application.Listener;

import application.GUI.Main;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
/**
 * the class that listens for mouse events
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-18 18:11:41
 **/
public class EventListener implements EventHandler<MouseEvent> {
    private ImageView imageView;
    private String behavior = "Relax";
    private static TimelinePool behaviorTimelinePool = new TimelinePool();
    private static TimelinePool msgTimelinePool = new TimelinePool();
    private static TimelinePool menuBarTimelinePool = new TimelinePool();

    public EventListener(ImageView imgView) {
        imageView = imgView;
    }

    public void handle(MouseEvent e) {
        //git fetch --unshallow
        if (e.getButton().name().equals("PRIMARY")) {
            Main.getUi().stopMedia();
            if ("Sleep".equals(getBehavior()) || "Sit".equals(getBehavior())) {
                mainImg();
                return;
            } else if ("Relax".equals(getBehavior())) {
                double x = e.getX();
                double y = e.getY();
//        System.out.println(x + " " + y);//测试眼睛等部位的位置
                //选择动作
                String behavior = Behavior(x, y);
                loadImg(behavior);
            }
        } else {//右键事件
            menuBarTimelinePool.stopAll();
            Main.getMenuBar().change();
            // 添加了menu自动消失的时间线以及管理
            Timeline tl = new Timeline(new KeyFrame(Duration.seconds(20), ae -> Main.getMenuBar().setInvisible()));
            tl.play();
            menuBarTimelinePool.addTimeLine(tl);
        }
    }
    // 下列方法是交互动作同时提高好感度
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
        return "Interact";
    }

    //点击部位后加载图片
    public void loadImg(String behavior) {
        setBehavior(behavior);
        if (!"Relax".equals(getBehavior())) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    imageView.setImage(Main.getUi().getImage(getBehavior()));
                    switch (getBehavior()) {
                        case "Interact":
                            if (Desuki.getInstance().getLikeGrade() >= 10)
                                Platform.runLater(() -> Main.getUi().setMsg(Main.getUi().getDialogAnalysis().getDialog(36)));
                            else
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
        setBehavior(behavior);
        if (!"Relax".equals(behavior)) {
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    imageView.setImage(Main.getUi().getImage(getBehavior()));
                    switch (getBehavior()) {
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
        setBehavior("Relax");
        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                imageView.setImage(Main.getUi().getImage(getBehavior()));
                return null;
            }
        };
        new Thread(task).start();
    }

    private double getTime() {
        switch (getBehavior()) {
            case "Relax":
                return 0;
            case "Interact":
                return 0.908;
            case "Sit":
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

    public String getBehavior() {
        return behavior;
    }

    public void setBehavior(String behavior) {
        this.behavior = behavior;
    }
}

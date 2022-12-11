package application;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Move extends Thread {
    private long time;
    private ImageView imageView;
    private int direID;
    double x;
    double maxx;
    double width;
    Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
    Stage stage;
    private EventListener listen;
    boolean exit;

    public Move(long time, ImageView imgView, int dire, Stage primaryStage, EventListener el) {
        this.time = time;
        imageView = imgView;
        direID = dire;
        stage = primaryStage;
        listen = el;
    }

    public void run() {
        //�����ͣ��
        /*ʹ��listen.petID�����Ƕ���һ������int petID = listen.petID;
         *����Ϊ���˶������е�����л����ʱʵ�ʵ�petID��ı䣬����ʹ��listen.petID�Ϳ�������ͬ���ı䡣
         *������ʹ��listen.mainimg(petID,0)��ʾ�ľ��ǵ�����л����ǰ�ĳ�����petID���Ǿɵ�petID��
         */
        imageView.addEventHandler(MouseEvent.MOUSE_PRESSED,
                e -> {
                    exit = true;
                    listen.mainImg();
                });
        while (!exit) {
            //���petID!=listen.petID�����ѡ��л��������ʱҪ�����˶���
            if (!"Move".equals(listen.behavior))
                break;
            width = imageView.getBoundsInLocal().getMaxX() - 600;
            x = stage.getX();
            maxx = screenBounds.getMaxX();
            double speed = 0.12;
            if (x + speed + width >= maxx || x - speed <= 0 || time <= 0) {
                this.interrupt();
                listen.mainImg();
                return;
            }
            if (direID == 0) {    //������
                stage.setX(x - speed);
            } else if (direID == 1) {    //������
                stage.setX(x + speed);
            }
            time -= 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

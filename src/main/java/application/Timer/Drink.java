package application.Timer;

import application.GUI.Main;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

/**
 * @projectName: deskipetto
 * @package: application.Timer
 * @className: Drink
 * @date: 2022/12/23 16:48
 */
public class Drink extends Thread {
    static Drink drink;
    static List<String> content = new ArrayList<>();

    static {
        content.add("俗话说\"女人是水造的\"，所以身为萌妹就要时刻喝水，这样就可以保持充足的水分，皮肤、头发就会更有光泽~");
        content.add("该喝水了哟，要多爱护自己，多喝水、多吃新鲜水果蔬菜、尽量保证充足睡眠。加油！");
        content.add("多喝水很简单的话，多喝水对身体好！只有心中挂念着你们的人才会说你的家人也老说的话：你要多喝水呀！！~");
        content.add("天气寒冷干燥。多喝水，注意保暖。多想念我~");
        content.add("来姨妈，多喝热水");
        content.add("感情呀，像喝水。有人喜欢喝凉水，喜欢那种透彻心底的感觉。有人则喜欢喝温水，暖暖的。也有人喜欢喝热水，能带来火热的感觉。");
        content.add("如果喝水能喝醉谁愿意去喝那伤心的酒。");
        content.add(" 一个人恋爱，喝水像饮酒，一个人失恋了，喝酸奶都像喝毒药。");
        content.add("其实水和酒一样，只是水能解渴，酒能解愁，有时候酒并不能解愁，所以和酒还不如喝水，喝水更健康");
        content.add("我不喜欢喝水，因为水喝多了，眼泪也就多了。");
    }

    public int timeInterval;

    public Drink(int timeInterval) {
        this.timeInterval = timeInterval;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            int x = random.nextInt(content.size());
            String words = content.get(x);
            Platform.runLater(() -> Main.getUi().setMsg(words, 5));
            try {
                sleep(1000L * timeInterval*60);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }


    public static void drink(int timeInterval) {
        if (drink==null)drink=new Drink(timeInterval);
        else {
            drink.stop();
            drink=new Drink(timeInterval);
        }
        drink.start();
    }
}

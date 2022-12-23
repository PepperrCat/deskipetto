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
        content.add("�׻�˵\"Ů����ˮ���\"��������Ϊ���þ�Ҫʱ�̺�ˮ�������Ϳ��Ա��ֳ����ˮ�֣�Ƥ����ͷ���ͻ���й���~");
        content.add("�ú�ˮ��Ӵ��Ҫ�మ���Լ������ˮ���������ˮ���߲ˡ�������֤����˯�ߡ����ͣ�");
        content.add("���ˮ�ܼ򵥵Ļ������ˮ������ã�ֻ�����й��������ǵ��˲Ż�˵��ļ���Ҳ��˵�Ļ�����Ҫ���ˮѽ����~");
        content.add("�������������ˮ��ע�Ᵽů����������~");
        content.add("�����裬�����ˮ");
        content.add("����ѽ�����ˮ������ϲ������ˮ��ϲ������͸���ĵ׵ĸо���������ϲ������ˮ��ůů�ġ�Ҳ����ϲ������ˮ���ܴ������ȵĸо���");
        content.add("�����ˮ�ܺ���˭Ը��ȥ�������ĵľơ�");
        content.add(" һ������������ˮ�����ƣ�һ����ʧ���ˣ������̶���ȶ�ҩ��");
        content.add("��ʵˮ�;�һ����ֻ��ˮ�ܽ�ʣ����ܽ���ʱ��Ʋ����ܽ����Ժ;ƻ������ˮ����ˮ������");
        content.add("�Ҳ�ϲ����ˮ����Ϊˮ�ȶ��ˣ�����Ҳ�Ͷ��ˡ�");
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

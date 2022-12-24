package application.GUI;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 *
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-23 15:59:31
 **/
public class PianoPlayThread extends Thread {
    InputStream inputStream;
    private void playPiano(InputStream inputStream) {
        //播放琴音方法
        try {
            //获取音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(inputStream));
           // System.out.println("获取音频输入流成功");
            //获取音频编码对象
            AudioFormat audioFormat = audioInputStream.getFormat();
           // System.out.println("获取编码对象成功");
            //设置数据输入
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat, AudioSystem.NOT_SPECIFIED);
            SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(audioFormat);
            sourceDataLine.start();
            //从输入流中读取数据发送到混音器
            int count;
            byte tempBuffer[] = new byte[1024];
            while ((count = audioInputStream.read(tempBuffer, 0, tempBuffer.length)) != -1) {
                if (count > 0) {
                    sourceDataLine.write(tempBuffer, 0, count);
                }
            }
            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (IOException | UnsupportedAudioFileException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    public PianoPlayThread(InputStream inputStream){
        this.inputStream = inputStream;
    }
    public void run() {
        playPiano(inputStream);
    }
}
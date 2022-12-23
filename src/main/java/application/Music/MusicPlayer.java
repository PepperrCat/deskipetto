package application.Music;

import application.Net.HttpClient;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * the class that play the music
 * first set the media then play
 *
 * @author ppcat
 * @version 0.1.0
 * @date 2022-12-23 21:20:25
 * @since 0.1.0
 **/
public class MusicPlayer {
    private MediaPlayer player;
    private Media media;
    private File[] mediaFiles;

    public void start() {
        if (media != null) {
            player = new MediaPlayer(media);
            player.play();
        } else {
            System.out.println("未设置播放对象");
        }
    }

    public void pause() {
        if (player != null) {
            player.pause();
        }
    }
    public void stop() {
        if (player != null) {
            player.stop();
        }
    }
    public void play() {
        if (player != null) {
            player.play();
        }
    }
    public void setMedia(Media media) {
        this.media = media;
    }

    public Media randomize() {
        try {
            return new Media(getMediaUrl());
        } catch (IOException e) {
            System.out.println("未连接网络");
        }
        return null;
    }
    private String getMediaUrl() throws IOException {
        HttpGet request = new HttpGet("https://api.uomg.com/api/rand.music?sort=%E7%83%AD%E6%AD%8C%E6%A6%9C&format=json");
        request.addHeader("User-Agent", "Mozilla/5.0");
        HttpResponse response = HttpClient.client.execute(request);
        BufferedReader rd = new BufferedReader(
                new InputStreamReader(response.getEntity().getContent()));
//        System.out.println(3);
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        response.getEntity().getContent().close();//关闭结果集
        JSONObject jsonObject = new JSONObject(result.toString()).getJSONObject("data");

        System.out.println(jsonObject.getString("name"));
        return jsonObject.getString("url");
    }
    public boolean isPlaying() {
        return player.getStatus().equals(MediaPlayer.Status.PLAYING);
    }
}

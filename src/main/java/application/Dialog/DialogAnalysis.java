package application.Dialog;

import application.GUI.Main;
import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * to analyze the dialog json
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-23 15:57:38
 **/
public class DialogAnalysis {
    private static String language = "simplifiedChinese";
    private Map<Integer ,Dialog> dialogs = new HashMap<>();

    public void startAnalyse() throws IOException, URISyntaxException {
        InputStream jsonfile = this.getClass().getResourceAsStream("/" + Main.getPetName() + "/" + "dialog.json");
        String json = inputStreamToString(jsonfile);
        JSONArray array = new JSONArray(json);
        for (int i = 0; i < array.length(); i++) {
            JSONObject subObject = array.getJSONObject(i);
            int index = subObject.getInt("index");
            String title = subObject.getString("title");
            String voiceFilename = subObject.getString("voiceFilename");
            JSONObject detail = subObject.getJSONObject("detail");
            dialogs.put(index, new Dialog(title, voiceFilename, detail));
        }
    }
    private String inputStreamToString(InputStream inputStream) {
        StringBuffer buffer = new StringBuffer();
        InputStreamReader inputStreamReader;
        try {
            inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            // 释放资源
            bufferedReader.close();
            inputStreamReader.close();
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buffer.toString();
    }
    public Dialog getDialog(int index) {
        return dialogs.get(index);
    }
    public void clearDialog() {
        dialogs.clear();
    }
    public Dialog randomDialog() {
        Random r = new Random();
        Dialog d = dialogs.get(r.nextInt(42));
        while (d == null) {
            d = dialogs.get(r.nextInt(42));
        }
        return d;
    }

    public static String getLanguage() {
        return language;
    }

    public static void setLanguage(String language) {
        DialogAnalysis.language = language;
    }
}

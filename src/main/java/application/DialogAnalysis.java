package application;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DialogAnalysis {
    private Map<Integer ,Dialog> dialogs = new HashMap<>();

    public void startAnalyse() throws IOException, URISyntaxException {
        File jsonfile = new File(this.getClass().getResource("/" + Main.getPetName() + "/" + "dialog.json").toURI());
        String json = FileUtils.readFileToString(jsonfile);
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
}

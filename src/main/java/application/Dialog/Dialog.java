package application.Dialog;

import org.json.JSONObject;

public class Dialog {
    private String title;
    private String voiceFilename;
    private LanguageMap detail;

    public Dialog(String title, String voiceFilename, JSONObject detail) {
        this.title = title;
        this.voiceFilename = voiceFilename;
        createLanguageMap(detail.getString("中文"), detail.getString("日文"), detail.getString("英文"), detail.getString("韩文"), detail.getString("中文(繁体)"));
    }

    private void createLanguageMap(String simplifiedChinese, String japanese, String english, String korean, String traditionalChinese) {
        detail = new LanguageMap(simplifiedChinese, japanese, english, korean, traditionalChinese);
    }

    public String getDetail(String language) {
        return detail.getDialog(language);
    }

    public String getTitle() {
        return title;
    }

    public String getVoiceFilename() {
        return voiceFilename;
    }
}

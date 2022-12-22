package application.Dialog;

public class LanguageMap {
    private String simplifiedChinese;
    private String Japanese;
    private String English;
    private String Korean;
    private String traditionalChinese;

    public LanguageMap(String simplifiedChinese, String japanese, String english, String korean, String traditionalChinese) {
        this.simplifiedChinese = simplifiedChinese;
        Japanese = japanese;
        English = english;
        Korean = korean;
        this.traditionalChinese = traditionalChinese;
    }
    public String getDialog(String language) {
        switch (language) {
            case "simplifiedChinese":
                return simplifiedChinese;
            case "Japanese":
                return Japanese;
            case "English":
                return English;
            case "Korean":
                return Korean;
            case "traditionalChinese":
                return traditionalChinese;
        }
        return simplifiedChinese;
    }
}

package application.Dialog;

/**
 * classify the dialog contents by language
 * @version 0.1.0
 * @author ppcat
 * @since 0.1.0
 * @date 2022-12-23 15:58:27
 **/
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

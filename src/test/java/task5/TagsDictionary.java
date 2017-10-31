package task5;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/31/2017.
 */
public class TagsDictionary {


    private static Map<String, Map<String, String>> dictionary;
    private static Map<String, String> engEng;
    private static Map<String, String> engRu;

    static {
        engEng = new HashMap<String, String>();
        engEng.put("action", "Action");
        engEng.put("adventures", "Adventures");

        engRu = new HashMap<String, String>();
        engRu.put("action", "Экшен");
        engRu.put("adventures", "Приключенческая игра");

        dictionary = new HashMap<String, Map<String, String>>();
        dictionary.put("en", engEng);
        dictionary.put("ru", engRu);

    }

    private String lang = "en";

    public TagsDictionary(String lang) {
        this.lang = lang;
    }

    private static Map<String, String> getDict(String lang) {
        return dictionary.get(lang);
    }

    public static String translate(String word, String lang) {
        return getDict(lang).get(word);
    }

    public String translate(String word) {
        return translate(word, lang);
    }
}

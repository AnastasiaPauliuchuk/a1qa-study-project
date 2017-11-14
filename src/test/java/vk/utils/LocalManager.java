package vk.utils;

import webdriver.PropertiesResourceManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/10/2017.
 */
public class LocalManager {

    private static final String LOC_FILE_TEMPLATE = "localization/loc_testdata_%1$s.properties";
    private static LocalManager localManager;
    private String lang = "en";
    private PropertiesResourceManager propManager;

    private LocalManager() {
        propManager = new PropertiesResourceManager(String.format(LOC_FILE_TEMPLATE, lang));
    }

    public static LocalManager getInstance() {
        if (localManager == null) {
            localManager = new LocalManager();
        }
        return localManager;
    }

    public void setLang(String lang) {
        this.lang = lang;
        propManager = new PropertiesResourceManager(String.format(LOC_FILE_TEMPLATE, lang));
    }

    public String getLocalSetting(String key) {
        return propManager.getProperty(key);
    }

    public LocalDateTime buildDateFromString(String dataStr) {
        String dateFormat = this.getLocalSetting("loc.dateformat");
        String today = this.getLocalSetting("loc.todayat");

        String str = dataStr.replace(today, "").trim();
        if (dateFormat.length() > str.length()) str = "0" + str;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat);
        return LocalDateTime.of(LocalDate.now(), LocalTime.parse(str.toUpperCase(), formatter));
    }
}

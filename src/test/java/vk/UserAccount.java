package vk;

import vk.utils.LocalManager;

/**
 * @author Anastasia Pauliuchuk
 *         created:  11/8/2017.
 */
public class UserAccount {
    private static final String PAGE_URL_TEMPLATE = "http://vk.com/id%s";
    private static final String LOC_USER_PROPERTY_TEMPLATE = "%1$s.%2$s";
    private String login;
    private String password;
    // private String name;
    // private String surname;
    private String id;
    private String lang;

    public UserAccount(String login, String password, String id, String lang) {
        this.login = login;
        this.password = password;
        this.id = id;
        this.lang = lang;
    }


    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLocalName() {
        return LocalManager.getInstance().getLocalSetting(String.format(LOC_USER_PROPERTY_TEMPLATE, "name", this.id));
    }

    public String getLocalSurname() {

        return LocalManager.getInstance().getLocalSetting(String.format(LOC_USER_PROPERTY_TEMPLATE, "surname", this.id));
    }

    public String getLocalFullname() {
        return String.format("%1$s %2$s", getLocalName(), getLocalSurname());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPageURL() {
        return String.format(PAGE_URL_TEMPLATE, this.id);
    }
}

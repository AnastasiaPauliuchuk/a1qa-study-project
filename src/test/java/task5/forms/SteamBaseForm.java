package task5.forms;

import org.openqa.selenium.By;
import task5.elements.SteamGamesSubMenu;
import task5.elements.SteamLangMenu;
import task5.elements.SteamMainMenu;
import webdriver.BaseForm;
import webdriver.elements.Label;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/26/2017.
 */
public class SteamBaseForm extends BaseForm {

    private static final Integer RELOAD_TIMEOUT = 1;
    public SteamLangMenu langMenu = new SteamLangMenu(By.cssSelector("div#language_dropdown"), "lang menu");
    public SteamMainMenu mainMenu = new SteamMainMenu(By.cssSelector("div.store_nav"), "main menu");
    public SteamGamesSubMenu gamesMenu = new SteamGamesSubMenu(By.cssSelector("div#genre_tab"), "games sub menu");
    private Label lbLogo = new Label(By.cssSelector("div.logo"), "steam logo");
    private Label lbLanguage = new Label(By.xpath("//*[@id=\"language_pulldown\"]"), "lang label");

    public SteamBaseForm() {
        super(By.id("store_header"), "Steam Main");
    }

    public void assertLogo() {
        assert (lbLogo.isPresent());
    }

    public boolean chooseLang(String lang) {
        lbLanguage.click();
        if (!langMenu.isCurrentLanguage(lang)) {
            langMenu.selectItem(lang);
            return true;
        } else {
            lbLanguage.click();
            return false;
        }

    }


    public void assertMenuVisible() {

        assert (mainMenu.isVisible());
    }

    public void assertMenuHidden(Integer timeoutForCondition) {

        assert (mainMenu.isHidden(timeoutForCondition));
    }


    public void assertMenuReload() {
        assertMenuHidden(RELOAD_TIMEOUT);
        assertMenuVisible();
    }
}

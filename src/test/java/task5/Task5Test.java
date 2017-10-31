package task5;


import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import task5.elements.SteamGamesSubMenu;
import task5.elements.SteamMainMenu;
import task5.elements.StreamGamesTabs;
import task5.forms.*;
import webdriver.BaseTest;
import webdriver.PropertiesResourceManager;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/26/2017.
 */
public class Task5Test extends BaseTest {


    private static final String DOWNLOAD_PROPERTIES_FILE = "download.properties";
    private static final String PROPERTIES_FILE = "selenium.properties";


    private final static String DEF_LANG = "en";
    private FileDownloader fileDownloader;
    private String lang;

    @BeforeTest
    public void before() {
        fileDownloader = new FileDownloader();
        fileDownloader.init(DOWNLOAD_PROPERTIES_FILE);
        this.lang = new PropertiesResourceManager(PROPERTIES_FILE).getProperty("site.language", DEF_LANG);
    }

    public void runTest() {

        logger.step(1);
        SteamBaseForm sf = new SteamBaseForm();

        logger.step(2);
        //Switch language if necessary
        sf.chooseLang(lang);
        //If page reloaded
        sf.assertMenuVisible();
        //Hover menu Games
        sf.mainMenu.selectItem(SteamMainMenu.Items.GAMES);
        //If pulldown menu shown
        sf.gamesMenu.assertSubMenu();
        //Click menu Action
        sf.gamesMenu.selectItem(SteamGamesSubMenu.Items.ACTION);

        logger.step(3);
        SteamGamesListForm sgf = new SteamGamesListForm();
        //Switch tab Specials
        sgf.mTabs.selectItem(StreamGamesTabs.Items.SPECIALS);

        logger.step(4);
        //  sgf.lblList.createList();
        //Click item with max discount
        sgf.selectMaxDiscountItem();

        logger.step(5);
        //If Age Check form appears, set age for adult content
        SteamAgeForm saf = new SteamAgeForm();
        if (saf.isDisplayed()) {
            saf.selectAdultYear();
        }

        //If Age and Content check form appears, click View page button
        SteamOnWorkForm steamOnWorkForm = new SteamOnWorkForm();
        if (steamOnWorkForm.isDisplayed()) {
            steamOnWorkForm.viewPage();
        }

        logger.step(6);
        SteamGameForm steamGameForm = new SteamGameForm();
        //Check discount, old price, final price
        steamGameForm.assertPrices(sgf.getMaxDiscountValues());

        logger.step(7);
        //Click Install Steam button in page header
        steamGameForm.goInstall();

        SteamInstallForm steamInstallForm = new SteamInstallForm();
        //Click Install Steam button
        steamInstallForm.clickInstall();

        //Check file download
        fileDownloader.waitUntilFileDownloaded();
        fileDownloader.assertFileDownloaded();

    }

    @AfterTest
    public void after() {

        //Delete files downloaded during the test
        fileDownloader.clean();
        super.after();
    }
}

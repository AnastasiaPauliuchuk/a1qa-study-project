package webdriver;

import com.opera.core.systems.OperaDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import webdriver.Browser.Browsers;

import javax.naming.NamingException;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static webdriver.Logger.getLoc;

/**
 * The class-initializer-based browser string parameter.
 */
public abstract class BrowserFactory {

    private static final String DOWNLOAD_PROPERTIES_FILE = "download.properties";
    private static final String BROWSER_PROPERTIES_FILE = "selenium.properties";

    private static Map<String, String> localization;

    static {
        localization = new HashMap<String, String>();
        localization.put("ru", "ru-RU");
        localization.put("en", "en-US");
    }
    /**
     * Setting up Driver
     *
     * @param type Browser type
     * @return RemoteWebDriver
     */
    public static RemoteWebDriver setUp(final Browsers type) {

        DesiredCapabilities capabilitiesProxy = null;


        RemoteWebDriver driver = null;
        File myFile = null;
        URL myTestURL;
        String downloadPath = "";
        String siteLanguage = "";
        try {
            PropertiesResourceManager prop = new PropertiesResourceManager(DOWNLOAD_PROPERTIES_FILE);
            downloadPath = prop.getProperty("downloadPath");
            siteLanguage = prop.getProperty("site.language");
        } catch (Exception e) {

        }


        switch (type) {
            case CHROME:
                DesiredCapabilities cp1 = DesiredCapabilities.chrome();
                //cp1.setCapability("chrome.switches", Arrays.asList("--disable-popup-blocking"));

                HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
                if (downloadPath.length() > 0)
                    chromePrefs.put("download.default_directory", downloadPath);
                chromePrefs.put("download.directory_upgrade", true);
                chromePrefs.put("browser.helperApps.alwaysAsk.force", false);

                chromePrefs.put("safebrowsing.enabled", true);
                chromePrefs.put("profile.default_content_settings.popups", 0);
                chromePrefs.put("download.prompt_for_download", "false");
                ChromeOptions options = new ChromeOptions();
                options.setExperimentalOption("prefs", chromePrefs);
                options.addArguments("start-maximized", "disable-popup-blocking", "--incognito");

                options.addArguments("--lang=" + localization.get(siteLanguage));
                cp1.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
                cp1.setCapability(ChromeOptions.CAPABILITY, options);

                myTestURL = ClassLoader.getSystemResource("chromedriver.exe");
                try {
                    myFile = new File(myTestURL.toURI());
                } catch (URISyntaxException e1) {
                    Logger.getInstance().debug(e1.getMessage());
                }
                System.setProperty("webdriver.chrome.driver", myFile.getAbsolutePath());
                driver = new ChromeDriver(cp1);
                break;

            case FIREFOX:
                //driver = new FirefoxDriver(capabilitiesProxy);

                FirefoxOptions ffoptions = new FirefoxOptions();

                ffoptions.addPreference("browser.helperApps.neverAsk.saveToDisk", "application/octet-stream")
                        .addPreference("browser.download.folderList", 2)
                        .addPreference("browser.privatebrowsing.autostart", true);
                // .addPreference("intl.accept_languages",localization.get(siteLanguage));
                if (downloadPath.length() > 0) ffoptions.addPreference("browser.download.dir", downloadPath);


                myTestURL = ClassLoader.getSystemResource("geckodriver.exe");
                try {
                    myFile = new File(myTestURL.toURI());
                } catch (URISyntaxException e1) {
                    Logger.getInstance().debug(e1.getMessage());
                }

                System.setProperty("webdriver.gecko.driver", myFile.getAbsolutePath());
                driver = new FirefoxDriver(ffoptions);
                break;

            case IEXPLORE:
                //local security request flag INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS
                //(but this flag may cause appearing "skipped" tests)
                if (new PropertiesResourceManager(Browser.PROPERTIES_FILE).getProperty("localrun").equalsIgnoreCase("true")) {
                    DesiredCapabilities cp = DesiredCapabilities.internetExplorer();
                    cp.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    URL myTestURL2 = ClassLoader.getSystemResource("IEDriverServer.exe");
                    try {
                        myFile = new File(myTestURL2.toURI());
                    } catch (URISyntaxException e1) {
                        Logger.getInstance().debug(e1.getMessage());
                    }
                    System.setProperty("webdriver.ie.driver", myFile.getAbsolutePath());
                    driver = new InternetExplorerDriver(cp);
                    // better to avoid
                } else {
                    // now remote connection will be refused, so use selenium server instead
                    driver = new InternetExplorerDriver();
                }
                break;

            case OPERA:
                //work on v.11-12 (Presto engine)

                driver = new OperaDriver();
                break;
            case SAFARI:
                //work on v.5.1+

                driver = new SafariDriver();
                break;
            default:
                break;
        }
        return driver;
    }

    /**
     * Setting up Driver
     *
     * @param type Browser type
     * @return RemoteWebDriver
     * @throws NamingException NamingException
     */
    public static RemoteWebDriver setUp(final String type) throws NamingException {
        for (Browsers t : Browsers.values()) {
            if (t.toString().equalsIgnoreCase(type)) {
                return setUp(t);
            }
        }
        throw new NamingException(getLoc("loc.browser.name.wrong") + ":\nchrome\nfirefox\niexplore\nopera\nsafari");
    }
}

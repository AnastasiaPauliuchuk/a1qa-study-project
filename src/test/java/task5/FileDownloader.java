package task5;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import webdriver.BaseEntity;
import webdriver.Browser;
import webdriver.Logger;
import webdriver.PropertiesResourceManager;

import java.io.File;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/29/2017.
 */
public class FileDownloader extends BaseEntity {

    private static final Integer TIME_INTERVAL = 2;
    private static final Integer TIMEOUT = 60;
    private static final String REGEXP_TMP_FILEFORMAT = "(\\.)(part|crdownload|tmp)$";
    protected static Logger logger = Logger.getInstance();
    private String downloadPath;
    private Set<String> filesInDirectory;
    private String fileDownloaded = "";

    private Set<String> getFileNames() {
        Set<String> fileNames = new HashSet<>();
        File d = new File(downloadPath);
        File[] files = d.listFiles();
        for (File f : files) {
            if (f.isFile()) fileNames.add(f.getName());
        }
        return fileNames;
    }

    private boolean isTmpFile(String filename) {
        Pattern p = Pattern.compile(REGEXP_TMP_FILEFORMAT);
        Matcher m = p.matcher(filename);
        return m.find();
    }

    public void init(String propFilename) {
        PropertiesResourceManager prop = new PropertiesResourceManager(propFilename);
        downloadPath = prop.getProperty("downloadPath");
        filesInDirectory = getFileNames();
    }

    private boolean directoryIsChanged() {
        return (getDirDifference().size() > 0);
    }

    private Set<String> getDirDifference() {

        Set<String> diff = getFileNames();
        diff.removeAll(filesInDirectory);
        return diff;
    }

    private boolean tmpFileExists() {
        Set<String> diff = getDirDifference();
        if (diff.size() > 0) {
            for (String filename : diff) {
                if (isTmpFile(filename)) return true;
            }
        }
        return false;
    }

    private void waitUntilFileStartDownloaded() {

        FluentWait<WebDriver> wait = new FluentWait(Browser.getInstance().getDriver())
                .withTimeout(TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(TIME_INTERVAL, TimeUnit.SECONDS);

        wait.until((WebDriver wd) -> directoryIsChanged());


    }

    public void waitUntilFileDownloaded() {

        waitUntilFileStartDownloaded();
        FluentWait<WebDriver> wait = new FluentWait(Browser.getInstance().getDriver())
                .withTimeout(TIMEOUT, TimeUnit.SECONDS)
                .pollingEvery(TIME_INTERVAL, TimeUnit.SECONDS);

        wait.until((WebDriver wd) -> !tmpFileExists());

        fileDownloaded = (String) getDirDifference().toArray()[0];
        info(getLoc("loc.downloaded"));
    }


    public void assertFileDownloaded() {

        File f = new File(downloadPath + "\\" + fileDownloaded);
        Assert.assertTrue(f.exists());
        Assert.assertTrue(f.length() > 0);

    }

    public void clean() {

        Set<String> diff = getDirDifference();
        for (String file : diff) {
            new File(downloadPath + "\\" + file).delete();

        }

    }

    @Override
    protected String formatLogMsg(String message) {
        return String.format("%1$s : '%2$s' %3$s", getLoc("loc.file"), fileDownloaded, message);
    }
}

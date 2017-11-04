package task5.forms;

import org.openqa.selenium.By;
import webdriver.BaseForm;
import webdriver.elements.Button;
import webdriver.elements.Label;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/28/2017.
 */
public class SteamGameForm extends BaseForm {

    private final static String REGEXP_DISCOUNT = "^(-)(\\d{1,2})(%)";
    private final static String REGEXP_PRICE = "^(\\$)(\\d+\\.*\\d+)";
    private final static String DISCOUNT_KEY = "discount";
    private final static String OLDPRICE_KEY = "oldprice";
    private final static String PRICE_KEY = "price";
    private Label lbPurchase = new Label(By.cssSelector("div.game_area_purchase"), "game purchase area");
    private Label lbDiscount = new Label(By.xpath(
            "//div[@class=\"game_area_purchase_game\"]//div[@class=\"discount_pct\"]"), "discount");
    private Label lbOldPrice = new Label(By.xpath(
            "//div[@id=\"game_area_purchase\"]//div[@class=\"discount_prices\"]/div[@class=\"discount_original_price\"]"), "oldprice");
    private Label lbPrice = new Label(By.xpath(
            "//div[@id=\"game_area_purchase\"]//div[@class=\"discount_prices\"]/div[@class=\"discount_final_price\"]"), "price");
    private Button btnInstall = new Button(By.xpath("//*[@id=\"global_action_menu\"]/div/a[contains(@class,\"header_install\")]"), "button install");

    public SteamGameForm() {
        super(By.id("game_area_purchase"), "Steam Product");
    }


    public void assertPrices(Map<String, Double> prices) {
        Matcher m;
        Pattern p = Pattern.compile(REGEXP_DISCOUNT);
        m = p.matcher(lbDiscount.getText());
        if (m.find())
            assertEquals(Double.parseDouble(m.group(2)), prices.get(DISCOUNT_KEY));
        p = Pattern.compile(REGEXP_PRICE);
        m = p.matcher(lbOldPrice.getText());
        if (m.find())
            assertEquals(Double.parseDouble(m.group(2)), prices.get(OLDPRICE_KEY));
        m = p.matcher(lbPrice.getText());
        if (m.find())
            assertEquals(Double.parseDouble(m.group(2)), prices.get(PRICE_KEY));
    }

    public void goInstall() {
        btnInstall.click();
    }
}

package task5.elements;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import webdriver.elements.BaseElement;
import webdriver.elements.Label;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Anastasia Pauliuchuk
 *         created:  10/27/2017.
 */
public class SteamGamesList extends BaseElement {

    private final static String REGEXP_DISCOUNT = "^(-)(\\d{1,2})(%)";
    private final static String REGEXP_PRICE = "^(\\$)(\\d+\\.*\\d+)";
    private final static String ITEM_SELECTOR_TEMPLATE = "//div[@id=\"DiscountsRows\"]/a%s/div[@class=\"discount_block tab_item_discount\"]";
    private final static String D_SELECTOR = "div.discount_pct";
    private final static String D_OLD_PRICE_SELECTOR = "div.discount_original_price";
    private final static String D_PRICE_SELECTOR = "div.discount_final_price";
    private ArrayList<Label> items;
    private Label maxDiscountItem;
    private Double discount;
    private String priceText;
    private String oldPriceText;


    public SteamGamesList(By loc, String nameOf) {
        super(loc, nameOf);
        items = new ArrayList<>();
    }

    public void createList() {

        List<WebElement> list = this.getElement().findElements(By.xpath(String.format(ITEM_SELECTOR_TEMPLATE, "")));

        for (int i = 1; i <= list.size(); i++) {
            Label item = new Label(By.xpath(String.format(ITEM_SELECTOR_TEMPLATE, String.format("[%d]", i))), String.format("item #%d", i));
            items.add(item);

        }
    }

    public void searchMaxDiscountGame() {


        Label maxLabel = null;
        Double maxDiscount = 0.0;
        Double d;
        Pattern p = Pattern.compile(REGEXP_DISCOUNT);
        Matcher m;

        for (Label l : items) {
            m = p.matcher(l.getElement().findElement(By.cssSelector(D_SELECTOR)).getText());
            if (m.find()) {
                d = Double.parseDouble(m.group(2));
                if (d > maxDiscount) {
                    maxDiscount = d;
                    maxLabel = l;
                }
            }
        }
        this.maxDiscountItem = maxLabel;
        this.discount = maxDiscount;
        this.priceText = maxDiscountItem.getElement().findElement(By.cssSelector(D_PRICE_SELECTOR)).getText();
        this.oldPriceText = maxDiscountItem.getElement().findElement(By.cssSelector(D_OLD_PRICE_SELECTOR)).getText();
        info(getLoc("loc.maxdiscountfound") + ": " + maxDiscount.toString());
    }

    public Map<String, Double> getMaxDiscountValues() {

        Map<String, Double> map = new HashMap<String, Double>();
        map.put("discount", this.discount);
        Pattern p = Pattern.compile(REGEXP_PRICE);
        Matcher m;
        m = p.matcher(this.priceText);
        if (m.find()) map.put("price", Double.parseDouble(m.group(2)));
        m = p.matcher(this.oldPriceText);
        if (m.find()) map.put("oldprice", Double.parseDouble(m.group(2)));
        return map;
    }


    public Label getMaxDiscountItem() {
        return maxDiscountItem;
    }

    @Override
    public String getElementType() {
        return getLoc("loc.list");
    }

}

package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishCardPage {
    private static SelenideElement newHeading = $("[class='heading heading_size_xl heading_theme_alfa-on-white']").shouldHave(text("Пополнение карты"));
    private static SelenideElement amountField= $("[data-test-id=amount] input");
    private static SelenideElement fromField= $("[data-test-id=from] input");
    private static SelenideElement transferButton = $("[data-test-id=action-transfer]");

    public ReplenishCardPage(){
        newHeading.should(appear, Duration.ofSeconds(20));
    }

    public static DashboardPage transferMoney(int amount, String cardNumber){

        amountField.setValue(String.valueOf(amount));
        fromField.setValue(cardNumber);
        transferButton.click();
        return new DashboardPage();
    }

}

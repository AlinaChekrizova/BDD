package page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import lombok.val;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;


public class DashboardPage {
    private static SelenideElement heading = $("[data-test-id=dashboard]");
//    private static SelenideElement depositButton = $("[data-test-id=92df3f1c-a033-48e6-8390-206f6b1f56c0v] button");


//    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public static ReplenishCardPage replenishCard(String id) {
        $("[data-test-id=\""+id+"\"] button").click();
        return new ReplenishCardPage();
    }


    public int getCardBalance(String id) {
        val text = $("[data-test-id=\""+id+"\"]").text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }









}

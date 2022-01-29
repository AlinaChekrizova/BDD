import data.DataHelper;
import lombok.var;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.ReplenishCardPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {
    @Test
    public void shouldBackToDashboardAfterReplenishCard() {
        String CARD = "92df3f1c-a033-48e6-8390-206f6b1f56c0";
        int amount = 10;
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationInfo);
        int BEFORE = dashboardPage.getCardBalance(CARD);
        ReplenishCardPage replenishCardPage = dashboardPage.replenishCard(CARD);
        DashboardPage dashboardPage2 = replenishCardPage.transferMoney(amount, "5559 0000 0000 0002");
        int AFTER = dashboardPage2.getCardBalance(CARD);
        int actual = AFTER;
        int expected = BEFORE + amount;
        assertEquals(expected, actual);
    }

}

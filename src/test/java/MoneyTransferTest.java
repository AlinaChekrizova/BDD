import data.DataHelper;
import org.junit.jupiter.api.Test;
import page.DashboardPage;
import page.LoginPage;
import page.ReplenishCardPage;
import page.VerificationPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MoneyTransferTest {


    @Test
    public void shouldBackToDashboardAfterReplenishCard() {
        String firstCardId = DataHelper.getCardId().getFirstCardId();
        String secondCardId = DataHelper.getCardId().getSecondCardId();
        String firstCardNumber = DataHelper.getCardNumbers().getFirstCard();
        String secondCardNumber = DataHelper.getCardNumbers().getSecondCard();
        int amount = 10;
        open("http://localhost:9999");
        LoginPage loginPage = new LoginPage();
        DataHelper.AuthInfo authInfo = DataHelper.getAuthInfo();
        VerificationPage verificationPage = loginPage.validLogin(authInfo);
        DataHelper.VerificationCode verificationInfo = DataHelper.getVerificationCodeFor(authInfo);
        DashboardPage dashboardPage = verificationPage.validVerify(verificationInfo);
        int before1 = dashboardPage.getCardBalance(firstCardId);
        int before2 = dashboardPage.getCardBalance(secondCardId);
        ReplenishCardPage replenishCardPage = dashboardPage.replenishCard(firstCardId);
        DashboardPage dashboardPage2 = replenishCardPage.transferMoney(amount, secondCardNumber);
        int after1 = dashboardPage2.getCardBalance(firstCardId);
        int after2 = dashboardPage2.getCardBalance(secondCardId);

        int actual = after1;
        int expected = before1 + amount;
        int actual1 = after2;
        int expected1 = before2 - amount;



        assertEquals(expected, actual);
        assertEquals(expected1, actual1);
    }

}

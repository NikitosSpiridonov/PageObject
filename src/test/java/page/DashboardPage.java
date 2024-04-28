package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {

    private final String cardAttribute = "data-test-id";
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";
    private final SelenideElement dashboard = $("[data-test-id=dashboard]");
    private final ElementsCollection cardsElements = $$(".list__item div");
    private final SelenideElement actionReload = $("[data-test-id=action-reload]");

    public DashboardPage() {
        dashboard.shouldBe(visible);
        cardsElements.get(0).shouldBe(visible);
    }

    public int getCardBalance(DataHelper.Card card) {
        return extractBalance(cardsElements.findBy(attribute(cardAttribute, card.getId())).shouldBe(visible).text());
    }

    private int extractBalance(String text) {
        int start = text.indexOf(balanceStart);
        int finish = text.indexOf(balanceFinish);
        String value = text.substring(start + balanceStart.length(), finish);
        return (int) Math.round(Double.parseDouble(value.replace(",", "."))); // отбрасываю копейки, меняю запятые на точки
    }

    public TransferPage clickButtonBalanceUp(DataHelper.Card card) {
        cardsElements.findBy(attribute(cardAttribute, card.getId())).lastChild().click();
        return new TransferPage();
    }

    public void clickActionReload() {
        actionReload.click();
    }
}
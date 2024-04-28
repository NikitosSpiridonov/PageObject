package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class TransferPage {

    private final SelenideElement inputAmount = $("[data-test-id=amount] input");
    private final SelenideElement inputFrom = $("[data-test-id=from] input");
    private final SelenideElement submitButton = $("[data-test-id=action-transfer]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");
    private final SelenideElement actionCancel = $("[data-test-id=action-cancel]");

    public TransferPage() {
        $("h1").shouldBe(visible).shouldHave(text("Пополнение карты"));
        inputAmount.shouldBe(visible);
    }

    public void validTransfer(int amount, DataHelper.Card card) {
        inputAmount.setValue(Integer.toString(amount));
        inputFrom.setValue(card.getNumber());
        submitButton.click();
    }

    public void getError() {
        errorNotification.shouldBe(visible).shouldHave(text("Ошибка"));
    }

    public void clickActionCancel() {
        actionCancel.click();
    }

    public void clickSubmitButton() {
        submitButton.click();
    }
}
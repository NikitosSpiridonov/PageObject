package page;

import com.codeborne.selenide.SelenideElement;
import data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class VerificationPage {
    private final SelenideElement inputCode = $("[data-test-id=code] input");
    private final SelenideElement submitButton = $("[data-test-id=action-verify]");

    public VerificationPage() {
        inputCode.shouldBe(visible);
    }

    public DashboardPage validVerify(DataHelper.VerificationCode verificationCode) {
        inputCode.setValue(verificationCode.getCode());
        submitButton.click();
        return new DashboardPage();
    }
}
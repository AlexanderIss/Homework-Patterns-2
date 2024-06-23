import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static com.codeborne.selenide.Selenide.*;

public class CardDeliveryTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void userCorrectRegisteredActive() {
        var registeredUser = DataGenerator.Registration.getRegistrationUser("active");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[type=button]").click();
        $("h2").shouldHave(Condition.exactText("  Личный кабинет")).shouldBe(Condition.visible);
    }

    @Test
    void userIncorrectPasswordRegisteredActive() {
        var registeredUser = DataGenerator.Registration.getRegistrationUser("active");
        var incorrectPassword = DataGenerator.getRandomPassword();
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(incorrectPassword);
        $("[type=button]").click();
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    void userIncorrectLoginRegisteredActive() {
        var registeredUser = DataGenerator.Registration.getRegistrationUser("active");
        var incorrectLogin = DataGenerator.getRandomLogin();
        $("[data-test-id='login'] input").setValue(incorrectLogin);
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[type=button]").click();
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }

    @Test
    void userBlocked() {
        var registeredUser = DataGenerator.Registration.getRegistrationUser("blocked");
        $("[data-test-id='login'] input").setValue(registeredUser.getLogin());
        $("[data-test-id='password'] input").setValue(registeredUser.getPassword());
        $("[type=button]").click();
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Пользователь заблокирован")).shouldBe(Condition.visible);
    }

    @Test
    void userNotRegistered() {
        var notRegisteredUser = DataGenerator.Registration.getUser("active");
        $("[data-test-id='login'] input").setValue(notRegisteredUser.getLogin());
        $("[data-test-id='password'] input").setValue(notRegisteredUser.getPassword());
        $("[type=button]").click();
        $(".notification__content").shouldHave(Condition.exactText("Ошибка! Неверно указан логин или пароль")).shouldBe(Condition.visible);
    }
}

package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.*;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

@Tag("smoke")
@DisplayName("Класс для проверки формы регистрации на сайте DemoQA")
public class RegistrationWithFakerTests extends TestBase {
    private final String
            PRACTICE_FORM_URL = "https://demoqa.com/automation-practice-form",
            firstName = getRandomFirstName(),
            lastName = getRandomLastName(),
            fullName = firstName + " " + lastName,
            email = getRandomEmail(),
            phoneNumber = getRandomPhoneNumber(),
            gender = getRandomGender(),
            month = getRandomMonth(),
            year = getRandomYear(),
            day = getRandomDay(month),
            dateOfBirth = day + " " + month + "," + year,
            subject = getRandomSubject(),
            hobbies = getRandomHobby(),
            picture = getRandomPicture(),
            address = getRandomAddress(),
            state = getRandomState(),
            city = getRandomCity(state),
            stateAndCity = state + " " + city;

    RegistrationPage registrationPage = new RegistrationPage();

    @BeforeEach
    void initialSetUp(){

        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем страницу и удаляем рекламу", () -> {
            registrationPage
                    .openPage()
                    .removeBanner();
        });
    }

    @Test
    @Feature("Регистрация пользователя на сайте DemoQA")
    @Story("Я как пользователь хочу иметь возможность регистрации с заполнением всех полей на форме")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("Тест формы регистрации с заполнением всех полей")
    void fillFormWithAllFieldsTest() {

        step("Заполняем все поля формы случайными значениями и жмем на кнопу Submit", () ->
        {
            registrationPage
                    .setFirstNameInput(firstName)
                    .setLastNameInput(lastName)
                    .setEmailInput(email)
                    .setGenderInput(gender)
                    .setUserNumberInput(phoneNumber)
                    .setDateOfBirth(day, month , year)
                    .setSubjectInput(subject)
                    .setHobbiesInput(hobbies)
                    .uploadPicture(picture)
                    .setCurrentAddress(address)
                    .setState(state)
                    .setCity(city)
                    .clickSubmitButton();
        });

        step("Проверяем таблицу с заполненными полями", () ->
        {
            registrationPage
                    .checkIfTableIsVisible()
                    .checkResult("Student Name", fullName)
                    .checkResult("Student Email", email)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber)
                    .checkResult("Date of Birth", dateOfBirth)
                    .checkResult("Subjects", subject)
                    .checkResult("Hobbies", hobbies)
                    .checkResult("Picture", picture)
                    .checkResult("Address", address)
                    .checkResult("State and City", stateAndCity);

        });
    }

    @Test
    @Feature("Регистрация пользователя на сайте DemoQA")
    @Story("Я как пользователь хочу иметь возможность регистрации с заполнением только обязательных полей на форме")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("Тест формы регистрации с заполнением обязательных полей")
    void fillFormWithRequiredFieldsTest() {

        step("Заполняем все обязательные поля формы случайными значениями и жмем на кнопу Submit", () ->
        {
            registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderInput(gender)
                .setUserNumberInput(phoneNumber)
                .clickSubmitButton();

        });

        step("Проверяем таблицу с заполненными полями", () ->
        {
            registrationPage
                    .checkIfTableIsVisible()
                    .checkResult("Student Name", fullName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber);
        });
    }

    @Test
    @Feature("Регистрация пользователя на сайте DemoQA")
    @Story("Я как пользователь хочу иметь возможность получать ошибку при не заполненных полях")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("Тест формы регистрации без заполнения обязательных полей")
    void emptyFieldsTest() {

        step("Жмем на кнопу Submit", () -> {
            registrationPage
                    .clickSubmitButton();
        });

        step("Проверяем отсутствие таблицы ", () ->
        {
            registrationPage
                    .checkIfTableIsNotVisible();
        });

    }
}

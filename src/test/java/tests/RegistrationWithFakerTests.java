package tests;

import helpers.TestBase;
import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.RegistrationPage;

import static io.qameta.allure.Allure.step;
import static utils.RandomUtils.*;

@Tag("DemoQa")
@DisplayName(" ласс дл€ проверки формы регистрации на сайте DemoQA")
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

        step("ќткрываем страницу и удал€ем рекламу", () -> {
            registrationPage
                    .openPage()
                    .removeBanner();
        });
    }

    @Test
    @Feature("–егистраци€ пользовател€ на сайте DemoQA")
    @Story("я как пользователь хочу иметь возможность регистрации с заполнением всех полей на форме")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("“ест формы регистрации с заполнением всех полей")
    void fillFormWithAllFieldsTest() {

        step("«аполн€ем все пол€ формы случайными значени€ми и жмем на кнопу Submit", () ->
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

        step("ѕровер€ем таблицу с заполненными пол€ми", () ->
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
    @Feature("–егистраци€ пользовател€ на сайте DemoQA")
    @Story("я как пользователь хочу иметь возможность регистрации с заполнением только об€зательных полей на форме")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("“ест формы регистрации с заполнением об€зательных полей")
    void fillFormWithRequiredFieldsTest() {

        step("«аполн€ем все об€зательные пол€ формы случайными значени€ми и жмем на кнопу Submit", () ->
        {
            registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderInput(gender)
                .setUserNumberInput(phoneNumber)
                .clickSubmitButton();

        });

        step("ѕровер€ем таблицу с заполненными пол€ми", () ->
        {
            registrationPage
                    .checkIfTableIsVisible()
                    .checkResult("Student Name", fullName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber);
        });
    }

    @Test
    @Feature("–егистраци€ пользовател€ на сайте DemoQA")
    @Story("я как пользователь хочу иметь возможность получать ошибку при не заполненных пол€х")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("“ест формы регистрации без заполнени€ об€зательных полей")
    void emptyFieldsTest() {

        step("∆мем на кнопу Submit", () -> {
            registrationPage
                    .clickSubmitButton();
        });

        step("ѕровер€ем отсутствие таблицы ", () ->
        {
            registrationPage
                    .checkIfTableIsNotVisible();
        });

    }
}

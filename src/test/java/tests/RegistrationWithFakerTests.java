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
@DisplayName("����� ��� �������� ����� ����������� �� ����� DemoQA")
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

        step("��������� �������� � ������� �������", () -> {
            registrationPage
                    .openPage()
                    .removeBanner();
        });
    }

    @Test
    @Feature("����������� ������������ �� ����� DemoQA")
    @Story("� ��� ������������ ���� ����� ����������� ����������� � ����������� ���� ����� �� �����")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("���� ����� ����������� � ����������� ���� �����")
    void fillFormWithAllFieldsTest() {

        step("��������� ��� ���� ����� ���������� ���������� � ���� �� ����� Submit", () ->
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

        step("��������� ������� � ������������ ������", () ->
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
    @Feature("����������� ������������ �� ����� DemoQA")
    @Story("� ��� ������������ ���� ����� ����������� ����������� � ����������� ������ ������������ ����� �� �����")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("���� ����� ����������� � ����������� ������������ �����")
    void fillFormWithRequiredFieldsTest() {

        step("��������� ��� ������������ ���� ����� ���������� ���������� � ���� �� ����� Submit", () ->
        {
            registrationPage
                .setFirstNameInput(firstName)
                .setLastNameInput(lastName)
                .setGenderInput(gender)
                .setUserNumberInput(phoneNumber)
                .clickSubmitButton();

        });

        step("��������� ������� � ������������ ������", () ->
        {
            registrationPage
                    .checkIfTableIsVisible()
                    .checkResult("Student Name", fullName)
                    .checkResult("Gender", gender)
                    .checkResult("Mobile", phoneNumber);
        });
    }

    @Test
    @Feature("����������� ������������ �� ����� DemoQA")
    @Story("� ��� ������������ ���� ����� ����������� �������� ������ ��� �� ����������� �����")
    @Owner("NikitaRyazanov")
    @Severity(SeverityLevel.BLOCKER)
    @Link(value = "PracticeForm", url = PRACTICE_FORM_URL)
    @DisplayName("���� ����� ����������� ��� ���������� ������������ �����")
    void emptyFieldsTest() {

        step("���� �� ����� Submit", () -> {
            registrationPage
                    .clickSubmitButton();
        });

        step("��������� ���������� ������� ", () ->
        {
            registrationPage
                    .checkIfTableIsNotVisible();
        });

    }
}

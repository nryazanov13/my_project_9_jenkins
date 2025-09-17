package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.CalendarComponent;
import pages.components.ResultTableComponent;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.executeJavaScript;


public class RegistrationPage {
    private final SelenideElement firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderWrapper = $("#genterWrapper"),
            userNumberInput = $("#userNumber"),
            calendarInput = $("#dateOfBirthInput"),
            subjectInput = $("#subjectsInput"),
            hobbiesInput = $("#hobbiesWrapper"),
            uploadPictureInput = $("#uploadPicture"),
            currentAddressInput = $("#currentAddress"),
            stateInput = $("#state"),
            cityInput = $("#city"),
            stateCityInput = $("#stateCity-wrapper"),

    submitInput = $("#submit");

    CalendarComponent calendarComponent = new CalendarComponent();

    ResultTableComponent resultTableComponent = new ResultTableComponent();

    public RegistrationPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    public RegistrationPage removeBanner() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }

    public RegistrationPage setFirstNameInput(String value) {
        firstNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setLastNameInput(String value) {
        lastNameInput.setValue(value);
        return this;
    }

    public RegistrationPage setEmailInput(String value) {
        emailInput.setValue(value);
        return this;
    }

    public RegistrationPage setGenderInput(String value) {
        genderWrapper.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setUserNumberInput(String value) {
        userNumberInput.setValue(value);
        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        calendarInput.click();
        calendarComponent.setDate(day, month, year);
        return this;
    }

    public RegistrationPage setSubjectInput(String value) {
        subjectInput.setValue(value).pressEnter();
        return this;
    }

    public RegistrationPage setHobbiesInput(String value) {
        hobbiesInput.$(byText(value)).click();
        return this;
    }

    //посмотреть если в java такой метод
    public RegistrationPage uploadPicture(String value) {
        uploadPictureInput.uploadFromClasspath(value);
        return this;
    }

    public RegistrationPage setCurrentAddress(String value) {
        currentAddressInput.setValue(value);
        return this;
    }

    public RegistrationPage setState(String value) {
        stateInput.click();
        stateCityInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage setCity(String value) {
        cityInput.click();
        stateCityInput.$(byText(value)).click();
        return this;
    }

    public RegistrationPage clickSubmitButton() {
        submitInput.scrollTo().click();
        return this;
    }

    public RegistrationPage checkIfTableIsVisible() {
        resultTableComponent.checkIfTableIsVisible();
        return this;
    }

    public RegistrationPage checkIfTableIsNotVisible() {
        resultTableComponent.checkIfTableIsNotVisible();
        return this;
    }


    //лучше вынести в отдельный компонент
    public RegistrationPage checkResult(String key, String value) {
        resultTableComponent.checkResult(key,value);
        return this;
    }
}

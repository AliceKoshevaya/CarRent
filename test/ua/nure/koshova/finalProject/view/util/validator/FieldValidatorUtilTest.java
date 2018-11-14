package ua.nure.koshova.finalProject.view.util.validator;

import org.junit.Assert;
import org.junit.Test;
import ua.nure.koshova.finalProject.view.util.validator.util.FieldValidatorUtil;

import static org.junit.Assert.*;

public class FieldValidatorUtilTest {
    @Test
    public void validateLogin() throws Exception {
        String login = "";
        String actualFileContent = FieldValidatorUtil.validateLogin(login);
        String expectedFileContent = "Login must be not empty";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }

    @Test
    public void validatePassword() throws Exception {
        String password = "fgh5";
        String actualFileContent = FieldValidatorUtil.validatePassword(password);
        String expectedFileContent = "Password contains invalid characters";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }

    @Test
    public void validateName() throws Exception {
        String name = "Alice";
        String actualFileContent = FieldValidatorUtil.validateName(name);
        String expectedFileContent = "";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }

    @Test
    public void validateLastName() throws Exception {
        String lastName = "Koshova";
        String actualFileContent = FieldValidatorUtil.validateLastName(lastName);
        String expectedFileContent = "";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }

    @Test
    public void validateSeries() throws Exception {
        String seria = "FF56789";
        String actualFileContent = FieldValidatorUtil.validateSeries(seria);
        String expectedFileContent = "Field passport series contains invalid characters";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }

}
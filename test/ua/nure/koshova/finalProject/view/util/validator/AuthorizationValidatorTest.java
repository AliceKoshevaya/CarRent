package ua.nure.koshova.finalProject.view.util.validator;

import org.junit.Assert;
import org.junit.Test;

public class AuthorizationValidatorTest {

    @Test
    public void validate() throws Exception {
        String login = "user";
        String password = "Users777";
        String actualFileContent = AuthorizationValidator.validate(login,null,password);;
        String expectedFileContent = "This user not exists";
        Assert.assertEquals(expectedFileContent, actualFileContent);
    }
}
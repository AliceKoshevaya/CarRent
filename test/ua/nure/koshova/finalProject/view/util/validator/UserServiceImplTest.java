package ua.nure.koshova.finalProject.view.util.validator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import ua.nure.koshova.finalProject.db.dao.impl.UserDaoImpl;
import ua.nure.koshova.finalProject.db.entity.User;
import ua.nure.koshova.finalProject.service.Impl.UserServiceImpl;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    UserDaoImpl usersDao = UserDaoImpl.getInstance();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testRegisterUser() {
        User user = new User();
        String login = "user";
        String password = "Users777";
        when(usersDao.findUser(login,password)).thenReturn(user);

        user.setLogin(login);
        user.setPassword(password);

        UserServiceImpl userServiceImpl = new UserServiceImpl();


    }
}

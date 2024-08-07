package user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests the UserDAO implementation.
 * It verifies the CRUD operations for users.
 * @author Ziya
 */


 
public class UserDAOTest {

    private UserDAOImpl userDAO;
    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @BeforeEach
    public void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        userDAO = new UserDAOImpl(dataSource);
    }

    @Test
    public void testAddUser() throws SQLException {
        doNothing().when(statement).executeUpdate();

        User user = new User(1, "Ziya Gurel", "ziyagurel3@gmail.com", "password123", UserType.CONSUMER, "123-456-7890", "1177 Belanger Avenue");
        userDAO.create(user);

        verify(statement, times(1)).executeUpdate();
    }

    @Test
    public void testReadUser() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("user_id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("Ziya Gurel");
        when(resultSet.getString("email")).thenReturn("ziyagurel3@gmail.com");
        when(resultSet.getString("password")).thenReturn("password123");
        when(resultSet.getString("user_type")).thenReturn("CONSUMER");
        when(resultSet.getString("contact_info")).thenReturn("123-456-7890");
        when(resultSet.getString("location")).thenReturn("1177 Belanger Avenue");

        User user = userDAO.read(1);
        assertNotNull(user);
        assertEquals("Ziya Gurel", user.getName());
    }

    @Test
    public void testUpdateUser() throws SQLException {
        doNothing().when(statement).executeUpdate();

        User user = new User(1, "Ziya Gurel", "ziyagurel3@gmail.com", "newpassword123", UserType.CONSUMER, "123-456-7890", "1177 Belanger Avenue");
        userDAO.update(user);

        verify(statement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteUser() throws SQLException {
        doNothing().when(statement).executeUpdate();

        userDAO.delete(1);

        verify(statement, times(1)).executeUpdate();
    }
}

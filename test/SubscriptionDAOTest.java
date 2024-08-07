package subscription;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests the SubscriptionDAO implementation.
 * It verifies the CRUD operations for subscriptions.
 * @author Ziya
 */

 
public class SubscriptionDAOTest {

    private SubscriptionDAOImpl subscriptionDAO;
    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    /**
     * This method sets up the test environment.
     * It runs before each test.
     */
    @BeforeEach
    public void setUp() throws SQLException {
        dataSource = mock(DataSource.class);
        connection = mock(Connection.class);
        statement = mock(PreparedStatement.class);
        resultSet = mock(ResultSet.class);

        when(dataSource.getConnection()).thenReturn(connection);
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);

        subscriptionDAO = new SubscriptionDAOImpl(dataSource);
    }

    /**
     * This test checks if a subscription can be added successfully.
     */
    @Test
    public void testAddSubscription() throws SQLException {
        doNothing().when(statement).executeUpdate();

        Subscription subscription = new Subscription("Ottawa", "EMAIL", "Fruits");
        subscriptionDAO.create(subscription);

        verify(statement, times(1)).executeUpdate();
    }

    /**
     * This test checks if a subscription can be retrieved successfully.
     */
    @Test
    public void testReadSubscription() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("subscription_id")).thenReturn(1);
        when(resultSet.getString("location")).thenReturn("Ottawa");
        when(resultSet.getString("communication_method")).thenReturn("EMAIL");
        when(resultSet.getString("food_preferences")).thenReturn("Fruits");

        Subscription subscription = subscriptionDAO.read(1);
        assertNotNull(subscription);
        assertEquals("Ottawa", subscription.getLocation());
    }

    /**
     * This test checks if a subscription can be updated successfully.
     */
    @Test
    public void testUpdateSubscription() throws SQLException {
        doNothing().when(statement).executeUpdate();

        Subscription subscription = new Subscription("Ottawa", "EMAIL", "Vegetables");
        subscription.setSubscriptionId(1);
        subscriptionDAO.update(subscription);

        verify(statement, times(1)).executeUpdate();
    }

    /**
     * This test checks if a subscription can be deleted successfully.
     */
    @Test
    public void testDeleteSubscription() throws SQLException {
        doNothing().when(statement).executeUpdate();

        subscriptionDAO.delete(1);

        verify(statement, times(1)).executeUpdate();
    }
}

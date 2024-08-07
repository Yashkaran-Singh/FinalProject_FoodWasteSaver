package order;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * This class tests the OrderDAO class.
 * It checks the CRUD operations for orders.
 * @author Ziya
 */
public class OrderDAOTest {

    private OrderDAO orderDAO;
    private Order testOrder;
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
        orderDAO = new OrderDAOImpl(dataSource);
        testOrder = new Order(1, 1, 1, new Date(), 5, 10.0, false);
    }

    /**
     * This test checks if an order can be created successfully.
     */
    @Test
    public void testCreateOrder() throws SQLException {
        when(connection.prepareStatement(anyString(), anyInt())).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt(1)).thenReturn(1);

        orderDAO.create(testOrder);
        verify(statement, times(1)).executeUpdate();
    }

    /**
     * This test checks if an order can be retrieved successfully.
     */
    @Test
    public void testReadOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);
        when(statement.executeQuery()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("order_id")).thenReturn(1);
        when(resultSet.getInt("user_id")).thenReturn(1);
        when(resultSet.getInt("surplus_food_id")).thenReturn(1);
        when(resultSet.getDate("order_date")).thenReturn(new java.sql.Date(new Date().getTime()));
        when(resultSet.getInt("quantity")).thenReturn(5);
        when(resultSet.getDouble("total_price")).thenReturn(10.0);
        when(resultSet.getBoolean("is_completed")).thenReturn(false);

        Order retrievedOrder = orderDAO.read(1);
        assertNotNull(retrievedOrder, "The order should be retrieved successfully");
    }

    /**
     * This test checks if an order can be deleted successfully.
     */
    @Test
    public void testDeleteOrder() throws SQLException {
        when(connection.prepareStatement(anyString())).thenReturn(statement);

        orderDAO.delete(1);
        verify(statement, times(1)).executeUpdate();
    }
}


    /**
     *  
     */
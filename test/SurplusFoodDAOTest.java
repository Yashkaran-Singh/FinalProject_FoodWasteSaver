package surplusfood;

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
 * This class tests the SurplusFoodDAO implementation.
 * It verifies the CRUD operations for surplus food items.
 * @author Ziya
 */


 
public class SurplusFoodDAOTest {

    private SurplusFoodDAOImpl surplusFoodDAO;
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

        surplusFoodDAO = new SurplusFoodDAOImpl(dataSource);
    }

    @Test
    public void testAddSurplusFood() throws SQLException {
        doNothing().when(statement).executeUpdate();

        SurplusFood surplusFood = new SurplusFood(1, "Apples", "Fresh apples", 100, new java.sql.Date(System.currentTimeMillis()), 0.5, 1);
        surplusFoodDAO.create(surplusFood);

        verify(statement, times(1)).executeUpdate();
    }

    @Test
    public void testReadSurplusFood() throws SQLException {
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getInt("surplus_food_id")).thenReturn(1);
        when(resultSet.getString("item_name")).thenReturn("Apples");
        when(resultSet.getString("item_description")).thenReturn("Fresh apples");
        when(resultSet.getInt("quantity")).thenReturn(100);
        when(resultSet.getDate("expiration_date")).thenReturn(new java.sql.Date(System.currentTimeMillis()));
        when(resultSet.getDouble("discount_rate")).thenReturn(0.5);
        when(resultSet.getInt("user_id")).thenReturn(1);

        SurplusFood surplusFood = surplusFoodDAO.read(1);
        assertNotNull(surplusFood);
        assertEquals("Apples", surplusFood.getItemName());
    }

    @Test
    public void testUpdateSurplusFood() throws SQLException {
        doNothing().when(statement).executeUpdate();

        SurplusFood surplusFood = new SurplusFood(1, "Apples", "Updated description", 100, new java.sql.Date(System.currentTimeMillis()), 0.5, 1);
        surplusFoodDAO.update(surplusFood);

        verify(statement, times(1)).executeUpdate();
    }

    @Test
    public void testDeleteSurplusFood() throws SQLException {
        doNothing().when(statement).executeUpdate();

        surplusFoodDAO.delete(1);

        verify(statement, times(1)).executeUpdate();
    }
}

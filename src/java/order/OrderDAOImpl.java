/**
    Author  : Yashkaran Singh
*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package order;

/**
 * Implementation of Order Data Access Object (DAO).
 * This class handles database operations related to orders.
 *
 * @author Yashkaran Singh
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;

/**
 *
 * @author Owner
 */
public class OrderDAOImpl implements OrderDAO {
    private final DataSource dataSource;

    /**
     * Constructs an OrderDAOImpl object with the specified DataSource.
     *
     * @param dataSource The DataSource to be used for database operations.
     */
    public OrderDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new order in the database.
     *
     * @param order The Order object to be created.
     */
    @Override
    public void create(Order order) {
        String sql = "INSERT INTO orders (user_id, surplus_food_id, order_date, quantity, total_price, is_completed) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, order.getUserId());
            statement.setInt(2, order.getSurplusFoodId());
            statement.setDate(3, new java.sql.Date(order.getOrderDate().getTime()));
            statement.setInt(4, order.getQuantity());
            statement.setDouble(5, order.getTotalPrice());
            statement.setBoolean(6, order.isCompleted());

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted == 0) {
                throw new SQLException("Creating order failed, no rows inserted.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    order.setOrderId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating order failed, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves an order from the database based on order ID.
     *
     * @param orderId The ID of the order to be retrieved.
     * @return The Order object if found, otherwise null.
     */
    @Override
    public Order read(int orderId) {
        String sql = "SELECT * FROM orders WHERE order_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapOrder(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Deletes an order from the database based on order ID.
     *
     * @param orderId The ID of the order to be deleted.
     */
    @Override
    public void delete(int orderId) {
        String sql = "DELETE FROM orders WHERE order_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, orderId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves all orders placed by a specific user from the database.
     *
     * @param userId The ID of the user.
     * @return A list of Order objects placed by the user.
     */
    @Override
    public List<Order> getOrdersByUser(int userId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Retrieves all orders from the database.
     *
     * @return A list of all Order objects.
     */
    @Override
    public List<Order> getAllOrders() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Helper method to map ResultSet to Order object.
     *
     * @param resultSet The ResultSet containing order data.
     * @return The Order object mapped from ResultSet.
     * @throws SQLException If a SQL exception occurs.
     */
    private Order mapOrder(ResultSet resultSet) throws SQLException {
        int orderId = resultSet.getInt("order_id");
        int userId = resultSet.getInt("user_id");
        int surplusFoodId = resultSet.getInt("surplus_food_id");
        Date orderDate = resultSet.getDate("order_date");
        int quantity = resultSet.getInt("quantity");
        double totalPrice = resultSet.getDouble("total_price");
        boolean isCompleted = resultSet.getBoolean("is_completed");

        return new Order(orderId, userId, surplusFoodId, orderDate, quantity, totalPrice, isCompleted);
    }
}

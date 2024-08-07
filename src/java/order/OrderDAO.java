/**
    Author  : Yashkaran Singh
*/
package order;

import java.util.List;

/**
 * Interface for Order Data Access Object (DAO).
 * Defines methods for interacting with orders in the database.
 */
public interface OrderDAO {
    /**
     * Creates a new order in the database.
     * @param order The Order object to be created.
     */
    void create(Order order);

    /**
     * Retrieves an order from the database based on order ID.
     * @param orderId The ID of the order to be retrieved.
     * @return The Order object if found, otherwise null.
     */
    Order read(int orderId);

    /**
     * Deletes an order from the database based on order ID.
     * @param orderId The ID of the order to be deleted.
     */
    void delete(int orderId);

    /**
     * Retrieves all orders placed by a specific user from the database.
     * @param userId The ID of the user.
     * @return A list of Order objects placed by the user.
     */
    List<Order> getOrdersByUser(int userId);

    /**
     * Retrieves all orders from the database.
     * @return A list of all Order objects.
     */
    List<Order> getAllOrders();

}

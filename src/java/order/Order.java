/**
    Author  : Yashkaran Singh
*/
package order;

import java.util.Date;

/**
 * Represents an order placed by a user for surplus food.
 */
public class Order {
    private int orderId;
    private int userId;
    private int surplusFoodId;
    private Date orderDate;
    private int quantity;
    private double totalPrice;
    private boolean isCompleted;

    /**
     * Constructs an Order object with specified parameters.
     * @param orderId The ID of the order.
     * @param userId The ID of the user placing the order.
     * @param surplusFoodId The ID of the surplus food ordered.
     * @param orderDate The date when the order was placed.
     * @param quantity The quantity of surplus food ordered.
     * @param totalPrice The total price of the order.
     * @param isCompleted Indicates if the order is completed.
     */
    public Order(int orderId, int userId, int surplusFoodId, Date orderDate, int quantity, double totalPrice, boolean isCompleted) {
        this.orderId = orderId;
        this.userId = userId;
        this.surplusFoodId = surplusFoodId;
        this.orderDate = orderDate;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.isCompleted = isCompleted;
    }

    // Getters and setters

    /**
     * Gets the ID of the order.
     * @return The order ID.
     */
    public int getOrderId() {
        return orderId;
    }

    /**
     * Sets the ID of the order.
     * @param orderId The order ID to set.
     */
    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    /**
     * Gets the ID of the user placing the order.
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user placing the order.
     * @param userId The user ID to set.
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the ID of the surplus food ordered.
     * @return The surplus food ID.
     */
    public int getSurplusFoodId() {
        return surplusFoodId;
    }

    /**
     * Sets the ID of the surplus food ordered.
     * @param surplusFoodId The surplus food ID to set.
     */
    public void setSurplusFoodId(int surplusFoodId) {
        this.surplusFoodId = surplusFoodId;
    }

    /**
     * Gets the date when the order was placed.
     * @return The order date.
     */
    public Date getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the date when the order was placed.
     * @param orderDate The order date to set.
     */
    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    /**
     * Gets the quantity of surplus food ordered.
     * @return The quantity of surplus food.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the quantity of surplus food ordered.
     * @param quantity The quantity of surplus food to set.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Gets the total price of the order.
     * @return The total price.
     */
    public double getTotalPrice() {
        return totalPrice;
    }

    /**
     * Sets the total price of the order.
     * @param totalPrice The total price to set.
     */
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Checks if the order is completed.
     * @return True if the order is completed, otherwise false.
     */
    public boolean isCompleted() {
        return isCompleted;
    }

    /**
     * Sets the completion status of the order.
     * @param completed The completion status to set.
     */
    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}

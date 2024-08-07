/**
    Author  : Yashkaran Singh
*/
package subscription;

/**
 * Represents a subscription for receiving updates or notifications.
 */
public class Subscription {
    private int subscriptionId;
    private int userId;
    private String subscriptionType; // Assuming this is a String, but you can change it to an enum if needed
    private String location;
    private String communicationMethod;
    private String foodPreferences;

    /**
     * Constructs a subscription with the specified location, communication method, and food preferences.
     * 
     * @param location the location associated with the subscription
     * @param communicationMethod the communication method preferred for receiving updates
     * @param foodPreferences the food preferences of the subscriber
     */
    public Subscription(String location, String communicationMethod, String foodPreferences) {
        this.location = location;
        this.communicationMethod = communicationMethod;
        this.foodPreferences = foodPreferences;
    }

    // Default constructor
    public Subscription() {
        // You can implement this if needed
    }

    // Getters and setters

    /**
     * Gets the subscription ID.
     * 
     * @return the subscription ID
     */
    public int getSubscriptionId() {
        return subscriptionId;
    }

    /**
     * Sets the subscription ID.
     * 
     * @param subscriptionId the subscription ID to set
     */
    public void setSubscriptionId(int subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    /**
     * Gets the user ID.
     * 
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the user ID.
     * 
     * @param userId the user ID to set
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the subscription type.
     * 
     * @return the subscription type
     */
    public String getSubscriptionType() {
        return subscriptionType;
    }

    /**
     * Sets the subscription type.
     * 
     * @param subscriptionType the subscription type to set
     */
    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    /**
     * Gets the location associated with the subscription.
     * 
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location associated with the subscription.
     * 
     * @param location the location to set
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the communication method preferred for receiving updates.
     * 
     * @return the communication method
     */
    public String getCommunicationMethod() {
        return communicationMethod;
    }

    /**
     * Sets the communication method preferred for receiving updates.
     * 
     * @param communicationMethod the communication method to set
     */
    public void setCommunicationMethod(String communicationMethod) {
        this.communicationMethod = communicationMethod;
    }

    /**
     * Gets the food preferences of the subscriber.
     * 
     * @return the food preferences
     */
    public String getFoodPreferences() {
        return foodPreferences;
    }

    /**
     * Sets the food preferences of the subscriber.
     * 
     * @param foodPreferences the food preferences to set
     */
    public void setFoodPreferences(String foodPreferences) {
        this.foodPreferences = foodPreferences;
    }
}

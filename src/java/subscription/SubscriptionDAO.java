/**
    Author  : Yashkaran Singh
*/
package subscription;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing subscriptions in the database.
 */
public class SubscriptionDAO {
    
    // JDBC URL, username, and password
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fwrp?zeroDateTimeBehavior=CONVERT_TO_NULL";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Rooot";

   
    private static final String INSERT_SUBSCRIPTION_SQL = "INSERT INTO subscriptions (location, communication_method, food_preferences) VALUES (?, ?, ?)";

    /**
     * Adds a new subscription to the database.
     * 
     * @param location the location associated with the subscription
     * @param communicationMethod the communication method preferred for receiving updates
     * @param foodPreferences the food preferences of the subscriber
     * @return true if the subscription was successfully added, false otherwise
     */
    public boolean addSubscription(String location, String communicationMethod, String foodPreferences) {
        try (
            // Establish connection
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            // Create prepared statement with parameters
            PreparedStatement pstmt = conn.prepareStatement(INSERT_SUBSCRIPTION_SQL)
        ) {
            // Set parameters
            pstmt.setString(1, location);
            pstmt.setString(2, communicationMethod);
            pstmt.setString(3, foodPreferences);

            // Execute the query
            int rowsInserted = pstmt.executeUpdate();

            // Return true if at least one row was inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}

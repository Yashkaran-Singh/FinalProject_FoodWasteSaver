/**
    Author  : Yashkaran Singh
*/
package feedback;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object (DAO) for managing feedback.
 */
public class FeedbackDAO {

    /**
     * Adds a new feedback to the database.
     * @param description The description of the feedback.
     */
    public void addFeedback(String description) {
        String url = "jdbc:mysql://localhost:3306/fwrp?zeroDateTimeBehavior=CONVERT_TO_NULL";
        String username = "root";
        String password = "Rooot";

        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO feedback (description) VALUES (?)")) {
            stmt.setString(1, description);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

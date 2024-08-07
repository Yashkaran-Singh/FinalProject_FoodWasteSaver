/**
    Author  : Yashkaran Singh
*/
package subscription;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class SubscriptionDAOImpl extends SubscriptionDAO {
    private final DataSource dataSource;

    public SubscriptionDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create(Subscription subscription) {
        String sql = "INSERT INTO subscriptions (user_id, subscription_type, location, communication_method, food_preferences) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subscription.getUserId());
            statement.setString(2, subscription.getSubscriptionType());
            statement.setString(3, subscription.getLocation());
            statement.setString(4, subscription.getCommunicationMethod());
            statement.setString(5, subscription.getFoodPreferences());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Subscription read(int subscriptionId) {
        String sql = "SELECT * FROM subscriptions WHERE subscription_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subscriptionId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapSubscription(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void update(Subscription subscription) {
        String sql = "UPDATE subscriptions SET user_id = ?, subscription_type = ?, location = ?, communication_method = ?, food_preferences = ? WHERE subscription_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subscription.getUserId());
            statement.setString(2, subscription.getSubscriptionType());
            statement.setString(3, subscription.getLocation());
            statement.setString(4, subscription.getCommunicationMethod());
            statement.setString(5, subscription.getFoodPreferences());
            statement.setInt(6, subscription.getSubscriptionId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int subscriptionId) {
        String sql = "DELETE FROM subscriptions WHERE subscription_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, subscriptionId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Subscription mapSubscription(ResultSet resultSet) throws SQLException {
        Subscription subscription = new Subscription();
        subscription.setSubscriptionId(resultSet.getInt("subscription_id"));
        subscription.setUserId(resultSet.getInt("user_id"));
        subscription.setSubscriptionType(resultSet.getString("subscription_type"));
        subscription.setLocation(resultSet.getString("location"));
        subscription.setCommunicationMethod(resultSet.getString("communication_method"));
        subscription.setFoodPreferences(resultSet.getString("food_preferences"));
        return subscription;
    }
}

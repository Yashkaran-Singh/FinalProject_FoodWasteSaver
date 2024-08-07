/**
    Author  : Yashkaran Singh
*/
package user;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Implementation of User Data Access Object (DAO).
 * This class handles database operations related to users.
 */
public class UserDAOImpl implements UserDAO {
    private final DataSource dataSource;

    /**
     * Constructs a UserDAOImpl object with default DataSource.
     */
    public UserDAOImpl() {
        // Initialize DataSource
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/fwrp?zeroDateTimeBehavior=CONVERT_TO_NULL");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Rooot");

        this.dataSource = basicDataSource;
    }

    /**
     * Constructs a UserDAOImpl object with custom DataSource.
     * @param dataSource The DataSource object to be used.
     */
    public UserDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Creates a new user in the database.
     * @param user The User object to be created.
     */
    @Override
    public void create(User user) {
        String sql = "INSERT INTO users (name, email, password, user_type, contact_info, location) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().getDatabaseValue());
            statement.setString(5, user.getContactInfo());
            statement.setString(6, user.getLocation());

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException("Error creating user", e);
        }
    }

    /**
     * Retrieves a user from the database based on user ID.
     * @param userId The ID of the user to be retrieved.
     * @return The User object if found, otherwise null.
     */
    @Override
    public User read(int userId) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Updates an existing user in the database.
     * @param user The User object to be updated.
     */
    @Override
    public void update(User user) {
        String sql = "UPDATE users SET name = ?, email = ?, password = ?, user_type = ?, contact_info = ?, location = ?" +
                "WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getUserType().getDatabaseValue());
            statement.setString(5, user.getContactInfo());
            statement.setString(6, user.getLocation());

            statement.setInt(7, user.getUserId()); // Fixed index from 8 to 7
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a user from the database based on user ID.
     * @param userId The ID of the user to be deleted.
     */
    @Override
    public void delete(int userId) {
        String sql = "DELETE FROM users WHERE user_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves a user from the database based on email.
     * @param email The email of the user to be retrieved.
     * @return The User object if found, otherwise null.
     */
    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return mapUser(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Retrieves the user type of a user based on email.
     * @param email The email of the user.
     * @return The UserType enum value.
     */
    @Override
    public UserType getUserType(String email) {
        UserType userType = null;
        String sql = "SELECT user_type FROM users WHERE email = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String userTypeString = resultSet.getString("user_type");
                    userType = UserType.valueOf(userTypeString);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle the exception appropriately
        }

        return userType;
    }

    /**
     * Authenticates a user based on email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return True if authentication succeeds, otherwise false.
     */
    @Override
    public boolean authenticateUser(String email, String password) {
        String sql = "SELECT COUNT(*) FROM users WHERE email = ? AND password = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, email);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count == 1;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Helper method to map ResultSet to User object
    private User mapUser(ResultSet resultSet) throws SQLException {
        return new UserBuilder()
                .setUserId(resultSet.getInt("user_id"))
                .setName(resultSet.getString("name"))
                .setEmail(resultSet.getString("email"))
                .setPassword(resultSet.getString("password"))
                .setUserType(UserType.valueOf(resultSet.getString("user_type")))
                .setContactInfo(resultSet.getString("contact_info"))
                .setLocation(resultSet.getString("location"))
                .build();
    }
}

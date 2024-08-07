/**
    Author  : Yashkaran Singh
*/
package inventory;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the InventoryItemDAO interface for managing inventory items.
 * This class provides methods to perform CRUD operations on inventory items in the database.
 * It also includes additional methods to retrieve specific types of inventory items.
 * <p>
 * This implementation uses JDBC for database interaction.
 * </p>
 * 
 * @author Yashkaran Singh
 */
public class InventoryItemDAOImpl implements InventoryItemDAO {
    private final DataSource dataSource;

    /**
     * Constructs an InventoryItemDAOImpl object with the default DataSource.
     * The default DataSource is configured to connect to a MySQL database running on localhost.
     */
    public InventoryItemDAOImpl() {
        // Initialize DataSource
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/fwrp?zeroDateTimeBehavior=CONVERT_TO_NULL");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Rooot");

        this.dataSource = basicDataSource;
    }

    /**
     * Adds an inventory item to the database.
     * 
     * @param item The inventory item to add.
     */
    public void addInventoryItem(InventoryItem item) {
        String sql = "INSERT INTO inventory_items (item_name, item_description, quantity, expiration_date, for_donation, surplus) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getItemDescription());
            statement.setInt(3, item.getQuantity());
            statement.setDate(4, item.getExpirationDate());
            statement.setBoolean(5, item.isForDonation());
            statement.setBoolean(6, item.isSurplus());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<InventoryItem> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                InventoryItem item = new InventoryItem();
                item.setItemId(resultSet.getInt("item_id"));
                item.setItemName(resultSet.getString("item_name"));
                item.setItemDescription(resultSet.getString("item_description"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setExpirationDate(resultSet.getDate("expiration_date"));
                item.setForDonation(resultSet.getBoolean("for_donation"));
                item.setSurplus(resultSet.getBoolean("surplus"));
                inventoryItems.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryItems;
    }

    @Override
    public void updateInventoryItem(InventoryItem item) {
        String sql = "UPDATE inventory_items SET item_name = ?, item_description = ?, quantity = ?, expiration_date = ?, " +
                     "for_donation = ?, surplus = ? WHERE item_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, item.getItemName());
            statement.setString(2, item.getItemDescription());
            statement.setInt(3, item.getQuantity());
            statement.setDate(4, item.getExpirationDate());
            statement.setBoolean(5, item.isForDonation());
            statement.setBoolean(6, item.isSurplus());
            statement.setInt(7, item.getItemId());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteInventoryItem(int itemId) {
        String sql = "DELETE FROM inventory_items WHERE item_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Retrieves all inventory items that are not marked for donation.
     * 
     * @return A list of inventory items not marked for donation.
     */
    public List<InventoryItem> getNonDonationInventoryItems() {
        List<InventoryItem> inventoryItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items WHERE for_donation = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, false); // Select non-donation items
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("item_id"));
                    item.setItemName(resultSet.getString("item_name"));
                    item.setItemDescription(resultSet.getString("item_description"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expiration_date"));
                    item.setForDonation(resultSet.getBoolean("for_donation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    inventoryItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return inventoryItems;
    }
    
    /**
     * Retrieves all inventory items that are marked for donation.
     * 
     * @return A list of inventory items marked for donation.
     */
    public List<InventoryItem> getDonationInventoryItems() {
        List<InventoryItem> donationItems = new ArrayList<>();
        String sql = "SELECT * FROM inventory_items WHERE for_donation = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setBoolean(1, true);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("item_id"));
                    item.setItemName(resultSet.getString("item_name"));
                    item.setItemDescription(resultSet.getString("item_description"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expiration_date"));
                    item.setForDonation(resultSet.getBoolean("for_donation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    donationItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return donationItems;
    }

    /**
     * Retrieves an inventory item by its ID.
     * 
     * @param itemId The ID of the inventory item to retrieve.
     * @return The inventory item with the specified ID, or null if not found.
     */
    public InventoryItem getInventoryItemById(int itemId) {
        String sql = "SELECT * FROM inventory_items WHERE item_id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, itemId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    InventoryItem item = new InventoryItem();
                    item.setItemId(resultSet.getInt("item_id"));
                    item.setItemName(resultSet.getString("item_name"));
                    item.setItemDescription(resultSet.getString("item_description"));
                    item.setQuantity(resultSet.getInt("quantity"));
                    item.setExpirationDate(resultSet.getDate("expiration_date"));
                    item.setForDonation(resultSet.getBoolean("for_donation"));
                    item.setSurplus(resultSet.getBoolean("surplus"));
                    return item;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Return null if no item found with the given ID
    }
}

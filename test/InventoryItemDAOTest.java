package inventory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class tests the InventoryItemDAO class.
 * It checks the CRUD operations for inventory items.
 * @author Ziya
 */
public class InventoryItemDAOTest {

    private InventoryItemDAO inventoryItemDAO;
    private InventoryItem testItem;

    /**
     * This method sets up the test environment.
     * It runs before each test.
     */
    @BeforeEach
    public void setUp() {
        inventoryItemDAO = new InventoryItemDAOImpl();
        testItem = new InventoryItem(1, "Apple", "Fresh red apple", 100, java.sql.Date.valueOf("2024-12-31"), true, false);
    }

    /**
     * This test checks if an inventory item can be added successfully.
     */
    @Test
    public void testAddInventoryItem() {
        inventoryItemDAO.addInventoryItem(testItem);
        List<InventoryItem> items = inventoryItemDAO.getAllInventoryItems();
        assertTrue(items.contains(testItem), "The inventory item should be added successfully");
    }

    /**
     * This test checks if all inventory items can be retrieved successfully.
     */
    @Test
    public void testGetAllInventoryItems() {
        List<InventoryItem> items = inventoryItemDAO.getAllInventoryItems();
        assertNotNull(items, "The list of inventory items should not be null");
    }

    /**
     * This test checks if an inventory item can be updated successfully.
     */
    @Test
    public void testUpdateInventoryItem() {
        inventoryItemDAO.addInventoryItem(testItem);
        testItem.setItemDescription("Fresh green apple");
        inventoryItemDAO.updateInventoryItem(testItem);
        InventoryItem updatedItem = inventoryItemDAO.getInventoryItemById(testItem.getItemId());
        assertEquals("Fresh green apple", updatedItem.getItemDescription(), "The inventory item description should be updated successfully");
    }

    /**
     * This test checks if an inventory item can be deleted successfully.
     */
    @Test
    public void testDeleteInventoryItem() {
        inventoryItemDAO.addInventoryItem(testItem);
        inventoryItemDAO.deleteInventoryItem(testItem.getItemId());
        InventoryItem deletedItem = inventoryItemDAO.getInventoryItemById(testItem.getItemId());
        assertNull(deletedItem, "The inventory item should be deleted successfully");
    }
}


    /**
     * 
     */
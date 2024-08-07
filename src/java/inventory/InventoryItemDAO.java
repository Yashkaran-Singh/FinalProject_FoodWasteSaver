/**
    Author  : Yashkaran Singh
*/
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package inventory;

import java.util.List;

/**
 * Interface for InventoryItem Data Access Object (DAO).
 * Defines methods for interacting with inventory items in the database.
 */
public interface InventoryItemDAO {
    /**
     * Adds an inventory item to the database.
     * @param item The InventoryItem object to be added.
     */
    void addInventoryItem(InventoryItem item);

    /**
     * Retrieves all inventory items from the database.
     * @return A list of InventoryItem objects.
     */
    List<InventoryItem> getAllInventoryItems();

    /**
     * Updates an existing inventory item in the database.
     * @param item The InventoryItem object to be updated.
     */
    void updateInventoryItem(InventoryItem item);

    /**
     * Deletes an inventory item from the database based on item ID.
     * @param itemId The ID of the item to be deleted.
     */
    void deleteInventoryItem(int itemId);
}

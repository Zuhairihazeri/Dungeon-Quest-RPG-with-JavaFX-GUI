package items;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages item storage and retrieval
 */
public class Inventory {
    private List<Item> items;
    private int maxSize;

    public Inventory(int maxSize) {
        this.items = new ArrayList<>();
        this.maxSize = maxSize;
    }

    /**
     * Add an item to the inventory
     */
    public boolean addItem(Item item) {
        if (items.size() >= maxSize) {
            System.out.println("Inventory is full!");
            return false;
        }
        items.add(item);
        return true;
    }

    /**
     * Remove an item from the inventory
     */
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    /**
     * Get an item by index
     */
    public Item getItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.get(index);
        }
        return null;
    }

    /**
     * Get all items
     */
    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    /**
     * Get number of items
     */
    public int getSize() {
        return items.size();
    }

    /**
     * Get max capacity
     */
    public int getMaxSize() {
        return maxSize;
    }

    /**
     * Check if inventory is empty
     */
    public boolean isEmpty() {
        return items.isEmpty();
    }

    /**
     * Display inventory contents
     */
    public void displayInventory() {
        if (items.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        System.out.println("\n=== INVENTORY (" + items.size() + "/" + maxSize + ") ===");
        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getDisplayString());
        }
    }
}

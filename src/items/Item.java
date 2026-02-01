package items;

import entities.Player;

/**
 * Interface for all items in the game
 */
public interface Item {
    /**
     * Get the item name
     */
    String getName();

    /**
     * Get the item description
     */
    String getDescription();

    /**
     * Use the item (returns true if item was consumed)
     */
    boolean use(Player player);

    /**
     * Get a display string for the item
     */
    default String getDisplayString() {
        return getName() + " - " + getDescription();
    }
}

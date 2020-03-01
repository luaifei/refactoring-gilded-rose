package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GildedRoseTest {

    @Test
    void should_update_all_inventories_status() {
        Inventory[] inventories = new Inventory[] {
                new Inventory("foo", 1, 3),
                new AgedBrieInventory(1, 3)
        };
        GildedRose gildedRose = new GildedRose(inventories);
        gildedRose.updateStatus();

        assertEquals(new Inventory("foo", 0, 2), gildedRose.getInventories()[0]);
        assertEquals(new Inventory("Aged Brie", 0, 4), gildedRose.getInventories()[1]);
    }
}

package com.gildedrose;

import java.util.Arrays;

class GildedRose {
    private Inventory[] inventories;

    public Inventory[] getInventories() {
        return Arrays.copyOf(inventories, inventories.length);
    }

    public GildedRose(Inventory[] inventories) {
        this.inventories = inventories;
    }

    public void updateStatus() {
        for (Inventory inventory : inventories) {
            inventory.updateStatus();
        }
    }
}

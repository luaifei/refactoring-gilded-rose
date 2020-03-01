package com.gildedrose;

class GildedRose {
    Inventory[] inventories;

    public GildedRose(Inventory[] inventories) {
        this.inventories = inventories;
    }

    public void updateStatus() {
        for (Inventory inventory : inventories) {
            inventory.updateStatus();
        }
    }
}

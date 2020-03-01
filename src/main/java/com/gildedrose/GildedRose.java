package com.gildedrose;

class GildedRose {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    Inventory[] inventories;

    public GildedRose(Inventory[] inventories) {
        this.inventories = inventories;
    }

    public void updateStatus() {
        for (Inventory inventory : inventories) {
            if (!inventory.name.equals(AGED_BRIE) && !inventory.name.equals(BACKSTAGE)) {
                if (inventory.quality > 0 && !inventory.name.equals(SULFURAS)) {
                    inventory.quality = inventory.quality - 1;
                }
            } else {
                if (inventory.quality < 50) {
                    inventory.quality = inventory.quality + 1;
                }

                if (inventory.name.equals(BACKSTAGE)) {
                    if (inventory.sellIn < 11 && inventory.quality < 50) {
                        inventory.quality = inventory.quality + 1;
                    }

                    if (inventory.sellIn < 6 && inventory.quality < 50) {
                        inventory.quality = inventory.quality + 1;
                    }
                }
            }

            if (!inventory.name.equals(SULFURAS)) {
                inventory.sellIn = inventory.sellIn - 1;
            }

            if (inventory.sellIn < 0) {
                if (!inventory.name.equals(AGED_BRIE)) {
                    if (!inventory.name.equals(BACKSTAGE)) {
                        if (inventory.quality > 0 && !inventory.name.equals(SULFURAS)) {
                            inventory.quality = inventory.quality - 1;
                        }
                    } else {
                        inventory.quality = 0;
                    }
                } else {
                    if (inventory.quality < 50) {
                        inventory.quality = inventory.quality + 1;
                    }
                }
            }
        }
    }
}

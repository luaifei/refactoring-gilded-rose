package com.gildedrose;

public class AgedBrieInventory extends Inventory {
    public AgedBrieInventory(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    public void updateStatus() {
        if (quality < 50) {
            quality = quality + 1;
        }

        sellIn = sellIn - 1;

        if (sellIn < 0 && quality < 50) {
            quality = quality + 1;
        }
    }
}

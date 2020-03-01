package com.gildedrose;

public class AgedBrieInventory extends Inventory {
    public AgedBrieInventory(int sellIn, int quality) {
        super("Aged Brie", sellIn, quality);
    }

    @Override
    public void updateStatus() {
        increaseQuality();

        sellIn = sellIn - 1;

        if (sellIn < 0) {
            increaseQuality();
        }
    }
}

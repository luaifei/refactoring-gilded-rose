package com.gildedrose;

public class SulfurasInventory extends Inventory {
    public SulfurasInventory(int sellIn, int quality) {
        super("Sulfuras, Hand of Ragnaros", sellIn, quality);
    }

    @Override
    public void updateStatus() {
        if (sellIn < 0) {
            quality = 0;
        }
    }
}

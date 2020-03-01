package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

@AllArgsConstructor
@EqualsAndHashCode
public class Inventory {
    String name;

    int sellIn;

    int quality;

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
    
    public void updateStatus() {
        if (quality > 0) {
            quality = quality - 1;
        }

        sellIn = sellIn - 1;

        if (sellIn < 0 && quality > 0) {
            quality = quality - 1;
        }
    }
}

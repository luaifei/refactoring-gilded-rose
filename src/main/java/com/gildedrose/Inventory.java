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
        decreaseQuality();

        sellIn = sellIn - 1;

        if (sellIn < 0) {
            decreaseQuality();
        }
    }

    void decreaseQuality() {
        quality = quality > 0 ? quality - 1 : quality;
    }

    void increaseQuality() {
        quality = quality < 50 ? quality + 1 : quality;
    }
}

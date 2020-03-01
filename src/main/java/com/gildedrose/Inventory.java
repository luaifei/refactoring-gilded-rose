package com.gildedrose;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Inventory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    private String name;

    private int sellIn;

    private int quality;

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
    
    public void updateStatus() {
        updateQualityBefore();

        updateSellIn();

        updateQualityAfter();
    }

    private void updateQualityBefore() {
        if (!name.equals(AGED_BRIE) && !name.equals(BACKSTAGE)) {
            if (quality > 0 && !name.equals(SULFURAS)) {
                quality = quality - 1;
            }
        } else {
            if (quality < 50) {
                quality = quality + 1;
            }

            if (name.equals(BACKSTAGE)) {
                if (sellIn < 11 && quality < 50) {
                    quality = quality + 1;
                }

                if (sellIn < 6 && quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }

    private void updateSellIn() {
        if (!name.equals(SULFURAS)) {
            sellIn = sellIn - 1;
        }
    }

    private void updateQualityAfter() {
        if (sellIn < 0) {
            if (!name.equals(AGED_BRIE)) {
                if (!name.equals(BACKSTAGE)) {
                    if (quality > 0 && !name.equals(SULFURAS)) {
                        quality = quality - 1;
                    }
                } else {
                    quality = 0;
                }
            } else {
                if (quality < 50) {
                    quality = quality + 1;
                }
            }
        }
    }

}

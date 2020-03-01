package com.gildedrose;

public class Inventory {
    public static final String AGED_BRIE = "Aged Brie";
    public static final String BACKSTAGE = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public String name;

    public int sellIn;

    public int quality;

    public Inventory(String name, int sellIn, int quality) {
        this.name = name;
        this.sellIn = sellIn;
        this.quality = quality;
    }

    @Override
    public String toString() {
        return this.name + ", " + this.sellIn + ", " + this.quality;
    }
    
    public void updateStatus() {
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

        if (!name.equals(SULFURAS)) {
            sellIn = sellIn - 1;
        }

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

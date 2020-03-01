package com.gildedrose;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test case
 * 1. 普通商品
 * 1.1 给定SellIn > 0 && quality >= 2, 如果更新商品信息后，然后SellIn - 1 和quality - 1；
 * 1.2 给定SellIn > 0 && 0 <= quality <= 1, 如果更新商品信息后，然后SellIn - 1，并且quality = 0；
 * 1.3 给定SellIn <= 0 && quality >= 2, 如果更新商品信息后，然后SellIn - 1， 并且quality - 2；
 *
 * 2. 商品Aged Brie
 * 2.1 给定SellIn > 0 && quality < 50, 如果更新商品信息后，然后SellIn - 1, 并且quality + 1；
 * 2.2 给定SellIn > 0 && quality >= 50，如果更新商品信息后，然后SellIn - 1，并且quality = 50；
 * 2.3 给定SellIn <= 0 && quality <= 48, 如果更新商品信息后，然后SellIn - 1，并且quality + 2；
 * 2.4 给定SellIn <= 0 && 48 < quality <= 50, 如果更新商品信息后，然后SellIn - 1, 并且quality = 50；
 * 2.5 给定任意SellIn && quality > 50, 如果更新商品信息后，然后SellIn - 1，并且quality不变；
 *
 * 3. 商品Sulfuras, Hand of Ragnaros
 * 3.1 给定任意的SellIn && 任意的quality，如果更新商品信息后，然后SellIn保持不变，并且quality不变
 *
 * 4. 商品Backstage passes to a TAFKAL80ETC concert
 * 4.1 给定SellIn > 0 && quality < 47, 如果更新商品信息后，然后SellIn - 1，并且quality + 3
 * 4.2 给定SellIn > 0 && 47 <= quality <= 50, 如果更新商品信息后，然后SellIn - 1，并且quality = 50
 * 4.3 给定SellIn > 0 && quality  > 50, 如果更新商品信息后，然后SellIn - 1，并且quality不变
 * 4.4 给定SellIn <= 0 && quality为任意值，如果更新商品信息后，然后SellIn - 1，并且quality = 0
 */
public class GildedRoseTest {

    @Test
    public void test_should_return_sellIn_0_and_quality_4_given_name_foo_and_sellIn_1_and_quality_5() {
        Inventory[] inventories = new Inventory[] { new Inventory("foo", 1, 5) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("foo", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 4);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_0_given_name_foo_and_sellIn_1_and_quality_1() {
        Inventory[] inventories = new Inventory[] { new Inventory("foo", 1, 1) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("foo", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 0);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_1_given_name_foo_and_sellIn_0_and_quality_3() {
        Inventory[] inventories = new Inventory[] { new Inventory("foo", 0, 3) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("foo", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 1);
        assertEquals(app.inventories[0].sellIn, -1);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_4_given_name_Aged_Brie_and_sellIn_1_and_quality_3() {
        Inventory[] inventories = new Inventory[] { new Inventory("Aged Brie", 1, 3) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Aged Brie", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 4);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_50_given_name_Aged_Brie_and_sellIn_1_and_quality_50() {
        Inventory[] inventories = new Inventory[] { new Inventory("Aged Brie", 1, 50) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Aged Brie", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 50);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_negative_1_and_quality_7_given_name_Aged_Brie_and_sellIn_0_and_quality_5() {
        Inventory[] inventories = new Inventory[] { new Inventory("Aged Brie", 0, 5) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Aged Brie", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 7);
        assertEquals(app.inventories[0].sellIn, -1);
    }

    @Test
    public void test_should_return_sellIn_negative_1_and_quality_50_given_name_Aged_Brie_and_sellIn_0_and_quality_49() {
        Inventory[] inventories = new Inventory[] { new Inventory("Aged Brie", 0, 49) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Aged Brie", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 50);
        assertEquals(app.inventories[0].sellIn, -1);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void test_should_return_quality_51_given_name_Aged_Brie_and_any_sellIn_and_quality_51(int sellIn) {
        Inventory[] inventories = new Inventory[] { new Inventory("Aged Brie", sellIn, 51) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Aged Brie", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 51);
        assertEquals(app.inventories[0].sellIn, sellIn - 1);
    }

    @ParameterizedTest
    @MethodSource("argumentsGenerator")
    public void test_should_return_same_sellIn_and_quality_given_name_Sulfuras(int sellIn, int quality) {
        Inventory[] inventories = new Inventory[] { new Inventory("Sulfuras, Hand of Ragnaros", sellIn, quality) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Sulfuras, Hand of Ragnaros", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, quality);
        assertEquals(app.inventories[0].sellIn, sellIn);
    }

    static Stream<Arguments> argumentsGenerator(){
        return Stream.of(Arguments.of(1, 1), Arguments.of(1, 5), Arguments.of(0, 50));
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_8_given_name_Backstage_and_sellIn_1_and_quality_5() {
        Inventory[] inventories = new Inventory[] { new Inventory("Backstage passes to a TAFKAL80ETC concert", 1, 5) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 8);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_50_given_name_Backstage_and_sellIn_1_and_quality_48() {
        Inventory[] inventories = new Inventory[] { new Inventory("Backstage passes to a TAFKAL80ETC concert", 1, 48) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 50);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_51_given_name_Backstage_and_sellIn_1_and_quality_51() {
        Inventory[] inventories = new Inventory[] { new Inventory("Backstage passes to a TAFKAL80ETC concert", 1, 51) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 51);
        assertEquals(app.inventories[0].sellIn, 0);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 48, 50, 51})
    public void test_should_return_quality_0_given_name_Backstage_and_sellIn_0(int quality) {
        Inventory[] inventories = new Inventory[] { new Inventory("Backstage passes to a TAFKAL80ETC concert", 0, quality) };
        GildedRose app = new GildedRose(inventories);
        app.updateStatus();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.inventories[0].name);
        assertEquals(app.inventories[0].quality, 0);
        assertEquals(app.inventories[0].sellIn, -1);
    }

}

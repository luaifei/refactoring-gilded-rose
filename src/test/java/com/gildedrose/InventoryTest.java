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
public class InventoryTest {
    @Test
    public void test_should_return_expected_formatter_when_to_string() {
        Inventory inventory = new Inventory("foo", 1, 5);
        assertEquals("foo, 1, 5", inventory.toString());
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_4_given_name_foo_and_sellIn_1_and_quality_5() {
        Inventory inventory = new Inventory("foo", 1, 5);

        inventory.updateStatus();

        assertEquals(new Inventory("foo", 0, 4), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_0_given_name_foo_and_sellIn_1_and_quality_1() {
        Inventory inventory = new Inventory("foo", 1, 1);

        inventory.updateStatus();

        assertEquals(new Inventory("foo", 0, 0), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_1_given_name_foo_and_sellIn_0_and_quality_3() {
        Inventory inventory = new Inventory("foo", 0, 3);

        inventory.updateStatus();

        assertEquals(new Inventory("foo", -1, 1), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_4_given_name_Aged_Brie_and_sellIn_1_and_quality_3() {
        Inventory inventory = new AgedBrieInventory(1, 3);

        inventory.updateStatus();

        assertEquals(new AgedBrieInventory(0, 4), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_50_given_name_Aged_Brie_and_sellIn_1_and_quality_50() {
        Inventory inventory = new AgedBrieInventory(1, 50);

        inventory.updateStatus();

        assertEquals(new AgedBrieInventory(0, 50), inventory);
    }

    @Test
    public void test_should_return_sellIn_negative_1_and_quality_7_given_name_Aged_Brie_and_sellIn_0_and_quality_5() {
        Inventory inventory = new AgedBrieInventory(0, 5);

        inventory.updateStatus();

        assertEquals(new AgedBrieInventory(-1, 7), inventory);
    }

    @Test
    public void test_should_return_sellIn_negative_1_and_quality_50_given_name_Aged_Brie_and_sellIn_0_and_quality_49() {
        Inventory inventory = new AgedBrieInventory(0, 49);

        inventory.updateStatus();

        assertEquals(new AgedBrieInventory(-1, 50), inventory);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void test_should_return_quality_51_given_name_Aged_Brie_and_any_sellIn_and_quality_51(int sellIn) {
        Inventory inventory = new AgedBrieInventory(sellIn, 51);

        inventory.updateStatus();

        assertEquals(new AgedBrieInventory(sellIn - 1, 51), inventory);
    }

    @ParameterizedTest
    @MethodSource("argumentsGenerator")
    public void test_should_return_same_sellIn_and_quality_given_name_Sulfuras(int sellIn, int quality) {
        Inventory inventory = new SulfurasInventory(sellIn, quality);

        inventory.updateStatus();

        assertEquals(new SulfurasInventory(sellIn, quality), inventory);
    }

    static Stream<Arguments> argumentsGenerator(){
        return Stream.of(Arguments.of(1, 1), Arguments.of(1, 5), Arguments.of(0, 50));
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_8_given_name_Backstage_and_sellIn_1_and_quality_5() {
        Inventory inventory = new BackstageInventory(1, 5);

        inventory.updateStatus();

        assertEquals(new BackstageInventory(0, 8), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_50_given_name_Backstage_and_sellIn_1_and_quality_48() {
        Inventory inventory = new BackstageInventory(1, 48);

        inventory.updateStatus();

        assertEquals(new BackstageInventory(0, 50), inventory);
    }

    @Test
    public void test_should_return_sellIn_0_and_quality_51_given_name_Backstage_and_sellIn_1_and_quality_51() {
        Inventory inventory = new BackstageInventory(1, 51);

        inventory.updateStatus();

        assertEquals(new BackstageInventory(0, 51), inventory);
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 48, 50, 51})
    public void test_should_return_quality_0_given_name_Backstage_and_sellIn_0(int quality) {
        Inventory inventory = new BackstageInventory(0, quality);

        inventory.updateStatus();

        assertEquals(new BackstageInventory(-1, 0), inventory);
    }
}

package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventoryTest {
    @Test
    public void test_should_return_expected_formatter_when_to_string() {
        Inventory inventory = new Inventory("foo", 1, 5);
        assertEquals("foo, 1, 5", inventory.toString());
    }
}

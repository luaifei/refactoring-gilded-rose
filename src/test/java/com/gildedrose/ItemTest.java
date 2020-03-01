package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    public void test_should_return_expected_formatter_when_to_string() {
        Item item = new Item("foo", 1, 5);
        assertEquals("foo, 1, 5", item.toString());
    }
}

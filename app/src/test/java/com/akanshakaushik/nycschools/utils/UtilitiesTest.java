package com.akanshakaushik.nycschools.utils;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UtilitiesTest {

    @Test
    public void testFormatAddress() {
        String input = "Test(Value)";
        String expected = "Test";
        assertEquals(expected, Utilities.formatAddress(input));
    }

    @Test
    public void testFormatAddressForDifferentCharacters() {
        String input = "Test{Value)";
        String expected = "Test{Value)";
        assertEquals(expected, Utilities.formatAddress(input));
    }

    @Test
    public void testFormatAddressForEmptyAddress() {
        String input = "";
        String expected = "";
        assertEquals(expected, Utilities.formatAddress(input));
    }

    @Test
    public void testFormatAddressForNullAddress() {
        String input = null;
        String expected = null;
        assertEquals(expected, Utilities.formatAddress(input));
    }
}
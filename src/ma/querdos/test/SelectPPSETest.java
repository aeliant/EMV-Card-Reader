package ma.querdos.test;

import org.junit.Test;

import static ma.querdos.util.ByteManipulation.hexStringToByteArray;

/**
 * @author : ${AUTHOR}
 * @date: 27/07/15
 * <p>
 * Copyright to Hamza ESSAYEGH - Querdos
 */
public class SelectPPSETest {

    private final byte[] resultSelectAID_1 = hexStringToByteArray("6F23840E3250 41592E5359532E4444463031 A511BF0C0E 610C4F07 A0000000041010 8701019000");
    private final byte[] resultSelectAID_2 = hexStringToByteArray("6F23840E3250 41592E5359532E4444463031 A511BF0C0E 610C4F07 A0000000041010 8701019000");
    private final byte[] resultSelectAID_3 = hexStringToByteArray("6F23840E3250 41592E5359532E4444463031 A511BF0C0E 610C4F07 A0000000041010 8701019000");

    @Test
    public void testGetNumberDirectory() throws Exception {

    }

}
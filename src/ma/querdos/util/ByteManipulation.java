package ma.querdos.util;

import java.math.BigInteger;

/**
 * Created by querdos on 16/07/15.
 * Copyright ¢ 2015 - Hamza Essayegh
 */
public class ByteManipulation {
    final private static char[] hexArray = "0123456789ABCDEF".toCharArray();

    /**
     * Convert an array of bytes into a readable string
     * @param bytes to convert
     * @return the readable string
     */
    public static String byteToHex(byte[] bytes) {
        if (bytes == null)
            return "NULL";
        char[] hexChars = new char[bytes.length * 2];
        for (int j=0; j<bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        return new String(hexChars);
    }

    /**
     * Convert a Hex String representation into a byte array
     * @param s
     * @return
     */
    public static byte[] hexStringToByteArray(String s) {
        String s1 = s.replace(" ", "");

        int len = s1.length();
        byte[] data = new byte[len/2];

        for (int i=0; i<len; i+=2)
            data[i/2] = (byte) ((Character.digit(s1.charAt(i), 16) << 4) + Character.digit(s1.charAt(i+1), 16));

        return data;
    }

    /**
     * Convert string HEX into a ASCII string
     * @param hex
     * @return
     */
    public static String convertHexToString(String hex){

        StringBuilder sb = new StringBuilder();
        StringBuilder temp = new StringBuilder();

        for( int i=0; i<hex.length()-1; i+=2 ){

            String output = hex.substring(i, (i + 2));

            int decimal = Integer.parseInt(output, 16);

            sb.append((char)decimal);

            temp.append(decimal);
        }

        return sb.toString();
    }

    public static String hexToBinary(String hex) {
        if (hex.isEmpty() || hex.equals("NULL"))
            return "NULL";

        int i = Integer.parseInt(hex, 16);
        String bin = Integer.toBinaryString(i);
        return bin;
    }

    public static String toBinary( byte[] bytes ) {
        if (bytes == null)
            return "NULL";

        StringBuilder sb = new StringBuilder(bytes.length * Byte.SIZE);
        for( int i = 0; i < Byte.SIZE * bytes.length; i++ )
            sb.append((bytes[i / Byte.SIZE] << i % Byte.SIZE & 0x80) == 0 ? '0' : '1');
        return sb.toString();
    }

    public static byte[] fromBinary( String s ) {
        int sLen = s.length();
        byte[] toReturn = new byte[(sLen + Byte.SIZE - 1) / Byte.SIZE];
        char c;
        for( int i = 0; i < sLen; i++ )
            if( (c = s.charAt(i)) == '1' )
                toReturn[i / Byte.SIZE] = (byte) (toReturn[i / Byte.SIZE] | (0x80 >>> (i % Byte.SIZE)));
            else if ( c != '0' )
                throw new IllegalArgumentException();
        return toReturn;
    }

    public static String hexToBin(String hex){
        return new BigInteger(hex, 16).toString(2);
    }
}

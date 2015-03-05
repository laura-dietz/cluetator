package edu.umass.cs.ciir.cluetator;

/**
 * User: dietz
 * Date: 3/5/15
 * Time: 9:58 AM
 */
public class NewlineEncoding {
    public static String escape(String input) {
        return input.replaceAll("[\\n\\t\\r]"," ");
    }

    public static String unescape(String input) {
        return input;
    }
}
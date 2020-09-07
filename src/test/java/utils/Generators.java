package utils;

public class Generators {

    public static String specialSymbolsString = "±!@#$%^&*()_+./,|\\[]{}\"'`~№:;?";

    public static String genString
            (int length, boolean containsCyrillic, boolean containsLatin, boolean containsNumeric, boolean containsSpaces,
             boolean containsSpecialSymbols){

        String ALPHA_NUMERIC_STRING = "";

        if(containsCyrillic){
            ALPHA_NUMERIC_STRING += "АБВГДЕЖЗИЙКЛМНОПРСТУФХЦЧШЩЭЮЯЬЙЪ";
        }
        if(containsLatin){
            ALPHA_NUMERIC_STRING += "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        }
        if(containsNumeric){
            ALPHA_NUMERIC_STRING += "0123456789";
        }
        if(containsSpaces){
            ALPHA_NUMERIC_STRING += " ";
        }
        if(containsSpecialSymbols){
            ALPHA_NUMERIC_STRING += specialSymbolsString;
        }
        StringBuilder builder = new StringBuilder();
        while (length-- != 0) {
            int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
}

package com.lx.reume.util;

public class Strings {

    public static  String underlineCase(String str){

        if (str == null) return null;
        int len = str.length();
        if (len == 0) return str;

        StringBuilder sb = new StringBuilder();
        sb.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < len; i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)){
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            }else{
                sb.append(c);
            }
        }
        return  sb.toString();

    }

}

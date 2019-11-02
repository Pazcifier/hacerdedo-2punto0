/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author estudiante.fit
 */
public class Validator {
    
    public static boolean onlyLetters(String s) {
        if (s.length() > 0) {
            String aux = s;
            char[] auxDiv = aux.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isLetter(auxDiv[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean onlyNumbers(String s) {
        if (s.length() > 0) {
            String aux = s;
            char[] auxDiv = aux.toCharArray();
            for (int i = 0; i < s.length(); i++) {
                if (!Character.isDigit(auxDiv[i])) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean notEmpty(String s) {
        s = s.replaceAll(" ", "");
        if (s.length() > 0) {
            return true;
        }
        return false;
    }
}

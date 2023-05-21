package com.rizvi.mit.du.exception;

public class GameExceptions {
    public boolean validateInput(String inputString) {
        try {
            Integer.parseInt(inputString);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

package com.barankosecki.entities.enums;

public enum OrderState {
    ORDERED,    // state = 1
    CANCELLED,  // state = 2
    SUPPLIED;    // state = 3

    public static int getValue(String s) {
        switch (s) {
            case "ORDERED":
                return 1;
            case "CANCELLED":
                return 2;
            case "SUPPLIED":
                return 3;
            default:
                return -1;
        }
    }
}

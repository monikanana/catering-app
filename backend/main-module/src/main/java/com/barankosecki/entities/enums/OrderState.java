package com.barankosecki.entities.enums;

public enum OrderState {
    ORDERED,    // state = 1
    CANCELLED,  // state = 2
    DELIVERED;    // state = 3

    public static int getValue(String s) {
        switch (s) {
            case "ORDERED":
                return 1;
            case "CANCELLED":
                return 2;
            case "DELIVERED":
                return 3;
            default:
                return -1;
        }
    }
}

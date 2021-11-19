package com.sorinbratosin.BestGPUPrice.Helper;

public class FormatPrice {

    private double price;

    public FormatPrice(String strPrice) {
        price = formatPrice(strPrice);
    }

    private double formatPrice(String s) {
        String trimLei = s.trim().substring(0, s.length()-4);
        if(trimLei.contains(".")) {
            trimLei = trimLei.replace(".", "");
        }
        if(trimLei.contains(",")) {
            trimLei = trimLei.replace(",", "");
        }
        if(trimLei.contains("de la")) {
            trimLei = trimLei.replace("de la", "");
        }
        String lastDigits = trimLei.substring(trimLei.length()-2);
        trimLei = trimLei.trim().substring(0, trimLei.length()-2) + "." + lastDigits;

        return Double.parseDouble(trimLei);
    }

    public double getPrice() {
        return price;
    }
}

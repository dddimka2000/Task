package org.example;

import java.util.List;

public class Country extends App {
    private String country_name;
    private String full_country_name;
    private String flag_url;
    private int same_letter_count;
    private List<Character> First_letter;

    public Country(String country_name, String full_country_name, String flag_url, int same_letter_count) {
        this.country_name = country_name;
        this.full_country_name = full_country_name;
        this.flag_url = flag_url;
        this.same_letter_count = same_letter_count;
    }

    public void setFirst_letter(List<Character> first_letter) {
        First_letter = first_letter;
    }

    public String getCountry_name() {
        return country_name;
    }

    public int showLetters() {
        int count = 0;
        for (int i = 0; i < First_letter.size(); i++) {
            if (First_letter.get(i).equals(country_name.charAt(0))) {
                count++;
            }
        }
        return count;
    }

    public void show() {
        System.out.println("country: " + country_name);
        System.out.println("full_country_name " + full_country_name);
        System.out.println("same_letter_count " + same_letter_count);
        System.out.println("flag_url " + flag_url);
        showWords();
        System.out.println("Кол-во стран начинающихся на туже букву: " + showLetters());
        System.out.println();
    }

    public void showCountry_name(String country_name) {
        if (country_name.equals(this.country_name)) {
            show();
        }

    }

    public void showWords() {
        int count = 0;
        if (full_country_name.length() != 0) {
            count++;
            for (int i = 0; i < full_country_name.length(); i++) {
                if (full_country_name.charAt(i) == ' ' || full_country_name.charAt(i) == '-' || full_country_name.charAt(i) == '’') {
                    count++;
                }
            }
        }
        System.out.println("Кол-во слов: " + count);
    }
}

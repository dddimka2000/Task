package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class App {
    public static void main(String[] args) throws IOException {
        Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/Список_государств").get();
        Element table = doc.select("table.wikitable").first();

        Elements rows_names = table.select("td");
        List<String> Inmormatrion = rows_names.stream().map((i) -> (i.select("td").text())).filter(item -> !item.equals("")).collect(Collectors.toList());

        Elements img_names = table.select("img");
        List<String> src = new ArrayList<>();
        for (Element element : img_names) {
            String[] s = element.attr("srcset").split(" ");
            src.add("https:" + s[0]);
        }
        List<Country> countries = new ArrayList<>();
        try {
            for (int i = 0; i < src.size(); i++) {
                countries.add(new Country(Inmormatrion.get(i * 3 + 1), Inmormatrion.get(i * 3 + 2), src.get(i), Integer.parseInt(Inmormatrion.get(i * 3))));
            }
        } catch (IndexOutOfBoundsException e) {
        }
        List<Character> First_letter = countries.stream().map(i -> i.getCountry_name().charAt(0)).collect(Collectors.toList());
        countries.forEach(i -> i.setFirst_letter(First_letter));
        countries.forEach(i -> i.show());

        //словарь
        Map <String,List> map=new HashMap<>();
        for (int i = 0; i < countries.size(); i++) {
            List<Map> array=new ArrayList<>();
            Map <String,String> final_map=new HashMap<>();
            final_map.put("country_name",countries.get(i).getCountry_name());
            final_map.put("full_country_name",countries.get(i).getFull_country_name());
            final_map.put("same_letter_count",Integer. toString(countries.get(i).getSame_letter_count()));
            final_map.put("flag_url",countries.get(i).getFlag_url());
            array.add(final_map);
            map.put(countries.get(i).getCountry_name(),array);
        }
        scan(map);

    }
    public static void scan(Map <String,List> map) {
        Scanner scanner = new Scanner(System.in);
        String scan = scanner.nextLine();
        System.out.println(map.get(scan));
    }
}

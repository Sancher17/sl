package com.senla.dataworker.readfile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFromCsv {

//    static String path = "dataworker\\src\\main\\java\\com\\senla\\dataworker\\books.csv";
    static String path = "dataworker\\src\\main\\java\\com\\senla\\dataworker\\orders.csv";

    public static void main(String[] args) {

        for (Object list : read()) {
            System.out.println(list);
        }
    }


    public static List read() {
        List<String> list = new ArrayList<>();

        String st;
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while ((st = br.readLine()) != null) {
                list.add(st);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}

package ua.goit.java.service;

public class Requests {

    public static String parsedDate(String date) {

        String year = date.substring(0, 4);
        String day = date.substring(8, 10);
        String month = date.substring(5, 7);

        String result = day + "." + month + "." + year;

        return result;
    }

}

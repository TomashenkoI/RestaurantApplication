package ua.goit.java.dao;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Requests {

    public String getCurrentTime(){

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String time = sdf.format(cal.getTime());

        Date date = new Date(System.currentTimeMillis());

        String year = date.toString().substring(0, 4);
        String day = date.toString().substring(8, 10);
        String month = date.toString().substring(5, 7);

        return day + "." + month + "." + year + " " + time;
    }

    public String parsedDate(String date) {

        String year = date.substring(0, 4);
        String day = date.substring(8, 10);
        String month = date.substring(5, 7);

        String result = day + "." + month + "." + year;

        return result;
    }
}

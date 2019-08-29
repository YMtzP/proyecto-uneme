/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import com.github.lgooddatepicker.components.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.Locale;
import javafx.util.converter.LocalDateStringConverter;

/**
 *
 * @author YareMtz
 */
public class DatePicker {
    
     public String formatoFechaLetra(com.github.lgooddatepicker.components.DatePicker dp) throws ParseException {
        String f = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        f = formateador.format(formatoDate(dp));
        return f;
    }
     
     public String StringToString(Date dp) throws ParseException {
        String f = "";
        SimpleDateFormat formateador = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy", new Locale("ES"));
        f = formateador.format(dp);
        return f;
    }
     
    public Date formatoToDateTime(String time) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = formatter.parse(time);
        return date;
    }
    
    public Date formatoToDateTimeComplete(String time) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        Date date = formatter.parse(time);
        return date;
    }

    public Date formatoDate(com.github.lgooddatepicker.components.DatePicker dp) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(dp.getDate().toString());
        return date;
    }
    
    public Date formatoStringToDate(String fecha) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(fecha);
        return date;
    }
    
    public Date formatoStringToDateTime(String fecha) throws ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");
        Date date = formatter.parse(fecha);
        return date;
    }
    
    public LocalDate formatoStringtoLocalDate(String fecha){
        LocalDate dt = LocalDate.parse(fecha);
        return dt;
    }
    
    public LocalTime formatoStringtoLocalTime(String fecha){
        LocalTime dt = LocalTime.parse(fecha);
        return dt;
    }
    
}

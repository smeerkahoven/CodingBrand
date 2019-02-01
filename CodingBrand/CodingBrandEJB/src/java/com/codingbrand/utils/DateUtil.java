/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codingbrand.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xeio
 */
public class DateUtil {
    
    public static final String LATIN_AMERICA_FORMAT = "dd/MM/yyyy";
    
    public static Date toLatinAmericaDateFormat(String date) {

        Optional op = Optional.ofNullable(date);

        if (!op.isPresent()) {
            return null;
        }

        if (date.length() == 0) {
            return null;
        }

        try {
            SimpleDateFormat formatter = new SimpleDateFormat(LATIN_AMERICA_FORMAT);
            Date parseDAte = formatter.parse(date);

            return parseDAte;
        } catch (ParseException ex) {
            Logger.getLogger(DateUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
    
    public static String toStringDate (Date date){
         SimpleDateFormat formatter = new SimpleDateFormat(LATIN_AMERICA_FORMAT);
         return formatter.format(date);
    }
}

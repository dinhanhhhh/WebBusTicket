/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.formatter;

import com.dtk.pojo.Coach;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author GIGABYTE
 */
public class CoachFormatter implements Formatter<Coach>{

    @Override
    public String print(Coach t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Coach parse(String id, Locale locale) throws ParseException {
        Coach c = new Coach();
        c.setId(Integer.parseInt(id));
        
        return c;
    }
    
}

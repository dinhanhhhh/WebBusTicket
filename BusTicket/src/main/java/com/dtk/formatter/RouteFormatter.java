/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dtk.formatter;

import com.dtk.pojo.Route;
import java.text.ParseException;
import java.util.Locale;
import org.springframework.format.Formatter;

/**
 *
 * @author GIGABYTE
 */
public class RouteFormatter implements Formatter<Route>{

    @Override
    public String print(Route t, Locale locale) {
        return String.valueOf(t.getId());
    }

    @Override
    public Route parse(String id, Locale locale) throws ParseException {
        Route r = new Route();
        r.setId(Integer.parseInt(id));
        
        return r;
    }
    
}

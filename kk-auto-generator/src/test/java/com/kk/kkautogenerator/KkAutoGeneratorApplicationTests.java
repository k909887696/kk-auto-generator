package com.kk.kkautogenerator;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


class KkAutoGeneratorApplicationTests {

    @Test
    void contextLoads() {
        String line ="2023052122001419741448941842\\t,202305212046169K8LcI\\t,退款\\t,酒店订单支付,2023-05-21 20:46:44,2023-05-22 10:18:03,\\t,\\t,\\t,\\t,**日(yin***@163.com)\\t,-880.00,-880.00,0.00,0.00,0.00,0.00,0.00,,0.00\\t,0.00,20230522101802LThMzt\\t,0.00,0.00,";
        String[] items = line.replaceAll("\t,",",").split(",");
        System.out.println(items.length);
    }


    @Test
    void caldeadline() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        if(day==1){
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day+7);
        }else{
            cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day+14);
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = formatter.format(cal.getTime());
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(dateString, pos);
    }
}

package com.example;

import com.example.designpattern.decoratorpattern.Beverage;
import com.example.designpattern.decoratorpattern.CustomInputStream;
import com.example.designpattern.decoratorpattern.DarkToast;
import com.example.designpattern.decoratorpattern.Milk;
import com.example.designpattern.decoratorpattern.Moka;
import com.example.designpattern.factorypattern.Pizza;
import com.example.designpattern.factorypattern.PizzaStore;
import com.example.designpattern.factorypattern.PizzaStore1;
import com.example.designpattern.observerpattern.DataBean;
import com.example.designpattern.observerpattern.Display1;
import com.example.designpattern.observerpattern.Display2;
import com.example.designpattern.observerpattern.Display3;

import java.io.IOException;
import java.io.InputStream;

public class MyClass2 {
    public static void main(String[] args) {
//        observerPattern();
//        decoratorPattern();
//        factoryPattern();
//        String sssss = "6bb4837eb74329105ee4568dda7dc67ed2ca2ad9";
//        System.out.println("main,  [args]      "+sssss.toUpperCase());
//
//        try {
//            String s = "#define LMT_TO_ENB_SCAN_CFG           (0xF004)";
//            s = s.replace(" ","");
//            int idx1 = s.indexOf("(");
//            String ss = s.substring(7,idx1);
//            System.out.println("main,  [args] idx1 = "+idx1+" "+ss+" "+ss.length());
//            int idx = s.indexOf("0x");
//            System.out.println("main,  [args] "+s.substring(idx+2 ,idx+6));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        int s = 0xF001;
        System.out.println("main,  [args] " + Integer.toHexString(s + 1));
        System.out.println("main,  [args] " + Integer.toHexString(s + 2));
        System.out.println("main,  [args] " + Integer.toHexString(s + 3));
        System.out.println("main,  [args] " + Integer.toHexString(s + 4));
        System.out.println("main,  [args] " + Integer.toHexString(s + 5));
        System.out.println("main,  [args] " + Integer.toHexString(s + 6));
        System.out.println("main,  [args] " + Integer.toHexString(s + 7));
        System.out.println("main,  [args] " + Integer.toHexString(s + 8));
        System.out.println("main,  [args] " + Integer.toHexString(s + 9));
        System.out.println("main,  [args] " + Integer.toHexString(s + 10));
        System.out.println("main,  [args] " + Integer.toHexString(s + 11));
        System.out.println("main,  [args] " + Integer.toHexString(s + 12));
        System.out.println("main,  [args] " + Integer.toHexString(s + 13));
        System.out.println("main,  [args] " + Integer.toHexString(s + 14));
        System.out.println("main,  [args] " + Integer.toHexString(s + 15));
        System.out.println("main,  [args] " + Integer.toHexString(s + 16));
        System.out.println("main,  [args] " + Integer.toHexString(s + 17));
        System.out.println("main,  [args] " + Integer.toHexString(s + 18));
        System.out.println("main,  [args] " + Integer.toHexString(s + 19));
        System.out.println("main,  [args] " + Integer.toHexString(s + 20));

        System.out.println("main,  [args] Integer.valueOf(\"F001\",16) "+Integer.valueOf("F001",16)+" "+(Integer.valueOf("F001",16) == s)+" "+Integer.toHexString(s));




    }

    /**
     * 工厂模式
     */
    private static void factoryPattern() {
        PizzaStore pizzaStore = new PizzaStore1();
        Pizza pizza = pizzaStore.createPizza(6);

    }

    /**
     * 装饰者模式
     */
    private static void decoratorPattern() {
        Beverage beverage = new DarkToast();
        beverage = new Moka(beverage);
        beverage = new Moka(beverage);
        beverage = new Milk(beverage);
        beverage.setSize(Beverage.Capacity.SMALL);
        System.out.println("decoratorPattern beverage " + beverage.getDescription() + " cost " + beverage.cost());
        int c;
        InputStream is = new CustomInputStream(System.in);
        try {
            while ((c = is.read()) > 0) {
                System.out.print((char) c);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 观察者模式
     */
    private static void observerPattern() {
        DataBean data = new DataBean();
        Display1 display1 = new Display1(data);
        Display2 display2 = new Display2(data);
        Display3 display3 = new Display3(data);
        data.dataChanged(80, 65, 30);
        data.dataChanged(82, 61, 26);
        data.dataChanged(89, 67, 29);
    }

}

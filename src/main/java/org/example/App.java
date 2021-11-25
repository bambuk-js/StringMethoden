package org.example;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Password: "+generatePassword());
        System.out.println("Password: "+generatePassword());
        System.out.println("Password: "+generatePassword());

        validateString();
    }
    public static String generatePassword(){
        String options = "abcdefghijklmnopqrstuvqxyz0123456789#*%&7(.ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        int count = 3;

        StringBuilder password = new StringBuilder();
        for(int i = 0; i < count; i++){
            password.append(appendRandStr(options, 4));
            if(i!=count-1){
                password.append('-');
            }
        }
        return password.toString();
    }
    private static String appendRandStr(String options, int length){
        StringBuilder string = new StringBuilder();
        for(int i = 0; i < length; i++){
            Random random = new Random();
            int index = random.nextInt(options.length());
            string.append(options.charAt(index));
        }
        return string.toString();
    }

    public static void validateString(){
        System.out.println("Email Validator is working: "+(validateEmail("peter.muster@lernende.bbw.ch")));
        System.out.println("Email Validator is working: "+(validateEmail("bb@bb.ch")));
        System.out.println("Email Validator is working: "+(!validateEmail("bb@bbch")));

        System.out.println("Date Validator is working: "+(validateDate("2009-11-16")));
        System.out.println("Date Validator is working: "+(validateDate("1999-11-06")));
        System.out.println("Date Validator is working: "+(!validateDate("2009-50-16")));

        System.out.println("Time Validator is working: "+(validateTime("18:03")));
        System.out.println("Time Validator is working: "+(validateTime("23:03")));
        System.out.println("Time Validator is working: "+(!validateTime("24:03")));

        System.out.println("Ip Validator is working: "+(validateIp("192.168.1.1")));
        System.out.println("Ip Validator is working: "+(validateIp("192.168.1.255")));
        System.out.println("Ip Validator is working: "+(!validateIp("1.1.1.01")));
    }

    public static boolean validateEmail(String string){
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        if(string.matches(pattern)){
            return true;
        }
        return false;
    }

    public static boolean validateDate(String string){
        String pattern = "^([0-9]{4}-[0-9]{2}-[0-9]{2})$";
        if(string.matches(pattern)){
            try {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                df.setLenient(false);
                df.parse(string);
                return true;
            } catch (ParseException e) {
                return false;
            }
        }
        return false;
    }

    public static boolean validateTime(String string){
        String pattern = "^([0-9]{2}:[0-9]{2})$";
        if(string.matches(pattern)){
            String hours = string.replaceAll("(([0-9]{2}):[0-9]{2})", "$2");
            String minutes = string.replaceAll("([0-9]{2}:([0-9]{2}))", "$2");
            int hoursInt = Integer.parseInt(hours);
            int minutesInt = Integer.parseInt(minutes);
            if(hoursInt > 23 || hoursInt < 1){
                return false;
            }
            if(minutesInt > 59 || minutesInt < 1){
                return false;
            }
            return true;
        }
        return false;
    }

    public static boolean validateIp(String string){
        String pattern = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
        if(string.matches(pattern)){
            return true;
        }
        return false;
    }
}

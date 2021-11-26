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
        System.out.println("Password: "+generatePassword());
        System.out.println("Password: "+generatePassword());
        System.out.println("Password: "+generatePassword());

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

        System.out.println("Mac Validator is working: "+(validateMac("01-23-45-67-89-AB")));
        System.out.println("Mac Validator is working: "+(validateMac("01:23:45:67:89:AB")));
        System.out.println("Mac Validator is working: "+(validateMac("0123.4567.89AB")));
        System.out.println("Mac Validator is working: "+(!validateMac("01-23-45-67-89-AH")));

        System.out.println("Login Validator is working: "+(validateLogin("5IS15PeMuster")));
        System.out.println("Login Validator is working: "+(validateLogin("5IA16PeMuster")));
        System.out.println("Login Validator is working: "+(!validateLogin("BA5IA16PeMuster")));

        System.out.println("Phone Validator is working: "+(validatePhone("+1 234 567 8901")));
        System.out.println("Phone Validator is working: "+(validatePhone("0041 234 567 89 01")));
        System.out.println("Phone Validator is working: "+(validatePhone("+1 (234) 56 89 901")));
        System.out.println("Phone Validator is working: "+(!validatePhone("+1/234/567/8901-000")));

        System.out.println("ISBN Validator is working: "+(validateISBN("3-8274-0599-8")));
        System.out.println("ISBN Validator is working: "+(validateISBN("3827405998")));
        System.out.println("ISBN Validator is working: "+(!validateISBN("38274059980")));
    }

    public static boolean validateEmail(String string){
        String pattern = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
        return string.matches(pattern);
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
            return minutesInt <= 59 && minutesInt >= 1;
        }
        return false;
    }

    public static boolean validateIp(String string){
        String pattern = "^((25[0-5]|(2[0-4]|1[0-9]|[1-9]|)[0-9])(\\.(?!$)|$)){4}$";
        return string.matches(pattern);
    }

    public static boolean validateMac(String string){
        String pattern = "^([0-9A-Fa-f]{2}[:-])"
                + "{5}([0-9A-Fa-f]{2})|"// | => fallback
                + "([0-9a-fA-F]{4}\\."
                + "[0-9a-fA-F]{4}\\."
                + "[0-9a-fA-F]{4})$";
        return string.matches(pattern);
    }

    public static boolean validateLogin(String string){
        String pattern = "^[0-9]{1}[A-Z]{2}[0-9]{2}[a-zA-zZ]{2,}$";
        return string.matches(pattern);
    }
    
    public static boolean validatePhone(String string){
        String pattern = "^(\\+|00)[0-9]{1,3}(\\([0-9]{3}\\)[0-9]{7}|[0-9]{10,11})$";
        return string.replace(" ", "").matches(pattern);
    }

    public static boolean validateISBN(String string){
        // 3-8274-0599-8, 3827405998
        String pattern = "^([0-9]{10})|([0-9]{1}\\-([0-9]{4}\\-){2}[0-9]{1})$";
        return string.matches(pattern);
    }
}

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class GrossPay {

    public static void main(String[] args) {
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        Scanner sc = new Scanner(System.in);

        Date myDate = new Date();
        String myDateFormat = "MM/dd/yyyy";
        SimpleDateFormat dtToday = new SimpleDateFormat(myDateFormat);

        double grossPay = 0;
        String name = "";
        String type = "";
        int unit = 0;

        System.out.print("Name of Employee: ");
        name = sc.nextLine();

        System.out.print("Would you like to use a flat rate? (y/n): ");
        String flatRateChoice = sc.nextLine();

        if (flatRateChoice.equalsIgnoreCase("y")) {
            type = "flat";
        } else {
            System.out.print("Type of Pay Schedule (flat/differential): ");
            type = sc.nextLine();
        }

        System.out.print("Items produced: ");
        unit = sc.nextInt();

        if (type.equalsIgnoreCase("flat")) {
            grossPay = unit * 7.50;
        } else if (type.equalsIgnoreCase("differential")) {
            if (unit > 0 && unit <= 112) {
                grossPay = unit * 7.15;
            } else if (unit > 112 && unit <= 150) {
                unit = unit - 112;
                grossPay = 112 * 7.15 + unit * 7.45;
            } else if (unit > 150 && unit <= 217) {
                unit = unit - 150;
                grossPay = 112 * 7.15 + 38 * 7.45 + unit * 7.95;
            } else {
                unit = unit - 217;
                grossPay = 112 * 7.15 + 38 * 7.45 + 67 * 7.95 + unit * 8.40;
            }
        } else {
            System.out.println("Wrong Type Selection");
            return;
        }

        double medicareTax = (grossPay * 1.45) / 100.0;
        double FICATax = (grossPay * 6.20) / 100.0;
        double FITTax = (grossPay * 25) / 100.0;
        double total = medicareTax + FICATax + FITTax;
        double netPay = grossPay - total;

        System.out.println("Employee Name: " + name);
        System.out.println("Current gross pay: " + nf.format(grossPay));
        System.out.println("Medicare tax: " + nf.format(medicareTax));
        System.out.println("FICA tax: " + nf.format(FICATax));
        System.out.println("Federal tax: " + nf.format(FITTax));
        System.out.println("Your net pay is: " + nf.format(netPay));
        System.out.println();
        System.out.println("Today's Date is: " + dtToday.format(myDate));
        System.out.println("Programmer: Mike Mordec");

        sc.close();
    }
}
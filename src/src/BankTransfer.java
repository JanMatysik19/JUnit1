package src;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class BankTransfer implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public final String name;
    public final String surname;
    public final double amount;
    private final String sourceAccountNumber;
    private final String targetAccountNumber;

    public BankTransfer(String name, String surname, double amount, String sourceAccountNumber, String targetAccountNumber) throws Exception {
        this.name = name;
        this.surname = surname;
        this.amount = amount;
        this.sourceAccountNumber = sourceAccountNumber;
        this.targetAccountNumber = targetAccountNumber;
    }


    private boolean isAccountNumberValid(String number) {
        var trimmed = number.replaceAll(" ", "");

        return trimmed.length() == 26;
    }

    private boolean isAmountValid(double amount) {
        return amount > 0;
    }


    public boolean doWireTransfer() {
        if (amount <= 0 ||
                sourceAccountNumber.replaceAll(" ", "").length() != 26 ||
                targetAccountNumber.replaceAll(" ", "").length() != 26) return false;

        try(var oos = new ObjectOutputStream(new FileOutputStream(toFile()))) {
            oos.writeObject(this);
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public File toFile() {
        var time = LocalTime.now();
        var now = time.format(DateTimeFormatter.ofPattern("HH_mm_ss"));

        return new File(surname + "_" + now + ".dat");
    }

    public static String displayFile(String name) throws IOException, ClassNotFoundException {
        try(var ois = new ObjectInputStream(new FileInputStream(name))) {
            var obj = (BankTransfer) ois.readObject();

            return "Bank transfer issued by " + obj.name + " " + obj.surname + " in the amount of $" + obj.amount + ".";
        } catch (Exception e) {
            throw e;
//            return "";
        }
    }
}

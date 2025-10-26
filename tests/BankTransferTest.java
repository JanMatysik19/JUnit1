import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.BankTransfer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BankTransferTest {
    BankTransfer bankTransfer;
    String parsedIssuedTime;

    @BeforeEach
    void setUp() throws Exception {
        bankTransfer = new BankTransfer("Anna", "Schmidt", 1500.00,
                        "30102010260000042270201111",
                        "89102010260000042270201111");
    }
    @AfterEach
    void tearDown() {
        bankTransfer = null;
        parsedIssuedTime = null;
    }


    @Test
    public void doWireTransferTest() {
        var time = LocalTime.now();
        parsedIssuedTime = time.format(DateTimeFormatter.ofPattern("HH_mm_ss"));

        boolean returnOut = bankTransfer.doWireTransfer(),    returnExp = true;
        Assertions.assertEquals(returnExp, returnOut, "Wire transfer returned value check.");

        boolean existsOut = Files.exists(Paths.get(bankTransfer.surname + "_" + parsedIssuedTime + ".dat")),     existsExp = true;
        Assertions.assertEquals(existsExp, existsOut, "Wire transfer serialized file creation check.");
    }

    @Test
    public void displayFileTest() throws IOException, ClassNotFoundException {
        var time = LocalTime.now();
        parsedIssuedTime = time.format(DateTimeFormatter.ofPattern("HH_mm_ss"));
        bankTransfer.doWireTransfer();

        String textOut = BankTransfer.displayFile(bankTransfer.surname + "_" + parsedIssuedTime + ".dat");
        String textExp = "Bank transfer issued by " + bankTransfer.name + " " + bankTransfer.surname + " in the amount of $" + bankTransfer.amount + ".";
        Assertions.assertEquals(textExp, textOut, "Displayed contents of the serialized file check.");
    }

    @Test
    public void toFileTest() {
        var time = LocalTime.now();
        parsedIssuedTime = time.format(DateTimeFormatter.ofPattern("HH_mm_ss"));
        bankTransfer.doWireTransfer();

        String nameOut = bankTransfer.toFile().getName(),   nameExp = bankTransfer.surname + "_" + parsedIssuedTime + ".dat";
        Assertions.assertEquals(nameExp, nameOut, "Serialized file name check.");
    }
}

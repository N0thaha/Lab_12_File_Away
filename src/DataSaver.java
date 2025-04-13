import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class DataSaver {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> records = new ArrayList<>();
        int idCounter = 1;
        boolean addMore;

        SafeInput.prettyHeader("User Record Collector");

        do {
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String id = String.format("%06d", idCounter);
            String email = SafeInput.getNonZeroLenString(in, "Enter Email");
            int yearOfBirth = SafeInput.getRangedInt(in, "Enter Year of Birth", 1900, 2024);

            String record = String.format("%s, %s, %s, %s, %d", firstName, lastName, id, email, yearOfBirth);
            records.add(record);
            idCounter++;
            addMore = SafeInput.getYNConfirm(in, "Do you want to enter another record");
        } while (addMore);

        String fileName = SafeInput.getNonZeroLenString(in, "Enter file name to save (without .csv)");
        if (!fileName.toLowerCase().endsWith(".csv")) {
            fileName += ".csv";
        }

        try {
            FileWriter writer = new FileWriter("src/" + fileName);
            for (String rec : records) {
                writer.write(rec + "\n");
            }
            writer.close();
            System.out.println("\nFile saved successfully as src/" + fileName);
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }

    }
}

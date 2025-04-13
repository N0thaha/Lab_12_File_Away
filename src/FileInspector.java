import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector {

    public static void main(String[] args) {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String lineContent = "";
        ArrayList<String> lines = new ArrayList<>();

        // Counters
        int lineCount = 0;
        int wordCount = 0;
        int charCount = 0;

        try {
            File workingDirectory = new File(System.getProperty("user.dir") + "\\src");
            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("Contents of the file:\n");

                while (reader.ready()) {
                    lineContent = reader.readLine();
                    lines.add(lineContent);
                    lineCount++;

                    System.out.printf("Line %4d: %s\n", lineCount, lineContent);

                    String[] words = lineContent.trim().split("\\s+");
                    if (!lineContent.trim().isEmpty()) {
                        wordCount += words.length;
                    }

                    charCount += lineContent.length();
                }

                reader.close();

                System.out.println("\n--- File Summary Report ---");
                System.out.println("File Name: " + selectedFile.getName());
                System.out.println("Number of lines: " + lineCount);
                System.out.println("Number of words: " + wordCount);
                System.out.println("Number of characters: " + charCount);
            } else {
                System.out.println("No file selected. Please run the program again.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IO Exception occurred!");
            e.printStackTrace();
        }
    }
}

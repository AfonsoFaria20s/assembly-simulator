import GUI.Window;

import javax.swing.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Window::new);

        // Path to the program.txt file
        String filePath = "src/program.txt"; // Update this path if necessary

        try {
            // Read all lines from the file into a List<String>
            List<String> lines = Files.readAllLines(Paths.get(filePath));

            // Convert List<String> to String[]
            String[] program = lines.toArray(new String[0]);

            // Create an Assembly instance with appropriate size
            Assembly asm = new Assembly(program.length);
            asm.loadProgram(program);
            asm.execute();

        } catch (IOException e) {
            // Handle potential IOExceptions such as file not found
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ExtractSignature {

    public static void main(String[] args) {
        try {
            // Command to extract the signature from c1.crt
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "bash", "-c", "openssl x509 -in c1.crt -noout -text | grep 'Signature Algorithm' -A 100"
            );

            // Redirect the output to signature.txt
            processBuilder.redirectOutput(new File("signature.txt"));
            processBuilder.redirectErrorStream(true);  // Redirect error stream to the same output

            // Start the process
            Process process = processBuilder.start();

            // Capture output and errors
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print error messages or other output
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Signature extracted successfully to signature.txt.");
            } else {
                System.out.println("Error extracting signature. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

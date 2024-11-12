import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

public class ExtractSignature {

    public static void main(String[] args) {
        try {
            // Command to extract the signature from c0.crt
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "bash", "-c", "openssl x509 -in c0.crt -noout -text | grep 'Signature Algorithm' -A 1"
            );

            // Redirect the output to signature.txt
            processBuilder.redirectOutput(new File("signature.txt"));

            // Start the process
            Process process = processBuilder.start();

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

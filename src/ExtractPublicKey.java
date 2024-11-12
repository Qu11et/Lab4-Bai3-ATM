import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

public class ExtractPublicKey {

    public static void main(String[] args) {
        try {
            // Command to extract public key from c1.crt
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "x509", "-in", "c1.crt", "-pubkey", "-noout"
            );

            // Redirect the output to public_key.pem
            processBuilder.redirectOutput(new File("public_key.pem"));

            // Start the process
            Process process = processBuilder.start();

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Public key extracted successfully to public_key.pem.");
            } else {
                System.out.println("Error extracting public key. Exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

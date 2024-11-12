import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;

public class ExtractPublicKey {

    public static void main(String[] args) {
        try {
            // Command to extract public key from c1.crt
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "x509", "-in", "c1.crt", "-pubkey", "-noout"
            );

            // Redirect the output to public_key.pem
            processBuilder.redirectOutput(new File("public_key.pem"));
            processBuilder.redirectErrorStream(true);  // Redirect error output to the same stream

            // Start the process
            Process process = processBuilder.start();

            // Read error messages if any
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

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

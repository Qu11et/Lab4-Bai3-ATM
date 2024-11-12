import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class VerifySignature {

    public static void main(String[] args) {
        try {
            // Command to verify the signature
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "openssl", "dgst", "-sha256", "-verify", "public_key.pem",
                    "-signature", "signature.bin", "tbs_data.bin"
            );

            // Start the process
            Process process = processBuilder.start();

            // Read the output from the verification process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print verification result
            }

            // Wait for the process to complete
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Verification process completed.");
            } else {
                System.out.println("Error in verification process. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

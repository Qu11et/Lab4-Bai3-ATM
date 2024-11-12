import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.IOException;

public class FetchCertificates {

    public static void main(String[] args) {
        try {
            // Define the script file path
            File scriptFile = new File("src/fetch_certs.sh");

            // Check if the script file exists
            if (!scriptFile.exists()) {
                System.out.println("Script file not found: " + scriptFile.getAbsolutePath());
                return;
            }

            // Make sure the script file is executable
            scriptFile.setExecutable(true);

            // Prepare ProcessBuilder to execute the script
             ProcessBuilder processBuilder = new ProcessBuilder("bash", scriptFile.getAbsolutePath());

            // Start the process
            Process process = processBuilder.start();

            

            // Read the output from the script execution
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }

            // Wait for the process to finish and get the exit code
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Certificates fetched successfully.");
            } else {
                System.out.println("Error fetching certificates. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
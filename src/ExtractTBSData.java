import java.io.*;

public class ExtractTBSData {

    public static void main(String[] args) {
        try {
            // Step 1: Convert the certificate to DER format (binary)
            ProcessBuilder toDerProcessBuilder = new ProcessBuilder(
                    "openssl", "x509", "-in", "c1.crt", "-outform", "DER", "-out", "c1.der"
            );
            Process toDerProcess = toDerProcessBuilder.start();

            // Capture output and error streams
            BufferedReader reader = new BufferedReader(new InputStreamReader(toDerProcess.getInputStream()));
            BufferedReader errorReader = new BufferedReader(new InputStreamReader(toDerProcess.getErrorStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            while ((line = errorReader.readLine()) != null) {
                System.err.println(line);
            }

            int toDerExitCode = toDerProcess.waitFor();

            if (toDerExitCode == 0) {
                System.out.println("Certificate converted to DER format successfully.");

                // Step 2: Extract the TBS data in binary from DER format
                ProcessBuilder extractTBSProcessBuilder = new ProcessBuilder(
                        "openssl", "asn1parse", "-in", "c1.der", "-inform", "DER", "-strparse", "4", "-out", "tbs_data.bin"
                );
                Process extractTBSProcess = extractTBSProcessBuilder.start();
                BufferedReader extractTBSReader = new BufferedReader(new InputStreamReader(extractTBSProcess.getInputStream()));
                BufferedReader extractTBSErrorReader = new BufferedReader(new InputStreamReader(extractTBSProcess.getErrorStream()));

                while ((line = extractTBSReader.readLine()) != null) {
                    System.out.println(line);
                }
                while ((line = extractTBSErrorReader.readLine()) != null) {
                    System.err.println(line);
                }

                int extractTBSExitCode = extractTBSProcess.waitFor();

                if (extractTBSExitCode == 0) {
                    System.out.println("TBS data extracted to binary format in tbs_data.bin.");
                } else {
                    System.out.println("Error extracting TBS data. Exit code: " + extractTBSExitCode);
                }

            } else {
                System.out.println("Error converting certificate to DER format. Exit code: " + toDerExitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

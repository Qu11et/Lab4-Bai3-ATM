import java.io.File;
import java.io.IOException;

public class ConvertSignatureToBinary {

    public static void main(String[] args) {
        try {
            // Step 1: Convert the certificate to DER format (binary)
            ProcessBuilder toDerProcessBuilder = new ProcessBuilder(
                    "openssl", "x509", "-in", "c0.crt", "-noout", "-outform", "DER", "-out", "c0.der"
            );
            Process toDerProcess = toDerProcessBuilder.start();
            int toDerExitCode = toDerProcess.waitFor();

            if (toDerExitCode == 0) {
                System.out.println("Certificate converted to DER format successfully.");

                // Step 2: Extract the signature in binary from DER format
                ProcessBuilder extractSignatureProcessBuilder = new ProcessBuilder(
                        "openssl", "asn1parse", "-in", "c0.der", "-inform", "DER", "-out", "signature.bin", "-noout", "-strparse", "4"
                );
                Process extractSignatureProcess = extractSignatureProcessBuilder.start();
                int extractSignatureExitCode = extractSignatureProcess.waitFor();

                if (extractSignatureExitCode == 0) {
                    System.out.println("Signature extracted to binary format in signature.bin.");
                } else {
                    System.out.println("Error extracting signature. Exit code: " + extractSignatureExitCode);
                }

            } else {
                System.out.println("Error converting certificate to DER format. Exit code: " + toDerExitCode);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

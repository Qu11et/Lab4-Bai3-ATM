public class Main {

    public static void main(String[] args) {
        try {
            // Step 1: Fetch certificates and save as c0.crt, c1.crt
            System.out.println("Fetching certificates...");
            FetchCertificates.main(null);

            // Step 2: Extract the public key from c1.crt
            System.out.println("Extracting public key...");
            ExtractPublicKey.main(null);

            // Step 3: Extract the signature from c0.crt
            System.out.println("Extracting signature...");
            ExtractSignature.main(null);

            // Step 4: Convert the signature to binary
            System.out.println("Converting signature to binary...");
            ConvertSignatureToBinary.main(null);

            // Step 5: Get TBS (To-Be-Signed) data of c0.crt
            System.out.println("Extracting TBS data...");
            ExtractTBSData.main(null);

            // Step 6: Verify the signature
            System.out.println("Verifying signature...");
            VerifySignature.main(null);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

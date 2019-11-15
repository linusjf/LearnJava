package command;

import java.util.concurrent.Callable;
import java.io.File;
import java.nio.file.Files;
import java.security.MessageDigest;

@Command(description = "Prints the checksum (MD5 by default) of a file to STDOUT.",
         name = "checksum", mixinStandardHelpOptions = true, version = "checksum 3.0")
class CheckSum implements Callable<Void> {

    @Parameters(index = "0", description = "The file whose checksum to calculate.")
    private File file;

    @Option(names = {"-a", "--algorithm"}, description = "MD5, SHA-1, SHA-256, ...")
    private String algorithm = "SHA-1";

    public static void main(String[] args) throws Exception {
        CommandLine.call(new CheckSum(), args);
    }

    @Override
    public Void call() throws Exception {
        byte[] fileContents = Files.readAllBytes(file.toPath());
        byte[] digest = MessageDigest.getInstance(algorithm).digest(fileContents);
        System.out.println(javax.xml.bind.DatatypeConverter.printHexBinary(digest));
        return null;
    }
}

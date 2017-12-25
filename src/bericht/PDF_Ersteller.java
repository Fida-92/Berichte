package bericht;

import de.nixosoft.jlr.*;
import java.io.File;
import java.io.IOException;

public class PDF_Ersteller {

    public static void main(String[] args) {
        File bericht = new File("C:\\Users\\User\\Desktop\\Invoices\\bericht_nach_Aenderung.tex");
        File bericht1 = new File("C:\\Users\\User\\Desktop\\Invoices\\invoice1.tex");
        File workingDirectory = new File("C:\\Users\\User\\Desktop\\Invoices");
        File desktop = new File("C:\\Users\\User\\Desktop\\Invoices");
        File tempDir = new File(workingDirectory.getAbsolutePath() + File.separator);
        DatenLeser daten = new DatenLeser();
        daten.wandeleInMapUm(daten.berichtFelder, daten.datenFelder);
        daten.leseDatei();
        if (!tempDir.isDirectory()) {
            tempDir.mkdir();
        }
        try {
            JLRGenerator pdfGen = new JLRGenerator();
            pdfGen.deleteTempFiles(false, true, true);
            if (!pdfGen.generate(bericht, desktop, workingDirectory)) {
                System.out.println(pdfGen.getErrorMessage());
            }
            JLROpener.open(pdfGen.getPDF());
            JLROpener.open(pdfGen.getPDF());
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

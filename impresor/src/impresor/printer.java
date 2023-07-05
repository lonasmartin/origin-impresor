package impresor;

import java.awt.print.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;

public class printer {

	public static void main(String[] args) {
        String printerName = "NPIC31334 (HP LaserJet Pro M201dw)"; // Nombre de la impresora a la que deseas enviar el archivo
        String filePath = "D:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\TMSYMS_BRCORVENJavaEnvironment\\PrivateTempStorage\\Remito000000063486300620232212.pdf"; // Ruta al archivo que deseas imprimir

        File file = new File(filePath);
        if (file.exists()) {
            try {
                FileInputStream fis = new FileInputStream(file);
                DocFlavor docFlavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
                Doc doc = new SimpleDoc(fis, docFlavor, null);
                PrintService[] printServices = PrintServiceLookup.lookupPrintServices(docFlavor, null);

                for (PrintService printService : printServices) {
                    if (printService.getName().equalsIgnoreCase(printerName)) {
                        DocPrintJob printJob = printService.createPrintJob();
                        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
                        attributes.add(new Copies(1)); // Cantidad de copias a imprimir
                        printJob.print(doc, attributes);
                        System.out.println("El archivo se envió a la impresora: " + printerName);
                        break;
                    }
                }
            } catch (FileNotFoundException | PrintException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("El archivo no existe.");
        }
	}

}

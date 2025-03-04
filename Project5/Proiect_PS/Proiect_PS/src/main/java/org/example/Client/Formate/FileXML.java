package org.example.Client.Formate;

import org.example.Client.Controller.LocatieAngajatController;
import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Repository.LocatieAngajatRepository;

import java.awt.*;
import java.io.*;
import java.util.List;

public class FileXML implements ICommand {
    private LocatieAngajatController locatieAngajatController;

    public FileXML(LocatieAngajatController locatieAngajatController) {
        this.locatieAngajatController = locatieAngajatController;
    }

    public FileXML() {
    }


    public void openFile(String filePath) {
        File file = new File(filePath);
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN) && file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                System.out.println("Eroare la deschiderea fișierului!");
            }
        } else {
            System.out.println("Fișierul nu poate fi deschis sau nu există.");
        }
    }

    @Override
    public void execute() {
        LocatieAngajatRepository locatieAngajatRepository = new LocatieAngajatRepository();
        List<LocatieAngajat> list = locatieAngajatRepository.getAllLocatiiAngajatOrderedById();

        String caleFisierSalvat = "C:\\Users\\filip\\Desktop\\PS\\Tema3_PS\\Fisiere\\Info.xml";

        StringBuilder continutFisier = new StringBuilder();
        continutFisier.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        continutFisier.append("<locatii>\n");
        for (LocatieAngajat locatieAngajat : list) {
            continutFisier.append("\t<locatieDeseu>\n");
            continutFisier.append("\t\t<idLocatieAngajat>").append(locatieAngajat.getIdLocatieAngajat()).append("</idLocatieAngajat>\n");
            continutFisier.append("\t\t<idLocatie>").append(locatieAngajat.getIdLocatieDeseu()).append("</idLocatie>\n");
            continutFisier.append("\t\t<angajat_id>").append(locatieAngajat.getIdAngajat()).append("</angajat_id>\n");
            continutFisier.append("\t</locatieDeseu>\n");
        }
        continutFisier.append("</locatii>");

        try{
            PrintWriter writer = new PrintWriter(caleFisierSalvat);

            writer.print(continutFisier);
            writer.close();
            System.out.println("Informatiile au fost salvate intr-un fisier .XML. Se deschide fisierul.");
            openFile(caleFisierSalvat);

        } catch (FileNotFoundException e) {
            System.out.println("Nu se poate crea fisierul .XML");
        }
    }
}

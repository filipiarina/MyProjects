package org.example.Client.Formate;

import org.example.Client.Controller.LocatieAngajatController;
import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Repository.LocatieAngajatRepository;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class FileJSON  implements ICommand {
    private LocatieAngajatController locatieAngajatController;

    public FileJSON(LocatieAngajatController locatieAngajatController) {
        this.locatieAngajatController = locatieAngajatController;
    }

    public FileJSON() {
    }

    public void openFile(String filePath) {
        File file = new File(filePath);
        Desktop desktop = Desktop.getDesktop();
        if (desktop.isSupported(Desktop.Action.OPEN) && file.exists()) {
            try {
                desktop.open(file);
            } catch (IOException ex) {
                System.out.println("Eroare la deschiderea fișierului: ");
            }
        } else {
            System.out.println("Fișierul nu poate fi deschis sau nu există.");
        }
    }

    @Override
    public void execute() {
        LocatieAngajatRepository locatieAngajatRepository = new LocatieAngajatRepository();
        List<LocatieAngajat> list = locatieAngajatRepository.getAllLocatiiAngajatOrderedById();

        String caleFisierSalvat = "C:\\Users\\filip\\Desktop\\PS\\Tema3_PS\\Fisiere\\Info.json";

        StringBuilder continutFisier = new StringBuilder("{\n\t\"locatiedeseu\":[\n");

        for (LocatieAngajat locatieAngajat:list) {
            continutFisier.append("\t\t{\n\t\t\t\"idLocatieAngajat\": ").append(locatieAngajat.getIdLocatieAngajat()).append(",\n\t\t\t");
            continutFisier.append("\"idLocatie\": \"").append(locatieAngajat.getIdLocatieDeseu()).append("\",\n\t\t\t");
            continutFisier.append("\"idAngajat\": ").append(locatieAngajat.getIdAngajat()).append(",\n\t\t\t");
        }

        continutFisier.deleteCharAt(continutFisier.length() - 1);
        continutFisier.deleteCharAt(continutFisier.length() - 1);
        continutFisier.append("\n\t]\n}");

        try{
            PrintWriter writer = new PrintWriter(caleFisierSalvat);

            writer.print(continutFisier);
            writer.close();
            System.out.println("Informatiile au fost salvate intr-un fisier .JSON. Se deschide fisierul.");
            openFile(caleFisierSalvat);

        } catch (FileNotFoundException e) {
            System.out.println("Nu se poate crea fisierul JSON");
        }
    }
}

package org.example.Client.Formate;

import org.example.Client.Controller.LocatieAngajatController;
import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Repository.LocatieAngajatRepository;

import java.awt.*;
import java.io.*;
import java.util.List;

public class FileCSV implements ICommand {
    private LocatieAngajatController locatieAngajatController;

    public FileCSV(LocatieAngajatController locatieAngajatController) {
        this.locatieAngajatController = locatieAngajatController;
    }

    public FileCSV() {
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

        String caleFisierSalvat = "C:\\Users\\filip\\Desktop\\PS\\Tema3_PS\\Fisiere\\Info.csv";

        try{
            PrintWriter writer = new PrintWriter(caleFisierSalvat);

            // Scrie antetele coloanelor
            writer.println("idLocatieAngjat, idLocatie, idAngajat");

            // Scrie datele fiecarui pachet intr-o linie separata
            for (LocatieAngajat locatieAngajat : list) {
                writer.println(locatieAngajat.getIdLocatieAngajat() + "," + locatieAngajat.getIdLocatieDeseu() + "," +
                        locatieAngajat.getIdAngajat());
            }
            writer.close();
            System.out.println("Informatiile au fost salvate intr-un fisier .CSV. Se deschide fisierul.");

            openFile(caleFisierSalvat);

        } catch (FileNotFoundException e) {
            System.out.println("Nu se poate crea fisierul CSV");
        }
    }


}

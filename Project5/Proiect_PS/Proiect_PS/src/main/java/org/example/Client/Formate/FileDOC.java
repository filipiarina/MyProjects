package org.example.Client.Formate;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.example.Client.Controller.LocatieAngajatController;
import org.example.Server.Model.LocatieAngajat;
import org.example.Server.Model.Repository.LocatieAngajatRepository;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class FileDOC implements ICommand {
    private LocatieAngajatController locatieAngajatController;

    public FileDOC(LocatieAngajatController locatieAngajatController) {
        this.locatieAngajatController = locatieAngajatController;
    }

    public FileDOC() {
    }

    public void writeFile(List<LocatieAngajat> list) {
        XWPFDocument document = new XWPFDocument();
        FileOutputStream out = null;

        try {
            String caleFisierSalvat = "C:\\Users\\filip\\Desktop\\PS\\Tema3_PS\\Fisiere\\Info.docx";
            out = new FileOutputStream(caleFisierSalvat);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        }

        //create table
        XWPFTable table = document.createTable();

        //header
        XWPFTableRow tableRowOne = table.getRow(0);
        tableRowOne.getCell(0).setText("idLocatieAngajat");
        tableRowOne.addNewTableCell().setText("idLocatie");
        tableRowOne.addNewTableCell().setText("idAngajat");

        //continut
        for (LocatieAngajat locatieAngajat: list) {
            XWPFTableRow tableRow = table.createRow();
            tableRow.getCell(0).setText(locatieAngajat.getIdLocatieAngajat().toString());
            tableRow.getCell(1).setText(locatieAngajat.getIdLocatieDeseu().toString());
            tableRow.getCell(2).setText(locatieAngajat.getIdLocatieAngajat().toString());
        }

        try {
            document.write(out);
            out.close();
            System.out.println("Informatiile au fost salvate intr-un fisier .DOCX. Se deschide fisierul.");

            // Deschide fișierul .DOCX după ce a fost creat
            openFile("C:\\Users\\filip\\Desktop\\PS\\PS_TEMA3\\Filip_Iarina_Tema3_PS\\Fisiere\\Info.docx");

        } catch(IOException exception) {
            System.out.println("Eroare la scrierea fișierului .DOCX: ");
        }
    }

    public void openFile(String filePath) {
        Desktop desktop = Desktop.getDesktop();
        File file = new File(filePath);

        try {
            if (desktop.isSupported(Desktop.Action.OPEN) && file.exists()) {
                desktop.open(file);
            } else {
                System.out.println("Fișierul .DOCX nu poate fi deschis sau nu există.");
            }
        } catch (IOException ex) {
            System.out.println("Eroare la deschiderea fișierului .DOCX: ");
        }
    }


    @Override
    public void execute() {
        LocatieAngajatRepository locatieAngajatRepository = new LocatieAngajatRepository();
        List<LocatieAngajat> list = locatieAngajatRepository.getAllLocatiiAngajatOrderedById();

        writeFile(list);
    }


}
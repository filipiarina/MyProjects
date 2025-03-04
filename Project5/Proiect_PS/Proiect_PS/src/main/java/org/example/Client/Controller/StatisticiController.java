package org.example.Client.Controller;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;


import javax.swing.*;
import java.util.List;


public class StatisticiController {
    /*private List<StatisticiGUI> statistici;

    public StatisticiController() {
        this.statistici=new ArrayList<>();

        JFreeChart chart = chart3(dateChart3());
        ChartPanel ch = new ChartPanel(chart);
        ch.setPreferredSize(new Dimension(800, 600));
        this.statistici.add(new StatisticiGUI());
        this.statistici.get(0).getPanel1().add(ch, new GridBagConstraints());

        JFreeChart chart3 = chart1(dateChart1());
        ChartPanel ch3 = new ChartPanel(chart3);
        ch3.setPreferredSize(new Dimension(800, 600));
        this.statistici.add(new StatisticiGUI());
        this.statistici.get(2).getPanel1().add(ch3, new GridBagConstraints());

    }

    public List<StatisticiGUI> getViews() {
        for (StatisticiGUI v: this.statistici) {
            v.setTitle("Statistici");
            v.setContentPane(v.getPanel1());

            v.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            v.setSize(800, 600);
        }

        return this.statistici;
    }


    private static CategoryDataset dateChart1() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        LocatieDeseuRepository locatieDeseuRepository = new LocatieDeseuRepository();
        List<LocatieDeseu> locatieDeseuList = locatieDeseuRepository.getAllLocatiiDeseuOrderedById();
        List<String> loc = new ArrayList<>();
        for (LocatieDeseu p: locatieDeseuList) {
            if (!loc.contains(p.getStadiu())) {
                loc.add(p.getStadiu());
            }
        }
        for (String d : loc) {
            int nr = 0;
            for (LocatieDeseu p: locatieDeseuList) {
                if (p.getStadiu().equals(d)) {
                    nr++;
                }
            }
            dataset.addValue(nr, d, d);
        }
        return dataset;
    }

    private JFreeChart chart1(CategoryDataset dataset) {
        JFreeChart chart= ChartFactory.createBarChart("Locatii deseuri", "Locatia", "Stadiul", dataset, PlotOrientation.VERTICAL, true,true,false);
        return chart;
    }




    private static PieDataset dateChart3() {
        DefaultPieDataset dataset = new DefaultPieDataset();
        LocatieDeseuRepository locatieDeseuRepository = new LocatieDeseuRepository();
        List<LocatieDeseu> locatieDeseuList = locatieDeseuRepository.getAllLocatiiDeseuOrderedById();
        List<String> list = new ArrayList<>();
        for (LocatieDeseu p: locatieDeseuList) {
            if (!list.contains(p.getAdresaLocatie())) {
                list.add(p.getAdresaLocatie());
            }
        }
        for (String d: list) {
            int nr = 0;
            for (LocatieDeseu p: locatieDeseuList) {
                if (p.getAdresaLocatie().equals(d)) {
                    nr++;
                }
            }
            dataset.setValue(d, nr);
        }
        return dataset;
    }

    private static JFreeChart chart3(PieDataset dataset) {
        JFreeChart chart = ChartFactory.createPieChart("Adresele locatiilor de deseuri", dataset, true, true, false);
        PiePlot plot = (PiePlot) chart.getPlot();
        PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0}: {1} ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        plot.setLabelGenerator(gen);
        return chart;
    }*/
    private JPanel mainPanel;
    private JTable statisticiTable;

    public StatisticiController() {
        // Initialize panel and layout
        mainPanel = new JPanel(new GridLayoutManager(1, 1));

        // Dummy data for demonstration
        String[] columnNames = {"Statistic 1", "Statistic 2"};
        Object[][] data = {
                {"Value 1", "Value 2"},
                {"Value 3", "Value 4"},
        };

        statisticiTable = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(statisticiTable);
        mainPanel.add(scrollPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public List<String[]> getStatistici() {
        // Retrieve data logic here, return as list of string arrays
        return List.of(new String[]{"Value 1", "Value 2"}, new String[]{"Value 3", "Value 4"});
    }
}

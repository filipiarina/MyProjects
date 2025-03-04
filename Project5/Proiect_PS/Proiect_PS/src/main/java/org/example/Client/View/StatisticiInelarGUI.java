package org.example.Client.View;

import org.example.Server.Model.LocatieDeseu;
import org.example.Server.Model.Repository.LocatieDeseuRepository;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.RingPlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticiInelarGUI extends JFrame  {
    private LocatieDeseuRepository locatieDeseuRepository;

    public StatisticiInelarGUI(LocatieDeseuRepository repository) {
        this.locatieDeseuRepository = repository;

        setTitle("Statistici Locatie Deseuri - Structură Inelară");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFreeChart doughnutChart = createChart();
        ChartPanel chartPanel = new ChartPanel(doughnutChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        setVisible(true);
    }

    private JFreeChart createChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        List<LocatieDeseu> artworks = locatieDeseuRepository.getAllLocatiiDeseuOrderedById();
        Map<String, Integer> typeCounts = new HashMap<>();
        for (LocatieDeseu artwork : artworks) {
            String type = artwork.getStadiu();
            typeCounts.put(type, typeCounts.getOrDefault(type, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : typeCounts.entrySet()) {
            dataset.setValue(entry.getKey(), entry.getValue());
        }

        JFreeChart chart = ChartFactory.createRingChart(
                "Starea deseurilor",
                dataset,
                true,
                true,
                false
        );
        RingPlot plot = (RingPlot) chart.getPlot();
        plot.setSectionDepth(0.30); // Ajustează adâncimea secțiunii pentru a modifica aspectul gogoașei
        return chart;
    }
}

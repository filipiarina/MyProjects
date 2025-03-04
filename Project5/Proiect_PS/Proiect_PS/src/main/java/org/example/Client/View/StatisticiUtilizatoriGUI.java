package org.example.Client.View;

import org.example.Server.Model.Repository.AdministratorRepository;
import org.example.Server.Model.Repository.UserRepository;
import org.example.Server.Model.User;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticiUtilizatoriGUI extends JFrame {
    private AdministratorRepository userRepository;

    public StatisticiUtilizatoriGUI(AdministratorRepository repository) {
        this.userRepository = repository;

        setTitle("Statistici Utilizatori - Structură Radială");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JFreeChart pieChart = createChart();
        ChartPanel chartPanel = new ChartPanel(pieChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
        setContentPane(chartPanel);
        setVisible(true);
    }

    private JFreeChart createChart() {
        DefaultPieDataset dataset = new DefaultPieDataset();

        try {
            List<User> users = userRepository.getAllUsers();
            Map<String, Integer> roleCounts = new HashMap<>();
            for (User user : users) {
                String role = user.getRole();
                roleCounts.put(role, roleCounts.getOrDefault(role, 0) + 1);
            }
            for (Map.Entry<String, Integer> entry : roleCounts.entrySet()) {
                dataset.setValue(entry.getKey(), entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ChartFactory.createPieChart(
                "Distribuția Utilizatorilor pe Roluri",
                dataset,
                true,
                true,
                false
        );
    }
}

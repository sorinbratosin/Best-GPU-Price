package com.sorinbratosin.BestGPUPrice.View;
import com.sorinbratosin.BestGPUPrice.Crawler.CrawlerManager;
import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.Database.IllegalCharactersException;
import com.sorinbratosin.BestGPUPrice.Service.GPUService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;


public class MainWindow {

    private JButton extractButton;
    private JTextField searchTextField;
    private JButton searchButton;
    private JCheckBox orderByPriceAsc;
    private JCheckBox inStock;
    private DefaultTableModel tableModel;
    private static final String[] COLUMN_NAMES = {"Id", "Name", "Price", "Availability", "URL"};
    private static final int NUM_OF_COLUMNS = COLUMN_NAMES.length;
    private List<GPU> gpuList = new ArrayList<>();
    private GPUService gpuService = new GPUService();

    public void createFrame() {
        JFrame jFrame = new JFrame("GPU Crawler");
        JPanel buttonAndTextPanel = new JPanel();
        JPanel gpuPanel = new JPanel();
        extractButton = new JButton("Extract");
        searchTextField = new JTextField(50);
        searchButton = new JButton("Search");
        orderByPriceAsc = new JCheckBox("Order Ascending");
        inStock = new JCheckBox("Show only available");

        buttonAndTextPanel.add(extractButton);
        buttonAndTextPanel.add(searchTextField);
        buttonAndTextPanel.add(inStock);
        buttonAndTextPanel.add(orderByPriceAsc);
        buttonAndTextPanel.add(searchButton);

        searchButton.addActionListener(e -> {
            try {
                String value = searchTextField.getText();
                if (!inStock.isSelected() && !orderByPriceAsc.isSelected()) {
                    tableModel.setDataVector(dataSelectAllGPUThatContain(value), COLUMN_NAMES);
                } else if (inStock.isSelected() && !orderByPriceAsc.isSelected()) {
                    tableModel.setDataVector(dataSelectAllGPUAvailableThatContain(value), COLUMN_NAMES);
                } else if (!inStock.isSelected() && orderByPriceAsc.isSelected()) {
                    tableModel.setDataVector(dataSelectAllGPUThatContainOrderByPriceAsc(value), COLUMN_NAMES);
                } else if (inStock.isSelected() && orderByPriceAsc.isSelected()) {
                    tableModel.setDataVector(dataSelectAllGPUAvailableThatContainOrderByPriceAsc(value), COLUMN_NAMES);
                }
            } catch (IllegalCharactersException illegalCharactersException) {
                searchTextField.setText("");
                JOptionPane.showMessageDialog(jFrame, illegalCharactersException.getMessage());
            }
        });

        extractButton.addActionListener(e -> {
            gpuService.truncateTable();
            CrawlerManager crawlerManager = new CrawlerManager();
            crawlerManager.crawl();

            while(crawlerManager.getEmagThread().isAlive() || crawlerManager.getPcGarageThread().isAlive()) {
                JOptionPane.showMessageDialog(jFrame, "Please wait for the extraction to be over!");
            }
            tableModel.setDataVector(dataSelectAllGPU(), COLUMN_NAMES);
        });

        tableModel = new DefaultTableModel(dataSelectAllGPU(), COLUMN_NAMES);
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);

        JScrollPane scroller = new JScrollPane(table);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        gpuPanel.setLayout(new BorderLayout());
        gpuPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        gpuPanel.add(scroller, BorderLayout.CENTER);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1500, 1000);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().add(BorderLayout.NORTH, buttonAndTextPanel);
        jFrame.getContentPane().add(BorderLayout.CENTER, gpuPanel);
    }

    private Object[][] tableData(int numOfRows) {
        Object[][] data = new Object[numOfRows][NUM_OF_COLUMNS];
        for (int x = 0; x < numOfRows; x++) {
            for (int j = 0; j < NUM_OF_COLUMNS; j++) {
                switch (j) {
                    case 0:
                        data[x][j] = gpuList.get(x).getId();
                        break;
                    case 1:
                        data[x][j] = gpuList.get(x).getName();
                        break;
                    case 2:
                        data[x][j] = gpuList.get(x).getPrice();
                        break;
                    case 3:
                        data[x][j] = gpuList.get(x).isAvailable();
                        break;
                    case 4:
                        data[x][j] = gpuList.get(x).getUrl();
                        break;
                }
            }
        }
        return data;
    }

    private Object[][] dataSelectAllGPU() {
        gpuList = gpuService.findAll();
        int numOfRows = gpuService.countAll();
        return tableData(numOfRows);
    }

    private Object[][] dataSelectAllGPUOrderByPriceAsc() {
        gpuList = gpuService.findAllOrderByPrice();
        int numOfRows = gpuService.countAll();
        return tableData(numOfRows);
    }

    private Object[][] dataSelectAllGPUThatContain(String containsName) throws IllegalCharactersException {
        gpuList = gpuService.findByName(containsName);
        int numOfRows = gpuService.countAllWhereNameContains(containsName);
        return tableData(numOfRows);
    }

    private Object[][] dataSelectAllGPUAvailableThatContain(String containsName) throws IllegalCharactersException {
        gpuList = gpuService.findAllAvailableThatContain(containsName);
        int numOfRows = gpuList.size();
        return tableData(numOfRows);
    }

    private Object[][] dataSelectAllGPUThatContainOrderByPriceAsc(String containsName) throws IllegalCharactersException {
        gpuList = gpuService.findAllThatContainOrderByPrice(containsName);
        int numOfRows = gpuList.size();
        return tableData(numOfRows);
    }

    private Object[][] dataSelectAllGPUAvailableThatContainOrderByPriceAsc(String containsName) throws IllegalCharactersException {
        gpuList = gpuService.findAllAvailableThatContainOrderByPrice(containsName);
        int numOfRows = gpuList.size();
        return tableData(numOfRows);
    }
}

package com.sorinbratosin.BestGPUPrice.View;
import com.sorinbratosin.BestGPUPrice.Service.GPUService;

import javax.swing.*;
import java.awt.*;

public class MainWindow {

    private JFrame jFrame;
    private JPanel buttonAndTextPanel;
    private JPanel gpuPanel;
    private JButton extractButton;
    private JTable table;
    private JScrollPane scroller;
    private JTextField searchTextField;
    private JButton searchButton;
    private JButton orderByAscPriceButton;
    private static final String[] COLUMN_NAMES = {"Id", "Name", "Price", "Availability", "URL"};

    private GPUService gpuService = new GPUService();

    public MainWindow() {
        createFrame();
    }

    private void createFrame() {
        jFrame = new JFrame("GPU Crawler");
        buttonAndTextPanel = new JPanel();
        gpuPanel = new JPanel();
        extractButton = new JButton("Extract");
        searchTextField = new JTextField();
        searchButton = new JButton("Search");
        orderByAscPriceButton = new JButton("Order Ascending");

        buttonAndTextPanel.add(extractButton);
        buttonAndTextPanel.add(searchTextField);
        buttonAndTextPanel.add(searchButton);
        buttonAndTextPanel.add(orderByAscPriceButton);


        int numOfColumns = COLUMN_NAMES.length;
        int numOfRows = gpuService.countAll();
        Object[][] data = new Object[numOfRows][numOfColumns];
        for(int x = 0; x < numOfRows; x++) {
            for(int j = 0; j < numOfColumns; j++) {
                switch (j) {
                    case 0:
                        data[x][j] = gpuService.findAll().get(x).getId();
                    break;
                    case 1:
                        data[x][j] = gpuService.findAll().get(x).getName();
                    break;
                    case 2:
                        data[x][j] = gpuService.findAll().get(x).getPrice();
                    break;
                    case 3:
                        data[x][j] = gpuService.findAll().get(x).isAvailable();
                    break;
                    case 4:
                        data[x][j] = gpuService.findAll().get(x).getUrl();
                    break;
                }
            }
        }

        table = new JTable(data , COLUMN_NAMES);
        table.setFillsViewportHeight(true);

        scroller = new JScrollPane(table);
        scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);

        gpuPanel.setLayout(new BorderLayout());
        gpuPanel.add(table.getTableHeader(), BorderLayout.PAGE_START);
        gpuPanel.add(scroller, BorderLayout.CENTER);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jFrame.setSize(1500,1500);
        jFrame.setLocationRelativeTo(null);
        jFrame.getContentPane().add(BorderLayout.NORTH, buttonAndTextPanel);
        jFrame.getContentPane().add(BorderLayout.CENTER,gpuPanel);
    }
}

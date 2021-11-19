package com.sorinbratosin.BestGPUPrice;

import com.sorinbratosin.BestGPUPrice.Crawler.PcGarageCrawler;
import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.View.MainWindow;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        /*EmagCrawler emagCrawler = new EmagCrawler();
        List<GPU> gpuList = emagCrawler.extract(); */

        /*int x = 0;
        for(GPU gpu : gpuList) {
            System.out.println(++x + ". " + gpu.getName() + "\n" + "Price: " + gpu.getPrice() + "; Available: " + gpu.isAvailable() + "; URL: " + gpu.getUrl());
        } */

        /*PcGarageCrawler pcGarageCrawler = new PcGarageCrawler();

            List<GPU> pcGarageGpuList = pcGarageCrawler.getAllGPU();
            int x = 0;
            for (GPU gpu : pcGarageGpuList) {
                System.out.println(++x + ". " + gpu.getName() + "\n" + "Price: " + gpu.getPrice() + "; Available: " + gpu.isAvailable() + "; URL: " + gpu.getUrl());
            }*/

        MainWindow mainWindow = new MainWindow();
        }
    }

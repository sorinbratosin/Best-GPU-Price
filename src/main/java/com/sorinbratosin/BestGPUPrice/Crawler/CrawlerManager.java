package com.sorinbratosin.BestGPUPrice.Crawler;

public class CrawlerManager {

    public void crawl() {

        EmagCrawler emagCrawler = new EmagCrawler();
        PcGarageCrawler pcGarageCrawler = new PcGarageCrawler();

        Thread emagThread = new Thread(emagCrawler);
        Thread pcGarageThread = new Thread(pcGarageCrawler);

        emagThread.start();
        pcGarageThread.start();
    }
}

package com.sorinbratosin.BestGPUPrice.Crawler;

public class CrawlerManager {

    private Thread emagThread;
    private Thread pcGarageThread;

    public void crawl() {

        EmagCrawler emagCrawler = new EmagCrawler();
        PcGarageCrawler pcGarageCrawler = new PcGarageCrawler();

        emagThread = new Thread(emagCrawler);
        pcGarageThread = new Thread(pcGarageCrawler);

        emagThread.start();
        pcGarageThread.start();
    }

    public Thread getEmagThread() {
        return emagThread;
    }

    public Thread getPcGarageThread() {
        return pcGarageThread;
    }
}

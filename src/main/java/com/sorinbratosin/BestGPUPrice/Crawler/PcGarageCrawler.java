package com.sorinbratosin.BestGPUPrice.Crawler;

public class PcGarageCrawler implements Runnable {

    private static final String PCGARAGE_LINK_FIRST_PAGE = "https://www.pcgarage.ro/placi-video/";

    @Override
    public void run() {
        PcGarageCrawlerPageByPage pcGarageCrawlerPageByPage = new PcGarageCrawlerPageByPage();
        pcGarageCrawlerPageByPage.extract(PCGARAGE_LINK_FIRST_PAGE);

        for(int x = 0; x <= pcGarageCrawlerPageByPage.getUrlPageList().size()-1; x++) {
            pcGarageCrawlerPageByPage.extract(pcGarageCrawlerPageByPage.getUrlPageList().get(x));
        }
    }
}

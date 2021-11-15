package com.sorinbratosin.BestGPUPrice.Crawler;

import com.sorinbratosin.BestGPUPrice.Database.GPU;

import java.util.ArrayList;
import java.util.List;

public class PcGarageCrawler {

    private static final String PCGARAGE_LINK_FIRST_PAGE = "https://www.pcgarage.ro/placi-video/";

    public List<GPU> getAllGPU() {
        List<GPU> list = new ArrayList<>();
        PcGarageCrawlerPageByPage pcGarageCrawlerPageByPage = new PcGarageCrawlerPageByPage();
        list.addAll(pcGarageCrawlerPageByPage.extract(PCGARAGE_LINK_FIRST_PAGE));

        for(int x = 0; x <= pcGarageCrawlerPageByPage.getUrlPageList().size()-1; x++) {
            list.addAll(pcGarageCrawlerPageByPage.extract(pcGarageCrawlerPageByPage.getUrlPageList().get(x)));
        }

        return list;
    }
}

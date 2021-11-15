package com.sorinbratosin.BestGPUPrice.Crawler;

import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.Helper.FormatPrice;
import com.sorinbratosin.BestGPUPrice.Service.GPUService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

public class EmagCrawler {

    private GPUService gpuService = new GPUService();

    public List<GPU> extract() {

        List<GPU> events = new ArrayList<>();
        List<String> urlPage = new ArrayList<>();
        urlPage.add("https://www.emag.ro/placi_video/c");

        System.setProperty("webdriver.chrome.driver", "D:\\IntelliJ projects\\Best-GPU-Price\\src\\main\\resources\\res\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("start-maximized");
        WebDriver driver = new ChromeDriver(options);

        for(int x = 0; x < urlPage.size(); x++) {

            driver.get(urlPage.get(x));

            String page = driver.getPageSource();
            Document doc = Jsoup.parse(page);
            Elements elements = doc.select("[data-department-id=\"23\"]");

            for (Element gpuElement : elements) {

                String strNumOfPages = doc.select(".listing-panel-footer .row .col-xs-12 ul#listing-paginator li.active span").text();
                strNumOfPages = strNumOfPages.substring(strNumOfPages.length()-1);
                int numOfPages = Integer.parseInt(strNumOfPages);

                if(urlPage.size() != numOfPages) {
                    for(int i = 2; i <= numOfPages; i++) {
                        urlPage.add("https://www.emag.ro/placi_video/p" + i + "/c");
                    }
                }


                String name = gpuElement.select(".card-v2-info .pad-hrz-xs .card-v2-title").text();
                String price = gpuElement.select(".card-v2-content .card-v2-pricing .product-new-price").text();
                String available = gpuElement.select(".card-estimate-placeholder .mrg-btm-xxs").text();

                Element link = gpuElement.select(".card-v2 .card-v2-info > a").first();
                String url = link.attr("href");

                GPU gpu = new GPU();

                if(name.contains("\"")) {
                    name = name.replace("\"", "");
                }
                gpu.setName(name);

                FormatPrice formatPrice = new FormatPrice(price);
                gpu.setPrice(formatPrice.getPrice());
                gpu.setUrl(url);

                String[] s = available.trim().split("\\s");
                if (s[0].equals("ultimul") || s[0].equals("ultimele") || s[0].equals("în") || s[0].equals("disponibil")) {
                    gpu.setAvailable(true);
                } else {
                    gpu.setAvailable(false);
                }

                events.add(gpu);
                gpuService.saveGPU(gpu);
            }
        }
        driver.quit();
        return events;
    }
}

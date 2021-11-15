package com.sorinbratosin.BestGPUPrice.Crawler;

import com.sorinbratosin.BestGPUPrice.Database.GPU;
import com.sorinbratosin.BestGPUPrice.Helper.FormatPrice;
import com.sorinbratosin.BestGPUPrice.Service.GPUService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.ArrayList;
import java.util.List;


public class PcGarageCrawlerPageByPage {

    private int numOfPages;
    private List<String> urlPageList = new ArrayList<>();
    private GPUService gpuService = new GPUService();

    public List<GPU> extract(String website) {

        List<GPU> events = new ArrayList<>();

        System.setProperty("webdriver.gecko.driver", "D:\\IntelliJ projects\\Best-GPU-Price\\src\\main\\resources\\res\\geckodriver.exe");
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("--enable-javascript");
        WebDriver driver = new FirefoxDriver(options);
        driver.manage().window().maximize();

        driver.get(website);
        String page = driver.getPageSource();
        driver.quit();

        Document doc = Jsoup.parse(page);

        Elements elements = doc.select(".standard #main_wrapper #website_container #website_container_m #content_wrapper div.clearfix #cat_container #category_container #category_content .container-fluid .grid_products #wrapper_listing_products div.product_box_parent");

        for (Element gpuElement : elements) {

                if(urlPageList.size()+1 != numOfPages) {
                    Element linkToLastPage = doc.select(".lr-pagination .pagination li").last().select("a").first();
                    String stringLinkToLastPage = linkToLastPage.attr("href");
                    String[] linkToLastPageArr = stringLinkToLastPage.split("/");
                    String lastIndexOfArr = linkToLastPageArr[linkToLastPageArr.length - 1];
                    String strNumOfPages = lastIndexOfArr.substring(lastIndexOfArr.length() - 2);
                    numOfPages = Integer.parseInt(strNumOfPages);

                    if (urlPageList.size() != numOfPages) {
                        for (int i = 2; i <= numOfPages; i++) {
                            urlPageList.add("https://www.pcgarage.ro/placi-video/pagina" + i + "/");
                        }
                    }
                }

                String name = gpuElement.select(".product_box_middle .product_box_name .my-0 a").text();
                String price = gpuElement.select(".product_box_bottom .product_box_price_cart .product_box_price_container .pb-price .price").text();
                String available = gpuElement.select(".product_box_bottom .product_box_availability").text();

                Element link = gpuElement.select(".product_box_image > a").first();
                String url = link.attr("href");

                GPU gpu = new GPU();
                if (name.contains("\"")) {
                    name = name.replace("\"", "");
                }

                gpu.setName(name);
                FormatPrice formatPrice = new FormatPrice(price);
                gpu.setPrice(formatPrice.getPrice());
                gpu.setUrl(url);

                String[] s = available.trim().split("\\s");
                if (s[0].equals("Nu")) {
                    gpu.setAvailable(false);
                } else {
                    gpu.setAvailable(true);
                }

                events.add(gpu);
                gpuService.saveGPU(gpu);
            }
        return events;
    }

    public List<String> getUrlPageList() {
        return urlPageList;
    }
}

package com.yoong.scraping.day2;

import java.io.FileWriter;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class MultiStockScraper {
    public static void main(String[] args) {
        String[] codes = {"005930", "000660", "035720", "005380", "035420"};
        
        String fileName = "stock_data.csv";

        try (FileWriter writer = new FileWriter(fileName)) {
        	
            writer.write("종목명,현재가,거래량\n");

            for (String code : codes) {
                try {
                    String url = "https://finance.naver.com/item/main.naver?code=" + code;
                    Document doc = Jsoup.connect(url)
        		            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36")
        		            .header("Referer", "https://finance.naver.com/")
        		            .get();

                    String name = doc.select(".wrap_company h2 a").text();
                    
                    String price = doc.select(".no_today .blind").first().text().replace(",", "");
                    String volume = doc.select(".no_info td").get(2).select(".blind").first().text().replace(",", "");

                    writer.write(String.format("%s,%s,%s\n", name, price, volume));
                    System.out.println(name + " 수집 완료...");

                    Thread.sleep(1000);

                } catch (Exception e) {
                    System.err.println("오류 발생: " + e.getMessage());
                }
            }
            System.out.println("====== 파일 저장 완료: " + fileName + " ======");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
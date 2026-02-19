package com.yoong.scraping.day1;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class NaverFinanceTest {

	public static void main(String[] args) {
		
		try {
		    // 1. 주소 설정
		    String itemCode = "005930"; 
		    String url = "https://finance.naver.com/item/main.naver?code=" + itemCode;

		    // 2. 연결 및 HTML 가져오기
		    Document doc = Jsoup.connect(url)
		            .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/145.0.0.0 Safari/537.36")
		            .header("Referer", "https://finance.naver.com/")
		            .get();

		    // 3. 데이터 추출
		    // 현재가
		    String nowPrice = doc.select(".no_today .blind").first().text();
		    
		    // 정보 테이블(전일, 시가, 고가, 거래량 등) 싹 다 긁어오기
		    Elements infoTds = doc.select(".no_info td");
		    
		    String prevPrice = infoTds.get(0).select(".blind").text(); // 전일가
		    String marketPrice = infoTds.get(1).select(".blind").text(); // 시가 
		    String volume = infoTds.get(2).select(".blind").first().text(); // 거래량 

		    // 4. 결과 출력
		    System.out.println("종목코드: " + itemCode);
		    System.out.println("현재가: " + nowPrice);
		    System.out.println("전일가: " + prevPrice);
		    System.out.println("거래량: " + volume);

		} catch (Exception e) {
		    e.printStackTrace();
		}

	}
}
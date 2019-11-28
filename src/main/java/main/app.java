package main;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class app {
    public static void main(String[] args) throws IOException {
    	Scanner in = new Scanner(System.in);
    	System.out.println("검색어를 입력세요.");
    	String value = in.nextLine();
        StringBuilder urlBuilder = new StringBuilder("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=pqFRCIwlvJWTAC5aXpVAGFANVuVscfsHwVOlV9f2eqSCCMEd1NL3gEp34l39FYSRxn0qEB8Iev5nI2xg3q1dVg%3D%3D"); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("searchSe","UTF-8") + "=" + URLEncoder.encode("dong", "UTF-8")); /*dong : 동(읍/면)명 road :도로명[default] post : 우편번호 */
        urlBuilder.append("&" + URLEncoder.encode("srchwrd","UTF-8") + "=" + URLEncoder.encode(value, "UTF-8")); /*검색어*/
        urlBuilder.append("&" + URLEncoder.encode("countPerPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 출력될 개수를 지정*/
        urlBuilder.append("&" + URLEncoder.encode("currentPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*출력될 페이지 번호*/
        
        
        try {
			Document doc = Jsoup.connect(urlBuilder.toString()).get();
			Elements el =doc.getAllElements();

			
//			Element el = doc.selectFirst("newAddressListAreaCd > InmAdres");
//			System.out.println(el.html());
		} catch (Exception e) {
			// TODO: handle exception
		}  

    }
}
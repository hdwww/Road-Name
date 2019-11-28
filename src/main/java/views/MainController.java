package views;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import util.Util;

public class MainController {
		@FXML
		private TextField dongTxt;
		@FXML
		private TextField numberTxt;
		@FXML
		private Label label;
		@FXML
		private Label label2;
		@FXML
		private Label label3;
		
		private String newZuso = "";
		private String oldZuso = "";
		private ArrayList<String> zipCode = new ArrayList<String>();
		
		@FXML
		private void initialize() {
			label.setText("");
			label2.setText("");
			
		}
		
		public void zuso() throws IOException {
			String dong = dongTxt.getText();
			String number = numberTxt.getText();
			
			if(dong.isEmpty() || number.isEmpty()) {
				Util.showAlert("에러", "모두 입력해주세요.", AlertType.INFORMATION);
				return;
			}
			String value = dong + " " + number;
	        StringBuilder urlBuilder = new StringBuilder("http://openapi.epost.go.kr/postal/retrieveNewAdressAreaCdService/retrieveNewAdressAreaCdService/getNewAddressListAreaCd"); /*URL*/
	        urlBuilder.append("?" + URLEncoder.encode("ServiceKey","UTF-8") + "=pqFRCIwlvJWTAC5aXpVAGFANVuVscfsHwVOlV9f2eqSCCMEd1NL3gEp34l39FYSRxn0qEB8Iev5nI2xg3q1dVg%3D%3D"); /*Service Key*/
	        urlBuilder.append("&" + URLEncoder.encode("searchSe","UTF-8") + "=" + URLEncoder.encode("dong", "UTF-8")); /*dong : 동(읍/면)명 road :도로명[default] post : 우편번호 */
	        urlBuilder.append("&" + URLEncoder.encode("srchwrd","UTF-8") + "=" + URLEncoder.encode(value, "UTF-8")); /*검색어*/
	        urlBuilder.append("&" + URLEncoder.encode("countPerPage","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*페이지당 출력될 개수를 지정*/
	        urlBuilder.append("&" + URLEncoder.encode("currentPage","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*출력될 페이지 번호*/
	        
	        
	        try {
				Document doc = Jsoup.connect(urlBuilder.toString()).get();
				Elements el =doc.getAllElements();
				zipCode = (ArrayList<String>) el.select("zipNo").eachText();
				
				newZuso = el.select("lnmAdres").text();
				oldZuso = el.select("rnAdres").text();
			} catch (Exception e) {
				Util.showAlert("에러", "검색중 오류발생", AlertType.ERROR);
			} 
	        if(newZuso.length() > 16) {
	  //      	newZuso = newZuso.replace(newZuso.charAt(newZuso.charAt(newZuso.indexOf(15))), "\n ");
	        }
	        
	        System.out.println(zipCode);
	        label.setText(newZuso);
	        label2.setText(oldZuso);
	        label3.setText(zipCode.get(0));
			
		}
		
}

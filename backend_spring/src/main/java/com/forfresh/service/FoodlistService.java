package com.forfresh.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.forfresh.model.dto.refrig.Foodlist;

@Service
public class FoodlistService {
	
	 //static String root = "http://localhost:8081/Receipt";
	 static String root = "http://k3a407.p.ssafy.io:8082/Receipt";
	
    public JSONArray getItemInfo(String foods) throws IOException {
        String addUrl = "/getCategoryInfo/";
        String finalUrl = root + addUrl;
        URL url = new URL(finalUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
        OutputStream os = conn.getOutputStream();
        // ObjectOutputStream oss = new ObjectOutputStream(os);
        // Request Body에 Data 셋팅.
        // oss.writeObject(foods);
        os.write(foods.getBytes()); 
        // Request Body에 Data 입력.
        os.flush();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        String result = "";
        while ((returnLine = br.readLine()) != null) {
            result+=returnLine;
        }
        conn.disconnect();
        JSONObject jsonObject = new JSONObject(result);
        JSONArray categoryArray = (JSONArray) jsonObject.get("data");
        return categoryArray;
    }

    public String getReceiptInfo(String receiptUrl) throws IOException {
        String addUrl = "/getReceiptInfo/";
        String finalUrl = root + addUrl;
        URL url = new URL(finalUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        
        // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
        OutputStream os = conn.getOutputStream();
        // Request Body에 Data 셋팅.
        os.write(receiptUrl.getBytes());        
        // Request Body에 Data 입력.
        os.flush();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();
        
        return result.toString();
    }
}

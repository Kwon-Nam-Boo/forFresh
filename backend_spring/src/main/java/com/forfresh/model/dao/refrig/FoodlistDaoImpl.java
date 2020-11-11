package com.forfresh.model.dao.refrig;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.json.JSONParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.forfresh.model.dto.refrig.Foodlist;

@Repository
public class FoodlistDaoImpl implements FoodlistDao {

    // static String root = "http://localhost:8081/Receipt";
    static String root = "http://k3a407.p.ssafy.io:8082/Receipt";

    @Override
    public List<Foodlist> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Foodlist> findAll(Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Foodlist> findAllById(Iterable<String> ids) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> List<S> saveAll(Iterable<S> entities) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void flush() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Foodlist> S saveAndFlush(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteInBatch(Iterable<Foodlist> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub

    }

    @Override
    public Foodlist getOne(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> List<S> findAll(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> List<S> findAll(Example<S> example, Sort sort) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Page<Foodlist> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Foodlist> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean existsById(String id) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void deleteById(String id) {
        // TODO Auto-generated method stub

    }

    @Override
    public void delete(Foodlist entity) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll(Iterable<? extends Foodlist> entities) {
        // TODO Auto-generated method stub

    }

    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub

    }

    @Override
    public <S extends Foodlist> Optional<S> findOne(Example<S> example) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> Page<S> findAll(Example<S> example, Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public <S extends Foodlist> long count(Example<S> example) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public <S extends Foodlist> boolean exists(Example<S> example) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public List<FoodlistExpiration> findByRefrigNo(int refrigNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<Foodlist> findByRefrigNoAndFoodName(int refrigNo, String foodName) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteByFoodNo(int foodNo) {
        // TODO Auto-generated method stub

    }


    @Override
    public String getItemInfo(String foods) throws IOException {
        String addUrl = "/getCategoryInfo/";
        String finalUrl = root + addUrl;
        URL url = new URL(finalUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // conn.setRequestMethod("GET");
        
        // BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        // String returnLine;
        // StringBuffer result = new StringBuffer();
        // while ((returnLine = br.readLine()) != null) {
        //     result.append(returnLine + "\n");
        // }
        // conn.disconnect();

        // return result.toString();

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
        StringBuffer result = new StringBuffer();
        while ((returnLine = br.readLine()) != null) {
            result.append(returnLine + "\n");
        }
        conn.disconnect();

        return result.toString();
    }

    @Override
    public Optional<Foodlist> findByFoodNo(int foodNo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
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

	@Override
	public String getCategoryInfo(String list) throws IOException{
		String addUrl = "/getCategoryInfo/";
        String finalUrl = root + addUrl;
        URL url = new URL(finalUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Type", "application/json");
        
        // Request Body에 Data를 담기위해 OutputStream 객체를 생성.
        OutputStream os = conn.getOutputStream();
        // Request Body에 Data 셋팅.
        os.write(list.getBytes());        
        // Request Body에 Data 입력.
        os.flush();
        
        BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));

        String returnLine;
        //StringBuffer result = new StringBuffer();
        String result ="";
        while ((returnLine = br.readLine()) != null) {
        	  result+=returnLine;
//            result.append(returnLine + "\n");
        }
        conn.disconnect();
        
        JSONObject jsonObject = new JSONObject(result);
        JSONArray bookInfoArray = (JSONArray) jsonObject.get("data");
        
        System.out.println(bookInfoArray);
        return result.toString();
		
	}
    @Override
    public Optional<FoodlistExpiration> findByFoodNoDetail(int foodNo) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

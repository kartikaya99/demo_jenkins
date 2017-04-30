package com.kk.app.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kk.app.model.DataBean;
import com.kk.app.util.DataUtil;

@Service
public class ExcelService {

	
	@Autowired
	DataUtil dataUtil;
	
	public void excelService(HttpServletResponse response , String id) throws IOException{
		
		  System.out.println("excel service");
		  System.out.println(dataUtil.getIdMap().get(id)+"   "+dataUtil.getIdMap().size());
		  /*XSSFWorkbook workbook = new XSSFWorkbook(); 
		  XSSFSheet sheet = workbook.createSheet("mySheet");*/
		  File file = new File("E:\\abcd.xlsx");
		  ServletOutputStream os = response.getOutputStream();
		 /* workbook.write(os);*/
		  InputStream is = new FileInputStream(file);
		  IOUtils.copy(is, os);
		  os.flush();
		  dataUtil.getIdMap().remove(id);
		  System.out.println("excel service complete");
	}
	
	public List<String> saveData(DataBean dataBean){
		
		     try{
		    	 System.out.println("Excel Service save data "+dataBean);
		    	 String uuid = UUID.randomUUID().toString();
		    	 DataUtil.getIdMap().putIfAbsent(uuid, dataBean.getList());
		    	 List<String> resList = new ArrayList<String>();
		    	 resList.add(uuid);
		    	 return resList;
		     }catch(Exception ex){
		    	 ex.printStackTrace();
		    	 throw new RuntimeException();
		     }
		
	}
	
}

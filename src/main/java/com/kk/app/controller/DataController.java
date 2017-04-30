package com.kk.app.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kk.app.model.DataBean;
import com.kk.app.service.ExcelService;

@RestController
public class DataController {

	@Autowired
	ExcelService excelService ;
	
	@RequestMapping(value = "/getexcel/{id}", method ={RequestMethod.GET , RequestMethod.OPTIONS})
	public /*ResponseEntity<List<String>>*/ void testExcel(HttpServletResponse response , @PathVariable("id") String id){
		response.setHeader("Content-Disposition","attachment;filename=myfile.xlsx");
		response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet ");
		try {
			excelService.excelService(response,id);
		} catch (IOException e) {
			e.printStackTrace();
		}
		/*System.out.println("++++++++++++++++++++++==");
		HttpHeaders head = new HttpHeaders();
		head.add("Access-Control-Allow-Origin", "*");
		head.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
		List<String> list = new ArrayList<>();
		list.add("ok");
		return new ResponseEntity<List<String>>(list,head,HttpStatus.OK);*/
	}
	
	@RequestMapping(value = "/getfile", method = {RequestMethod.GET , RequestMethod.OPTIONS})
	public ResponseEntity<InputStreamResource> downloadPDFFile()
	        throws IOException {
	    ClassPathResource pdfFile = new ClassPathResource("abcd.xlsx");
	    HttpHeaders head = new HttpHeaders();
		head.add("Content-Type", "application/octet-stream");
		head.add("Content-Disposition", "attachment;filename=myfile.xlsx");
		return  new ResponseEntity<>(new InputStreamResource(pdfFile.getInputStream()),head,HttpStatus.OK);
/*	    ResponseEntity
	            .ok()
	            .contentLength(pdfFile.contentLength()).headers(head)
	            .contentType(
	                    MediaType.parseMediaType("application/octet-stream")
	            .body(new InputStreamResource(pdfFile.getInputStream()));*/
	}
	
	@RequestMapping(value = "/postdata", method = {RequestMethod.GET , RequestMethod.POST})
	public ResponseEntity<List<String>> postData(@RequestBody DataBean dataBean){
		
		System.out.println("postData "+dataBean);
		try{
			return ResponseEntity.ok().body(excelService.saveData(dataBean));
		}catch(Exception ex){
			ex.printStackTrace();
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	

	@RequestMapping(value = "/test", method = {RequestMethod.GET})
	public ResponseEntity<List<String>> getData(){
		List<String> list = new ArrayList<>();
		list.add("abcde");
		return ResponseEntity.ok().body(list);
	}
	
}

package com.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.main.helper.FileUploadHelper;

@RestController
public class FileUploadController {
	
	@Autowired
	private FileUploadHelper fileUploadHelper;
	
	@PostMapping("/upload-file")
	public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file)
	{
		//checks size 
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.getContentType());
		System.out.println(file.getName());
		//---------------------
		try {
		
		//validation
		if(file.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Request must contain file");
		}
		
		if(!file.getContentType().equals("image/jpeg"))
		{
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("only jpeg file allowed");
		}
		
		
		//now file upload code..
		
		boolean f = fileUploadHelper.uploadFile(file);
		if(f)
		{
			//this is for static
			//return ResponseEntity.ok("File is successfully uploaded");
			
			//this is for dynamic
			return ResponseEntity.ok(ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/image/").path(file.getOriginalFilename()).toUriString());
		}
		
		} catch (Exception e) {
			e.printStackTrace(); 
		}
		
		return ResponseEntity.ok("working");
	}

}

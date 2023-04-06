package com.main.helper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileUploadHelper {
	
	//dynamic way
//public final String UPLOAD_DIR="C:\\Users\\Hp\\Desktop\\Spring_Boot_workspace\\FileUploading\\src\\main\\resources\\static\\Image"; 

	// for dynamic (dynamic we can use in ny system)
	public final String UPLOAD_DIR= new ClassPathResource("static/image").getFile().getAbsolutePath();
	
	public FileUploadHelper() throws IOException {
		 
	}

	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean f=false;
		
		try {
			
			InputStream is = multipartFile.getInputStream();
			byte data[]= new byte[is.available()];
			is.read(data);
			
			//write
			FileOutputStream fos=new FileOutputStream(UPLOAD_DIR+"\\"+multipartFile.getOriginalFilename());
			fos.write(data);
			
			fos.flush();
			fos.close();
			f=true;
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}
}

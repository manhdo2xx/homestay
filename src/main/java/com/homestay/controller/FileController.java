package com.homestay.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.homestay.annotation.IgnoreAuth;
import com.homestay.entity.ConfigEntity;
import com.homestay.entity.EIException;
import com.homestay.service.ConfigService;
import com.homestay.utils.R;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.util.Date;

@RestController
@RequestMapping("file")
public class FileController {
	@Autowired
    private ConfigService configService;

	@RequestMapping("/upload")
    @IgnoreAuth
	public R upload(@RequestParam("file") MultipartFile file,String type) throws Exception {
		if (file.isEmpty()) {
			throw new EIException("Upload file cannot be empty");
		}
		String fileExt = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")+1);
		String decode = URLDecoder.decode(ResourceUtils.getURL("classpath:static").getPath(), "utf-8");
		String realPath = System.getProperty("user.dir")+"/src/main/resources/static/file";
		File path = new File(realPath);
		if(!path.exists()) {
		    path = new File("");
		}
		File upload = new File(path.getAbsolutePath(),"/");
		if(!upload.exists()) {
		    upload.mkdirs();
		}
		String fileName = new Date().getTime()+"."+fileExt;
        if(StringUtils.isNotBlank(type) && type.contains("_template")) {
            fileName = type + "."+fileExt;
            new File(upload.getAbsolutePath()+"/"+fileName).deleteOnExit();
        }
		File dest = new File(upload.getAbsolutePath()+"/"+fileName);
		file.transferTo(dest);
		if(StringUtils.isNotBlank(type) && type.equals("1")) {
			ConfigEntity configEntity = configService.selectOne(new EntityWrapper<ConfigEntity>().eq("name", "faceFile"));
			if(configEntity==null) {
				configEntity = new ConfigEntity();
				configEntity.setName("faceFile");
				configEntity.setValue(fileName);
			} else {
				configEntity.setValue(fileName);
			}
			configService.insertOrUpdate(configEntity);
		}
		return R.ok().put("file", fileName);
	}

	@IgnoreAuth
	@RequestMapping("/download")
	public ResponseEntity<byte[]> download(@RequestParam String fileName) {
		try {
			File path = new File(ResourceUtils.getURL("classpath:static").getPath());
			if(!path.exists()) {
			    path = new File("");
			}
			File upload = new File(path.getAbsolutePath(),"/file/");
			if(!upload.exists()) {
			    upload.mkdirs();
			}
			File file = new File(upload.getAbsolutePath()+"/"+fileName);
			if(file.exists()){
				HttpHeaders headers = new HttpHeaders();
			    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			    headers.setContentDispositionFormData("attachment", fileName);
			    return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file),headers, HttpStatus.CREATED);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<byte[]>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

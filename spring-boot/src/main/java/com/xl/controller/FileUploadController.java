package com.xl.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

/**
  * TODO 文件上传路由
  * 
  * @author xl
  * @date 2017年3月3日 下午8:36:32
  */
@Controller
@RequestMapping("/upload")
public class FileUploadController {
	
	
	/**
	 * TODO 单文件上传控制器
	 * 
	 * @param file
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/upload", method = RequestMethod.POST )
	public String upload( @RequestParam("file") MultipartFile file ) throws IOException {// 文件上传
		
		BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(file.getOriginalFilename())));
		System.out.println("文件名称：" + file.getOriginalFilename());
		outputStream.write(file.getBytes());
		outputStream.flush();
		outputStream.close();
		return "文件上传成功";
	}
	
	/**
	 * TODO 多文件上传控制器
	 * 
	 * @param servletRequest
	 * @return
	 */
	@RequestMapping(value = "multifileupload", method = RequestMethod.POST)
	public @ResponseBody String upload(HttpServletRequest servletRequest) {
		
		List<MultipartFile> files = ((MultipartHttpServletRequest) servletRequest).getFiles("file");
		for (MultipartFile multipartFile : files) {
			
			try {
				BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(new File(multipartFile.getOriginalFilename())));
				outputStream.write(multipartFile.getBytes());
				outputStream.flush();
				outputStream.close();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "文件上传失败";
			} catch (IOException e) {
				e.printStackTrace();
				return "文件上传失败";
			}
		}
		return "文件上传成功";
	}
}

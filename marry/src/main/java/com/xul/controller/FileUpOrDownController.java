package com.xul.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.xul.util.FileOperateUtil;


@SuppressWarnings("unused")
@Controller
@RequestMapping("/file")
public class FileUpOrDownController {

	@RequestMapping(value = "/upload")
	public String upload(HttpServletRequest request, HttpServletResponse response) throws IOException {
		init(request);
		try {
			FileOperateUtil.upload(request);
			request.setAttribute("msg", "ok");
			request.setAttribute("map", getMap());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return  "redirect:list";
	}

	@RequestMapping(value = "/toUpload")
	public String toUpload() {
		
		return "upload";
	}
	
	@RequestMapping(value="/list")
    public ModelAndView list(HttpServletRequest request){       
        init(request);
        request.setAttribute("map", getMap());
        return new ModelAndView("/list");
    }
	
	@RequestMapping("/download")
	@ResponseBody
	public String download(HttpServletRequest request, HttpServletResponse response) {
		init(request);
		try {
			String downloadfFileName = request.getParameter("filename");
			downloadfFileName = new String(downloadfFileName.getBytes("iso-8859-1"), "utf-8");
			String fileName = downloadfFileName.substring(downloadfFileName.indexOf("_") + 1);
			String userAgent = request.getHeader("User-Agent");
			byte[] bytes = userAgent.contains("MSIE") ? fileName.getBytes() : fileName.getBytes("UTF-8");
			fileName = new String(bytes, "ISO-8859-1");
			response.setHeader("Content-disposition", String.format("attachment; filename=\"%s\"", fileName));
			FileOperateUtil.download(downloadfFileName, response.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "Error uploading failed system";
	}

	private void init(HttpServletRequest request) {
		if (FileOperateUtil.FILEDIR == null) {
			FileOperateUtil.FILEDIR = request.getSession().getServletContext().getRealPath("/") + "file/";
		}
	}

	private Map<String, String> getMap() {
		Map<String, String> map = new HashMap<String, String>();
		File[] files = new File(FileOperateUtil.FILEDIR).listFiles();
		if (files != null) {
			for (File file : files) {
				if (file.isDirectory()) {
					File[] files2 = file.listFiles();
					if (files2 != null) {
						for (File file2 : files2) {
							String name = file2.getName();
							map.put(file2.getParentFile().getName() + "/" + name,name.substring(name.lastIndexOf("_") + 1));
						}
					}
				}
			}
		}
		return map;
	}
}

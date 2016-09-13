package com.itlijunjie.pt.controller;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/upload")
public class UploadController {
	@RequestMapping("/index")
	public String index(HttpServletRequest request) {
		return "upload/index";
	}

	@RequestMapping("/add")
	public String upload(
			@RequestParam(value = "file", required = false) MultipartFile file,
			HttpServletRequest request, ModelMap model) {

		System.out.println("开始");
		String path = request.getSession().getServletContext().getRealPath("/")
				+ "upload/";
		String fileName = new Date().getTime() + file.getOriginalFilename();
		System.out.println("path======" + path + fileName);
		File targetFile = new File(path, fileName);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}

		// 保存
		try {
			file.transferTo(targetFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("fileUrl", request.getContextPath() + "/upload/"
				+ fileName);

		return "upload/result";
	}
}

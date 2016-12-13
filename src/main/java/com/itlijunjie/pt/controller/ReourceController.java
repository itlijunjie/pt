package com.itlijunjie.pt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Date;

/**
 * Created by ljj on 13/12/2016.
 * 资源管理控制器
 */
@Controller
@RequestMapping("/reource")
public class ReourceController {

    /**
     * 查看所有资源
     * @param request 资源链接
     * @return 返回查看资源界面
     */
    @RequestMapping("/reources")
    public String index(HttpServletRequest request) {
        return "reource/list";
    }

    @RequestMapping("/add")
    public String add(HttpServletRequest request) {
        return "reource/add";
    }

    @RequestMapping("/upload")
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

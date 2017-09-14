package com.itlijunjie.pt.controller;

import com.google.flatbuffers.FlatBufferBuilder;
import com.itlijunjie.pt.fbs.FlatBuffersResult;
import com.itlijunjie.pt.services.IProductInterfaceService;
import com.itlijunjie.pt.vo.ProductInterface;
import com.itlijunjie.pt.vo.exception.ProductInterfaceException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Map;

@Controller
@RequestMapping("/call")
public class FlatbuffersConreoller {
    private IProductInterfaceService productInterfaceService;

    public IProductInterfaceService getProductInterfaceService() {
        return productInterfaceService;
    }

    @Resource
    public void setProductInterfaceService(
            IProductInterfaceService productInterfaceService) {
        this.productInterfaceService = productInterfaceService;
    }

    @RequestMapping(value = "/flatbuffers/{productname}/{parameter}")
    public void getProtobuf(@PathVariable String productname, @PathVariable String parameter, HttpServletResponse response, HttpServletRequest request) {
        String url = request.getRequestURI();
        try {
            this.charReader(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] l = url.split("/");
        parameter = l[l.length - 1];
        Map<String, String[]> params = request.getParameterMap();
        for (int i = 0; i < params.size(); i++) {
            if (i == 0) {
                parameter = parameter + "?" + params.keySet().toArray()[i].toString() + "=" + request.getParameter(params.keySet().toArray()[i].toString());
            } else {
                parameter = parameter + "&" + params.keySet().toArray()[i].toString() + "=" + request.getParameter(params.keySet().toArray()[i].toString());
            }
        }
        ProductInterface p = productInterfaceService.getProductInterface(productname, parameter);
        response.setContentType("application/octet-stream");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setCharacterEncoding("utf-8");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        byte[] byteArray = create(p);
        ByteBuffer bb = ByteBuffer.wrap(byteArray);
        FlatBuffersResult fbr = FlatBuffersResult.getRootAsFlatBuffersResult(bb);

        System.out.println(fbr.res());
        try {
            out.write(byteArray);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private byte[] create(ProductInterface p) {
        FlatBufferBuilder builder = new FlatBufferBuilder(0);
        int res = builder.createString(p.getIfresule());
        int john = FlatBuffersResult.createFlatBuffersResult(builder, res);
//        builder.finish(john);
        FlatBuffersResult.finishFlatBuffersResultBuffer(builder, john);
        ByteBuffer bb = builder.dataBuffer();
        byte[] dataByte = Arrays.copyOfRange(bb.array(), bb.position(), (bb.position() + builder.offset()));
        return dataByte;
    }

    void charReader(HttpServletRequest request) throws IOException {
        BufferedReader br = request.getReader();
        String str, wholeStr = "";
        while ((str = br.readLine()) != null) {
            wholeStr += str;
        }
        System.out.println(wholeStr);
    }

    @ExceptionHandler(ProductInterfaceException.class)
    public void handlerException(Exception ex, HttpServletResponse response) {
        response.setContentType("text/plain");
        response.setHeader("pragma", "no-cache");
        response.setHeader("cache-control", "no-cache");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }
        out.print(ex.getMessage());
    }
}
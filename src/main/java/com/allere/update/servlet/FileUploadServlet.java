package com.allere.update.servlet;

/**
 * Created by G_dragon on 2015/12/30.
 */

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.allere.update.service.IUpdateService;
import com.allere.update.utils.ValidateUtils;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hashing;
import com.google.common.io.Files;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.FileUploadBase.FileSizeLimitExceededException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet
public class FileUploadServlet extends HttpServlet {

    private static final long serialVersionUID = 2827297299439162553L;

    private IUpdateService updateService;

    private void initialize(){
        if(null != updateService){
            return;
        }

        ServletContext servletContext = this.getServletContext();
        WebApplicationContext context =
                WebApplicationContextUtils.getWebApplicationContext(servletContext);
        updateService = (IUpdateService) context.getBean("updateJPAService");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //使用同一种处理方法
//        doPost(request, response);

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        initialize();

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        //保存路径
        String savePath = getServletContext().getRealPath("/upload");
        File saveDir = new File(savePath);
        // 如果目录不存在，就创建目录
        if(!saveDir.exists()){
            saveDir.mkdir();
        }

        // 创建文件上传核心类
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        //设置编码
        sfu.setHeaderEncoding("UTF-8");
        // 设置上传的单个文件的最大字节数为2M
        sfu.setFileSizeMax(1024 * 1024 * 100);
        //设置整个表单的最大字节数为10M
        sfu.setSizeMax(1024 * 1024 * 100);

        try{
            // 处理表单请求
            List<FileItem> itemList = sfu.parseRequest(request);

            String description = "";
            String sysVersion = "";
            String sysType = "";
            long size = 0;
            String url = "";
            String fileName="";
            long checkSum = 0;

            for (FileItem fileItem : itemList) {
                // 对应表单中的控件的name
                String fieldName = fileItem.getFieldName();
                System.out.println("控件名称：" + fieldName);
                // 如果是普通表单控件
                if(fileItem.isFormField()){
                    String value = fileItem.getString();
                    //重新编码,解决乱码
                    value = new String(value.getBytes("ISO-8859-1"),"UTF-8");

                    if(fieldName.equalsIgnoreCase("sys_type")) {
                        sysType = value;
                    }else if(fieldName.equalsIgnoreCase("sys_version")){
                        sysVersion = value;
                    }else if(fieldName.equalsIgnoreCase("description")){
                        description = value;
                    }
//                    System.out.println("普通内容：" + fieldName + "=" + value);
                    // 上传文件
                }else{

                    size = fileItem.getSize();  // 获得文件大小

                    fileName = fileItem.getName(); // 获得文件名

                    //设置不允许上传的文件格式
                    if(fileName.endsWith(".exe")){
                        request.setAttribute("msg", "不允许上传的类型！");
                    }else{
                        //将文件保存到指定的路径
                        File file = new File(savePath,fileName);
                        fileItem.write(file);
                        request.setAttribute("msg", "上传成功！");

                        HashCode hashCode = Files.hash(file, Hashing.adler32());

                        checkSum = hashCode.padToLong();

                        url = "workspace://"+ UUID.randomUUID().toString();
                    }
                }
            }

            updateService.saveToSpace(sysType, sysVersion, fileName, url, size, checkSum, description);

        }catch(FileSizeLimitExceededException e){
            request.setAttribute("msg", "文件太大");
        }catch(FileUploadException e){
            e.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }



        //上传完毕后  转发到首页
        request.getRequestDispatcher("/index").forward(request, response);
    }

}

package com.lx.reume.util;

import com.lx.reume.bean.UploadParams;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Uploads {
    private static final String BASE_DIR = "upload";
    private static final String IMG_DIR = "img";

    /**
     * 图片上传
     * @param item 文件参数
     * @param request 请求
     * @param oldImage 以前的图片路径
     * @return 数据库的图片路径
     * @throws Exception
     */

    public static String uploadImage(FileItem item, HttpServletRequest request, String oldImage) throws Exception {
        if (oldImage != null && oldImage.length() == 0){
            oldImage = null;
        }

        if (item == null) return oldImage;
        InputStream is = item.getInputStream();
        if (is.available() == 0) return oldImage;
        ServletContext ctx = request.getServletContext();
        String filename = UUID.randomUUID() + "." + FilenameUtils.getExtension(item.getName());
        String image = BASE_DIR + "/" + IMG_DIR + "/" + filename;
        String filepath = ctx.getRealPath(image);
        FileUtils.copyInputStreamToFile(item.getInputStream(), new File(filepath));
        if (oldImage != null){
            FileUtils.deleteQuietly(new File(ctx.getRealPath(oldImage)));
        }
        return image;
    }


    public static UploadParams parseRequest(HttpServletRequest request) throws Exception{
        ServletFileUpload upload = new ServletFileUpload(new DiskFileItemFactory());
        upload.setHeaderEncoding("UTF-8");
        List<FileItem> items = upload.parseRequest(request);
        Map<String,Object> params = new HashMap<>();
        Map<String, FileItem> fileParams = new HashMap<>();

        for (FileItem item : items) {
            String filedname = item.getFieldName();
            if (item.isFormField()){
                params.put(filedname,item.getString("UTF-8"));

            }else {
                fileParams.put(filedname,item);
            }
        }

        UploadParams uploadParams = new UploadParams();
        uploadParams.setParams(params);
        uploadParams.setFileParams(fileParams);
        return uploadParams;

    }



}

package com.lx.reume.servlet;

import com.lx.reume.bean.Award;
import com.lx.reume.bean.Company;
import com.lx.reume.bean.UploadParams;
import com.lx.reume.util.Uploads;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/award/*")
public class AwardServlet extends BaseServlet<Award> {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("awards",service.list());
        forward(request,response,"admin/award.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        UploadParams uploadParams = Uploads.parseRequest(request);
        Award award = new Award();
        BeanUtils.populate(award,uploadParams.getParams());
        FileItem item = uploadParams.getFileParam("imageFile");
        award.setImage(Uploads.uploadImage(item,request,award.getImage()));
        if (service.save(award)){
            redirect(request, response, "award/admin");
        }else {
            forwardError(request, response, "获奖成就保存失败");
        }

    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        if (service.remove(ids)){
            redirect(request,response,"award/admin");
        }else {
            forwardError(request,response,"获奖成就删除失败");
        }
    }
}
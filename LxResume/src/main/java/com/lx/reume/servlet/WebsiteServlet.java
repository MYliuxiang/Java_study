package com.lx.reume.servlet;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.lx.reume.bean.User;
import com.lx.reume.bean.Website;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@WebServlet("/website/*")
public class WebsiteServlet extends BaseServlet<Website> {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {

        List<Website> websites = service.list();
        Website website = (websites != null && !websites.isEmpty()) ? websites.get(0) : null;
        request.setAttribute("website", website);
        forward(request,response, "admin/website.jsp");

    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

        Website website = new Website();
        BeanUtils.populate(website,request.getParameterMap());
        if (service.save(website)) {
            redirect(request, response, "website/admin");
        } else {
            forwardError(request, response, "网站信息保存失败");
        }
    }


}
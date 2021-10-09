package com.lx.reume.servlet;

import com.lx.reume.service.BaseService;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import sun.util.calendar.BaseCalendar;
import sun.util.calendar.LocalGregorianCalendar;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Date;

public abstract class BaseServlet<T> extends HttpServlet {
    static {
        DateConverter dateConverter = new DateConverter(null);
        dateConverter.setPatterns(new String[]{"yyyy-MM-dd"});
        ConvertUtils.register(dateConverter, Date.class);
    }

    protected BaseService<T> service = newService();
    private Object ServletException;
    private Object IOException;

    protected BaseService<T> newService() {
        // com.mj.xr.servlet.WebsiteServlet
        // com.mj.xr.service.impl.WebsiteServiceImpl
        try {
            String clsName = getClass().getName()
                    .replace(".servlet.", ".service.impl.")
                    .replace("Servlet", "ServiceImpl");
            return (BaseService<T>) Class.forName(clsName).newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            req.setCharacterEncoding("UTF-8");
            String uri = req.getRequestURI();
            String[] cmps = uri.split("/");
            String methodName = cmps[cmps.length - 1];
            Method method = getClass().getMethod(methodName,
                    HttpServletRequest.class,
                    HttpServletResponse.class);
            method.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            Throwable cause = e;
            while (cause.getCause() != null) {
                cause = cause.getCause();
            }
            forwardError(req, resp, cause.getClass().getSimpleName() + ":" + cause.getMessage());
        }
    }

    protected void redirect(HttpServletRequest request, HttpServletResponse response, String path) throws IOException {
        response.sendRedirect(request.getContextPath() + "/" + path);
    }

    protected void forward(HttpServletRequest request, HttpServletResponse response, String path) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/page/" + path).forward(request, response);
    }

    protected void forwardError(HttpServletRequest request, HttpServletResponse response, String error) throws ServletException, IOException {

        request.setAttribute("error", error);
        forward(request, response, "error.jsp");
    }


}

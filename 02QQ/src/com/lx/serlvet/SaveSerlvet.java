package com.lx.serlvet;

import com.lx.Data;
import com.lx.bean.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/save")
public class SaveSerlvet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doGet(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String age = req.getParameter("age");
        String height = req.getParameter("height");
        
        //转化Java Bean对象
        Customer customer = new Customer();
        customer.setAge(Integer.valueOf(age));
        customer.setName(name);
        customer.setHeight(Double.valueOf(height));
        Data.add(customer);
        resp.sendRedirect("/crm/list");
    }
}

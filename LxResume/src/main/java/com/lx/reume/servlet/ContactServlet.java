package com.lx.reume.servlet;

import com.lx.reume.bean.Contact;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/contact/*")
public class ContactServlet extends BaseServlet<Contact> {
    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {

    }
}
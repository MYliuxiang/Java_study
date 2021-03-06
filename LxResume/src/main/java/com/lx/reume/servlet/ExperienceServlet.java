package com.lx.reume.servlet;

import com.lx.reume.bean.Company;
import com.lx.reume.bean.Experience;
import com.lx.reume.service.CompanyService;
import com.lx.reume.service.impl.CompanyServiceImpl;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/experience/*")
public class ExperienceServlet extends BaseServlet<Experience> {

    private CompanyService companyService = new CompanyServiceImpl();

    public void admin(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setAttribute("experiences", service.list());
        request.setAttribute("companies", companyService.list());
        forward(request, response, "admin/experience.jsp");
    }

    public void save(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Experience experience = new Experience();
        BeanUtils.populate(experience, request.getParameterMap());

        // 对公司信息作特殊处理
        Company company = new Company();
        company.setId(Integer.valueOf(request.getParameter("companyId")));
        experience.setCompany(company);

        if (service.save(experience)) { // 保存成功
            redirect(request, response, "experience/admin");
        } else {
            forwardError(request, response, "工作经验保存失败");
        }
    }

    public void remove(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String[] idStrs = request.getParameterValues("id");
        List<Integer> ids = new ArrayList<>();
        for (String idStr : idStrs) {
            ids.add(Integer.valueOf(idStr));
        }
        // 删除
        if (service.remove(ids)) {
            redirect(request, response, "experience/admin");
        } else {
            forwardError(request, response, "工作经验删除失败");
        }
    }
}
package com.codecool.labourent.controllers;

import com.codecool.labourent.config.TemplateEngineUtil;
import com.codecool.labourent.dbConnection.ServiceQueries;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/list"})
public class ListPageController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine engine = TemplateEngineUtil.getTemplateEngine(req.getServletContext());
        WebContext context = new WebContext(req, resp, req.getServletContext());
        origanizeTable(req, context);
        /*filterTable(req, context);*/
        engine.process("listPage.html", context, resp.getWriter());
    }

    private void origanizeTable(HttpServletRequest req, WebContext context) {
        String queryString = req.getQueryString();
        if (queryString != null){
            String columnName = req.getParameter("column");
            String sortDirection = req.getParameter("sort");
            String categoryId = req.getParameter("categoryId");
            switchSortDirection(sortDirection, context);
            context.setVariable("categoryId", categoryId);
            filterTable(req, context, columnName, sortDirection, categoryId);

        } else {
            context.setVariable("services", ServiceQueries.getAllRecordsFromTable("id", "asc"));
            context.setVariable("sortDirection", "asc");
            context.setVariable("categoryId", "all");
        }
    }

    private void switchSortDirection(String direction, WebContext context) {

        if (direction.equals("asc")){
            context.setVariable("sortDirection", "desc");
        } else {
            context.setVariable("sortDirection", "asc");
        }
    }

    private void filterTable(HttpServletRequest req, WebContext context, String columnName, String sortDirection , String ascOrDesc) {
        if (!req.getParameter("categoryId").equals("all")) {
            int categoryId = Integer.parseInt(req.getParameter("categoryId"));
            context.setVariable("services", ServiceQueries.getFilteredRecordsFromTable(columnName, ascOrDesc, categoryId));
        }
        else {
            context.setVariable("services", ServiceQueries.getAllRecordsFromTable(columnName, sortDirection));
        }
    }


}

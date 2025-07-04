package com.example.taskmanager.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.taskmanager.dao.TaskDao;

@WebServlet("/task-add")
public class TaskAddServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String deadline = request.getParameter("deadline");

        TaskDao dao = new TaskDao();
        dao.insertTask(title, deadline);

        response.sendRedirect(request.getContextPath()+"/task-list");
    }
}

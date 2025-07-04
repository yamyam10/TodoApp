package com.example.taskmanager.servlet;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.taskmanager.dao.TaskDao;

@WebServlet("/task-update")
public class TaskUpdateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String done = request.getParameter("done");

        if (done == null) done = "0";

        TaskDao dao = new TaskDao();
        dao.updateTaskDone(id, done);

        response.sendRedirect("task-list");
    }
}

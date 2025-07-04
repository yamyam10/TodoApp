package com.example.taskmanager.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.taskmanager.bean.Task;
import com.example.taskmanager.dao.TaskDao;

@WebServlet("/task-list")
public class TaskListServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filter = request.getParameter("filter");
        if (filter == null) filter = "all";

        String sort = request.getParameter("sort");
        if (sort == null) sort = "asc";

        TaskDao dao = new TaskDao();
        List<Task> tasks = dao.getTasks(filter, sort);

        request.setAttribute("tasks", tasks);
        request.setAttribute("filter", filter);
        request.setAttribute("sort", sort);

        RequestDispatcher rd = request.getRequestDispatcher("/jsp/task_list.jsp");
        rd.forward(request, response);
    }
}

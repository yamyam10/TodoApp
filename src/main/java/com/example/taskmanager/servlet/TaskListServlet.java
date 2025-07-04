package com.example.taskmanager.servlet;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.example.taskmanager.bean.Task;
import com.example.taskmanager.dao.TaskDao;

@WebServlet("/task-list")
public class TaskListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String filter = request.getParameter("filter");
        String sort = request.getParameter("sort");

        if (filter == null) filter = "all";     // デフォルト
        if (sort == null) sort = "asc";         // デフォルト

        TaskDao dao = new TaskDao();
        List<Task> taskList = null;
		try {
			taskList = dao.findByFilterAndSort(filter, sort);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		request.setAttribute("tasks", taskList);
		request.setAttribute("filter", filter);
		request.setAttribute("sort", sort);

        request.getRequestDispatcher("/jsp/task_list.jsp").forward(request, response);
    }
}

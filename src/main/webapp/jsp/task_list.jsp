<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.time.LocalDate" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    // 今日の日付を取得（yyyy-MM-dd 形式）
    String today = LocalDate.now().format(DateTimeFormatter.ISO_DATE);
    request.setAttribute("today", today);
%>

<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧</title>
<style>
    .overdue {
        color: red;
    }
    .done {
        text-decoration: line-through;
        color: gray;
    }
</style>
</head>
<body>

<h1>タスク一覧</h1>

<a href="${pageContext.request.contextPath}/jsp/task_add.jsp">＋タスク追加</a>

<form method="get" action="${pageContext.request.contextPath}/task-list">
    表示：
    <select name="filter">
        <option value="all" ${filter == 'all' ? 'selected' : ''}>すべて</option>
        <option value="done" ${filter == 'done' ? 'selected' : ''}>完了済</option>
        <option value="undone" ${filter == 'undone' ? 'selected' : ''}>未完了</option>
        <option value="today" ${filter == 'today' ? 'selected' : ''}>今日のやること</option>
    </select>

    ソート：
    <select name="sort">
        <option value="asc" ${sort == 'asc' ? 'selected' : ''}>締切昇順</option>
        <option value="desc" ${sort == 'desc' ? 'selected' : ''}>締切降順</option>
    </select>

    <input type="submit" value="適用">
</form>

<hr>

<c:choose>
  <c:when test="${not empty tasks}">
    <c:set var="hasTodayTask" value="false" />
    <c:forEach var="task" items="${tasks}">
      <c:set var="isToday" value="${task.deadline == today}" />
      <c:if test="${isToday}">
        <c:set var="hasTodayTask" value="true" />
      </c:if>

      <form action="${pageContext.request.contextPath}/task-update" method="post" style="margin-bottom:8px;">
        <input type="hidden" name="id" value="${task.id}" />
        <c:set var="cssClass" value="" />
        <c:if test="${task.done == '1'}">
          <c:set var="cssClass" value="done" />
        </c:if>
        <c:if test="${task.deadline lt today && task.done != '1'}">
          <c:set var="cssClass" value="${cssClass} overdue" />
        </c:if>

        <input type="checkbox" name="done" value="1"
               ${task.done == '1' ? 'checked' : ''} onchange="this.form.submit()" />
        <span class="${cssClass}">${task.title}</span>
        （締切: ${task.deadline}）
      </form>
    </c:forEach>

    <script>
      <c:if test="${filter == 'today' && hasTodayTask == true}">
        alert("今日のやることがあります！");
      </c:if>
    </script>

  </c:when>
  <c:otherwise>
    <p>タスクがありません。</p>
  </c:otherwise>
</c:choose>

</body>
</html>


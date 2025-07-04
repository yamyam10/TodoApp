<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
<meta charset="UTF-8">
<title>タスク一覧</title>
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
    <c:forEach var="task" items="${tasks}">
      <form action="${pageContext.request.contextPath}/task-update" method="post" style="margin-bottom:8px;">
        <input type="hidden" name="id" value="${task.id}" />
        <input type="checkbox" name="done" value="1"
               ${task.done == '1' ? 'checked' : ''} onchange="this.form.submit()" />
        ${task.title}（締切: ${task.deadline}）
      </form>
    </c:forEach>
  </c:when>
  <c:otherwise>
    <p>タスクがありません。</p>
  </c:otherwise>
</c:choose>

</body>
</html>

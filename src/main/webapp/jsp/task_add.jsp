<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク追加</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/task_add.css">
</head>
<body>

<div class="container">
    <h1>タスク追加</h1>

    <form action="${pageContext.request.contextPath}/task-add" method="post" class="task-form">
        <label for="title">タイトル</label>
        <input type="text" id="title" name="title" required>

        <label for="deadline">締切日</label>
        <input type="date" id="deadline" name="deadline" required>

        <input type="submit" value="追加" class="btn-submit">
    </form>

    <a href="${pageContext.request.contextPath}/task-list" class="btn-back">← 戻る</a>
</div>

</body>
</html>

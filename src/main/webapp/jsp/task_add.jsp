<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>タスク追加</title>
</head>
<body>
<form action="<%= request.getContextPath() %>/task-add" method="post">
    タイトル: <input type="text" name="title" required><br>
    締切日: <input type="date" name="deadline" required><br>
    <input type="submit" value="追加">
</form>

<a href="task-list">← 戻る</a>
</body>
</html>
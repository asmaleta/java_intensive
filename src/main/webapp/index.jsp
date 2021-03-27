<%@ page import="java.util.List" %>
<%@ page import="models.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="views.ResultTableView" %><%--
  Created by IntelliJ IDEA.
  User: aleksandr
  Date: 24.03.2021
  Time: 16:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"%>



<html>
<form method="post">
    <input required type="text" name = "id" placeholder="id">
    <input required type="text" name="name" placeholder="name" >
    <input required type="text" name="surname" placeholder="surname" >
    <input required type="text" name="age"placeholder="age" >
    <input type="hidden" name="command" value="update">
    <input type="submit" value="Update">
</form>
<form method="post">
    <input required type="text" name="name" placeholder="name" >
    <input required type="text" name="surname" placeholder="surname" >
    <input required type="text" name="age"placeholder="age" >
    <input type="hidden" name="command" value="add">
    <input type="submit" value="Add">
</form>
<form method="post">
    <input required pe="text" name="id" placeholder="id">
    <input type="hidden" name="command" value="delete">
    <input type="submit" value="Delete">
</form>
<head>
    <title>Student list</title>
</head>
<body>
<%List<Student> students = (List<Student>)request.getSession().getAttribute("students");
    if (students == null) students = new ArrayList<>();
%>
<%=new ResultTableView().build(students)%>
<form method="post">
    <input type="hidden" name="command" value="show">
    <input type="submit" value="Update data">
</form>
<form method="post">
    <input type="hidden" name="command" value="sort">
    <input type="submit" value="Sort">
</form>
</body>
</html>
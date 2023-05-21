<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h1>User</h1>
<#if user?? >
    User info<br>
    UserName: ${user.username}<br>
    Id: ${user.id}<br>
    Role: ${user.role}<br>
<#else>
    <form action="/user/form" method="post">
        <div>Username:</div>
        <input type="text" name="username"/>
        <div>Password:</div>
        <input type="text" name="password"/>
        <div>Role:</div>
        <input type="text" name="role"/>
        <br/><br/>
        <input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/>
    </form>
</#if>
<script src="/js/main.js"></script>
</body>
</html>

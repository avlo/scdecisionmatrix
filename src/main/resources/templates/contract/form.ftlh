<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Spring Boot Form Submission</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h1>Spring Boot Form Submission</h1>
<#if contracts?? >
    <#list contracts as contract>
        <tr>
            <td>${contract.id}</td>
            <td>${contract.participant}</td>
            <td>${contract.text }</td>
        </tr>
    </#list>
    Your submitted data<br>
    Name: ${user.name}<br>
    Message: ${user.message}<br>
    <#list contracts as contract>
        <tr>
            <td>${contract.id}</td>
            <td>${contract.participant}</td>
            <td>${contract.text }</td>
        </tr>
    </#list>
<#else>
    <form action="/form" method="post">
        <div>Name:</div>
        <input type="text" name="name"/>
        <br/><br/>
        <div>Message:</div>
        <textarea rows="2" name="message"></textarea>
        <br/><br/>
        <input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/>
    </form>
</#if>
<script src="/js/main.js"></script>
</body>
</html>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Contracts</title>
    <link href="/css/main.css" rel="stylesheet">
</head>
<body>
<h1>Contracts</h1>
<#if contracts?has_content >
    Contract Owner<br>
    Name: ${contract.user.username}<br><br>
    Existing Contracts:<br>
    <#list contracts as contract>
        <tr>
            <td>${contract.id}</td>
            <td>${contract.text}</td>
        </tr>
    </#list>
<#else>
    <form action="/contract/form" method="post">
        hidden userId: ${userId}<br>
        <div>Contract Text:</div>
        <input type="text" name="text"/>
        <br/><br/>
        <input type="hidden" name="userId" id="userId" value="${userId}" />
        <input type="submit" value="Submit"/>
        <input type="reset" value="Reset"/>
    </form>
</#if>
<script src="/js/main.js"></script>
</body>
</html>

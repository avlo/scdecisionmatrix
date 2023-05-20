<div id="header">
    <h2>FreeMarker Spring MVC Hello World</h2>
</div>
<div id="content">
<#--    <fieldset>-->
<#--        <legend>Add Car</legend>-->
<#--        <form name="car" action="add" method="post">-->
<#--            Make : <input type="text" name="make" /><br/>-->
<#--            Model: <input type="text" name="model" /><br/>-->
<#--            <input type="submit" value="Save" />-->
<#--        </form>-->
<#--    </fieldset>-->
<#--    <br/>-->
    <table class="datatable">
        <tr>
            <th>Contract</th>
        </tr>
        <#list contracts as contract>
            <tr>
                <td>${contract.id}</td>
                <td>${contract.participant}</td>
                <td>${contract.text }</td>
            </tr>
        </#list>
    </table>
</div>
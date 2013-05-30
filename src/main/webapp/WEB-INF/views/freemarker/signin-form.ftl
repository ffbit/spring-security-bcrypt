<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message "signin.page.title"/><@spring.message "application.title.separator"/><@spring.message
        "application.title.name"/></title>
    <link rel="shortcut icon" href="<@spring.url '/resources/images/favicon.ico' />"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/lib/bootstrap/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/signin.css' />"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/lib/bootstrap/css/bootstrap-responsive.min.css' />"/>
</head>

<body>

<div class="container">

    <#if securityException?has_content>
        <div class="row">
            <div class="span6 offset3 alert alert-error signin-alert">
                <@spring.message "${securityException}"/>
            </div>
        </div>
    </#if>

    <form class="form-signin" action="<@spring.url '/security_check' />" method="post" accept-charset="UTF-8">
        <h2 class="form-signin-heading"><@spring.message "signin.page.header"/></h2>
        <input name="username" type="text" class="input-block-level"
               placeholder="<@spring.message 'signin.page.form.label.username'/>">
        <input name="password" type="password" class="input-block-level"
               placeholder="<@spring.message 'signin.page.form.label.password'/>">
        <label class="checkbox">
            <input type="checkbox" name="remember-me-parameter"><@spring.message "signin.page.form.label.rememberme"/>
        </label>
        <button class="btn btn-large btn-primary" type="submit"><@spring.message 'signin.page.form.submit'/></button>
    </form>

</div>
<!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="resources/lib/jquery/jquery-1.10.0.min.js"></script>
<script src="resources/lib/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>

<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><@spring.message "signup.page.title"/><@spring.message "application.title.separator"/><@spring.message
        "application.title.name"/></title>
    <link rel="shortcut icon" href="<@spring.url '/resources/images/favicon.ico' />"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/lib/bootstrap/css/bootstrap.min.css' />"/>
    <link rel="stylesheet" type="text/css" href="<@spring.url '/resources/styles/main.css' />"/>
    <link rel="stylesheet" type="text/css"
          href="<@spring.url '/resources/lib/bootstrap/css/bootstrap-responsive.min.css' />"/>
</head>

<body>

<div class="container">
    <#assign usernameErrors>
        <@spring.bind "signUpForm.username"/>
        <@spring.showErrors ' '/>
    </#assign>

    <#if errors?? && errors?size gt 0>
        <div class="span6 offset3 alert alert-error signup-alert">
            <ul>
                <#list errors as e>
                    <li><@spring.message "${e}"/></li>
                </#list>
            </ul>
        </div>
    </#if>

    <form class="form-signin" action="" method="post" accept-charset="UTF-8">
        <h2 class="form-signin-heading"><@spring.message "signup.page.header"/></h2>
        <input name="username" type="text" class="input-block-level error" value="${signUpForm.username!''}"
               placeholder="<@spring.message 'signup.page.form.label.username'/>">
        <input name="password" type="password" class="input-block-level"
               placeholder="<@spring.message 'signup.page.form.label.password'/>">
        <input name="passwordConfirmation" type="password" class="input-block-level"
               placeholder="<@spring.message 'signup.page.form.label.password.confirm'/>">
        <input class="btn btn-large btn-primary" type="submit" value="<@spring.message 'signup.page.form.submit'/>"/>
        <@spring.message 'signup.page.form.or'/> <a href="<@spring.url '/signin' />"><@spring.message
        'signup.page.form.signin'/></a>
    </form>

</div>
<!-- /container -->

<!-- Le javascript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<@spring.url '/resources/lib/jquery/jquery-1.10.0.min.js' />"></script>
<script src="<@spring.url '/resources/lib/bootstrap/js/bootstrap.min.js' />"></script>

</body>
</html>

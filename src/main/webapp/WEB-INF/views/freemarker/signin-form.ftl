<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title><@spring.message "signin.page.title"/><@spring.message "application.title.separator"/><@spring.message
        "application.title.name"/></title>
    <link rel="icon" href="/resources/images/favicon.ico"/>
    <link rel="stylesheet" type="text/css" href="/resources/styles/main.css"/>
    <script type="text/javascript" src="/resources/scripts/main.js"></script>
</head>
<body>
<h1><@spring.message "signin.page.header"/></h1>

<div>
    <#if securityException?has_content>
        <div class="error">
            <@spring.message "${securityException}"/>
        </div>
    </#if>
    <form action="/security_check" method="post" accept-charset="UTF-8">
        <div>
            <label for="username"><@spring.message "signin.page.form.label.username"/>
                <input name="username" id="username" type="text"/>
            </label>
        </div>
        <div>
            <label for="password"><@spring.message "signin.page.form.label.password"/>
                <input name="password" id="password" type="password"/>
            </label>
        </div>
        <div>
            <label for="remember-me"><@spring.message "signin.page.form.label.rememberme"/>
                <input name="remember-me-parameter" id="remember-me" type="checkbox"/>
            </label>
        </div>
        <div>
            <input type="submit" value="<@spring.message 'signin.page.form.submit'/>"/>
        </div>
    </form>
</div>
</body>
</html>

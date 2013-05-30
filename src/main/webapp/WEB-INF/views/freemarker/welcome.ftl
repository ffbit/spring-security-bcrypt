<#import "/spring.ftl" as spring />
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title><@spring.message "welcome.page.title"/><@spring.message "application.title.separator"/><@spring.message "application.title.name"/></title>
    <link rel="icon" href="/resources/images/favicon.ico" />
    <link rel="stylesheet" type="text/css" href="/resources/styles/main.css" />
    <script type="text/javascript" src="/resources/scripts/main.js"></script>
  </head>
  <body>
    <h1><@spring.message "welcome.page.header"/></h1>
    <div>
      <a href="<@spring.url '/logout' />"><@spring.message "logout"/></a>
    </div>
  </body>
</html>

<%@ page language="java" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:html locale="true">

<head>
<title><bean:message key="title.MainMenu"/></title>
<html:base/>
</head>

<body bgcolor="white">

<hr>
<h1><bean:message key="h1.MainMenu"/></h1>
<html:errors/>
<hr>

<h2><bean:message key="h2.MainMenu"/></h2>

<a href="AdminLoginPage.jsp"><bean:message key="prompt.MainMenu.adminLogin"/></a><br/>
<a href="CustomerLoginPage.jsp"><bean:message key="prompt.MainMenu.customerLogin"/></a><br/>

<hr>
<h5>&copy; 2002-2004 The AndroMDA team</h5>

</body>

</html:html>

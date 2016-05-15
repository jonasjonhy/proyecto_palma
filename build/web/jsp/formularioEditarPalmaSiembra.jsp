<%-- 
    Document   : formularioEditarPalmaSiembra
    Created on : 28/11/2014, 01:40:51 AM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
<%
    String idSiembra = request.getParameter("idSiembra");
%>   
<%=facadeMauricio.formulacioEditarPalmaSiembra(idSiembra)%>;

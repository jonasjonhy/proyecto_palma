<%-- 
    Document   : formularioEditarNivelacionNutriente
    Created on : 21/11/2014, 05:00:14 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadeMauricio" scope="page" class="Facade.FacadeMauricio" />
<%
    String idNivelacion = request.getParameter("idNivelacion");
%>   
    <%=facadeMauricio.FormularioEditarNivelacionNutriente(idNivelacion)%>;


<%-- 
    Document   : formularioEditarOtros
    Created on : 27/11/2014, 11:37:00 AM
    Author     : Pedro
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
<%
    String idOtro= request.getParameter("idOtro");
%>   
<%=facadePedro.FormularioEditarOtro(idOtro)%>;

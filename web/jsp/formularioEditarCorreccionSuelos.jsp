<%-- 
    Document   : formularioEditarCorreccionSuelos
    Created on : 27/11/2014, 11:36:24 AM
    Author     : Pedro
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
<%
    String idCorreccion = request.getParameter("idCorreccion");
%>   
<%=facadePedro.FormularioEditaCorreccionSuelo(idCorreccion)%>;



<%-- 
    Document   : formularioEditarSiembraCobertura
    Created on : 27/11/2014, 11:36:43 AM
    Author     : Pedro
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadePedro" scope="page" class="Facade.FacadePedro" />
<%
    String idCobertura= request.getParameter("idCobertura");
%>   
<%=facadePedro.FormularioEditaCobertura(idCobertura)%>;

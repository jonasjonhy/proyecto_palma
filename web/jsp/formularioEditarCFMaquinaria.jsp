<%-- 
    Document   : formularioEditarCFMaquinaria
    Created on : 3/12/2014, 03:46:56 AM
    Author     : Oscar Torres
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadeOscar" scope="page" class="Facade.FacadeOscar" />
<%
    String id = request.getParameter("idCFMaquinaria");
    
%>   
    <%=facadeOscar.editarCFMaquinaria(id)%>;



<%-- 
    Document   : formularioEditarNivelacionNutriente
    Created on : 21/11/2014, 05:00:14 PM
    Author     : mauricio uribe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="facadeRosemberg" scope="page" class="Facade.FacadeRosemberg" />
<%
    String idCosto = request.getParameter("idCosto");
    
%>   
    <%=facadeRosemberg.formularioEditarCostoTierra(idCosto)%>;


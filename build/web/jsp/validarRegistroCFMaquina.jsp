<%-- 
    Document   : validarRegistroCFMaquina
    Created on : 1/12/2014, 11:20:44 PM
    Author     : Oscar Torres
--%>

<%@page import="javax.swing.JOptionPane"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
    </head>
    <body>
         <%
        HttpSession sesionU = request.getSession();
        String idLote = (String) sesionU.getAttribute("idLote");
        String idPropietario = (String) sesionU.getAttribute("id");
        String hacienda = (String) sesionU.getAttribute("idHacienda");
        String otraM = request.getParameter("otraMaquina");
        String subtotalCostos=request.getParameter("subtotalc");
        String PrecioVidaUtil=request.getParameter("preautil");
        String subtotalMaq=request.getParameter("subtotalMaquinaria");
    
         %>
        <jsp:useBean id="facade" scope="page" class="Facade.Facade" />
        <jsp:useBean id="facadeOscar" scope="page" class="Facade.FacadeOscar" />
        <jsp:useBean id="CFM" scope="page" class="Dto.CFMaquinaria" />
        <jsp:setProperty name="CFM" property="*"/>
        <%
        CFM.setIdHacienda(Integer.parseInt(hacienda));
        CFM.setSubtotalCompra(Double.parseDouble(subtotalCostos));
        CFM.setPrecioAñoUtil(Double.parseDouble(PrecioVidaUtil));
        CFM.setSubtotalMaquinaria(Double.parseDouble(subtotalMaq));
        if(CFM.getIdMaquina()<0){
            %>
            <script type="text/javascript"> 
            alert("ERROR..Debe seleccionar alguna Maquina\nVuelva a intentarlo")
            location.href="../jsp/tablaCFMaquinaria.jsp";
            </script>
            <%
        }else{        
        boolean registro = facadeOscar.registrarCFMaquina(CFM, otraM,idPropietario);
        
        if(!registro){
            %>
            <script type="text/javascript"> 
                  alert("ERROR...\nVuelvelo a intentar Se detecto un error")
                  location.href="../jsp/tablaCFMaquinaria.jsp";
              </script>
            <%
            }else{
                %>
                <script type="text/javascript"> 
                  alert("REGISTRO EXITOSO\n Se ha registrado una nueva maquina para la Hacienda\nAhora mismo la podra ver en la TABLA DE Maquina")
                  location.href="../jsp/tablaCFMaquinaria.jsp";
                </script>
                <%
            }
        }
            %>
    </body>
</html>
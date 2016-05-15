<%-- 
    Document   : generadorReporte
    Created on : 8/05/2014, 04:05:09 PM
    Author     : Luis Miguel Blanco
--%>
<%@page import="com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline"%>
<%@page import="com.itextpdf.tool.xml.pipeline.html.HtmlPipeline"%>
<%@page import="com.itextpdf.tool.xml.pipeline.html.HtmlPipeline"%>
<%@page import="com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline"%>
<%@page import="com.itextpdf.tool.xml.Pipeline"%>
<%@page import="com.itextpdf.text.*"%>
<%@page import="com.itextpdf.text.pdf.*"%>

<jsp:useBean id="facade" scope="page" class="Facade.Facade" />
<jsp:useBean id="facadeB" scope="page" class="Facade.FacadeBrian" />
<jsp:useBean id="pdf" scope="page" class="util.crearPDF" />

<%@ page  language="java" import="java.io.*,java.sql.*,java.awt.Color" contentType="text/html; charset=utf-8"  errorPage=""  %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>REPORTE</title>
    </head>
    <body>
        <%

            HttpSession sesionU = request.getSession();
            String idPropietario = (String) sesionU.getAttribute("id");
            String hacienda = (String) sesionU.getAttribute("idHacienda");
            String idLote = (String) sesionU.getAttribute("idLote");
            String tipo=request.getParameter("tipo");
            
            String rutaLocal = request.getSession().getServletContext().getRealPath("");

            response.setContentType("application/pdf");

            Document document = new Document();
            ByteArrayOutputStream buffer = new ByteArrayOutputStream();
            PdfWriter fd = PdfWriter.getInstance(document, response.getOutputStream());

            facadeB.generarReportePDF(idLote,hacienda, tipo, document, rutaLocal, fd);

            DataOutput output = new DataOutputStream(response.getOutputStream());
            byte[] bytes = buffer.toByteArray();
            response.setContentLength(bytes.length);
            for (int j = 0; j < bytes.length; j++) {
                output.writeByte(bytes[j]);
            }

        %>
    </body>
</html>

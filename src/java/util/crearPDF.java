/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import Dao.*;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.*;
import com.itextpdf.tool.xml.XMLWorker;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.itextpdf.tool.xml.html.Tags;
import com.itextpdf.tool.xml.parser.XMLParser;
import com.itextpdf.tool.xml.pipeline.css.CSSResolver;
import com.itextpdf.tool.xml.pipeline.css.CssResolverPipeline;
import com.itextpdf.tool.xml.pipeline.end.PdfWriterPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipeline;
import com.itextpdf.tool.xml.pipeline.html.HtmlPipelineContext;
import java.io.*;

/**
 *
 * @author brialxsfxubun
 */
public class crearPDF extends Thread {

    private String id_pdf;
    private String tipo;
    private Document document;
    public crearPDF() {
    }

    public crearPDF(String id, String tipo, Document document) {

        this.id_pdf = id;
        this.tipo = tipo;
        this.document=document;
    }

    public  void crearInformePDF(String id, String tipo, String rutaImg, Document document,PdfWriter fd) {
        System.out.println("Genera el puto pdf");
        try {
            
            //Document document = new Document(PageSize.A4);
            
            HtmlPipelineContext htmlContext = new HtmlPipelineContext(null);

            htmlContext.setTagFactory(Tags.getHtmlTagProcessorFactory());

            CSSResolver cssResolver = XMLWorkerHelper.getInstance().getDefaultCssResolver(false);

            cssResolver.addCss("hr { background-color: green; height: 5px; width: 80%; }", true);

            Pipeline<?> pipeline = new CssResolverPipeline(cssResolver, new HtmlPipeline(htmlContext, new PdfWriterPipeline(document,fd )));

            XMLWorker worker = new XMLWorker(pipeline, true);
            XMLParser xmlParser = new XMLParser(worker);

            document.open();
            System.out.println(rutaImg);
            
            Image logo = Image.getInstance(rutaImg + "header.png");
            document.add(logo);

            String html = "<html>\n";
            String body = "<body>\n";
            String header = "<p><hr/></p>\n";
            body += header + "</body>";
            html += body + "</html>\n\n";
            xmlParser.parse(new StringReader(html));

            tableTitle(document, tipo);
            tableCostos(document, id, tipo, rutaImg);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            xmlParser.parse(new StringReader(html));

            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void tableTitle(Document document, String tipo) {
        try {
            PdfPTable title = new PdfPTable(2);
            PdfPCell title1, title2;

            Font font = new Font();
            font.setStyle(1);
            font.setSize(18);
            font.setColor(0, 166, 80);

            if (tipo.equalsIgnoreCase("lot")) {
                title1 = new PdfPCell(new Paragraph("COSTOS DE", font));
                title1.setBorder(0);
                title1.setHorizontalAlignment(Element.ALIGN_RIGHT);

                font = new Font();
                font.setStyle(1);
                font.setSize(18);
                font.setColor(245, 130, 31);

                title2 = new PdfPCell(new Paragraph("ESTABLECIMIENTO", font));
                title2.setBorder(0);
            } else {
                title1 = new PdfPCell(new Paragraph("COSTOS", font));
                title1.setBorder(0);
                title1.setHorizontalAlignment(Element.ALIGN_RIGHT);

                font = new Font();
                font.setStyle(1);
                font.setSize(18);
                font.setColor(245, 130, 31);

                title2 = new PdfPCell(new Paragraph("FIJOS", font));
                title2.setBorder(0);
            }

            title.addCell(title1);
            title.addCell(title2);
            title.setHorizontalAlignment(Element.ALIGN_CENTER);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(title);

            return;
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }

    public void correr(Document d, String ruta, PdfWriter fd) {
        System.out.println("Entra al puto run");
        
        String split = System.getProperty("file.separator");
        String rutaI=ruta+split+"images"+split;

        crearInformePDF(getId_pdf(), getTipo(), rutaI,d,  fd);
    }

    private void tableCostos(Document document, String id, String tipo, String rutaImg) {
        try {
            PdfPTable title = new PdfPTable(2);
            Paragraph p;
            PdfPCell c;
            Font font = new Font();
            font.setStyle(1);

            String tipoCosto[] = getTipoCosto(tipo);
            double costos[] = getCostos(id, tipo);

            boolean x = crearPieChart3D(costos, tipoCosto, rutaImg, tipo);

            for (int i = 0; i < costos.length; i++) {

                p = new Paragraph(tipoCosto[i], font);
                title.addCell(new PdfPCell(p));

                p = new Paragraph("$ " + costos[i]);
                c = new PdfPCell(p);
                c.setHorizontalAlignment(Element.ALIGN_RIGHT);
                title.addCell(c);

            }

            p = new Paragraph(" ");
            c = new PdfPCell(p);
            c.setColspan(2);
            c.setBorder(0);
            title.addCell(c);

            p = new Paragraph("TOTAL COSTOS ", font);
            c = new PdfPCell(p);
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            title.addCell(c);

            p = new Paragraph("$ " + sumaCostos(costos), font);
            c = new PdfPCell(p);
            c.setHorizontalAlignment(Element.ALIGN_RIGHT);
            title.addCell(c);

            title.setHorizontalAlignment(Element.ALIGN_CENTER);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            document.add(title);

            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));

            agregarChart(x, rutaImg, document);

            document.add(new Paragraph(" "));

            agregarPorcentajes(costos, tipoCosto, document);

            return;
        } catch (DocumentException ex) {
            ex.printStackTrace();
        }
    }


    private double[] getCostos(String id, String tipo) {

        if (tipo.equalsIgnoreCase("lot")) {
            double costos[] = {new LaborSiembra_DAO().getTotalLaborSiembra(id), new PreparacionSuelo_DAO().getTotalPreparacionSuelo(id), new nivelacionNutrientes_DAO().subtotalNivelacionesDeLote(id),
                new CorreccionSuelo_DAO().subtotalesCorreccionSuelos(id), new PalmaSiembra_DAO().subtotalesPalmaSiembraDeLote(id), new Otro_DAO().subtotalesOtro(id), new SiembraCobertura_DAO().subtotalesSiembraCobertura(id)};
            return costos;
        } else {
            double costos[] = {new CostoTierra_DAO().getSumatoriaSubtotales(id), new CFMaquinaria_DAO().getSumatoriaSubtotales(id), new CFAnimales_DAO().getSumatoriaSubtotales(id),
                new CFHerramientas_DAO().getSumatoriaSubtotales(id), new CostoAdministracion_DAO().getSumatoriaSubtotales(id)};
            return costos;
        }

    }

    private double sumaCostos(double[] costos) {
        double total = 0;

        for (double x : costos) {
            total += x;
        }
        return total;
    }

    private boolean crearPieChart3D(double[] costos, String tipos[], String ruta, String tipo) {
        try {
            boolean x = new PieChart3D().getPieChart3D(ruta, costos, tipos, tipo);
            System.out.println("CrearPieChart " + x);
            return x;
        } catch (IOException ex) {
            return false;
        }
    }

    private void agregarChart(boolean x, String rutaImg, Document document) {

        try {
            if (x) {
                Image chart = Image.getInstance(rutaImg + "pieChart3D.jpeg");
                document.add(chart);

            } else {
                Font f = new Font();
                f.setSize(15);
                f.setStyle(1);

                Paragraph p = new Paragraph("NO SE ENCONTRARON REGISTROS", f);
                p.setAlignment(1);
                document.add(p);
            }
        } catch (BadElementException ex) {
        } catch (IOException | DocumentException ex) {
            ex.printStackTrace();
        }

    }

    private void agregarPorcentajes(double[] costos, String[] tipoCosto, Document document) {

        try {

            PdfPTable title = new PdfPTable(2);
            Paragraph p;
            PdfPCell c;

            Font font = new Font();
            font.setStyle(1);

            p = new Paragraph("TABLA PORCENTAJES", font);
            c = new PdfPCell(p);
            c.setColspan(2);
            c.setHorizontalAlignment(Element.ALIGN_CENTER);
            title.addCell(c);

            double porc = 0.0, total = sumaCostos(costos);

            for (int i = 0; i < costos.length; i++) {

                font = new Font();
                font.setStyle(2);
                p = new Paragraph(tipoCosto[i], font);
                title.addCell(new PdfPCell(p));

                if (costos[i] != 0) {
                    porc = (costos[i] * 100) / total;
                } else {
                    porc = 0.0;
                }

                p = new Paragraph(porc + " %");
                c = new PdfPCell(p);
                c.setHorizontalAlignment(Element.ALIGN_RIGHT);
                title.addCell(c);

            }

            document.add(title);

        } catch (Exception ex) {

        }

    }

    private String[] getTipoCosto(String tipo) {
        if (tipo.equalsIgnoreCase("hac")) {
            String tipos[] = {"Costos Tierra", "Costos Maqui. y Equipos", "Costos Animales", "Costos Herramientas", "Costos Administración"};
            return tipos;
        } else {
            String tipos[] = {"Costos Siembra Palmas", "Costos Preparacion Suelo", "Costos Nivelacion Suelos", "Costos Correcion Suelos", "Costos Palmas Para Siembra", "Otros", "Costos Siembra Coberturas"};
            return tipos;
        }
    }

    

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getId_pdf() {
        return id_pdf;
    }

    public void setId_pdf(String id_pdf) {
        System.out.println("Entra al set");
        this.id_pdf = id_pdf;
    }


}

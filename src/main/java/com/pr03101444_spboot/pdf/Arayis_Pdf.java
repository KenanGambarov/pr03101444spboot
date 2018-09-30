package com.pr03101444_spboot.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.HeaderFooter;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Arayis_Pdf extends AbstractITextPdfView{

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int seh = 1;
        int row = 0;

        @SuppressWarnings("unchecked")
        Main main = (Main) model.get("main");
        Arayis_pojo a = (Arayis_pojo) model.get("a");
        List<Arayis_pojo> list = (List<Arayis_pojo>) model.get("list");
        String ad = a.getId1();
        
        BaseFont bfTimes = BaseFont.createFont("fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font1 = new Font(bfTimes, 11, Font.NORMAL);
        Font font2 = new Font(bfTimes, 12, Font.BOLD);
        Paragraph par;
        PdfPCell cell = null;
        
        writer.setPageEvent(new HeaderFooter(seh));
        
        switch (a.getAction()) {
                case "1":
                    ad = a.getAd() + " üzrə fəaliyyətdə olan müəssisələr";
                    break;
                case "2":
                    ad = a.getAd() + " üzrə hesabat təqdim etmeyen müəssisələr";
            }

            par = new Paragraph(new Paragraph(ad + "\n\n", font2));
            par.setAlignment(Element.ALIGN_CENTER);
            document.add(par);
////----------------------------------------------------
            float[] colsWidth1 = {2.3f, 3f, 11f, 3.5f, 2.5f, 2.5f, 3f, 2.5f, 2.7f, 2f};
            PdfPTable table = new PdfPTable(colsWidth1);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            
            cell = new PdfPCell(new Paragraph("Say", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Kod", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Ad", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Ərazi kodu", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Seksiya", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Fəaliy-yət kodu", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Tabelik", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Mül-kiyyət", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Hüquqi forma", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("İri, kiçik", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setRowspan(2);
            table.addCell(cell);
//            a.getSay() = 0;
            Iterator<Arayis_pojo> it = list.iterator();
            while (it.hasNext()) {
                Arayis_pojo as = it.next();
                cell = new PdfPCell(new Paragraph("" + a.getSay(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut1(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph(as.getAdi(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut2(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut3(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut4(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut5(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut6(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut7(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);

                cell = new PdfPCell(new Paragraph("" + as.getSut8(), font1));
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setBorder(0);
                table.addCell(cell);
    }
            document.add(table);
    }
    
}

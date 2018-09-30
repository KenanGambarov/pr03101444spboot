package com.pr03101444_spboot.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pr03101444_spboot.object.HeaderFooter;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Cedvel_Pdf extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {
        int row = 0;
        

        @SuppressWarnings("unchecked")
        int leng = (int) model.get("leng");
        Yekun_pojo yn = (Yekun_pojo) model.get("yn");
        Yekun_pojo yb = (Yekun_pojo) model.get("y");
        String feal1[] = yb.getFeal1();
        String ced[][] = yb.getCed();
        int setr = yb.getSetr();
        int seh = yn.getSeh();

        BaseFont bfTimes = BaseFont.createFont("fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font1 = new Font(bfTimes, 11, Font.NORMAL);
        Font font2 = new Font(bfTimes, 12, Font.BOLD);
        Paragraph par;
        PdfPCell cell = null;

        writer.setPageEvent(new HeaderFooter(seh));
        int x;
        int i;
        int z;
        int a;
        int y;

        for (x = 0; x < leng; x += 5) {
            par = new Paragraph(new Paragraph(yn.getBasliq() + "\n\n", font2));
            par.setAlignment(Element.ALIGN_CENTER);
            document.add(par);
////----------------------------------------------------
            float[] colsWidth1 = {3f, 0.7f, 1.2f, 1.2f, 1.2f, 1.2f, 1.2f};
            PdfPTable table = new PdfPTable(colsWidth1);
            table.setWidthPercentage(100);
            table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

            cell = new PdfPCell(new Paragraph("Göstəricinin adı", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("Məsrəf-lərin kodu", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            a = 5;
            cell = new PdfPCell(new Paragraph("Fəaliyyət növləri", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setColspan(a);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(" ", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph(" ", font1));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.addCell(cell);

            i = x;
            while (i < x + 5 && i < leng) {
                cell = new PdfPCell(new Paragraph(feal1[i + 1], font1));
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                table.addCell(cell);
                i++;
            }
            int t;
            if (i == leng) {
                for (t = leng - x; t < 5; t++) {
                    cell = new PdfPCell(new Paragraph(" ", font1));
                    cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                    table.addCell(cell);
                }
            }

            for (int r = 1; r <= setr; r++) {
                switch (r) {
                    case 1:
                        cell = new PdfPCell(new Paragraph(ced[r][1] + "\n\n o cümlədən əsas vəsaitlərin növləri üzrə:", font1));
                        cell.setHorizontalAlignment(row);
                        cell.setBorder(0);
                        table.addCell(cell);
                        break;
                    default:
                        cell = new PdfPCell(new Paragraph(ced[r][1], font1));
                        cell.setHorizontalAlignment(row);
                        cell.setBorder(0);
                        table.addCell(cell);
                }

                cell = new PdfPCell(new Paragraph(ced[r][2], font1));
                cell.setHorizontalAlignment(1);
                cell.setBorder(0);
                table.addCell(cell);

                z = x;
                while (z < x + 5 && z < leng) {

                    cell = new PdfPCell(new Paragraph("" + ced[r][z + 3], font1));
                    cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                    cell.setBorder(0);
                    table.addCell(cell);
                    z++;

                }

                if (z == leng) {
                    for (t = leng - x; t < 5; t++) {
                        cell = new PdfPCell(new Paragraph("", font1));
                        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                        cell.setBorder(0);
                        table.addCell(cell);
                    }
                }
            }
            document.add(table);
        }

    }
}

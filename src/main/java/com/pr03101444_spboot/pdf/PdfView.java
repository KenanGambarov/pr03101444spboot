package com.pr03101444_spboot.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.object.HeaderFooter;
import com.pr03101444_spboot.object.Katalog;
import com.pr03101444_spboot.object.TableRow;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PdfView extends AbstractITextPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document doc, PdfWriter writer, HttpServletRequest request, HttpServletResponse response) throws Exception {


        int seh = 1;
        int row = 0;

        @SuppressWarnings("unchecked")
        Main main = (Main) model.get("main");
        TableRow tlr = (TableRow) model.get("tlr");
        Katalog kat = (Katalog) model.get("kat");
        List<TableRow> list = (List<TableRow>) model.get("list");

//        OutputStream out = response.getOutputStream();

//        doc = new Document(PageSize.A4.rotate(), 50, 50, 60, 50);
//        writer = PdfWriter.getInstance(doc, out);
        BaseFont bfTimes = BaseFont.createFont("fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
        Font font1 = new Font(bfTimes, 11, Font.NORMAL);
        Font font2 = new Font(bfTimes, 12, Font.BOLD);
        Font font3 = new Font(bfTimes, 14, Font.BOLD);
        Paragraph par;
        PdfPCell cell = null;
        
        writer.setPageEvent(new HeaderFooter(seh));


        float[] colsWidth1 = {4f, 30f, 7f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 8.5f, 8f};
//            float[] colsWidth1 = {1.5f, 11f, 2.5f, 3.5f, 2.5f, 2.5f, 3f, 2.5f, 2.7f, 2.7f};
        PdfPTable table = new PdfPTable(colsWidth1);
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
//            table.setHeaderRows(2);
        returnOver(writer, bfTimes);
        float[] colsWidth0 = {3f, 6f};
        PdfPTable tablex00 = new PdfPTable(colsWidth0);
        tablex00.setWidthPercentage(96);
        tablex00.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        tablex00.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
//            tablex00.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);

        par = new Paragraph(new Paragraph("R Ə S M İ   S T A T İ S T İ K A   H E S A B A T I\n", font3));
        par.setAlignment(Element.ALIGN_CENTER);
        doc.add(par);

        par = new Paragraph(new Paragraph("1-ƏF №-li forma\n\n\n", font3));
        par.setAlignment(Element.ALIGN_CENTER);
        doc.add(par);

        PdfPCell cell0 = null;

        String mm[] = {"", "Hesabatı təqdim edən müəssisənin adı:", "Müəssisənin kodu:", "Hesabatın qeydə alınma tarixi:", "Adamsaat:", "IP ünvanı:"};

        cell0 = new PdfPCell(new Paragraph(mm[1], font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(kat.getAd() + "", font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(mm[2], font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(main.getLogin(), font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(mm[3], font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(tlr.getDate() + " " + tlr.getTime() + "\n", font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(mm[4], font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(tlr.getAdamsaat(), font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(mm[5], font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        cell0 = new PdfPCell(new Paragraph(tlr.getIp() + "", font2));
        cell0.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell0.setBorder(0);
        tablex00.addCell(cell0);

        doc.add(tablex00);

        par = new Paragraph(new Paragraph("\n", font2));
        par.setAlignment(Element.ALIGN_CENTER);
        doc.add(par);

        par = new Paragraph(new Paragraph("Əsas vəsaitlərin hərəkəti və əsaslı təmiri (torpaq və faydalı qazıntılar istisna olunmaqla)\n", font3));
        par.setAlignment(Element.ALIGN_CENTER);
        doc.add(par);

        par = new Paragraph(new Paragraph("manat   \n", font3));
        par.setAlignment(Element.ALIGN_RIGHT);
        par.setSpacingAfter(Element.ALIGN_MIDDLE);
        doc.add(par);

        cell = new PdfPCell(new Paragraph("Sət-rin №-si", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Göstəricinin adı", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Məhsulun kodu", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Hesabat dövrü ərzində maddi əsas vəsaitlərin əldə edilməsinə çəkilmiş xərclər (torpaq və faydalı qazıntılar istisna olunmaqla)", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("o cümlədən:", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setColspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Əsas vəsaitlərin əsaslı təmiri üzrə kənar müəssisə və təşkilat ların göstərdikləri xidmətlə-rin dəyəri", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("o cümlə-dən, xarici ölkələ-rin hüquqi şəxslərinin göstər-dikləri xidmət-lər", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Silinmiş əsas vəsaitlər bazar qiyməti ilə", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("Silinmiş əsas vəsaitlə-rin yaşı (tam rəqəmlə)", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setRowspan(2);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("ölkədə istehsal edilmiş yeni (istifa-dədə olma- mış) əsas vəsait-lər", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        cell = new PdfPCell(new Paragraph("idxal olun-muş yeni və istifadə edilmiş əsas vəsait-lər", font1));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        table.addCell(cell);

        Iterator<TableRow> itr = list.iterator();
        while (itr.hasNext()) {
            TableRow lst = itr.next();
            cell = new PdfPCell(new Paragraph(lst.getSira(), font1));
            cell.setHorizontalAlignment(0);
            cell.setBorder(0);
            table.addCell(cell);

            String ad1 = lst.getAdi();
            if (lst.getAdi() == null) {
                ad1 = "";
            }
            switch (lst.getSira()) {
                case "01":
                    cell = new PdfPCell(new Paragraph(ad1 + "\n\n o cümlədən əsas vəsaitlərin növləri üzrə:", font1));
                    cell.setHorizontalAlignment(row);
                    cell.setBorder(0);
                    table.addCell(cell);
                    break;
                default:
                    cell = new PdfPCell(new Paragraph(ad1, font1));
                    cell.setHorizontalAlignment(row);
                    cell.setBorder(0);
                    table.addCell(cell);
            }

            String kod1 = lst.getMehs_kodu();
            if (lst.getMehs_kodu() == null) {
                kod1 = "";
            }
            cell = new PdfPCell(new Paragraph(kod1, font1));
            cell.setHorizontalAlignment(0);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut1(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut2(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut3(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut4(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut5(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut6(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

            cell = new PdfPCell(new Paragraph("" + lst.getSut7(), font1));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setBorder(0);
            table.addCell(cell);

        }
        doc.add(table);
        returnOver(writer, bfTimes);

    }

    public PdfContentByte returnOver(PdfWriter pwriter, BaseFont bfTimes) {
        PdfContentByte over = pwriter.getDirectContent();
        over.saveState();
        over.beginText();
        over.setTextRenderingMode(PdfContentByte.TEXT_RENDER_MODE_FILL_STROKE);
        over.setLineWidth(1.2f);
        over.setRGBColorStroke(0xFF, 0x00, 0x00);
        over.setRGBColorFill(0xFF, 0xFF, 0xFF);
        over.setFontAndSize(bfTimes, 14);
        over.setTextMatrix(1.9f, 1.5f, -2, 1, 90, 70);
//        over.setTextMatrix(1f, 2f, -2, 1, 120, 70);
        over.showText("H E S A B A T    Q E Y D Ə    A L I N D I !");
        over.endText();
        over.restoreState();
        return over;
    }

}

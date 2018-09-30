package com.pr03101444_spboot.pdf;

import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class Cedvel_xlsx extends AbstractExcelView{

    @Override
    protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfw, HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        @SuppressWarnings("unchecked")
        int leng = (int) map.get("leng");
        Yekun_pojo yn = (Yekun_pojo) map.get("yn");
        Yekun_pojo yb = (Yekun_pojo) map.get("y");
        List<String> list = (List<String>) map.get("list");
        int sut = (int) map.get("sut");
        String feal1[] = yb.getFeal1();
        String ced[][] = yb.getCed();
        int setr = yb.getSetr();
        int seh = yb.getSeh();
        
        HSSFFont font1 = createFont(hssfw, (short) 11, "Times New Roman", (short) 400);
        HSSFFont font2 = createFont(hssfw, (short) 12, "Times New Roman", (short) 700);
        HSSFSheet sheet = hssfw.createSheet("Əsas vəsaitlərin hərəkəti və əsaslı təmiri (torpaq və faydalı qazıntılar istisna olunmaqla)\n\n");
        sheet.setColumnWidth(0, 9700);
        sheet.setColumnWidth(1, 3200);
        sheet.setColumnWidth(1 + yb.getList().size(), 3000);

        HSSFRow row = sheet.createRow(sheet.getLastRowNum() + 1);
        HSSFCell cell = createCell(hssfw, row, 0, (short) 1, (short) 2, font2, false, false);
        cell.setCellValue(yn.getBasliq());

        row = sheet.createRow(sheet.getLastRowNum() + 2);
        cell = createCell(hssfw, row, 0, (short) 2, (short) 1, font2, true, true);
        cell.setCellValue("Göstəricinin adı");
        cell = createCell(hssfw, row, 1, (short) 2, (short) 1, font2, true, true);
        cell.setCellValue("Məsrəflərin kodu");
        sheet.addMergedRegion(new CellRangeAddress(sheet.getLastRowNum(), sheet.getLastRowNum(), 2, 1 + leng));
        for (int i = 2; i < 2 + leng; i++) {
            cell = createCell(hssfw, row, i, (short) 2, (short) 1, font2, true, true);
            cell.setCellValue("Fəaliyyət növləri");
        }

        row = sheet.createRow(sheet.getLastRowNum() + 1);
        cell = createCell(hssfw, row, 0, (short) 2, (short) 1, font2, true, true);
        cell.setCellValue(" ");
        cell = createCell(hssfw, row, 1, (short) 2, (short) 1, font2, true, true);
        cell.setCellValue(" ");
        for (int i = 2; i < 2 + leng; i++) {
            cell = createCell(hssfw, row, i, (short) 2, (short) 1, font2, true, true);
            cell.setCellValue(feal1[i - 1]);
        }

//        Iterator<TableRow> itr = this.list.iterator();
//        while (itr.hasNext()) {
//            TableRow tbl = itr.next();
        for (int i = 1; i <= setr; i++) {
            row = sheet.createRow(sheet.getLastRowNum() + 1);

//            String ad1 = tbl.getAdi();
//            String ad2 = tbl.getAd();
//            if (tbl.getAdi() == null) {
//                ad1 = "";
//            }
            switch (i) {
                case 1:
                    cell = createCell(hssfw, row, 0, (short) 1, (short) 1, font1, false, false);
                    cell.setCellValue(ced[i][1]);
                    cell = createCell(hssfw, sheet.createRow(sheet.getLastRowNum() + 1), 0, (short) 1, (short) 1, font1, false, false);
                    cell.setCellValue("\n\n o cümlədən əsas vəsaitlərin növləri üzrə:");
                    break;
                default:
                    cell = createCell(hssfw, row, 0, (short) 1, (short) 1, font1, false, false);
                    cell.setCellValue(ced[i][1]);
            }

            cell = createCell(hssfw, row, 1, (short) 1, (short) 1, font1, false, false);
            cell.setCellValue(ced[i][2]);

//            for (int r = 2; r < 2 + getfnCount(); r++) {
            for (int j = 3; j <= yb.getSut() + 2; j++) {
                cell = createCell(hssfw, row, j - 1, (short) 3, (short) 1, font1, false, false);
//                Double dbl = tbl.getMap(list2.get(r - 2));
//                if (tbl.getMap(list2.get(r - 2)) == null) {
//                    dbl = 0.0;
//                }
                Double dbl;
                if (ced[i][j].equals("")) {
                    ced[i][j] = "0.0";
                    dbl = Double.parseDouble(ced[i][j]);
                } else {
                    dbl = Double.parseDouble(ced[i][j]);
                }
                cell.setCellValue(dbl);
            }
        }
    }
    
    private HSSFFont createFont(HSSFWorkbook workbook, short fontheight, String fontname, short boldweight) {
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints(fontheight);
        font.setFontName(fontname);
        font.setBoldweight(boldweight);
        return font;
    }

    private HSSFCell createCell(HSSFWorkbook workbook, HSSFRow row, int column, short halign, short valign, HSSFFont font, boolean wrap, boolean borders) {
        HSSFCell cell = row.createCell(column);
        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setAlignment(halign);
        cellStyle.setVerticalAlignment(valign);
        cellStyle.setFont(font);
        cellStyle.setWrapText(wrap);
        if (borders) {
            cellStyle.setBorderBottom((short) 1);
            cellStyle.setBorderLeft((short) 1);
            cellStyle.setBorderRight((short) 1);
            cellStyle.setBorderTop((short) 1);
        }
        cell.setCellStyle(cellStyle);
        return cell;
    }
    
}

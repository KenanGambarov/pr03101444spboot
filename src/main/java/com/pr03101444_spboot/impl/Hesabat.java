package com.pr03101444_spboot.impl;

import com.pr03101444_spboot.object.Katalog;
import com.pr03101444_spboot.object.TableRow;
import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.pr03101444_spboot.dao.Hesabat_Dao;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.Iterator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Hesabat implements Hesabat_Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<TableRow> listhes() {
        String SQL = " SELECT sira, adi, mehs_kodu,"
                + "   IFNULL(h.sut1,0) sut1, IFNULL(h.sut2,0) sut2, IFNULL(h.sut3,0) sut3, IFNULL(h.sut4,0) sut4, IFNULL(h.sut5,0) sut5, "
                + "   IFNULL(h.sut6,0) sut6, IFNULL(h.sut7,0) sut7, adamsaat, ip "
                + "    FROM ("
                + " (SELECT  sira, adi, mehs_kodu  FROM db03101444.af WHERE sira<36) a "
                + " LEFT JOIN "
                + " (SELECT * FROM db03101444.afhes_copy WHERE mkod = ?) h ON a.sira=h.sira_h) "
                + " ORDER BY sira";
        String SQL1 = "CALL db03101444.listhes(?)";
        RowMapper<TableRow> mapper = (ResultSet rs, int i) -> {
            TableRow row2 = new TableRow();
            row2.setSira(rs.getString("sira"));
            row2.setAdi(rs.getString("adi"));
            row2.setMehs_kodu(rs.getString("mehs_kodu"));
            row2.setSut1(rs.getDouble("sut1"));
            row2.setSut2(rs.getDouble("sut2"));
            row2.setSut3(rs.getDouble("sut3"));
            row2.setSut4(rs.getDouble("sut4"));
            row2.setSut5(rs.getDouble("sut5"));
            row2.setSut6(rs.getDouble("sut6"));
            row2.setSut7(rs.getInt("sut7"));
            row2.setAdamsaat(rs.getString("adamsaat"));
            return row2;
        };

//        List<TableRow> list1 = jdbcTemplate.query(SQL, new Object[]{selectfactorycode, selectfactorycode}, mapper);
//        List<TableRow> list2 = new ArrayList<>();
//        for (TableRow tableRow : list1) {
//            String SQL1 = "SELECT * "
//                    + "  FROM ( "
//                    + "(SELECT mehs_koduh FROM db03101444.afhes WHERE sira_h > 36 AND mkod = ?) a"
//                    + " LEFT JOIN "
//                    + "(SELECT mehs_kodu, adi FROM db03101444.af1) b ON a.mehs_koduh = b.mehs_kodu)";
//
//            RowMapper<TableRow> mapper2 = (ResultSet rs, int i) -> {
//                TableRow row2 = new TableRow();
//                row2.setMehs_koduh1(rs.getString("mehs_kodu"));
//                row2.setAdi_h(rs.getString("adi"));
//                return row2;
//            };
//            list2 = jdbcTemplate.query(SQL1, new Object[]{selectfactorycode}, mapper2);
//        }
//         Main m = new Main();
//         System.out.println("login10 = " + m.getMain().getLogin());
        return jdbcTemplate.query(SQL1, new Object[]{selectfactorycode}, mapper);
    }

    @Override
    public void SaveHes(Katalog kat, TableRow tlr, List<TableRow> list) {
        String SQL = " DELETE FROM db03101444.afhes_copy WHERE mkod = ?";
        jdbcTemplate.update(SQL, new Object[]{selectfactorycode});
        Iterator<TableRow> itr = list.iterator();
        while (itr.hasNext()) {
            TableRow row1 = itr.next();
            if (row1.getSut1() + row1.getSut2() + row1.getSut3() + row1.getSut4() + row1.getSut5() + row1.getSut6() + row1.getSut7() != 0) {
                String SQL1 = "INSERT INTO db03101444.afhes_copy(mkod,sira_h,mehs_koduh,status,sut1,sut2,sut3,sut4,sut5,sut6,sut7,feal,seksiya,mulk,ik,ik1,tab,htforma,arazi,adamsaat,date,time,ip) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,now(),now(),?)";
                jdbcTemplate.update(SQL1, selectfactorycode, row1.getSira(), row1.getMehs_kodu(), status, row1.getSut1(), row1.getSut2(), row1.getSut3(), row1.getSut4(), row1.getSut5(), row1.getSut6(), row1.getSut7(), kat.getFn(), kat.getSeksiya(), kat.getMn(), kat.getIk(), kat.getIk1(), kat.getTab(), kat.getHtforma(), kat.getArazi(), tlr.getAdamsaat(), tlr.getIp());
            }
        }
    }

    public String bold(Object d1, Object d2, String priznak) {
        return "(" + d1 + priznak + d2 + ")";
    }

    @Override
    public Katalog Rows() {
        String SQL = "SELECT ad, seksiya, htforma, bucce, ik, ik1, say, fn, mn, kodarazi, tab FROM tesnifat.kataloq_2011 WHERE kod= ? ";
        RowMapper<Katalog> mapper = (ResultSet rs, int i) -> {
            Katalog kat1 = new Katalog();
            kat1.setAd(rs.getString("ad"));
            kat1.setBucce(rs.getString("bucce"));
            kat1.setSay(rs.getString("say"));
            kat1.setSeksiya(rs.getString("seksiya"));
            kat1.setIk(rs.getString("ik"));
            kat1.setIk1(rs.getString("ik1"));
            kat1.setHtforma(rs.getString("htforma"));
            kat1.setFn(rs.getString("fn"));
            kat1.setMn(rs.getString("mn"));
            kat1.setArazi(rs.getString("kodarazi"));
            kat1.setTab(rs.getString("tab"));
            return kat1;
        };
        return jdbcTemplate.queryForObject(SQL, new Object[]{selectfactorycode}, mapper);
    }

    @Override
    public TableRow tabl(HttpServletRequest request) {
        String SQL = "SELECT * FROM db03101444.afhes_copy WHERE mkod =?";
        ResultSetExtractor<TableRow> rse = (rs) -> {
            TableRow row = new TableRow();
            if (!rs.first()) {
                row.setIp(request.getRemoteAddr());
                return row;
            } else {
                row.setAdamsaat(rs.getString("adamsaat"));
                row.setIp(rs.getString("ip"));
                row.setDate(rs.getString("date"));
                row.setTime(rs.getString("time"));
                return row;
            }
        };
        return jdbcTemplate.query(SQL, new Object[]{selectfactorycode}, rse);
    }

    @Override
    public List<Katalog> rayonlar(Main main) {
        String areacode = main.getAreacode();
        String SQL = "SELECT ad, kod FROM tesnifat.kataloq_2011 WHERE  LEFT (kodarazi,3) = SUBSTRING(?,1,3) AND bazpr != '0' ";
        RowMapper<Katalog> mapper = (ResultSet rs, int i) -> {
            Katalog kat = new Katalog();
            kat.setAd(rs.getString("ad"));
            kat.setKod(rs.getString("kod"));
            return kat;
        };
        return jdbcTemplate.query(SQL, new Object[]{areacode}, mapper);
    }

    @Override
    public String rayonAdi(Main main) {
        String areacode = main.getAreacode();
        System.out.println("areacode = " + areacode);
        String SQL = "SELECT B FROM tesnifat.region WHERE KOD = ? ";
        String rayon = (String) jdbcTemplate.queryForObject(SQL, new Object[]{areacode}, String.class);
        return rayon;
    }

    private String selectfactorycode;
    private String code;
    private List<TableRow> list;
    private List<Katalog> list2;
    private String message;
    private TableRow tlr;
    private int status;
    private int say=0;

    @Override
    public String getSelectfactorycode(Main main, String kod) {
        status = main.getStatus();
        switch (status) {
            case 0:
                selectfactorycode = main.getLogin();
                break;
            case 1:
                selectfactorycode = kod;
                break;
            case 2:
                selectfactorycode = kod;
                break;
        }
        return selectfactorycode;
    }

    public void setSelectfactorycode(String selectfactorycode) {
        this.selectfactorycode = selectfactorycode;
    }

    public List<Katalog> getList2() {
        return list2;
    }

    public void setList2(List<Katalog> list2) {
        this.list2 = list2;
    }

    public List<TableRow> getList() {
        return list;
    }

    public void setList(List<TableRow> list) {
        this.list = list;
    }

    @Override
    public String getMessage(TableRow tr, List<TableRow> list) {
        int row = 35;
        int col = 6;
        int count = 0;
        message = "";
        double[][] dat = new double[row + 1][col + 1];
        double sum = 0;

        for (int i = 1; i <= col; i++) {
            for (int j = 1; j <= row; j++) {
                dat[j][i] = 0;
            }
        }

        int i2 = 0;
        Iterator<TableRow> itr = list.iterator();
        while (itr.hasNext()) {
            i2 = i2 + 1;
            tr = itr.next();
            for (int j = 1; j <= 6; j++) {
                dat[i2][j] = tr.sut(j);
            }
        }

        for (int j = 1; j <= 6; j++) {
            for (int i = 2; i <= 35; i++) {
                sum = sum + dat[i][j];
            }
            if (dat[1][j] - sum != 0) {
                count++;
                message = message + "\n Sütun " + j + " :  Sətir 1  Sətir (2+3+4+5+....+35) " + bold(dat[1][j], sum, " != ");
            }
            sum = 0;
        }

        for (int j = 1; j <= 35; j++) {
            sum = dat[j][2] + dat[j][3];
            if (dat[j][1] != sum) {
                count++;
                message = message + "\n Sətir " + j + "  Sütun 01 = Sütun 02 + Sütun 03 " + bold(dat[j][1], sum, " != ");
            }
            sum = 0;
        }

        for (int j = 1; j <= 35; j++) {
            if (dat[j][4] < dat[j][5]) {
                count++;
                message = message + "\n Sətir " + j + "  Sütun 04 >= Sütun 05 " + bold(dat[j][4], dat[j][5], " < ");
            }
        }

//        for (int j = 1; j <= 6; j++) {
//            for (int i = 37; i <= 46; i++) {
//                sum = sum + dat[i][j];
//            }
//
//            if (dat[36][j] - sum != 0) {
//                count++;
//                this.message = this.message + "\n Sütun " + j + " :  Sətir 36  Sətir (37+38+39+....+45+46) " + bold(dat[36][j], sum, " != ");
//            }
//            sum = 0;
//        }
        for (int j = 1; j <= 6; j++) {
            for (int i = 1; i <= 35; i++) {
                sum = sum + dat[i][j];
            }
        }

        if (sum == 0) {
            message = "Hesabat boş ola bilməz";
        }
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public TableRow getTlr() {
        return tlr;
    }

    public void setTlr(TableRow tlr) {
        this.tlr = tlr;
    }

    public int getSay() {
        say++;
        return say;
    }

    public void setSay(int say) {
        this.say = say;
    }


}

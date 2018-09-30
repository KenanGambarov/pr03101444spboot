package com.pr03101444_spboot.impl;

import com.pr03101444_spboot.dao.Yekun_Dao;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Yekun implements Yekun_Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public String Redirect(Yekun_pojo yb, Arayis_pojo a) {
        String redirect = "";
        if (yb.getValue() != null) {
            if (yb.getValue().equals("1") && yb.getNov() == 1) {
                redirect = "redirect:/arayisfeal1";
            } else if (yb.getValue().equals("1") && yb.getNov() == 2) {
                redirect = "redirect:/arayisfeal2";
            } else if (yb.getValue().equals("2")) {
                redirect = "redirect:/arayis_mues";
            } else if (yb.getValue().equals("3")) {
                redirect = "redirect:/arayis_sahibkar";
            } else if (yb.getValue().equals("sut1") || yb.getValue().equals("sut2") || yb.getValue().equals("sut3") || yb.getValue().equals("sut4") || yb.getValue().equals("sut5") || yb.getValue().equals("sut6") || yb.getValue().equals("sut7")) {
                redirect = "redirect:/cedvel";
            } else if (yb.getValue().equals("4")) {
                redirect = "redirect:/baxis";
            } else if (yb.getValue().equals("0")) {
                redirect = "redirect:/empty";
            } else {
                redirect = "/yekun";
            }
        } else {
            redirect = "/yekun";
        }
        return redirect;
    }

    @Override
    public List<Yekun_pojo> getIqtisadireg(Main main) {
        String sert = " ";
        if (main.getStatus() == 1) {
            sert = "WHERE zona = SUBSTRING('" + main.getAreacode() + "',1,3) ";
        }

        String SQL = "SELECT IF(id IS NULL, '000', id) AS iq,"
                + "   IF(zona IS NULL, '000', zona) AS zona1,"
                + "   IF(id IS NULL AND zona IS NULL, '000', IF(zona IS NULL, pole, zona)) AS zona,"
                + "   IF(id IS NULL AND zona IS NULL, 'Bütün ərazilər',IF(zona IS NULL, reqion, IF(zona in (201,202), concat(b,'(Gəncə)'),b))) AS araziadi,"
                + "   IF(id IS NULL AND zona IS NULL, '', IF(zona IS NULL, '&nbsp;&nbsp;&nbsp;&nbsp;', '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;')) AS probel "
                + "   FROM  "
                + "     (SELECT  id, zona, reqion, b, pole FROM tesnifat.region, tesnifat.iregion WHERE ID_IQTISADI_RAYON = ID GROUP BY id, zona WITH ROLLUP ) a "
                + sert + "ORDER BY iq, zona1";

        RowMapper<Yekun_pojo> mapper = (ResultSet rs, int i) -> {
            Yekun_pojo yp = new Yekun_pojo();
            yp.setZona1(rs.getString("zona"));
            yp.setAraziadi(rs.getString("araziadi"));
            yp.setZona(rs.getString("zona"));
            return yp;
        };

        return jdbcTemplate.query(SQL, mapper);
    }

    @Override
    public List<Yekun_pojo> getMulkiyyet() {
        String SQL = "SELECT ID, AD FROM tesnifat.mulkiyyet "
                + "      UNION "
                + "      SELECT '1,2,3,4,5,6' AS ID, 'Qeyri dövlət mülkiyyəti' AS AD "
                + "      FROM tesnifat.mulkiyyet GROUP BY ID "
                + "      UNION "
                + "      SELECT '3,4,5,6' AS ID, 'Xarici investisiyalı mülkiyyətlər' AS AD "
                + "      FROM tesnifat.mulkiyyet GROUP BY ID "
                + "   ORDER BY ID";

        RowMapper<Yekun_pojo> mapper = (ResultSet rs, int i) -> {
            Yekun_pojo yp = new Yekun_pojo();
            yp.setMulk(rs.getString("AD"));
            yp.setId(rs.getString("ID"));
            return yp;
        };

        return jdbcTemplate.query(SQL, mapper);
    }

    @Override
    public List<Yekun_pojo> getFnkod() {
        String SQL = "SELECT * FROM db03101444.feal_sahe ORDER BY seksiya";

        RowMapper<Yekun_pojo> mapper = (ResultSet rs, int i) -> {
            Yekun_pojo yp = new Yekun_pojo();
            yp.setSahe(rs.getString("seksiya") + " - " + rs.getString("sahe"));
            yp.setSeksiya(rs.getString("seksiya"));
            return yp;
        };

        return jdbcTemplate.query(SQL, mapper);
    }

    @Override
    public int getListyek(Yekun_pojo yn, Yekun_pojo y) {
        String sert = "";
        String value = y.getValue();
        String fk = y.getFk();
        String selectmulkiyyet = y.getSelectmulkiyyet();
        int sut = 0;  // cədvəldə sütunların sayı
        int setr = 0; // cədvəldə sətirlərin sayı
        String feal1[];
        String ced[][];
        String SQL1 = "", SQL2 = "";

        if (value != null) {
            switch (value) {
                case "":
                    value = "sut1";
                    break;
                case "0":
                    value = "sut1";
                    break;
                case "1":
                    value = "sut1";
                    break;
                case "2":
                    value = "sut1";
                    break;
                case "3":
                    value = "sut1";
                    break;
                case "4":
                    value = "sut1";
            }
        } else {
            value = "sut1";
        }

        if (fk != null && !fk.equals("0")) {

        } else {
            fk = "5";
        }

        if (selectmulkiyyet != null) {
            if (selectmulkiyyet.equals("0")) {
                sert = "";
            } else if (selectmulkiyyet.equals("1")) {
                sert = " WHERE mulk=1 ";
            } else if (!selectmulkiyyet.equals("1")) {
                sert = " WHERE mulk<>1 ";
            }
        } else {
            sert = "";
        }

        SQL2 = "SELECT COUNT(DISTINCT(mehs_koduh)) " // cemi üçün say
                + " FROM ("
                + "   SELECT  '' arazi, a.adi, h.mehs_koduh, h.mulk, LEFT(h.feal," + fk + ") feal, IFNULL(h." + value + ", 0.0) " + value + "  FROM "
                + "     (SELECT  LEFT(arazi,3) arazi, mulk, LEFT(feal," + fk + ") feal, mehs_koduh, SUM(" + value + ") " + value + "  FROM db03101444.afhes_copy " + sert + " GROUP BY mehs_koduh, LEFT(feal," + fk + ")) h, "
                + "     (SELECT adi, mehs_kodu FROM db03101444.af UNION SELECT adi, mehs_kodu FROM db03101444.af1) a "
                + " WHERE  a.mehs_kodu = h.mehs_koduh AND " + value + " <> 0  ORDER BY h.mehs_koduh, LEFT(h.feal," + fk + ")) s";
//        System.out.println("SQL2 = " + SQL2);
        SQL1 = "SELECT adi, mehs_koduh, feal, " + value + " AS val FROM "
                + "   (SELECT '' arazi, adi, mulk, mehs_koduh, feal, IFNULL(" + value + ", 0.0) " + value + "  FROM " // cemi üçün
                + "     (SELECT  LEFT(arazi,3) arazi, mulk, LEFT(feal," + fk + ") feal, mehs_koduh, SUM(" + value + ") " + value + "  FROM db03101444.afhes_copy " + sert + " GROUP BY  mehs_koduh, LEFT(feal," + fk + ")) h, "
                + "     (SELECT adi, mehs_kodu FROM db03101444.af UNION SELECT adi, mehs_kodu FROM db03101444.af1) a  WHERE  a.mehs_kodu = h.mehs_koduh) g "
                + " WHERE " + value + " <> 0 ORDER BY  mehs_koduh, feal";

        String sut_sql = "SELECT COUNT(DISTINCT(LEFT(feal," + fk + "))) FROM db03101444.afhes" + sert;
        sut = jdbcTemplate.queryForObject(sut_sql, BigInteger.class).intValue();

        feal1 = new String[sut + 1];
        for (int i = 0; i <= feal1.length - 1; i++) {
            feal1[i] = "";
        }

        String ced_uz = Integer.toString(380 + feal1.length * 100) + "px";
        int leng = feal1.length - 1;
        int leng2 = leng * 100;

//        int s = 0;
        String feal1_sql = "SELECT DISTINCT(LEFT(feal," + fk + ")) FROM db03101444.afhes" + sert + " ORDER BY LEFT(feal," + fk + ")";
        List<String> list = (List<String>) jdbcTemplate.queryForList(feal1_sql, String.class);
        for (int i = 1; i <= list.size(); i++) {
            feal1[i] = list.get(i - 1);
        }

        setr = jdbcTemplate.queryForObject(SQL2, BigInteger.class).intValue();
        ced = new String[setr + 1][sut + 3];
        for (int i = 0; i <= setr; i++) {
            for (int j = 0; j <= sut + 2; j++) {
                ced[i][j] = "";
            }
        }

        int x = 0;
        RowMapper<Yekun_pojo> mapper = (ResultSet rs, int i) -> {
            Yekun_pojo yo = new Yekun_pojo();
            yo.setAd(rs.getString("adi"));
            yo.setKod(rs.getString("mehs_koduh"));
            yo.setFeal(rs.getString("feal"));
            yo.setErrmes(rs.getString("val"));
            return yo;
        };
        List<Yekun_pojo> list2 = (List<Yekun_pojo>) jdbcTemplate.query(SQL1, mapper);

        Iterator<Yekun_pojo> it = list2.iterator();
        while (it.hasNext()) {
            Yekun_pojo y1 = it.next();
            if (!ced[x][2].equals(y1.getKod())) {
                x = x + 1;
                ced[x][1] = y1.getAd();
                ced[x][2] = y1.getKod();
            }
            for (int s = 1; s <= sut; s++) {
                if (y1.getFeal().equals(feal1[s])) {
                    ced[x][s + 2] = y1.getErrmes();
                }
            }
        }

        for (int i = 1; i <= setr; i++) {
            if (ced[i][2].equals("00000") || ced[i][2].equals("41999")) {
                ced[i][2] = "X";
            }
        }
        yn.setSut(sut);
        yn.setSetr(setr);
        yn.setFeal1(feal1);
        yn.setCed(ced);
        yn.setCed_uz(ced_uz);
        yn.setList(list);
        yn.setLeng2(leng2);
        return leng;
    }

    @Override
    public List<TableRow> baxis(Yekun_pojo yn, int status) {
        String sert1 = "";
        String sert2 = "";
        String sert3 = "";
        String sert4 = "";
        String sert5 = "";
        String sert6 = "";
        String sert7 = "";

        if (yn.getKod() != null) {
            if (!yn.getKod().equals("")) {
                sert1 = " mkod IN (" + yn.getKod() + ") AND";
            }
        } else {
            yn.setKod("");
        }

        if (yn.getSira() != null) {
            if (!yn.getSira().equals("")) {
                sert2 = " sira_h IN (" + yn.getSira() + ") AND ";
            }
        } else {
            yn.setSira("");
        }

//        if (iqt != null) {
//            if (!iqt.equals("") && !iqt.equals("0") && !iqt.equals("000")) {
//                sert3 = " LEFT (arazi,3) IN (" + iqt + ") AND ";
//            }
//        } else {
//            iqt = "";
//        }
        if (yn.getIqt() != null) {
            switch (yn.getIqt()) {
                case "":
                    yn.setIqt("");
                    break;
                case "000":
                    yn.setIqt("");
                    break;
                default:
                    sert3 = " LEFT (arazi,3) IN (" + yn.getIqt() + ") AND";
            }
        } else {
            yn.setIqt("");
        }

        if (yn.getFeal() != null) {
            if (!yn.getFeal().equals("") && !yn.getFeal().equals("0")) {
                sert4 = " feal IN (" + yn.getFeal() + ") AND ";
            }
        } else {
            yn.setFeal("");
        }

        if (yn.getMn2() != null) {
            if (!yn.getMn2().equals("") && !yn.getMn2().equals("0")) {
                sert5 = " mulk IN (" + yn.getMn2() + ") AND ";
            }
        } else {
            yn.setMn2("");
        }

        if (yn.getSek() != null) {
            if (!yn.getSek().equals("") && !yn.getSek().equals("0")) {
                sert6 = " h.seksiya IN ('" + yn.getSek() + "') AND ";
            }
        } else {
            yn.setSek("");
        }

        if (yn.getTab() != null) {
            if (!yn.getTab().equals("") && !yn.getTab().equals("0")) {
                sert7 = " h.tab IN (" + yn.getTab() + ") AND ";
            }
        } else {
            yn.setTab("");
        }

        if (status == 1 && !sert1.equals("")) {
            sert4 = "";
            sert5 = "";
            sert6 = "";
            sert7 = "";
        } else if (!sert1.equals("")) {
            sert3 = "";
            sert4 = "";
            sert5 = "";
            sert6 = "";
            sert7 = "";
        } else {

        }

        String SQL = "SELECT '1' nomre, sira_h, adi, mehs_koduh, feal, LEFT(arazi,3) arazi, mkod, "
                + "       IFNULL(h.sut1, 0) sut1, IFNULL(h.sut2, 0) sut2, IFNULL(h.sut3, 0) sut3, IFNULL(h.sut4, 0) sut4, "
                + "       IFNULL(h.sut5, 0) sut5, IFNULL(h.sut6, 0) sut6, IFNULL(h.sut7, 0) sut7 "
                + " FROM ( "
                + "    (SELECT Ad, q.*  FROM db03101444.afhes q, tesnifat.kataloq_2011 y WHERE mkod IN (kod)) h "
                + "     LEFT JOIN  (SELECT sira, adi, mehs_kodu FROM db03101444.af UNION SELECT sira, adi, mehs_kodu FROM db03101444.af1) m "
                + "     ON h.mehs_koduh = m.mehs_kodu)  WHERE " + sert1 + sert2 + sert3 + sert4 + sert5 + sert6 + sert7 + " sut1 + sut2 + sut3 + sut4 + sut5 + sut6 + sut7 <> 0  GROUP BY mkod,sira_h "
                + " UNION ALL "
                + " (SELECT  '0' nomre, ' ' sira_h, Ad, ' ' mehs_koduh, ' ' feal, LEFT(arazi, 3) arazi, mkod, "
                + "         '0' sut1, '0' sut2, '0' sut3, '0' sut4, '0' sut5, '0' sut6, '0' sut7 "
                + "  FROM db03101444.afhes h,  tesnifat.kataloq_2011 q WHERE " + sert1 + sert2 + sert3 + sert4 + sert5 + sert6 + sert7 + " mkod = kod  GROUP BY mkod) "
                + " ORDER BY arazi, mkod, sira_h";
//        System.out.println("sql = " + SQL);

        RowMapper<TableRow> mapper = (ResultSet rs, int i) -> {
            TableRow row = new TableRow();
            row.setSira(rs.getString("sira_h"));
            row.setAdi(rs.getString("adi"));
//                lst.setMehs_kodu(rs.getString("mehs_kodu"));
            row.setKod(rs.getString("mehs_koduh"));
//                lst.setAd(rs.getString("ad2"));
            row.setNomre(rs.getString("nomre"));
            row.setKod_c(rs.getString("mkod"));
            row.setAdi_h(rs.getString("arazi"));
            row.setSut1(rs.getDouble("sut1"));
            row.setSut2(rs.getDouble("sut2"));
            row.setSut3(rs.getDouble("sut3"));
            row.setSut4(rs.getDouble("sut4"));
            row.setSut5(rs.getDouble("sut5"));
            row.setSut6(rs.getDouble("sut6"));
            row.setSut7(rs.getInt("sut7"));
            return row;
        };

        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public Yekun_pojo GoHes(Yekun_pojo yn){
        String mkod = "";
        if(yn.getSelectcode() != null){
        mkod = yn.getSelectcode();
        }
        String SQL = "SELECT kod FROM tesnifat.kataloq_2011 WHERE bazpr != 0 AND kod='" + mkod + "'";
        ResultSetExtractor<Yekun_pojo> rse = (rs) -> {
            Yekun_pojo y = new Yekun_pojo();
            if (!rs.first()) {
                y.setErrmes("Bu kodda müəssisə mövcud deyil");
                return y;
            } else{
                y.setErrmes2("redirect:/hesabat");
                return y;
            }
        };
        return  jdbcTemplate.query(SQL, rse);
    }
    
    @Override
    public int chap_pdf(Yekun_pojo yn, Yekun_pojo y){
        String sert = "";
        String value = y.getValue();
        String fk = y.getFk();
        String selectmulkiyyet = y.getSelectmulkiyyet();
        int sut = 0;  // cədvəldə sütunların sayı
        int setr = 0; // cədvəldə sətirlərin sayı
        String feal1[];
        String ced[][];
        String SQL1 = "", SQL2 = "";

        System.out.println("value = " + value + " " + fk);
        if (value != null) {
            switch (value) {
                case "":
                    value = "sut1";
                    break;
                case "0":
                    value = "sut1";
                    break;
                case "1":
                    value = "sut1";
                    break;
                case "2":
                    value = "sut1";
                    break;
                case "3":
                    value = "sut1";
                    break;
                case "4":
                    value = "sut1";
            }
        } else {
            value = "sut1";
        }

        if (fk != null && !fk.equals("0")) {

        } else {
            fk = "5";
        }

        if (selectmulkiyyet != null) {
            if (selectmulkiyyet.equals("0")) {
                sert = "";
            } else if (selectmulkiyyet.equals("1")) {
                sert = " WHERE mulk=1 ";
            } else if (!selectmulkiyyet.equals("1")) {
                sert = " WHERE mulk<>1 ";
            }
        } else {
            sert = "";
        }

        SQL2 = "SELECT COUNT(DISTINCT(mehs_koduh)) " // cemi üçün say
                + " FROM ("
                + "   SELECT  '' arazi, a.adi, h.mehs_koduh, h.mulk, LEFT(h.feal," + fk + ") feal, IFNULL(h." + value + ", 0.0) " + value + "  FROM "
                + "     (SELECT  LEFT(arazi,3) arazi, mulk, LEFT(feal," + fk + ") feal, mehs_koduh, SUM(" + value + ") " + value + "  FROM db03101444.afhes " + sert + " GROUP BY mehs_koduh, LEFT(feal," + fk + ")) h, "
                + "     (SELECT adi, mehs_kodu FROM db03101444.af UNION SELECT adi, mehs_kodu FROM db03101444.af1) a "
                + " WHERE  a.mehs_kodu = h.mehs_koduh AND " + value + " <> 0  ORDER BY h.mehs_koduh, LEFT(h.feal," + fk + ")) s";
//        System.out.println("SQL2 = " + SQL2);
        SQL1 = "SELECT adi, mehs_koduh, feal, " + value + " AS val FROM "
                + "   (SELECT '' arazi, adi, mulk, mehs_koduh, feal, IFNULL(" + value + ", 0.0) " + value + "  FROM " // cemi üçün
                + "     (SELECT  LEFT(arazi,3) arazi, mulk, LEFT(feal," + fk + ") feal, mehs_koduh, SUM(" + value + ") " + value + "  FROM db03101444.afhes " + sert + " GROUP BY  mehs_koduh, LEFT(feal," + fk + ")) h, "
                + "     (SELECT adi, mehs_kodu FROM db03101444.af UNION SELECT adi, mehs_kodu FROM db03101444.af1) a  WHERE  a.mehs_kodu = h.mehs_koduh) g "
                + " WHERE " + value + " <> 0 ORDER BY  mehs_koduh, feal";

        String sut_sql = "SELECT COUNT(DISTINCT(LEFT(feal," + fk + "))) FROM db03101444.afhes " + sert;
        sut = jdbcTemplate.queryForObject(sut_sql, BigInteger.class).intValue();

        feal1 = new String[sut + 1];
        for (int i = 0; i <= feal1.length - 1; i++) {
            feal1[i] = "";
        }

        String ced_uz = Integer.toString(380 + feal1.length * 100) + "px";
        int leng = feal1.length - 1;
        int leng2 = leng * 100;

//        int s = 0;
        String feal1_sql = "SELECT DISTINCT(LEFT(feal," + fk + ")) FROM db03101444.afhes " + sert + " ORDER BY LEFT(feal," + fk + ")";
        List<String> list = (List<String>) jdbcTemplate.queryForList(feal1_sql, String.class);
        for (int i = 1; i <= list.size(); i++) {
            feal1[i] = list.get(i - 1);
        }

        setr = jdbcTemplate.queryForObject(SQL2, BigInteger.class).intValue();
        System.out.println("setr = " + setr);
        ced = new String[setr + 1][sut + 3];
        for (int i = 0; i <= setr; i++) {
            for (int j = 0; j <= sut + 2; j++) {
                ced[i][j] = "";
            }
        }

        int x = 0;
        RowMapper<Yekun_pojo> mapper = (ResultSet rs, int i) -> {
            Yekun_pojo yo = new Yekun_pojo();
            yo.setAd(rs.getString("adi"));
            yo.setKod(rs.getString("mehs_koduh"));
            yo.setFeal(rs.getString("feal"));
            yo.setErrmes(rs.getString("val"));
            return yo;
        };
        List<Yekun_pojo> list2 = (List<Yekun_pojo>) jdbcTemplate.query(SQL1, mapper);

        Iterator<Yekun_pojo> it = list2.iterator();
        while (it.hasNext()) {
            Yekun_pojo y1 = it.next();
            if (!ced[x][2].equals(y1.getKod())) {
                x = x + 1;
                ced[x][1] = y1.getAd();
                ced[x][2] = y1.getKod();
            }
            for (int s = 1; s <= sut; s++) {
                if (y1.getFeal().equals(feal1[s])) {
                    ced[x][s + 2] = y1.getErrmes();
                }
            }
        }

        for (int i = 1; i <= setr; i++) {
            if (ced[i][2].equals("00000") || ced[i][2].equals("41999")) {
                ced[i][2] = "X";
            }
        }
        yn.setSut(sut);
        yn.setSetr(setr);
        yn.setFeal1(feal1);
        yn.setCed(ced);
        yn.setCed_uz(ced_uz);
        yn.setList(list);
        yn.setLeng2(leng2);
        return leng;
    }


}

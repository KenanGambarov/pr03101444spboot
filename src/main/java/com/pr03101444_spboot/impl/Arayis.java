package com.pr03101444_spboot.impl;

import com.pr03101444_spboot.dao.Arayis_Dao;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.sql.ResultSet;
import java.util.List;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
public class Arayis implements Arayis_Dao {
    
    private int nov;
    
    public int getNov() {
        return nov;
    }
    
    public void setNov(int nov) {
        this.nov = nov;
    }
    
    private JdbcTemplate jdbcTemplate;
    
    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    @Override
    public List<Arayis_pojo> listfeal(Yekun_pojo yn) {
        String sert = "";
        if (yn.getSelectiqtisadireg() != null) {
            switch (yn.getSelectiqtisadireg()) {
                case "000":
                    sert = " ";
                    break;
                default:
                    sert = "WHERE POLE = '" + yn.getSelectiqtisadireg() + "' OR kodarazi IN (" + yn.getSelectiqtisadireg() + ")";
            }
        } else {
            sert = "";
        }
        
        String SQL = "SELECT IFNULL(nt.ID,'000') id, IFNULL(nt.kodarazi,'000') zona,"
                + "       IF(nt.ID IS NULL AND nt.kodarazi IS NULL, '000', IF(nt.ID IS NOT NULL AND nt.kodarazi IS NULL, nt.POLE, nt.ZONA)) AS req,"
                + "       IF(nt.ID IS NULL,'Azərbaycan Respublikası', IF(nt.kodarazi IS NULL, CONCAT('  ',nt.REQION), CONCAT('   ',nt.b))) adi,"
                + "       feal, s1 cemi, s2 mues, s3 rsi, ROUND(s1/feal*100,1) f1, ROUND(s2/feal*100,1) f2, ROUND(s3/feal*100,1) f3, feal-s1 etmeyen, adam"
                + " FROM "
                + "   (SELECT  ts.*, SUM(ts.sayi) feal, SUM(IFNULL(fz.s1,0)) s1, SUM(IFNULL(fz.s2,0)) s2, SUM(IFNULL(fz.s3,0)) s3, SUM(IFNULL(fz.adam,0)) adam FROM"
                + "     (SELECT  LEFT(k.kodarazi,3) kodarazi, k.tab, k.mn, COUNT(DISTINCT k.kod) AS sayi, r.b, r.ZONA, ir.ID, ir.REQION, ir.pole"
                + "      FROM tesnifat.kataloq_2011 AS k, tesnifat.region AS r, tesnifat.iregion AS ir WHERE ir.ID=r.ID_IQTISADI_RAYON AND r.ZONA=LEFT(k.kodarazi,3) AND k.bazpr != '0' "
                + "      GROUP BY ir.ID,LEFT(k.kodarazi,3)) ts"
                + "   LEFT JOIN "
                + "    (SELECT  kodarazi, fn, mn, tab, SUM(s1) s1, SUM(s2) s2, SUM(s3) s3, SUM(adam) adam FROM"
                + "       (SELECT  DISTINCT(mkod) mkod, LEFT(arazi,3) kodarazi, feal fn, mulk mn, tab, status, 1 s1, IF(status=0 OR status=2, 1,0) s2,"
                + "             IF(status=1, 1,0) s3, adamsaat adam  FROM db03101444.afhes_copy  GROUP BY mkod) hs GROUP BY hs.kodarazi) fz"
                + "     ON fz.kodarazi=ts.kodarazi GROUP BY ts.ID,ts.kodarazi WITH ROLLUP) nt " + sert + "  "
                + " ORDER BY id,kodarazi ";
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("adi"));
            a.setSut1(rs.getString("feal"));
            a.setSut2(rs.getString("cemi"));
            a.setSut3(rs.getString("mues"));
            a.setSut4(rs.getString("rsi"));
            a.setSut5(rs.getString("f1"));
            a.setSut6(rs.getString("f2"));
            a.setSut7(rs.getString("f3"));
            a.setSut8(rs.getString("etmeyen"));
            a.setSut9(rs.getString("adam"));
            a.setSut10(rs.getString("req"));
            a.setZona(rs.getString("zona"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public List<Arayis_pojo> listfeal2(Yekun_pojo yn) {
        String sert = "";
        String sert2 = "";
        String sert3 = "";
        
        switch (yn.getSelectmulkiyyet()) {
            case "0":
                sert = " ";
                break;
            case "1,2,3,4,5,6":
                sert = "AND id IN (2,3,4,5,6)";
                break;
            case "3,4,5,6":
                sert = "AND id IN (4,5,6)";
                break;
            default:
                sert = "AND id = '" + yn.getSelectmulkiyyet() + "' ";
        }
        if (yn.getSelectiqtisadireg() != null) {
            switch (yn.getSelectiqtisadireg()) {
                case "000":
                    sert2 = " ";
                    sert3 = " ";
                    break;
                default:
                    sert2 = "AND LEFT(arazi, 3) IN (" + yn.getSelectiqtisadireg() + ")";
                    sert3 = "AND LEFT(kodarazi, 3) IN (" + yn.getSelectiqtisadireg() + ")";
            }
        } else {
            sert = "";
        }
        
        String SQL = " SELECT "
                + "  IFNULL(w.id, '000') id,  IF(LENGTH(w.id) = 1, w.id, '000') req,"
                + "  IF(w.id = 000, 'Bütün mülkiyyət növləri', IF(w.id = '1' OR w.id = '1,2,3,4,5,6', CONCAT('', w.mad), IF(w.id = '3' OR w.id = '3,4,5,6', CONCAT('', w.mad), CONCAT('', w.mad)))) adi, "
                + "  feal, d1 cemi, d2 mues, d3 rsi, ROUND(d1 / feal * 100, 1) f1, ROUND(d2 / feal * 100, 1) f2, ROUND(d3 / feal * 100, 1) f3, w.feal - w.d1 etmeyen,  adam "
                + "  FROM (SELECT  pp.*, SUM(pp.count1) feal, SUM(IFNULL(ff.d1, 0)) d1, SUM(IFNULL(ff.d2, 0)) d2, SUM(IFNULL(ff.d3, 0)) d3, SUM(IFNULL(ff.adam, 0)) adam "
                + "  FROM (SELECT  IFNULL(id, '000') id, count1, mad "
                + "    FROM (SELECT  m.id AS id, COUNT(DISTINCT (k.kod)) AS count1, m.Ad AS mad "
                + "      FROM tesnifat.kataloq_2011 AS k, tesnifat.mulkiyyet m  WHERE k.mn IN (1, 2, 3, 4, 5, 6) AND m.id = k.mn AND k.bazpr != '0' " + sert3 + " GROUP BY m.id WITH ROLLUP "
                + "      UNION ALL "
                + "      SELECT  '1,2,3,4,5,6' AS id, COUNT(DISTINCT (k.kod)) AS count1, 'Qeyri dövlət mülkiyyəti' AS mad "
                + "      FROM tesnifat.kataloq_2011 AS k, tesnifat.mulkiyyet m  WHERE k.mn IN (2, 3, 4, 5, 6) AND m.id = k.mn AND k.bazpr != '0' " + sert3 + " "
                + "      UNION ALL "
                + "      SELECT  '3,4,5,6' AS id, COUNT(DISTINCT (k.kod)) AS count1, 'Xarici investisiyalı mülkiyyətlər' AS mad "
                + "      FROM tesnifat.kataloq_2011 AS k, tesnifat.mulkiyyet m  WHERE k.mn IN (4, 5, 6) AND m.id = k.mn AND k.bazpr != '0' " + sert3 + " GROUP BY id) pp) pp "
                + "    LEFT JOIN "
                + "      (SELECT  mkod, arazi kodarazi, feal fn, mn, tab, ik, Seksiya, htforma, SUM(d1) d1, SUM(d2) d2, SUM(d3) d3, SUM(adam) adam "
                + "       FROM "
                + "        (SELECT mkod, arazi, feal, IFNULL(mulk, '000') mn, tab, ik, Seksiya, htforma, status, SUM(d1) d1, SUM(d2) d2, SUM(d3) d3, SUM(adam) adam "
                + "         FROM "
                + "           (SELECT   mkod, LEFT(arazi, 3) arazi, feal, mulk, tab, ik, Seksiya, htforma, i.status, 1 d1, IF(status = 0 OR status = 2, 1, 0) d2, IF(status = 1, 1, 0) d3, adamsaat adam "
                + "            FROM db03101444.afhes_copy i  WHERE mulk IN (1, 2, 3, 4, 5, 6) " + sert2 + " GROUP BY mkod) gg  GROUP BY mulk WITH ROLLUP "
                + "        UNION ALL "
                + "        SELECT  mkod, LEFT(arazi, 3) arazi, feal, '1,2,3,4,5,6' mulk, tab, ik, Seksiya, htforma, i.status, 1 d1, "
                + "                IF(status = 0 OR status = 2, 1, 0) d2, IF(status = 1, 1, 0) d3, adamsaat adam "
                + "        FROM db03101444.afhes_copy i  WHERE mulk IN (2, 3, 4, 5, 6) " + sert2 + " GROUP BY mkod "
                + "        UNION ALL "
                + "        SELECT  mkod, LEFT(arazi, 3) arazi, feal, '3,4,5,6' mulk, tab, ik, Seksiya, htforma, i.status, 1 d1, "
                + "                IF(status = 0 OR status = 2, 1, 0) d2, IF(status = 1, 1, 0) d3, adamsaat adam "
                + "        FROM db03101444.afhes_copy i  WHERE mulk IN (4, 5, 6) " + sert2 + "  GROUP BY mkod) pp "
                + "      GROUP BY pp.mn) ff "
                + "      ON ff.mn = pp.id "
                + "  GROUP BY pp.id) w WHERE feal <> 0 " + sert + " ORDER BY id ";
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("adi"));
            a.setSut1(rs.getString("feal"));
            a.setSut2(rs.getString("cemi"));
            a.setSut3(rs.getString("mues"));
            a.setSut4(rs.getString("rsi"));
            a.setSut5(rs.getString("f1"));
            a.setSut6(rs.getString("f2"));
            a.setSut7(rs.getString("f3"));
            a.setSut8(rs.getString("etmeyen"));
            a.setSut9(rs.getString("adam"));
            a.setSut10(rs.getString("id"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
        
    }
    
    @Override
    public List<Arayis_pojo> teqdimedenler(Arayis_pojo as) {
        String sert = "";
        if (as.getAction() != null && as.getId() != null) {
            switch (as.getAction()) {
                case "3":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w ";
                    }
                    break;
                case "4":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE status = 0 ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE status = 0 ";
                    }
                    break;
                case "5":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE status = 1 ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE status = 1 ";
                    }
                    break;
                case "6":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w ";
                            break;
                        default:
                            sert = " AND mn IN (" + as.getId() + ") ) w ";
                    }
                    break;
                case "7":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE status = 0 ";
                            break;
                        default:
                            sert = " AND mn IN (" + as.getId() + ") ) w WHERE status = 0 ";
                    }
                    break;
                case "8":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE status = 1 ";
                            break;
                        default:
                            sert = " AND mn IN (" + as.getId() + ") ) w WHERE status = 1 ";
                    }
                    break;
                case "9":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE mulk = 1 ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE mulk = 1 ";
                    }
                    break;
                case "10":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE mulk != 1 ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w  WHERE mulk != 1";
                    }
                    break;
                case "11":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w ";
                    }
                    break;
                case "12":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE ik1 = 2";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE ik1 = 2 ";
                    }
                    break;
                case "13":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE ik1 = 3";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE ik1 = 3 ";
                    }
                    break;
                case "14":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w WHERE ik1 = 1";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w WHERE ik1 = 1 ";
                    }
                    break;
                case "15":
                    switch (as.getId()) {
                        case "000":
                            sert = " ) w ";
                            break;
                        default:
                            sert = " AND req IN (" + as.getId() + ") ) w ";
                    }
                
            }
            
        } else {
            sert = " ) w ";
        }
        
        String SQL = "SELECT *  FROM ("
                + " SELECT mkod, IF(status != 1, 0,status) status, a.ik1, a.arazi, a.seksiya, a.feal, a.tab, a.mulk, a.htforma, a.ik, date, time, k.ad, k.kod, k.req, k.mn FROM db03101444.afhes_copy a, tesnifat.kataloq_2011 k "
                + " WHERE  a.mkod = k.kod " + sert + "  GROUP BY mkod";
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("ad"));
            a.setSut1(rs.getString("mkod"));
            a.setSut2(rs.getString("arazi"));
            a.setSut3(rs.getString("seksiya"));
            a.setSut4(rs.getString("feal"));
            a.setSut5(rs.getString("tab"));
            a.setSut6(rs.getString("mulk"));
            a.setSut7(rs.getString("htforma"));
            a.setSut8(rs.getString("ik"));
            a.setSut9(rs.getString("time"));
            a.setSut10(rs.getString("date"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public List<Arayis_pojo> muessise(Yekun_pojo yn) {
        String sert = "";
        if (yn.getSelectiqtisadireg() != null) {
            switch (yn.getSelectiqtisadireg()) {
                case "000":
                    sert = " ";
                    break;
                default:
                    sert = "WHERE POLE = '" + yn.getSelectiqtisadireg() + "' OR kodarazi IN (" + yn.getSelectiqtisadireg() + ")";
            }
        } else {
            sert = "";
        }
        
        String SQL = "SELECT IFNULL(nt.ID,'000') id, IFNULL(nt.kodarazi,'000') zona,"
                + "       IF(nt.ID IS NULL AND nt.kodarazi IS NULL, '000', IF(nt.ID IS NOT NULL AND nt.kodarazi IS NULL, nt.POLE, nt.ZONA)) AS req,"
                + "       IF(nt.ID IS NULL,'Azərbaycan Respublikası', IF(nt.kodarazi IS NULL, CONCAT('  ',nt.REQION), CONCAT('   ',nt.b))) adi,"
                + "       s1 dovlet, s2 qeyri_dovlet, s1+s2 cemi"
                + " FROM ("
                + "    SELECT  ts.*,  SUM(IFNULL(fz.s1, 0)) s1,  SUM(IFNULL(fz.s2, 0)) s2"
                + "     FROM ("
                + "       SELECT LEFT(k.kodarazi, 3) kodarazi, k.TAB, k.MN, COUNT(DISTINCT k.kod) AS sayi, r.b, r.zona, ir.id, ir.REQION, ir.POLE"
                + "         FROM "
                + "         tesnifat.kataloq_2011 AS k, tesnifat.region AS r, tesnifat.iregion AS ir WHERE ir.id = r.ID_IQTISADI_RAYON  AND "
                + "         r.zona = LEFT(k.kodarazi, 3) AND k.BAZPR != '0' GROUP BY ir.id, LEFT(k.kodarazi, 3)) ts"
                + "       LEFT JOIN (SELECT kodarazi, fn, MN, TAB, SUM(s1) s1, SUM(s2) s2 "
                + " FROM ( "
                + "         SELECT DISTINCT (mkod) mkod, LEFT(arazi, 3) kodarazi, feal fn, mulk MN, TAB, status,"
                + "         IF(mulk = 1, 1, 0) s1, IF(mulk != 1, 1, 0) s2"
                + "      FROM "
                + "        db03101444.afhes_copy GROUP BY mkod) hs GROUP BY hs.kodarazi) fz ON fz.kodarazi = ts.kodarazi GROUP BY ts.id, ts.kodarazi WITH ROLLUP) nt " + sert
                + "   ORDER BY id,kodarazi ";
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("adi"));
            a.setSut1(rs.getString("dovlet"));
            a.setSut2(rs.getString("qeyri_dovlet"));
            a.setSut3(rs.getString("cemi"));
            a.setSut4(rs.getString("req"));
            a.setZona(rs.getString("zona"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public List<Arayis_pojo> sahibkar(Yekun_pojo yn) {
        String sert = "";
        
        if (yn.getSelectiqtisadireg() != null) {
            switch (yn.getSelectiqtisadireg()) {
                case "":
                    sert = " ";
                    break;
                case "000":
                    sert = " ";
                    break;
                default:
                    sert = "WHERE POLE = '" + yn.getSelectiqtisadireg() + "' OR kodarazi IN (" + yn.getSelectiqtisadireg() + ")";
            }
        } else {
            sert = "";
        }
        
        String SQL = "SELECT IFNULL(nt.ID,'000') id, IFNULL(nt.kodarazi,'000') zona,"
                + "       IF(nt.ID IS NULL AND nt.kodarazi IS NULL, '000', IF(nt.ID IS NOT NULL AND nt.kodarazi IS NULL, nt.POLE, nt.ZONA)) AS req,"
                + "       IF(nt.ID IS NULL,'Azərbaycan Respublikası', IF(nt.kodarazi IS NULL, CONCAT('  ',nt.REQION), CONCAT('   ',nt.b))) adi,"
                + "       s1 kicik, s3 orta, s2 iri,  s1+s2+s3 cemi"
                + " FROM ( "
                + "    SELECT  ts.*, SUM(IFNULL(fz.s1,0)) s1, SUM(IFNULL(fz.s2,0)) s2, SUM(IFNULL(fz.s3,0)) s3"
                + "     FROM ("
                + "       SELECT  LEFT(k.kodarazi,3) kodarazi, k.tab, k.mn, k.ik1, COUNT(DISTINCT k.kod) AS sayi, r.b, r.ZONA, ir.ID, ir.REQION, ir.pole"
                + "     FROM "
                + "       tesnifat.kataloq_2011 AS k, tesnifat.region AS r, tesnifat.iregion AS ir WHERE ir.ID=r.ID_IQTISADI_RAYON"
                + "       AND r.ZONA=LEFT(k.kodarazi,3) AND k.bazpr != '0' "
                + "       GROUP BY ir.ID,LEFT(k.kodarazi,3)) ts"
                + "  LEFT JOIN ("
                + "     SELECT  kodarazi, fn, mn, tab, SUM(s1) s1, SUM(s2) s2, SUM(s3) s3 "
                + "     FROM ("
                + "       SELECT DISTINCT(mkod) mkod, LEFT(arazi,3) kodarazi, feal fn, mulk mn, tab, ik1, status, IF(ik1 = 1,1,0) s1,"
                + "         IF(ik1 = 2, 1,0) s2, IF(ik1=3, 1,0) s3  "
                + "       FROM "
                + "        db03101444.afhes_copy  GROUP BY mkod) hs GROUP BY hs.kodarazi) fz ON fz.kodarazi=ts.kodarazi GROUP BY ts.ID,ts.kodarazi WITH ROLLUP) nt " + sert
                + "   ORDER BY id,kodarazi ";
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("adi"));
            a.setSut1(rs.getString("kicik"));
            a.setSut2(rs.getString("orta"));
            a.setSut3(rs.getString("iri"));
            a.setSut4(rs.getString("cemi"));
            a.setSut5(rs.getString("req"));
            a.setZona(rs.getString("zona"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public List<Arayis_pojo> pdf(Arayis_pojo as, Yekun_pojo yn, Main main) {
        String SQL = "";
        String sert = "";
        String sert2 = "";
        System.out.println("pdf = " + yn.getNov() + " " + as.getId() + " " + as.getAction());
        switch (yn.getNov()) {
            case 1:
                switch (as.getId()) {
                    case "000":
                        sert = "";
                        break;
                    default:
                        sert = "req IN (" + as.getId() + ") AND";
                }
                break;
            case 2:
                switch (as.getId()) {
                    case "000":
                        sert = "";
                        break;
                    case "1,2,3,4,5,6":
                        sert = "mn IN (2,3,4,5,6) AND";
                        break;
                    case "3,4,5,6":
                        sert = "mn IN (4,5,6) AND";
                        break;
                    default:
                        sert = "mn IN (" + as.getId() + ") AND";
                }
            
        }
        
        if (main.getStatus() == 1) {
            if (yn.getSelectiqtisadireg() != null) {
                switch (yn.getSelectiqtisadireg()) {
                    case "000":
                        sert2 = "";
                        break;
                    default:
                        sert2 = " LEFT(kodarazi, 3) IN (" + yn.getSelectiqtisadireg() + ") AND";
                }
            }
        }
        
        switch (as.getAction()) {
            case "1":
                SQL = "SELECT ad, kod, kodarazi, seksiya, fn, tab, mn, htforma, ik FROM tesnifat.kataloq_2011 WHERE " + sert + sert2 + " bazpr != '0' ";
                break;
            case "2":
                SQL = " SELECT k.kod, kodarazi, k.seksiya, fn, k.tab, mn, k.htforma, k.ik, k.ad FROM tesnifat.kataloq_2011 k "
                        + " WHERE " + sert + sert2 + " k.kod NOT IN (SELECT mkod FROM db03101444.afhes_copy a) AND bazpr != '0' ";
        }
        
        System.out.println("sql = " + SQL);
        
        RowMapper<Arayis_pojo> mapper = (ResultSet rs, int i) -> {
            Arayis_pojo a = new Arayis_pojo();
            a.setAdi(rs.getString("ad"));
            a.setSut1(rs.getString("kod"));
            a.setSut2(rs.getString("kodarazi"));
            a.setSut3(rs.getString("seksiya"));
            a.setSut4(rs.getString("fn"));
            a.setSut5(rs.getString("tab"));
            a.setSut6(rs.getString("mn"));
            a.setSut7(rs.getString("htforma"));
            a.setSut8(rs.getString("ik"));
            return a;
        };
        
        return jdbcTemplate.query(SQL, mapper);
    }
    
    @Override
    public List<TableRow> teqdimeden_pdf(Arayis_pojo as){
        String SQL = " SELECT sira, adi, a.mehs_kodu, ad, "
                + "   IFNULL(h.sut1,0) sut1, IFNULL(h.sut2,0) sut2, IFNULL(h.sut3,0) sut3, IFNULL(h.sut4,0) sut4, IFNULL(h.sut5,0) sut5, "
                + "   IFNULL(h.sut6,0) sut6, IFNULL(h.sut7,0) sut7, m.mehs_kodu, sira_h, adamsaat, ip "
                + "    FROM ("
                + " (SELECT  sira, adi, mehs_kodu  FROM db03101444.af) a "
                + " LEFT JOIN "
                + " (SELECT * FROM db03101444.afhes_copy WHERE mkod = '" + as.getKod1() + "') h ON a.sira=h.sira_h "
                + " LEFT JOIN "
                + "(SELECT adi AS ad, mehs_kodu FROM db03101444.af1) m ON h.mehs_koduh = m.mehs_kodu) "
                + " ORDER BY sira";
        
        RowMapper<TableRow> mapper = (ResultSet rs, int i) -> {
            TableRow row2 = new TableRow();
            row2.setSira(rs.getString("sira"));
            row2.setAdi(rs.getString("adi"));
            row2.setAd(rs.getString("ad"));
            row2.setKod(rs.getString("m.mehs_kodu"));
            row2.setMehs_kodu(rs.getString("mehs_kodu"));
            row2.setSut1(rs.getDouble("sut1"));
            row2.setSut2(rs.getDouble("sut2"));
            row2.setSut3(rs.getDouble("sut3"));
            row2.setSut4(rs.getDouble("sut4"));
            row2.setSut5(rs.getDouble("sut5"));
            row2.setSut6(rs.getDouble("sut6"));
            row2.setSut7(rs.getInt("sut7"));
            return row2;
        };
        return jdbcTemplate.query(SQL, mapper);
    }
}

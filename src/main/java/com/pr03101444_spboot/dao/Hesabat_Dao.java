package com.pr03101444_spboot.dao;

import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.object.Katalog;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public interface Hesabat_Dao {
    
    List<TableRow> listhes();
    
    void SaveHes(Katalog kat, TableRow tlr, List<TableRow> list);
    
    Katalog Rows();
    
    TableRow tabl(HttpServletRequest request);
    
    String getMessage(TableRow tr, List<TableRow> list);
    
    List<Katalog>rayonlar(Main main);
    
    String rayonAdi(Main main);
    
    String getSelectfactorycode(Main main, String kod);
    
}

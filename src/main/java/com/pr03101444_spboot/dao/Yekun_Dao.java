package com.pr03101444_spboot.dao;

import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;

public interface Yekun_Dao {
    
    String Redirect(Yekun_pojo yb, Arayis_pojo a);
    
    List<Yekun_pojo> getIqtisadireg(Main main);
    
    List<Yekun_pojo> getMulkiyyet();
    
    List<Yekun_pojo> getFnkod();
    
    int getListyek(Yekun_pojo yn, Yekun_pojo y);
    
    List<TableRow> baxis(Yekun_pojo yn, int status);
    
    Yekun_pojo GoHes(Yekun_pojo yn);
    
    int chap_pdf(Yekun_pojo yn, Yekun_pojo y);
}

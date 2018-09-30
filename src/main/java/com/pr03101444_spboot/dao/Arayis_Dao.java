package com.pr03101444_spboot.dao;

import com.pr03101444_spboot.impl.Arayis;
import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;


public interface Arayis_Dao {
    
    List<Arayis_pojo> listfeal(Yekun_pojo yn);
    
    List<Arayis_pojo> listfeal2(Yekun_pojo yn);
    
    List<Arayis_pojo> teqdimedenler(Arayis_pojo as);
    
    List<Arayis_pojo> muessise(Yekun_pojo yn);
    
    List<Arayis_pojo> sahibkar(Yekun_pojo yn);
    
    List<Arayis_pojo> pdf(Arayis_pojo as, Yekun_pojo yn, Main main);
    
    List<TableRow> teqdimeden_pdf(Arayis_pojo as);
}

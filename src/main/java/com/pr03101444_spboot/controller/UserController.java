package com.pr03101444_spboot.controller;

import com.pr03101444_spboot.dao.Arayis_Dao;
import com.pr03101444_spboot.dao.Hesabat_Dao;
import com.pr03101444_spboot.dao.Main_Dao;
import com.pr03101444_spboot.dao.Yekun_Dao;
import com.pr03101444_spboot.impl.Arayis;
import com.pr03101444_spboot.impl.Hesabat;
import com.pr03101444_spboot.impl.Main;
import com.pr03101444_spboot.impl.Yekun;
import com.pr03101444_spboot.object.Arayis_pojo;
import com.pr03101444_spboot.object.Katalog;
import com.pr03101444_spboot.object.TableRow;
import com.pr03101444_spboot.object.Yekun_pojo;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    Main_Dao md;
    @Autowired
    Hesabat_Dao hd;
    @Autowired
    Yekun_Dao yn;
    @Autowired
    Arayis_Dao as;
    
    private Main main;
    private Katalog kat;
    private TableRow tlr;
    private Yekun_pojo y;
    private List<TableRow> list;
    private String kod;
    private int status;
    

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String forLogin(Model model, @RequestParam(value = "key", required = false) String key, @RequestParam(value = "messages", required = false) String messages, String url, Katalog kat) {
        if (key == null) {
            return "redirect:http://localhost:8080/loginControl/?param=pr03101444_spboot";
        } else {
            if (!key.matches("^[0-9]{19,19}$")) {
                messages = "Sizin bu hesabatla işləmək səlahiyyətiniz yoxdur";
                return "rediect:message";
            }

            main = md.checkKey(key);      
            status = main.getStatus();

            if (status != 0 && status != 1 && status != 2 && status != 3) {
                messages = "Sizin bu hesabatla işləmək səlahiyyətiniz yoxdur";
                return "rediect:message";
            }

            switch (status) {
                case 0:
                    url = "redirect:/hesabat/*";
                    break;
                case 1:
                    url = "redirect:/rayonlar";
                    break;
                default:
                    url = "redirect:/yekun";
                    break;
            }

        }
        return url;
    }

    @RequestMapping(value = "/hesabat/{code}", method = RequestMethod.GET, produces = "text/plain;charset=UTF-8")
    public String HesabG(@ModelAttribute("ht")Hesabat ht,@PathVariable("code") String kod1, Model model,HttpServletRequest request, String message) {      
        if(status != 2){
        kod = kod1;
        }
        System.out.println("kodct = " + kod);
        hd.getSelectfactorycode(main, kod);
        kat = hd.Rows();
        ht.setTlr(hd.tabl(request));
        ht.setList(hd.listhes());
        model.addAttribute("kat", kat);
        model.addAttribute("message", message);
        model.addAttribute("status", status);
        return "hesabat";
    }
    
    @RequestMapping(value = "/hesabat", method = RequestMethod.POST, produces = "text/plain;charset=UTF-8")
    public String HesabP(@ModelAttribute("ht")Hesabat ht, Model model, HttpServletRequest request, String message){
        hd.getSelectfactorycode(main, kod);
        tlr = ht.getTlr();
        list = ht.getList();
        message = hd.getMessage(tlr, list);
        if(message.isEmpty()){
        hd.SaveHes(kat, tlr, list);
        message ="Hesabat qeydə alındı!";
        }
        model.addAttribute("kat", kat);
        model.addAttribute("message", message);
        model.addAttribute("status", status);
        return "hesabat";
    }

    @RequestMapping(value = "/hesabat/pdf", method = {RequestMethod.GET,RequestMethod.POST})
    public String pdf(Model model, HttpServletRequest request) {
        hd.getSelectfactorycode(main, kod);
        tlr = hd.tabl(request);
        kat = hd.Rows();
        list = hd.listhes();
        model.addAttribute("main", main);
        model.addAttribute("tlr", tlr);
        model.addAttribute("kat", kat);
        model.addAttribute("list", list);
        return "pdfView";
    }

    @RequestMapping(value = "/rayonlar", method = {RequestMethod.GET, RequestMethod.POST})
    public String Rayonlar(@ModelAttribute("ht") Hesabat ht, Model model, HttpServletRequest request) {
        String rayon = hd.rayonAdi(main);
        ht.setList2(hd.rayonlar(main));
        model.addAttribute("rayon", rayon);
        return "rayonlar";
    }
        
    @RequestMapping(value = "/yekun", method = {RequestMethod.GET,RequestMethod.POST})
    public String Yekun(@ModelAttribute("yb")Yekun_pojo yb, Arayis_pojo a, Model map) {
        String redirect = "";
        String errmes = "";
        String errmes2 = "";
        List<Yekun_pojo>iqtisadireg= yn.getIqtisadireg(main);
        List<Yekun_pojo>mulkiyyet= yn.getMulkiyyet();
        List<Yekun_pojo>fnkod = yn.getFnkod();
        if(yb.getSelectcode() != null && !yb.getSelectcode().equals("")){
            errmes2 = yn.GoHes(yb).getErrmes2();
        if(status == 2 && errmes2 != null){
            kod = yb.getSelectcode();
            redirect = errmes2;     
        }
        if(status == 2 && yn.GoHes(yb).getErrmes() != null && !yn.GoHes(yb).getErrmes().equals("")){
            errmes = yn.GoHes(yb).getErrmes();
            redirect = "yekun";     
        }
        }
        if(redirect.equals("")){
            redirect = yn.Redirect(yb, a);
            y = yb;
        }
        map.addAttribute("errmes2", errmes2);
        map.addAttribute("iqtisadireg", iqtisadireg);
        map.addAttribute("mulkiyyet", mulkiyyet);
        map.addAttribute("fnkod", fnkod);
        map.addAttribute("a", a);
        map.addAttribute("status", status);
        map.addAttribute("errmes", errmes);
        return redirect;
    }
    
    @RequestMapping(value = "/cedvel", method = {RequestMethod.GET, RequestMethod.POST})
    public String Cedvel(@ModelAttribute("yn")Yekun_pojo y, Model map, HttpServletRequest request) {
        int leng = yn.getListyek(y,this.y);
        String basliq = this.y.getBasliq();
        map.addAttribute("ced_uz", y.getCed_uz());
        map.addAttribute("list", y.getList());
        map.addAttribute("leng", leng);
        map.addAttribute("basliq", basliq);
        return "cedvel";
    }
    
    @RequestMapping(value = "/baxis", method = {RequestMethod.GET, RequestMethod.POST})
    public String Baxis(@ModelAttribute("yn")Yekun_pojo y, Model map, HttpServletRequest request) {
        List<Yekun_pojo>iqtisadireg= yn.getIqtisadireg(main);
        List<TableRow>baxis = yn.baxis(y, status);
        map.addAttribute("iqtisadireg", iqtisadireg);
        map.addAttribute("baxis", baxis);
        return "baxis";
    }
    
    @RequestMapping(value = "/arayisfeal1", method = {RequestMethod.GET, RequestMethod.POST})
    public String Arayisfeal1(@ModelAttribute("as")Arayis_pojo a, Model map, HttpServletRequest request) {
        List<Arayis_pojo>listfeal = as.listfeal(y);
        map.addAttribute("listfeal", listfeal);
        return "arayisfeal1";
    }
    
    @RequestMapping(value = "/arayisfeal2", method = {RequestMethod.GET, RequestMethod.POST})
    public String Arayisfeal2(@ModelAttribute("as")Arayis_pojo a, Model map) {
        List<Arayis_pojo>listfeal2 = as.listfeal2(y);
        map.addAttribute("listfeal2", listfeal2);
        return "arayisfeal2";
    }
    
    @RequestMapping(value = "/arayis_mues", method = {RequestMethod.GET, RequestMethod.POST})
    public String Arayis_mues(@ModelAttribute("as")Arayis_pojo a, Model map) {
        List<Arayis_pojo>muessise = as.muessise(y);
        map.addAttribute("muessise", muessise);
        return "arayis_mues";
    }
    
    @RequestMapping(value = "/arayis_sahibkar", method = {RequestMethod.GET, RequestMethod.POST})
    public String Arayis_sahibkar(@ModelAttribute("as")Arayis_pojo a, Model map) {
        List<Arayis_pojo>sahibkar = as.sahibkar(y);
        map.addAttribute("sahibkar", sahibkar);
        return "arayis_sahibkar";
    }
    
    @RequestMapping(value = "/teqdimeden", method = {RequestMethod.GET, RequestMethod.POST})
    public String Teqdimeden(@ModelAttribute("as")Arayis_pojo a, Model map) {
        List<Arayis_pojo>teqdimedenler = as.teqdimedenler(a);
        map.addAttribute("teqdimedenler", teqdimedenler);
        return "teqdimeden";
    }
    
    @RequestMapping(value = "/empty", method = {RequestMethod.GET, RequestMethod.POST})
    public String empty() {
        return "empty";
    }
    
    @RequestMapping(value = "/cedvel/xlsx", method = {RequestMethod.GET, RequestMethod.POST})
    public String Cedvel_Excel(Yekun_pojo y, Model map, HttpServletRequest request) {
        int leng = yn.getListyek(this.y,y);
        map.addAttribute("sut", y.getSut());
        map.addAttribute("list", y.getList());
        map.addAttribute("leng", leng);
        map.addAttribute("yn", y);
        map.addAttribute("y", this.y);
        return "cedvel_xlsx";
    }
    
    @RequestMapping(value = "/cedvel/pdf", method = {RequestMethod.GET, RequestMethod.POST})
    public String Cedvel_Pdf(Yekun_pojo y, Model map, HttpServletRequest request) {
        int leng = yn.getListyek(this.y,y);
        map.addAttribute("ced_uz", y.getCed_uz());
        map.addAttribute("list", y.getList());
        map.addAttribute("leng", leng);
        map.addAttribute("yn", y);
        map.addAttribute("y", this.y);
        return "cedvel_Pdf";
    }
    
    @RequestMapping(value = "/arayis/pdf", method = {RequestMethod.GET,RequestMethod.POST})
    public String arayisPdf(Model model, Arayis_pojo a) {
        List<Arayis_pojo>pdf=as.pdf(a, y, main);
        model.addAttribute("a", a);
        model.addAttribute("main", main);
        model.addAttribute("list", pdf);
        return "arayis_Pdf";
    }
    
    @RequestMapping(value = "/arayis/teqdimeden_pdf", method = {RequestMethod.GET,RequestMethod.POST})
    public String teqdimeden_pdf(Model model, Arayis_pojo a, HttpServletRequest request,@RequestParam(value = "kod1", required = false)String kod) {
        List<TableRow>pdf=as.teqdimeden_pdf(a);
        hd.getSelectfactorycode(main, kod);
        tlr = hd.tabl(request);
        System.out.println("adam =" + tlr.getAdamsaat());
        model.addAttribute("a", a);
        model.addAttribute("tlr", tlr);
        model.addAttribute("list", pdf);
        return "teqdimeden_Pdf";
    }
}

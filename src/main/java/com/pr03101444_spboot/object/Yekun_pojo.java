package com.pr03101444_spboot.object;

import java.util.List;


public class Yekun_pojo {
    
    private String selectfnkod;
    private String selectmulkiyyet;
    private String selectiqtisadireg;
    private String selectcode;
    private String value;
    private int val;
    private String zona;
    private String zona1;
    private String araziadi;
    private String seksiya;
    private String sahe;
    private String mulk;
    private String fn;
    private int nov;
    private String id;
    private String iqt;
    private String kod;
    private String sira;
    private String ad;
    private String kod2;
    private String kod3;
    private String feal;
    private String tab;
    private String sek;
    private String mn;
    private String mn2;
    private String ik;
    private String fk;
    private String basliq;
    private String ray;
    private int seh = 1;
    private String ced_uz;
    private int leng;
    private int leng2;
    private String ced[][];
    private String[] feal1;
    private int sut;
    private int setr;
    private String cedvel;
    private String errmes;
    private String errmes2;
    private Arayis_pojo a;
    private List<String>list;

    public String getSelectfnkod() {
        return selectfnkod;
    }

    public void setSelectfnkod(String selectfnkod) {
        this.selectfnkod = selectfnkod;
    }

    public String getSelectmulkiyyet() {
        return selectmulkiyyet;
    }

    public void setSelectmulkiyyet(String selectmulkiyyet) {
        this.selectmulkiyyet = selectmulkiyyet;
    }

    public String getSelectiqtisadireg() {
        return selectiqtisadireg;
    }

    public void setSelectiqtisadireg(String selectiqtisadireg) {
        this.selectiqtisadireg = selectiqtisadireg;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getZona1() {
        return zona1;
    }

    public void setZona1(String zona1) {
        this.zona1 = zona1;
    }

    public String getAraziadi() {
        return araziadi;
    }

    public void setAraziadi(String araziadi) {
        this.araziadi = araziadi;
    }

    public String getSeksiya() {
        return seksiya;
    }

    public void setSeksiya(String seksiya) {
        this.seksiya = seksiya;
    }

    public String getSahe() {
        return sahe;
    }

    public void setSahe(String sahe) {
        this.sahe = sahe;
    }

    public String getMulk() {
        return mulk;
    }

    public void setMulk(String mulk) {
        this.mulk = mulk;
    }

    public String getFn() {
        return fn;
    }

    public void setFn(String fn) {
        this.fn = fn;
    }

    public int getNov() {
        return nov;
    }

    public void setNov(int nov) {
        this.nov = nov;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIqt() {
        return iqt;
    }

    public void setIqt(String iqt) {
        this.iqt = iqt;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getSira() {
        return sira;
    }

    public void setSira(String sira) {
        this.sira = sira;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getKod2() {
        return kod2;
    }

    public void setKod2(String kod2) {
        this.kod2 = kod2;
    }

    public String getKod3() {
        return kod3;
    }

    public void setKod3(String kod3) {
        this.kod3 = kod3;
    }

    public String getFeal() {
        return feal;
    }

    public void setFeal(String feal) {
        this.feal = feal;
    }

    public String getTab() {
        return tab;
    }

    public void setTab(String tab) {
        this.tab = tab;
    }

    public String getSek() {
        return sek;
    }

    public void setSek(String sek) {
        this.sek = sek;
    }

    public String getMn() {
        return mn;
    }

    public void setMn(String mn) {
        this.mn = mn;
    }

    public String getMn2() {
        return mn2;
    }

    public void setMn2(String mn2) {
        this.mn2 = mn2;
    }

    public String getIk() {
        return ik;
    }

    public void setIk(String ik) {
        this.ik = ik;
    }

    public String getFk() {
        return fk;
    }

    public void setFk(String fk) {
        this.fk = fk;
    }

    public String getBasliq() {
        switch (value) {
            case "sut1":
                basliq = "Maddi əsas vəsaitlərin əldə edilməsinə çəkilmiş xərclər";
                break;
            case "sut2":
                basliq = "Ölkədə istehsal edilmiş yeni əsas vəsaitlər";
                break;
            case "sut3":
                basliq = "İdxal olunmuş yeni və istifadə edilmiş əsas vəsaitlər";
                break;
            case "sut4":
                basliq = "Əsas vəsaitlərin əsaslı təmiri üzrə kənar müəssisə və təşkilatların göstərdikləri xidmətlərin dəyəri";
                break;
            case "sut5":
                basliq = "Xarici ölkələrin hüquqi şəxslərinin göstərdikləri xidmətlər";
                break;
            case "sut6":
                basliq = "Silinmiş əsas vəsaitlərin bazar qiyməti";
                break;
            case "sut7":
                basliq = "Silinmiş əsas vəsaitlərin yaşı (tam rəqəmlə)";
        }
        if (selectmulkiyyet.equals("1") && !selectmulkiyyet.equals("0")) {
            basliq = basliq + " (Dövlət mülkiyyəti üzrə)";
        } else if (!selectmulkiyyet.equals("1") && !selectmulkiyyet.equals("0") && !selectmulkiyyet.equals("")) {
            basliq = basliq + " (Qeyri - dövlət mülkiyyəti üzrə)";
        } else {
            basliq = basliq + "";
        }
        return basliq;
    }

    public void setBasliq(String basliq) {
        this.basliq = basliq;
    }

    public String getRay() {
        return ray;
    }

    public void setRay(String ray) {
        this.ray = ray;
    }

    public int getSeh() {
        return seh;
    }

    public void setSeh(int seh) {
        this.seh = seh;
    }

    public String getCed_uz() {
        return ced_uz;
    }

    public void setCed_uz(String ced_uz) {
        this.ced_uz = ced_uz;
    }

    public int getLeng() {
        return leng;
    }

    public void setLeng(int leng) {
        this.leng = leng;
    }

    public int getLeng2() {
        return leng2;
    }

    public void setLeng2(int leng2) {
        this.leng2 = leng2;
    }

    public String[][] getCed() {
        return ced;
    }

    public void setCed(String[][] ced) {
        this.ced = ced;
    }

    public String[] getFeal1() {
        return feal1;
    }

    public void setFeal1(String[] feal1) {
        this.feal1 = feal1;
    }

    public int getSut() {
        return sut;
    }

    public void setSut(int sut) {
        this.sut = sut;
    }

    public int getSetr() {
        return setr;
    }

    public void setSetr(int setr) {
        this.setr = setr;
    }

    public String getCedvel() {
        return cedvel;
    }

    public void setCedvel(String cedvel) {
        this.cedvel = cedvel;
    }

    public String getErrmes() {
        return errmes;
    }

    public void setErrmes(String errmes) {
        this.errmes = errmes;
    }

    public String getErrmes2() {
        return errmes2;
    }

    public void setErrmes2(String errmes2) {
        this.errmes2 = errmes2;
    }

    public Arayis_pojo getA() {
        return a;
    }

    public void setA(Arayis_pojo a) {
        this.a = a;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getSelectcode() {
        return selectcode;
    }

    public void setSelectcode(String selectcode) {
        this.selectcode = selectcode;
    }
    
    
}

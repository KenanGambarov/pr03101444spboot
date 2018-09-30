package com.pr03101444_spboot.object;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class TableRow implements Serializable {

    public TableRow(String sira, String adi, String mehs_kodu, double sut1, double sut2, double sut3, double sut4, double sut5, double sut6, int sut7) {
        this.sira = sira;
        this.adi = adi;
        this.mehs_kodu = mehs_kodu;
        this.sut1 = sut1;
        this.sut2 = sut2;
        this.sut3 = sut3;
        this.sut4 = sut4;
        this.sut5 = sut5;
        this.sut6 = sut6;
        this.sut7 = sut7;
    }

    public TableRow() {

    }

    public String getSira_c() {
        return sira_c;
    }

    public void setSira_c(String sira_c) {
        this.sira_c = sira_c;
    }

    public double sut(int i) {
        switch (i) {
            case 1:
                return sut1;
            case 2:
                return sut2;
            case 3:
                return sut3;
            case 4:
                return sut4;
            case 5:
                return sut5;
            case 6:
                return sut6;
        }
        return 0;
    }

    public int getSay() {
        say++;
        return say;
    }

    public void setSay(int say) {
        this.say = say;
    }

    private String sira;
    private String adi;
    private String ad;
    private String mehs_kodu;
    private String adi_h;
    private String mehs_koduh1;
    private double sut1=0.0;
    private double sut2=0.0;
    private double sut3=0.0;
    private double sut4=0.0;
    private double sut5=0.0;
    private double sut6=0.0;
    private int sut7=0;
    private String selectmehsul;
    private List<TableRow> list;
    private List<TableRow> adkod;
    private String adamsaat;
    private String ip;
    private String kod;
    private String kod_c;
    private String kod_b;
    private String nomre;
    private int say = 0;
    private List<TableRow> mehsul34;
    private String sira_h;
    private String sira_v = "36";
    private String sira_c;
    private String date;
    private String time;
    private TableRow row;


    public String getSira() {
        return sira;
    }

    public void setSira(String sira) {
        this.sira = sira;
    }

    public String getAdi() {
        return adi;
    }

    public void setAdi(String adi) {
        this.adi = adi;
    }

    public String getMehs_kodu() {
        return mehs_kodu;
    }

    public void setMehs_kodu(String mehs_kodu) {
        this.mehs_kodu = mehs_kodu;
    }

    public String getAdi_h() {
        return adi_h;
    }

    public String getNomre() {
        return nomre;
    }

    public void setNomre(String nomre) {
        this.nomre = nomre;
    }

    public void setAdi_h(String adi_h) {
        this.adi_h = adi_h;
    }

    public double getSut1() {
//        System.out.println("sut1 = " + sut1);
        return sut1;
    }

    public void setSut1(double sut1) {
        this.sut1 = sut1;
    }

    public double getSut2() {
        return sut2;
    }

    public void setSut2(double sut2) {
        this.sut2 = sut2;
    }

    public double getSut3() {
        return sut3;
    }

    public void setSut3(double sut3) {
        this.sut3 = sut3;
    }

    public double getSut4() {
        return sut4;
    }

    public void setSut4(double sut4) {
        this.sut4 = sut4;
    }

    public double getSut5() {
        return sut5;
    }

    public void setSut5(double sut5) {
        this.sut5 = sut5;
    }

    public double getSut6() {
        return sut6;
    }

    public void setSut6(double sut6) {
        this.sut6 = sut6;
    }

    public int getSut7() {
        return sut7;
    }

    public void setSut7(int sut7) {
        this.sut7 = sut7;
    }

    public String getAdamsaat() {
        return adamsaat;
    }

    public void setAdamsaat(String adamsaat) {
        this.adamsaat = adamsaat;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSelectmehsul() {
        return selectmehsul;
    }

    public void setSelectmehsul(String selectmehsul) {
        this.selectmehsul = selectmehsul;
    }

    public List<TableRow> getList() {
        return list;
    }

    public List<TableRow> getAdkod() {
        return adkod;
    }

    public String getKod() {
        return kod;
    }

    public void setKod(String kod) {
        this.kod = kod;
    }

    public String getKod_c() {
        return kod_c;
    }

    public void setKod_c(String kod_c) {
        this.kod_c = kod_c;
    }

    public String getKod_b() {
        return kod_b;
    }

    public void setKod_b(String kod_b) {
        this.kod_b = kod_b;
    }

    public String getSira_v() {
        return sira_v;
    }

    public void setSira_v(String sira_v) {
        this.sira_v = sira_v;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMehs_koduh1() {
        return mehs_koduh1;
    }

    public void setMehs_koduh1(String mehs_koduh1) {
        this.mehs_koduh1 = mehs_koduh1;
    }

    public List<TableRow> getMehsul34() {
        return mehsul34;
    }

    public void setMehsul34(List<TableRow> mehsul34) {
        this.mehsul34 = mehsul34;
    }

    public String getSira_h() {
        return sira_h;
    }

    public void setSira_h(String sira_h) {
        this.sira_h = sira_h;
    }


    public TableRow getRow() {
        return row;
    }

    public void setRow(TableRow row) {
        this.row = row;
    }

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

}

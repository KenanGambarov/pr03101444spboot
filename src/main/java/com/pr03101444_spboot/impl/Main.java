package com.pr03101444_spboot.impl;

import java.sql.ResultSet;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import com.pr03101444_spboot.dao.Main_Dao;
import java.sql.SQLException;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class Main implements Main_Dao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Main checkKey(String key){
            
            String SQL = "SELECT login, areacode, status, uip FROM tesnifat.accesskey WHERE rkey=? ";

               RowMapper<Main>mapper = (ResultSet rs, int i) -> {
                   main = new Main();
                   main.setLogin(rs.getString("login"));
                   main.setAreacode(rs.getString("areacode"));
                   main.setStatus(rs.getInt("status"));
                   main.setIp(rs.getString("uip"));
                   
                   return main;
        };

            return jdbcTemplate.queryForObject(SQL, new Object[]{key}, mapper);
    }
    
    private int status;
    private String selectfactorycode;
    private String login;
    private String areacode;
    private String ip;
    private Main main;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getSelectfactorycode() {
        return selectfactorycode;
    }

    public void setSelectfactorycode(String selectfactorycode) {
        this.selectfactorycode = selectfactorycode;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAreacode() {
        return areacode;
    }

    public void setAreacode(String areacode) {
        this.areacode = areacode;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

}

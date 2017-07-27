package com.example.groovydemo.sql;

import java.util.List;
import java.util.Map;

/**
 * Description：
 * Created on 2017/7/25
 * Author : 萧
 */
public class SqlDataBean {

    public static final String PRIMARYKEY = "PRIMARYKEY";

    public static final String FILEDTYPE = "type";
    public static final String FILEDTABLE = "table";

    public static final String FILED_SQL = "fileds_insql";

    public static final String FILED_JAVABEAN = "fileds_javabean";

    public String table;

    public List<Map> fileds;

    public SqlDataBean(String table, List<Map> fileds) {
        this.table = table;
        this.fileds = fileds;
    }

    public String getTable() {
        return table;
    }

    /**
     * @param table
     */
    public void setTable(String table) {
        this.table = table;
    }

    public List<Map> getFileds() {
        return fileds;
    }

    public void setFileds(List<Map> fileds) {
        this.fileds = fileds;
    }

    public SqlDataBean() {
    }

    @Override
    public String toString() {
        return "SqlDataBean{" +
                "table='" + table + '\'' +
                ", fileds=" + fileds +
                '}';
    }
}

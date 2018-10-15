package com.cssc.eos.entity;


import java.util.List;
import java.util.Map;

public class TableRow {

    private List<Map<String, Object>> rows;

    private Boolean more;

    public TableRow(){

    }

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }

    public Boolean getMore() {
        return more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }
}

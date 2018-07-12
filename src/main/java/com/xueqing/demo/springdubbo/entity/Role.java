package com.xueqing.demo.springdubbo.entity;

import java.io.Serializable;

public class Role  implements Serializable {
    private String rid;
    private String rname;

    public String getRid() {
        return rid;
    }

    public void setRid(String rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }
}

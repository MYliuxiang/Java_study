package com.lx.reume.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lx.reume.bean.base.BaseBean;
import com.lx.reume.bean.base.DateBean;

public class Education extends DateBean {

    private String name;
    private String intro;
    private Integer type;


    @JsonIgnore
    public  String getTypeString(){
        switch (type){
            case 1: return "小学";
            case 2: return "初中";
            case 3: return "高中";
            case 4: return "中专";
            case 5: return "大专";
            case 6: return "本科";
            case 7: return "硕士";
            case 8: return "博士";
            default:
                return "其他";
        }
    }
    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

package com.lx.reume.bean;

import com.lx.reume.bean.base.BaseBean;

public class Award extends BaseBean {

    private String name;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    private String image;
    private String intro;


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



}

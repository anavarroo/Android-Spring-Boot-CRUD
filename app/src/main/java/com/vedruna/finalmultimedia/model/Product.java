package com.vedruna.finalmultimedia.model;

public class Product {


    private Long id;
    private String productname;
    private String desc;
    private String img;



    public Product(){

    }
    public Product(Long id, String productname, String desc, String img) {
        this.id = id;
        this.productname = productname;
        this.desc = desc;
        this.img = img;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }


    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return
                "id=" + getId() +
                        ", Productname='" + getProductname() + '\'' +
                        ", desc='" + getDesc() + '\''

                ;
    }
}

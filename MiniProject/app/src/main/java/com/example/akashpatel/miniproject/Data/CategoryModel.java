package com.example.akashpatel.miniproject.Data;

public class CategoryModel {
    private String id;
    private String cat_name;
    private String cat_image;

    public CategoryModel(String id, String cat_name, String cat_image) {
        this.id = id;
        this.cat_name = cat_name;
        this.cat_image = cat_image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCat_name() { return cat_name; }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public String getCat_image() {
        return cat_image;
    }

    public void setCat_image(String cat_image) {
        this.cat_image = cat_image;
    }
}

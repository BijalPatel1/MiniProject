package com.example.akashpatel.miniproject.Data;

import java.util.List;

public class Category_List_Model {
    private List<CategoryModel> data=null;

    public Category_List_Model(List<CategoryModel> data) {
        this.data = data;
    }

    public List<CategoryModel> getData() {
        return data;
    }

    public void setData(List<CategoryModel> data) {
        this.data = data;
    }
}

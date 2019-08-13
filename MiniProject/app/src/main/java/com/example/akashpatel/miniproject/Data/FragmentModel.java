package com.example.akashpatel.miniproject.Data;

public class FragmentModel {
    private String id;
    private String images;

    public FragmentModel(String id, String images) {
        this.id = id;
        this.images = images;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }
}

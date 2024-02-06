package fr.eilco.booksprojects.data;


import android.graphics.Bitmap;

import java.util.HashMap;

public final class ImageData {
    private static ImageData instance;

    private HashMap<String, Bitmap> imageMap;
    private ImageData() {imageMap = new HashMap<>();};

    public static ImageData getInstance() {
        if (instance == null) {
            instance = new ImageData();
        }
        return instance;
    }

    public Bitmap getImage(String isbn){
        return imageMap.get(isbn);
    }

    public void addImage(String isbn, Bitmap image){
        imageMap.put(isbn, image);
    }

    public void deleteImage(String isbn){
        imageMap.remove(isbn);
    }

    public void clearImages() {
        imageMap.clear();
    }
}

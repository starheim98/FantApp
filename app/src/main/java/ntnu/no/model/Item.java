package ntnu.no.model;

import java.util.List;

public class Item {
    private List<Photo> photos;
    private String title;
    private String price;
    private String description;

    public Item(List<Photo> photos, String title, String price, String description) {
        this.photos = photos;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Item(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public void addPhoto(Photo photo){
        this.photos.add(photo);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

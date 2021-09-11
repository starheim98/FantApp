package ntnu.no;

import android.media.Image;

public class Item {
    private Image image;
    private String title;
    private String price;
    private String description;

    public Item(Image image, String title, String price, String description) {
        this.image = image;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Item(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

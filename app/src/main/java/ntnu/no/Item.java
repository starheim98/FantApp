package ntnu.no;

import java.util.List;

public class Item {
    private String imgUrl;
    private String title;
    private String price;
    private String description;

    public Item(String imgUrl, String title, String price, String description) {
        this.imgUrl = imgUrl;
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public Item(String title, String price, String description) {
        this.title = title;
        this.price = price;
        this.description = description;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
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

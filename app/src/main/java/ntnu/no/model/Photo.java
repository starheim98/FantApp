package ntnu.no.model;

public class Photo {
    private String subPath;

    public Photo(String subPath) {
        this.subPath = subPath;
    }

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }
}

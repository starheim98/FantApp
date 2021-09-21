package ntnu.no.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Photo implements Parcelable {
    private String subPath;

    public Photo(String subPath) {
        this.subPath = subPath;
    }

    protected Photo(Parcel in) {
        subPath = in.readString();
    }

    public static final Creator<Photo> CREATOR = new Creator<Photo>() {
        @Override
        public Photo createFromParcel(Parcel in) {
            return new Photo(in);
        }

        @Override
        public Photo[] newArray(int size) {
            return new Photo[size];
        }
    };

    public String getSubPath() {
        return subPath;
    }

    public void setSubPath(String subPath) {
        this.subPath = subPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(subPath);
    }
}

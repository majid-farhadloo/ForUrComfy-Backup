package LayOutReturn;

import android.os.Parcel;
import android.os.Parcelable;

public class DeliveryItem implements Parcelable {

    private String message;
    private int cell;
    private String location;
    private String name;
    private String id;

    /**
     * No args constructor for use in serialization
     *
     */
    public DeliveryItem() {
    }

    /**
     *
     * @param id
     * @param message
     * @param location
     * @param name
     * @param cell
     */
    public DeliveryItem(String message, int cell, String location, String name, String id) {
        super();
        this.message = message;
        this.cell = cell;
        this.location = location;
        this.name = name;
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCell() {
        return cell;
    }

    public void setCell(int cell) {
        this.cell = cell;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "DeliveryItem{" +
                "message='" + message + '\'' +
                ", cell=" + cell +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.message);
        dest.writeInt(this.cell);
        dest.writeString(this.location);
        dest.writeString(this.name);
        dest.writeString(this.id);
    }

    protected DeliveryItem(Parcel in) {
        this.message = in.readString();
        this.cell = in.readInt();
        this.location = in.readString();
        this.name = in.readString();
        this.id = in.readString();
    }

    public static final Parcelable.Creator<DeliveryItem> CREATOR = new Parcelable.Creator<DeliveryItem>() {
        @Override
        public DeliveryItem createFromParcel(Parcel source) {
            return new DeliveryItem(source);
        }

        @Override
        public DeliveryItem[] newArray(int size) {
            return new DeliveryItem[size];
        }
    };
}
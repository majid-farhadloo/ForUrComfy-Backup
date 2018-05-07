package LayOutReturn;

/**
 * Created by farha on 5/6/2018.
 */

public class DeliveryItem
{
    String cell;
    String location;
    String name;
    String id;
    int quantity;

    public DeliveryItem() {

    }
    public DeliveryItem(String cell, String location, String name, String id, int quantity) {
        this.cell = cell;
        this.location = location;
        this.name = name;
        this.id = id;
        this.quantity = quantity;
    }

    public String getCell() {
        return cell;
    }

    public void setCell(String cell) {
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

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "DeliveryItem{" +
                "cell='" + cell + '\'' +
                ", location='" + location + '\'' +
                ", name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", quantity=" + quantity +
                '}';
    }
}

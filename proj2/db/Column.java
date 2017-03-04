package db;

/**
 * Created by nithin on 2/24/17.
 */
public class Column {
    String name;
    Data type;

    public Column(String name, Data type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Column)) {
            return false;
        }
        if (this.name.equals(((Column) o).name)) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return type.hashCode() + name.hashCode();
    }

    public String toString() {

        return this.name;
    }

}

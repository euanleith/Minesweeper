public class Square extends Tile {
    private int value;

    Square() {
        type = TileType.square;
    }

    void setValue(int value) {
        this.value = value;
    }

    int getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (mark) return "M";
        if (isVisible()) {
            if (value == 0) return " ";
            return Integer.toString(value);
        }
        return "-";
    }
}

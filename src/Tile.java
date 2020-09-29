public class Tile {
    private boolean visible;
    TileType type;
    boolean mark;

    enum TileType {
        square,
        mine;
    }

    boolean isVisible() {
        return visible;
    }

    void setVisible() {
        visible = true;
    }

    public String toString() {
        return "T";
    }

    TileType getType() {
        return type;
    }

    void setMark(boolean mark) {
        this.mark = mark;
    }

    boolean getMark() {
        return mark;
    }
}

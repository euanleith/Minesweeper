public class Mine extends Tile {

    Mine() {
        type = TileType.mine;
    }

    @Override
    public String toString() {
        if (mark) return "M";
        if (isVisible()) return Main.ANSI_RED + "M" + Main.ANSI_RESET;
        return "-";
    }
}

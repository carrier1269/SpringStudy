public enum EnumTest {
    EAST(1, ">"), SOUTH(2, "V"), WEST(3, "<"), NORTH(4, "^");

    private static final EnumTest[] Array = EnumTest.values();
    private final int value;
    private final String symbol;

    EnumTest(int value, String Symbol){
        this.value = value;
        this.symbol = Symbol;
    }

    public int getValue(){ return value; }
    public String getSymbol(){ return symbol; }

    public static EnumTest of(int dir){
        if (dir < 1 || dir >4 )
            throw new IllegalArgumentException("Invalid value :" + dir);

        return Array[dir - 1];
    }
}

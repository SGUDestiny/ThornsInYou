package destiny.thornsinyou.client.recipebook;

public enum CopperKettleRecipeBookTab {
    TEA("tea"),
    MISC("misc");

    public final String name;

    CopperKettleRecipeBookTab(String name) {
        this.name = name;
    }

    public static CopperKettleRecipeBookTab findByName(String name) {
        for (CopperKettleRecipeBookTab value : values()) {
            if (value.name.equals(name)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

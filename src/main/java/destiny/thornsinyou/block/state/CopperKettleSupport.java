package destiny.thornsinyou.block.state;

import net.minecraft.util.StringRepresentable;

public enum CopperKettleSupport implements StringRepresentable
{
    NONE("none"),
    TRAY("tray"),
    HANDLE("handle");

    private final String supportName;

    CopperKettleSupport(String name) {
        this.supportName = name;
    }

    @Override
    public String toString() {
        return this.getSerializedName();
    }

    @Override
    public String getSerializedName() {
        return this.supportName;
    }
}
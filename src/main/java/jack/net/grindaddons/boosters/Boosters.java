package jack.net.grindaddons.boosters;

import jack.net.grindaddons.utilities.CC;

public enum Boosters {

    DOUBLE_EXP(CC.translate("&f&l** &6&lDOUBLE EXP &f&l**")),
    X2MULTIPLIER(CC.translate("&7&l** &3&l2X MULTIPLIER &7&l**")),
    X3MULTIPLIER(CC.translate("&8&l** &e&l3X MULTIPLIER &8&l**"));

    private final String displayName;

    Boosters(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

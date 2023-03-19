package jack.net.grindaddons.boosters.itembuilder;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.boosters.Boosters;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    private final Core core;

    private final NamespacedKey doubleXpKey;
    private final NamespacedKey x2MultiplierKey;
    private final NamespacedKey x3MultiplierKey;


    public Items(Core core) {
        this.core = core;


        this.doubleXpKey = new NamespacedKey(core, "doublexp");
        this.x2MultiplierKey = new NamespacedKey(core, "x2multiplier");
        this.x3MultiplierKey = new NamespacedKey(core, "x3multiplier");
    }

    public ItemStack getDoubleXpItem() {
        return new ItemBuild(Material.REDSTONE_TORCH, 1).setDisplayName(Boosters.DOUBLE_EXP.getDisplayName()).addKey(doubleXpKey, "doublexp").build();
    }

    public void giveDoubleXpItem(Player player) {
        player.getInventory().addItem(getDoubleXpItem());
    }

    public ItemStack getX2MultiplierItem() {
        return new ItemBuild(Material.NETHER_STAR, 1).setDisplayName(Boosters.X2MULTIPLIER.getDisplayName()).addKey(x2MultiplierKey, "x2multiplier").build();
    }

    public void giveX2Multiplier(Player player) {
        player.getInventory().addItem(getX2MultiplierItem());
    }

    public ItemStack getX3MultiplierItem() {
        return new ItemBuild(Material.GHAST_TEAR, 1).setDisplayName(Boosters.X3MULTIPLIER.getDisplayName()).addKey(x3MultiplierKey, "x3multiplier").build();
    }

    public void giveX3Multiplier(Player player) {
        player.getInventory().addItem(getX3MultiplierItem());
    }
}

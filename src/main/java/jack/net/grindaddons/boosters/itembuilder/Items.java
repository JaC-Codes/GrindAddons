package jack.net.grindaddons.boosters.itembuilder;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.boosters.Boosters;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class Items {

    private final Core core;

    private final NamespacedKey doubleXpKey;
    private final NamespacedKey x2MultiplierKey;
    private final NamespacedKey x3MultiplierKey;
    private final NamespacedKey mobSwordKey;


    public Items(Core core) {
        this.core = core;


        this.doubleXpKey = new NamespacedKey(core, "doublexp");
        this.x2MultiplierKey = new NamespacedKey(core, "x2multiplier");
        this.x3MultiplierKey = new NamespacedKey(core, "x3multiplier");
        this.mobSwordKey = new NamespacedKey(core, "mobsword");
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

    public ItemStack getMobSwordItem(int amount) {
        String itemCheck = this.core.getMobSwordConfiguration().getString("Mobsword.Item.material");
        if (itemCheck == null) {
            System.out.println("Can't find config path for mob sword material");
        }
        Material material = Material.matchMaterial(itemCheck);
        if (material == null) {
            System.out.println("Can't match material");
        }
        return new ItemBuild((material), amount)
                .setDisplayName(this.core.getMobSwordConfiguration().getString("Mobsword.Item.display-name"))
                .addKey(mobSwordKey, "mobsword").setLore(String.valueOf(this.core.getMobSwordConfiguration().getStringList("Mobsword.Item.lore")))
                .setItemGlowing()
                .setUnsafeEnchant(Enchantment.FIRE_ASPECT, 1)
                .build();
    }

    public void giveMobSword(Player player, int amount) {
        player.getInventory().addItem(getMobSwordItem(amount));
    }
}

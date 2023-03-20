package jack.net.grindaddons.boosters.listeners;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.utilities.CC;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class MultipliersListener implements Listener {

    private final Core core;

    private final NamespacedKey x2MultiplierKey;
    private final NamespacedKey x3MultiplierKey;

    public MultipliersListener(Core core) {
        this.core = core;

        this.x2MultiplierKey = new NamespacedKey(core, "x2multiplier");
        this.x3MultiplierKey = new NamespacedKey(core, "x3multiplier");
    }

    /*

                    Need to add timers for both - 30 minutes



     */

    @EventHandler
    public void onItemPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!block.getType().equals(Material.REDSTONE_TORCH)) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (meta.getPersistentDataContainer().has(x2MultiplierKey, PersistentDataType.STRING) ||
                meta.getPersistentDataContainer().has(x3MultiplierKey, PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        boolean action = event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || (event.getAction().equals(Action.RIGHT_CLICK_AIR));
        if (!item.hasItemMeta()) return;
        ItemMeta meta = item.getItemMeta();
        if (!action) return;
        if (meta.getPersistentDataContainer().has(x2MultiplierKey, PersistentDataType.STRING)) {
            player.sendMessage(CC.translate("&e2x Multiplier Activated"));
            if (item.getAmount() >= 1) {
                item.setAmount(item.getAmount() - 1);
            }
        } else if (meta.getPersistentDataContainer().has(x3MultiplierKey, PersistentDataType.STRING)) {
            player.sendMessage(CC.translate("&b3x Multiplier Activated"));
            if (item.getAmount() >= 1) {
                item.setAmount(item.getAmount() - 1);
            }
        }
    }
}

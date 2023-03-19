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
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class DoubleXpListener implements Listener {

    private final Core core;

    private final NamespacedKey doublexp;
    private final NamespacedKey playerDxp;

    public DoubleXpListener(Core core) {
        this.core = core;

        this.doublexp = new NamespacedKey(core, "doublexp");
        this.playerDxp = new NamespacedKey(core, "playerdxp");
    }


    /*
                Need to add a timer to doublexp and add updating actionbar with timer
     */

    @EventHandler
    public void onItemPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        Block block = event.getBlock();
        if (!block.getType().equals(Material.REDSTONE_TORCH)) return;
        ItemStack item = player.getInventory().getItemInMainHand();
        if (!item.hasItemMeta()) return;
        if (item.getItemMeta().getPersistentDataContainer().has(doublexp, PersistentDataType.STRING)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        boolean action = event.getAction().equals(Action.RIGHT_CLICK_BLOCK) || (event.getAction().equals(Action.RIGHT_CLICK_AIR));
        if (!item.hasItemMeta()) return;
        if (!item.getItemMeta().getPersistentDataContainer().has(doublexp, PersistentDataType.STRING)) return;
        if (action) {
            PersistentDataContainer container = player.getPersistentDataContainer();
            container.set(playerDxp, PersistentDataType.STRING, "playerdxp");
            player.sendMessage(CC.translate("&7DoubleXP Activated"));
            if (item.getAmount() >= 1) {
                item.setAmount(item.getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void xpChangeEvent(PlayerExpChangeEvent event) {
        Player player = event.getPlayer();
        if (player.getPersistentDataContainer().has(playerDxp, PersistentDataType.STRING)) {
            int doubleXp = event.getAmount() * 2;
            event.setAmount(doubleXp);
        }
    }
}

package jack.net.grindaddons.sugarland;

import jack.net.grindaddons.Core;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class WorldInitializer {

    private final Core core;

    private boolean worldBoolean = false;

    public WorldInitializer(Core core) {
        this.core = core;
    }

    public void worldExists() {
        String confCheck = this.core.getSugarLandConfiguration().getString("World.");
        if (confCheck == null) {
            System.out.println("Can't find world name");
        }
        World world = Bukkit.getWorld(confCheck);
        worldBoolean = true;

    }

    public Boolean isSugarLandWorld(Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (worldBoolean) {
                    //Do something

                }
            }
        }.runTaskTimer(core, 1200L, 1200L);
        return null;
    }

    public boolean isWorldBoolean() {
        return worldBoolean;
    }

    public void setWorldBoolean(boolean worldBoolean) {
        this.worldBoolean = worldBoolean;
    }
}

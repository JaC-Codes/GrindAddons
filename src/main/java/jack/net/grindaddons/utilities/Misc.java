package jack.net.grindaddons.utilities;

import jack.net.grindaddons.Core;
import org.bukkit.entity.Player;

public class Misc {

    private final Core core;

    public Misc(Core core) {
        this.core = core;
    }

    public void usage(Player player) {
        for (final String m : this.core.getConfig().getStringList("Usage")) {
            player.sendMessage(CC.translate(m));
        }
    }
}

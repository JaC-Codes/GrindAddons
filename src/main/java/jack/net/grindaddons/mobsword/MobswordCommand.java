package jack.net.grindaddons.mobsword;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.boosters.itembuilder.Items;
import jack.net.grindaddons.commandmanager.SubCommand;
import jack.net.grindaddons.utilities.Misc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class MobswordCommand extends SubCommand {

    private final Core core;
    private final Items item;
    private final Misc misc;

    public MobswordCommand(Core core) {
        this.core = core;
        this.item = new Items(core);
        this.misc = new Misc(core);
    }


    @Override
    public String getName() {
        return "mobsword";
    }

    // grindaddons mobsword give <player> <amount>  3 args, 4 length

    @Override
    public void perform(Player player, String[] args) {
        if (args.length < 4) {
            misc.usage(player);
        }
        if (!args[1].equalsIgnoreCase("give")) return;
        Player target = Bukkit.getPlayerExact(args[2]);
        if (target == null) return;
        int amount = Integer.parseInt(args[3]);
        item.giveMobSword(player, amount);
    }
}

package jack.net.grindaddons.boosters.commands;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.boosters.Boosters;
import jack.net.grindaddons.boosters.commandmanager.SubCommand;
import jack.net.grindaddons.boosters.itembuilder.Items;
import jack.net.grindaddons.utilities.CC;
import jack.net.grindaddons.utilities.Misc;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class BoosterCommands extends SubCommand {

    private final Core core;
    private final Items items;
    private final Misc misc;

    public BoosterCommands(Core core) {
        this.core = core;
        this.misc = new Misc(core);
        this.items = new Items(core);
    }

    @Override
    public String getName() {
        return "give";
    }

    // Boosters give <player> <booster> <amount>

    @Override
    public void perform(Player player, String[] args) {
        System.out.println("");
        String confCheck = this.core.getConfig().getString("Give-Booster-Permission");
        if (confCheck == null) {
            System.out.println("Can't find 'Give-Booster-Permission'");
        }
        if (!player.hasPermission(confCheck)) {
            player.sendMessage("&cYou do not have permission to use this command.");
        }

        argsCheck(player, args);

        if (args.length == 4) {
            Player target = Bukkit.getPlayerExact(args[1]);
            int amount = Integer.parseInt(args[3]);


            switch (args[2]) {
                case "doublexp" -> {
                    argsCheck(player, args);
                    if (target.getPlayer() == null) {
                        player.sendMessage(CC.translate("&cPlayer is not online"));
                    }
                    items.giveDoubleXpItem(target);
                    player.sendMessage(CC.translate("&7You have received &6%amount% %item-name%")
                            .replace("%amount%", String.valueOf(amount))
                            .replace("%item-name", Boosters.DOUBLE_EXP.getDisplayName()));
                }
                case "2xmultiplier" -> {
                    argsCheck(player, args);
                    if (target.getPlayer() == null) {
                        player.sendMessage(CC.translate("&cPlayer is not online"));
                    }
                    items.giveX2Multiplier(target);
                    player.sendMessage(CC.translate("&eYou have received &6%amount% %item-name%")
                            .replace("%amount%", String.valueOf(amount))
                            .replace("%item-name", Boosters.X2MULTIPLIER.getDisplayName()));
                }
                case "3xmultiplier" -> {
                    argsCheck(player, args);
                    if (target.getPlayer() == null) {
                        player.sendMessage(CC.translate("&cPlayer is not online"));
                    }
                    items.giveX3Multiplier(player);
                    player.sendMessage(CC.translate("&eYou have received &6%amount% %item-name%")
                            .replace("%amount%", String.valueOf(amount))
                            .replace("%item-name", Boosters.X3MULTIPLIER.getDisplayName()));
                }
            }
        }
    }


    private void argsCheck(Player player, String[] args) {
        if (args.length < 4) {
            misc.usage(player);
        }
    }
}

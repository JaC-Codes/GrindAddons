package jack.net.grindaddons.commandmanager;

import jack.net.grindaddons.Core;
import jack.net.grindaddons.boosters.commands.BoosterCommands;
import jack.net.grindaddons.utilities.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CommandManager implements CommandExecutor {

    private final Core core;

    private final ArrayList<SubCommand> subCommands = new ArrayList<>();

    public CommandManager(Core core) {
        this.core = core;

        subCommands.add(new BoosterCommands(core));
    }



    @Override
    @SuppressWarnings("ALL")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length > 0) {
                for (int i = 0; i < getSubcommands().size(); i++) {
                    if (args[0].equalsIgnoreCase(getSubcommands().get(i).getName())) {
                        getSubcommands().get(i).perform(player, args);
                    }
                }
            } else if (args.length == 0) {
                for (final String i : this.core.getConfig().getStringList("Usage")) {
                    player.sendMessage(CC.translate(i));
                }
            }

        }


        return true;
    }

    public ArrayList<SubCommand> getSubcommands() {
        return subCommands;
    }
}

package jack.net.grindaddons;

import jack.net.grindaddons.commandmanager.CommandManager;
import jack.net.grindaddons.boosters.listeners.DoubleXpListener;
import jack.net.grindaddons.utilities.Config;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

@Getter
public class Core extends JavaPlugin {

    private Core instance;

    private final File sugarLandSettings = new File(getDataFolder(), "sugarland.yml");
    private final File mobSwordSettings = new File(getDataFolder(), "mobsword.yml");

    private final FileConfiguration sugarLandConfiguration = YamlConfiguration.loadConfiguration(sugarLandSettings);
    private final FileConfiguration mobSwordConfiguration = YamlConfiguration.loadConfiguration(mobSwordSettings);

    public void onEnable() {
        instance = this;
        this.Config();

        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + " §6=== ENABLE START ===");
        Bukkit.getConsoleSender().sendMessage(prefix + " §fLoading §6Listeners");
        registerEvents();
        Bukkit.getConsoleSender().sendMessage(prefix + " §fLoading §eCommands");
        registerCommands();
        Bukkit.getConsoleSender().sendMessage(prefix + " §eMade by §6Jack");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== ENABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) ===");

    }

    private void registerCommands() {
        if (getCommand("grindaddons") == null) {
            System.out.println("Can't find main command");
        }
        getCommand("grindaddons").setExecutor(new CommandManager(this));
    }

    private void registerEvents() {
        PluginManager manager = Bukkit.getPluginManager();
        manager.registerEvents(new DoubleXpListener(this), this);
    }

    private void Config() {
        getConfig().options().copyDefaults();
        saveDefaultConfig();
        new Config(sugarLandSettings, sugarLandConfiguration, "sugarland.yml", this);
        new Config(mobSwordSettings, mobSwordConfiguration, "mobsword.yml", this);
    }

    public void onDisable() {
        instance = null;


        this.Config();
        long duration = System.currentTimeMillis();
        String prefix = "§3[" + getDescription().getName() + " " + getDescription().getVersion() + "] ";
        Bukkit.getConsoleSender().sendMessage(prefix + "§6=== DISABLING ===");
        Bukkit.getConsoleSender().sendMessage(prefix + "§fDisabling §6Listeners");
        Bukkit.getConsoleSender().sendMessage(prefix + "§fDisabling §eCommands");
        Bukkit.getConsoleSender().sendMessage(prefix + "§eMade by §6Jack");
        Bukkit.getConsoleSender().sendMessage(
                prefix + "§6=== DISABLE §aCOMPLETE §6(Took §d" + (System.currentTimeMillis() - duration) + "ms§6) =");
    }
}



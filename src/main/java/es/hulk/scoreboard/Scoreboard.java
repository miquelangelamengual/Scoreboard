package es.hulk.scoreboard;

import es.hulk.scoreboard.command.ReloadCommand;
import es.hulk.scoreboard.command.ScoreboardCommand;
import es.hulk.scoreboard.providers.ScoreboardProvider;
import es.hulk.scoreboard.utils.FileConfig;
import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.command.CommandFramework;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public class Scoreboard extends JavaPlugin {

    private CommandFramework commandFramework;
    private static Scoreboard instance;

    private es.hulk.scoreboard.utils.scoreboard.Scoreboard scoreboard;
    private FileConfig mainConfig;
    private boolean isPlaceholderAPI = false;

    @Override
    public void onEnable() {
        commandFramework = new CommandFramework(this);
        instance = this;

        Utils.sendConsole("&8[&bScoreboard&8]");
        Utils.sendConsole("&8[&bScoreboard&8] &aLoading plugin...");
        Utils.sendConsole("&8[&bScoreboard&8]");

        mainConfig = new FileConfig(this, "config.yml");
        Utils.sendConsole("&8[&bScoreboard&8] &aConfig loaded");

        if (Bukkit.getPluginManager().getPlugin("PlaceholderAPI") != null) {
            this.isPlaceholderAPI = true;
            Utils.sendConsole("&8[&bScoreboard&8] &aHooked into PlaceholderAPI");
        }

        if (mainConfig.getBoolean("SCOREBOARD.ENABLED")) {
            this.scoreboard = new es.hulk.scoreboard.utils.scoreboard.Scoreboard(this, new ScoreboardProvider());
            scoreboard.setTicks(2);
            Utils.sendConsole("&8[&bScoreboard&8] &aScoreboard Enabled");
        }
        
        commandFramework.registerCommands(new ReloadCommand());
        commandFramework.registerCommands(new ScoreboardCommand());
        Utils.sendConsole("&8[&bScoreboard&8] &aCommands loaded");
        Utils.sendConsole("&8[&bScoreboard&8]");
    }

    @Override
    public void onDisable() {
    }

    public static Scoreboard getInstance() {
        return instance;
    }

    public CommandFramework getCommandFramework() {
        return commandFramework;
    }

    public void setCommandFramework(CommandFramework commandFramework) {
        this.commandFramework = commandFramework;
    }

    public static Scoreboard get() {
        return getPlugin(Scoreboard.class);
    }


}

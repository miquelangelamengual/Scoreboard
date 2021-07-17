package es.hulk.scoreboard;

import es.hulk.scoreboard.command.ReloadCommand;
import es.hulk.scoreboard.command.ScoreboardCommand;
import es.hulk.scoreboard.providers.ScoreboardProvider;
import es.hulk.scoreboard.utils.FileConfig;
import es.hulk.scoreboard.utils.command.CommandFramework;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
@SuppressWarnings("deprecation")
public class Scoreboard extends JavaPlugin {

    private CommandFramework commandFramework;
    private static Scoreboard instance;

    private es.hulk.scoreboard.utils.scoreboard.Scoreboard scoreboard;
    private FileConfig mainConfig;

    @Override
    public void onEnable() {
        commandFramework = new CommandFramework(this);
        instance = this;

        mainConfig = new FileConfig(this, "config.yml");

        if (mainConfig.getBoolean("SCOREBOARD.ENABLED")) {
            this.scoreboard = new es.hulk.scoreboard.utils.scoreboard.Scoreboard(this, new ScoreboardProvider());
            scoreboard.setTicks(2);
        }
        
        commandFramework.registerCommands(new ReloadCommand());
        commandFramework.registerCommands(new ScoreboardCommand());
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

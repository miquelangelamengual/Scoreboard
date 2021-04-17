package es.hulk.scoreboard;

import es.hulk.scoreboard.providers.ScoreboardProvider;
import es.hulk.scoreboard.utils.FileConfig;
import es.hulk.scoreboard.utils.command.CommandFramework;
import es.hulk.scoreboard.utils.scoreboard.Scoreboard;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.plugin.java.JavaPlugin;

@Setter
@Getter
public class Score extends JavaPlugin {

    private CommandFramework commandFramework;
    private static Score instance;

    private Scoreboard scoreboard;
    private FileConfig mainConfig;

    @Override
    public void onEnable() {
        commandFramework = new CommandFramework(this);
        instance = this;

        mainConfig = new FileConfig(this, "config.yml");

        if (mainConfig.getBoolean("SCOREBOARD.ENABLED")) {
            this.scoreboard = new Scoreboard(this, new ScoreboardProvider());
            scoreboard.setTicks(2);
        }
    }

    @Override
    public void onDisable() {
    }

    public static Score getInstance() {
        return instance;
    }

    public CommandFramework getCommandFramework() {
        return commandFramework;
    }

    public void setCommandFramework(CommandFramework commandFramework) {
        this.commandFramework = commandFramework;
    }

    public static Score get() {
        return getPlugin(Score.class);
    }


}

package es.hulk.scoreboard.command;

import es.hulk.scoreboard.Score;
import es.hulk.scoreboard.utils.FileConfig;
import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.command.BaseCommand;
import es.hulk.scoreboard.utils.command.Command;
import es.hulk.scoreboard.utils.command.CommandArgs;
import org.bukkit.entity.Player;

public class ReloadCommand extends BaseCommand {

    private FileConfig mainConfig = Score.get().getMainConfig();

    @Command(name = "reloadscoreboard", permission = "scoreboard.command.reload")

    @Override
    public void onCommand(CommandArgs cmd) {
        Player player = cmd.getPlayer();
        mainConfig.reload();
        player.sendMessage(Utils.color(mainConfig.getString("FILE-RELOAD")));
    }
}
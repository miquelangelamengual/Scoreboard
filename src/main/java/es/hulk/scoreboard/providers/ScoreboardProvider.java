package es.hulk.scoreboard.providers;

import es.hulk.scoreboard.Score;
import es.hulk.scoreboard.utils.FileConfig;
import es.hulk.scoreboard.utils.Utils;
import es.hulk.scoreboard.utils.scoreboard.ScoreboardAdapter;
import es.hulk.scoreboard.utils.scoreboard.ScoreboardStyle;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardProvider implements ScoreboardAdapter {

    private final FileConfig mainConfig = Score.get().getMainConfig();

    @Override
    public String getTitle(Player player) {
        return Utils.color(mainConfig.getString("SCOREBOARD.TITLE"));
    }

    @Override
    public List<String> getLines(Player player) {
        final List<String> board = new ArrayList<String>();

        for (String s : mainConfig.getStringList("SCOREBOARD.LINES")) {
            board.add(PlaceholderAPI.setPlaceholders(player, s.replaceAll("<player>", player.getName())));
        }
        return board;
    }

    @Override
    public ScoreboardStyle getBoardStyle(Player player) {
        return ScoreboardStyle.MODERN;
    }
}

package es.hulk.scoreboard.utils.scoreboard;

import es.hulk.scoreboard.utils.scoreboard.events.ScoreboardCreateEvent;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
public class Scoreboard {

	private JavaPlugin plugin;
	private ScoreboardAdapter adapter;
	private Map<UUID, ScoreboardManager> boards;
	private ScoreboardThread thread;
	private ScoreboardListener listeners;
	private long ticks = 2;
	private boolean hook = false;
	private ScoreboardStyle assembleStyle = ScoreboardStyle.MODERN;

	public Scoreboard(JavaPlugin plugin, ScoreboardAdapter adapter) {
		if (plugin == null) {
			throw new RuntimeException("Scoreboard can not be instantiated without a plugin instance!");
		}

		this.plugin = plugin;
		this.adapter = adapter;
		this.boards = new ConcurrentHashMap<>();

		this.setup();
	}

	public void setup() {
		listeners = new ScoreboardListener(this);

		//Register Events
		this.plugin.getServer().getPluginManager().registerEvents(listeners, this.plugin);

		//Ensure that the thread has stopped running
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		//Register new boards for existing online players
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			//Make sure it doesn't double up
			ScoreboardCreateEvent createEvent = new ScoreboardCreateEvent(player);

			Bukkit.getPluginManager().callEvent(createEvent);
			if (createEvent.isCancelled()) {
				return;
			}

			getBoards().put(player.getUniqueId(), new ScoreboardManager(player, this));
		}

		//Start Thread
		this.thread = new ScoreboardThread(this);
	}

	public void cleanup() {
		if (this.thread != null) {
			this.thread.stop();
			this.thread = null;
		}

		if (listeners != null) {
			HandlerList.unregisterAll(listeners);
			listeners = null;
		}

		for (UUID uuid : getBoards().keySet()) {
			Player player = Bukkit.getPlayer(uuid);
			getBoards().remove(uuid);
			player.setScoreboard(Bukkit.getScoreboardManager().getMainScoreboard());
		}
	}

	public JavaPlugin getPlugin() {
		return this.plugin;
	}

	public ScoreboardAdapter getAdapter() {
		return this.adapter;
	}

	public Map<UUID, ScoreboardManager> getBoards() {
		return this.boards;
	}

	public ScoreboardThread getThread() {
		return this.thread;
	}

	public ScoreboardListener getListeners() {
		return this.listeners;
	}

	public long getTicks() {
		return this.ticks;
	}

	public boolean isHook() {
		return this.hook;
	}

	public ScoreboardStyle getAssembleStyle() {
		return this.assembleStyle;
	}

	public void setPlugin(final JavaPlugin plugin) {
		this.plugin = plugin;
	}

	public void setAdapter(final ScoreboardAdapter adapter) {
		this.adapter = adapter;
	}

	public void setBoards(final Map<UUID, ScoreboardManager> boards) {
		this.boards = boards;
	}

	public void setThread(final ScoreboardThread thread) {
		this.thread = thread;
	}

	public void setListeners(final ScoreboardListener listeners) {
		this.listeners = listeners;
	}

	public void setTicks(final long ticks) {
		this.ticks = ticks;
	}

	public void setHook(final boolean hook) {
		this.hook = hook;
	}

	public void setAssembleStyle(final ScoreboardStyle assembleStyle) {
		this.assembleStyle = assembleStyle;
	}
}


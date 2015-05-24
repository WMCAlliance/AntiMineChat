package im.wma.dev.amc;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;


public class AntiMineChat extends JavaPlugin
{
	public static AntiMineChat thePlugin = null;
	public AntiMineChat()
	{
		thePlugin = this;
	}
	@Override
	public void onEnable()
	{
		getLogger().info("AntiMineChat loaded");
		getServer().getPluginManager().registerEvents(new EventListener(), this);
	}

	@Override
	public void onDisable()
	{
		getLogger().info("AntiMineChat unloaded");
		HandlerList.unregisterAll(thePlugin);

	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (sender instanceof Player) {
			sender.sendMessage("AnitMineChat is on");
		} else {
			sender.sendMessage("Console hacker");
			return false;
		}
		return false;
	}
}
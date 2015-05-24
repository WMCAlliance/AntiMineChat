package im.wma.dev.amc;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class EventListener implements Listener {
	private Map<Player, Location> locations;

	public EventListener()
	{
		locations = new HashMap<Player, Location>();
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event)
	{
		locations.put(event.getPlayer(), event.getPlayer().getLocation());
	}

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event)
	{
		locations.remove(event.getPlayer());
	}

	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerTeleport(PlayerTeleportEvent event)
	{
		//Update stored location if they teleport, to compensate for commands or plugins teleporting players on login
		locations.put(event.getPlayer(), event.getTo());
	}

	@EventHandler(ignoreCancelled = true)
	public void onPlayerChat(AsyncPlayerChatEvent event)
	{
		try
		{
			if(event.getPlayer().getLocation().distanceSquared(locations.get(event.getPlayer())) < 1.0D)
			{
			    if (event.getPlayer().hasPermission("amc.allow")) {
			    	return;
			    }else
					event.setCancelled(true);
					event.getPlayer().sendMessage(ChatColor.RED + "You cannot chat without having moved first");
			}
		}
		catch(IllegalArgumentException e)
		{
			//Do nothing, Player is in a different world than the stored location
		}
	}

}

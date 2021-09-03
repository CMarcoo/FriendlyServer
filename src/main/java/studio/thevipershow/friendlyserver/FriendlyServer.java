package studio.thevipershow.friendlyserver;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import studio.thevipershow.friendlyserver.commands.MainCommand;
import studio.thevipershow.friendlyserver.listeners.FriendlyListener;

public final class FriendlyServer extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        final PluginManager manager = getServer().getPluginManager();
        final FriendlyListener friendlyListener = new FriendlyListener(this);
        manager.registerEvents(friendlyListener, this);
        getCommand("friend").setExecutor(new MainCommand(friendlyListener.getDamageAllowData(), this));
    }
}

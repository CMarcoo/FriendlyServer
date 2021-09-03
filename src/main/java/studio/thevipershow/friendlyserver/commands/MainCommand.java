package studio.thevipershow.friendlyserver.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import studio.thevipershow.friendlyserver.data.DamageAllowData;

import java.util.Optional;

public final class MainCommand implements CommandExecutor {

    private final Plugin plugin;
    private final DamageAllowData data;

    public MainCommand(DamageAllowData data, Plugin plugin) {
        this.data = data;
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Command available only to players!");
        } else {
            final Player executor = (Player) sender;
            if (args.length == 1) {
                final String friendName = args[0];
                final Optional<? extends Player> friendAdd = plugin.getServer().getOnlinePlayers().stream().filter(p -> p.getName().equals(friendName)).findFirst();
                if (friendAdd.isPresent()) {
                    data.addDamager(executor.getUniqueId(), friendAdd.get().getUniqueId());
                    executor.sendMessage("Player added to your friend list!");
                } else {
                    executor.sendMessage("That player is not online!");
                }
            } else {
                executor.sendMessage("Wrong command syntax, use this:");
                executor.sendMessage("/friend NAME");
            }
        }
        return true;
    }
}

package studio.thevipershow.friendlyserver.listeners;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.plugin.Plugin;
import studio.thevipershow.friendlyserver.data.DamageAllowData;

import java.util.UUID;

public class FriendlyListener implements Listener {

    private final Plugin plugin;
    private final DamageAllowData damageAllowData;

    public FriendlyListener(Plugin plugin) {
        this.plugin = plugin;
        this.damageAllowData = new DamageAllowData();
    }

    @EventHandler(ignoreCancelled = true)
    public void onEntityDamage(EntityDamageByEntityEvent event) {
        final Entity damaged = event.getEntity();
        final Entity damager = event.getDamager();

        if (damaged.getType() == EntityType.PLAYER && damager.getType() == EntityType.PLAYER) {
            final UUID damagedUUID = damaged.getUniqueId();
            final Player player = (Player) damager;
            if (damageAllowData.damageableBy(damagedUUID, event.getDamager().getUniqueId())) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&8[&f!&8] &cYou cannot hit this player!"));
            }
        }
    }

    public final DamageAllowData getDamageAllowData() {
        return damageAllowData;
    }
}

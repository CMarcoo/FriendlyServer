package studio.thevipershow.friendlyserver.data;

import java.util.*;

public final class DamageAllowData {

    private final Map<UUID, Set<UUID>> damageableBy = new HashMap<>();

    public boolean damageableBy(final UUID damaged, final UUID damager) {
        if (damageableBy.containsKey(damaged)) {
            return damageableBy.get(damaged).contains(damager);
        } else {
            damageableBy.put(damaged, new HashSet<>());
            return false;
        }
    }

    public void addDamager(final UUID damaged, final UUID damager) {
        if (damageableBy.containsKey(damaged)) {
            damageableBy.get(damaged).add(damager);
        } else {
            final Set<UUID> set = new HashSet<>();
            set.add(damager);
            damageableBy.put(damaged, set);
        }
    }

    public void removeDamager(final UUID damaged, final UUID damager) {
        if (damageableBy.containsKey(damaged)) {
            damageableBy.get(damaged).remove(damager);
        }
    }
}

package me.tonymaster21.bungeemaster.Effects;

import ch.njol.skript.lang.Effect;
import ch.njol.skript.lang.Expression;
import ch.njol.skript.lang.SkriptParser;
import ch.njol.util.Kleenean;
import com.sun.istack.internal.Nullable;
import me.dommi2212.BungeeBridge.packets.PacketConnectPlayer;
import org.bukkit.event.Event;

import java.util.UUID;

/**
 * Created by TonyMaster21 on 10/22/2017.
 */
public class EffConnectPlayer extends Effect {
    private Expression<String> server;
    private Expression<String> uuid;
    @Override
    public boolean init(Expression<?>[] e, int matchedPattern, Kleenean isDelayed, SkriptParser.ParseResult parser) {
        uuid = (Expression<String>) e[0];
        server = (Expression<String>) e[1];
        return true;
    }

    @Override
    public String toString(@Nullable Event paramEvent, boolean paramBoolean) {
        return "(bm|bungeemaster) connect %string% to [server ]%string%";
    }

    @Override
    protected void execute(Event e) {
        if (server != null || uuid != null) {
            UUID theuuid = null;
            theuuid = UUID.fromString(uuid.getSingle(e));
            PacketConnectPlayer packet = new PacketConnectPlayer(theuuid, server.getSingle(e));
            Object obj = packet.send();
        }
    }
}

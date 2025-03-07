package us.xgraza.sdrpc;

import com.sun.jna.Callback;
import com.sun.jna.Structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 03/07/25
 * @see <a href="https://github.com/discord/discord-rpc/blob/master/include/discord_rpc.h">discord_rpc.h</a>
 */
public final class DiscordEventHandlers extends Structure
{
    private static final List<String> FIELD_ORDER = new LinkedList<>();

    static
    {
        FIELD_ORDER.add("ready");
        FIELD_ORDER.add("disconnected");
        FIELD_ORDER.add("errored");
        FIELD_ORDER.add("joinGame");
        FIELD_ORDER.add("spectateGame");
        FIELD_ORDER.add("joinRequest");
    }

    public ReadyCallback ready;
    public DisconnectedCallback disconnected;
    public ErroredCallback errored;
    public JoinGameCallback joinGame;
    public SpectateGameCallback spectateGame;
    public JoinRequestCallback joinRequest;

    DiscordEventHandlers()
    {
        // package-private constructor so people cant init this outside of EVENT_HANDLERS
    }

    public interface ReadyCallback extends Callback
    {
        void callback(final DiscordUser request);
    }

    public interface DisconnectedCallback extends Callback
    {
        void callback(final int errorCode, final String message);
    }

    public interface ErroredCallback extends Callback
    {
        void callback(final int errorCode, final String message);
    }

    public interface JoinGameCallback extends Callback
    {
        void callback(final String joinSecret);
    }

    public interface SpectateGameCallback extends Callback
    {
        void callback(final String spectateSecret);
    }

    public interface JoinRequestCallback extends Callback
    {
        void callback(final DiscordUser request);
    }

    @Override
    public List<String> getFieldOrder()
    {
        return FIELD_ORDER;
    }
}

package us.xgraza.sdrpc;

import com.sun.jna.Library;

/**
 * @author xgraza
 * @since 03/07/25
 * @see <a href="https://github.com/discord/discord-rpc/blob/master/include/discord_rpc.h">discord_rpc.h</a>
 */
public interface NativeDiscordRPC extends Library
{
    void Discord_Initialize(final String applicationId,
                            final DiscordEventHandlers handlers,
                            final int autoRegister,
                            final String optionalSteamId);

    void Discord_Shutdown();

    void Discord_RunCallbacks();

    void Discord_UpdatePresence(final DiscordRichPresence presence);
    void Discord_ClearPresence();

    void Discord_UpdateHandlers(final DiscordEventHandlers handlers);
}

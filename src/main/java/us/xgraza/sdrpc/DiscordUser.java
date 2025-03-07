package us.xgraza.sdrpc;

import com.sun.jna.Structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 03/07/25
 * @see <a href="https://github.com/discord/discord-rpc/blob/master/include/discord_rpc.h">discord_rpc.h</a>
 */
public final class DiscordUser extends Structure
{
    private static final List<String> FIELD_ORDER = new LinkedList<>();

    static
    {
        FIELD_ORDER.add("userId");
        FIELD_ORDER.add("username");
        FIELD_ORDER.add("discriminator");
        FIELD_ORDER.add("avatar");
    }

    public String userId, username, discriminator, avatar;

    @Override
    protected List<String> getFieldOrder()
    {
        return FIELD_ORDER;
    }
}

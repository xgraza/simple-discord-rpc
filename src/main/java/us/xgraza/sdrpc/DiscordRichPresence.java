package us.xgraza.sdrpc;

import com.sun.jna.Structure;

import java.util.LinkedList;
import java.util.List;

/**
 * @author xgraza
 * @since 03/07/25
 */
public final class DiscordRichPresence extends Structure
{
    private static final List<String> FIELD_ORDER = new LinkedList<>();

    static
    {
        FIELD_ORDER.add("state");
        FIELD_ORDER.add("details");
        FIELD_ORDER.add("startTimestamp");
        FIELD_ORDER.add("endTimestamp");
        FIELD_ORDER.add("largeImageKey");
        FIELD_ORDER.add("largeImageText");
        FIELD_ORDER.add("smallImageKey");
        FIELD_ORDER.add("smallImageText");
        FIELD_ORDER.add("partyId");
        FIELD_ORDER.add("partySize");
        FIELD_ORDER.add("partyMax");
        FIELD_ORDER.add("partyPrivacy");
        FIELD_ORDER.add("matchSecret");
        FIELD_ORDER.add("joinSecret");
        FIELD_ORDER.add("spectateSecret");
        FIELD_ORDER.add("instance");
    }

    public String state, details;
    public long startTimestamp, endTimestamp;
    public String largeImageKey, largeImageText, smallImageKey, smallImageText, partyId;
    public int partySize, partyMax, partyPrivacy;
    public String matchSecret, joinSecret, spectateSecret;
    public int instance;

    @Override
    public List<String> getFieldOrder()
    {
        return FIELD_ORDER;
    }
}

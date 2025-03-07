package us.xgraza.sdrpc;

import com.sun.jna.Native;

/**
 * @author xgraza
 * @since 03/07/25
 */
public final class DiscordRPC
{
    private static final NativeDiscordRPC NATIVE;
    public static final DiscordEventHandlers EVENT_HANDLERS;

    private static Thread CALLBACK_THREAD;

    static
    {
        NATIVE = Native.load(getLibraryForOS(), NativeDiscordRPC.class);
        EVENT_HANDLERS = new DiscordEventHandlers();
    }

    /**
     * Runs the native Discord_Initialize function from the DLL
     * @param applicationId the application ID containing the data for the RPC
     */
    public static void init(final String applicationId)
    {
        NATIVE.Discord_Initialize(applicationId, EVENT_HANDLERS, 0, null);
        NATIVE.Discord_RunCallbacks();
    }

    /**
     * Stops the callback thread, clears your presence, and shuts down the connection to the RPC
     */
    public static void shutdown()
    {
        if (CALLBACK_THREAD != null)
        {
            CALLBACK_THREAD.interrupt();
            CALLBACK_THREAD = null;
        }
        NATIVE.Discord_ClearPresence();
        NATIVE.Discord_Shutdown();
    }

    /**
     * Sends a presence update to discord
     * @param presence the {@link DiscordRichPresence} object containing the data
     */
    public static void updatePresence(final DiscordRichPresence presence)
    {
        NATIVE.Discord_UpdatePresence(presence);
    }

    /**
     * Clears the presence on discord
     */
    public static void clearPresence()
    {
        NATIVE.Discord_ClearPresence();
    }

    /**
     * Creates a callback thread and runs Discord_RunCallbacks to receive events
     * @param runnable the lambda to run code in the callback thread every update
     * @param updateIntervalMS how often to update (recommend 500-1000ms)
     */
    public static void runCallbacks(final Runnable runnable, final long updateIntervalMS)
    {
        if (CALLBACK_THREAD != null)
        {
            throw new RuntimeException("callback thread active, call shutdown and re-initialize RPC");
        }

        CALLBACK_THREAD = new Thread(() ->
        {
            while (!Thread.currentThread().isInterrupted())
            {
                try
                {
                    Thread.sleep(updateIntervalMS);
                } catch (final InterruptedException e)
                {
                    e.printStackTrace();
                    return;
                }
                NATIVE.Discord_RunCallbacks();
                runnable.run();
            }
        }, "DiscordRPC-Callback-Thread");
        CALLBACK_THREAD.start();
    }

    /**
     * Gets the proper library for the OS
     * @return the file name in resources
     */
    private static String getLibraryForOS()
    {
        final String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win"))
        {
            return "discord-rpc";
        }
        return "libdiscord-rpc";
    }
}

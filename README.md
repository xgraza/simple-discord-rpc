# simple-discord-rpc

---

A simple implementation for [Discord RPC](https://github.com/discord/discord-rpc)

> [!WARNING]
> "discord-rpc" has been depreciated by Discord in favor for the GameSDK
> I just didn't want to signup for the SDK and go through all the other stuff they want

---

## Usage

Simply call `DiscordRPC.init(applicationId)`

Example:
```java
final String applicationId = "someappid"; // https://discord.com/developers/applications
DiscordRPC.init(applicationId);
```

---

## Events

> [!NOTE]
> You need to define your events before running the callback to receive events

Access & define your own events with `DiscordRPC.EVENT_HANDLERS`

Example:
```java
DiscordRPC.EVENT_HANDLERS.ready = (user) ->
        {
            System.out.printf("Connected to DiscordRPC with user %s#%s (%s)%n", 
                user.username, user.discriminator, user.userId);
        };
```

Available events are:

```java
ready(final DiscordUser request);
disconnected(final int errorCode, final String message);
errored(final int errorCode, final String message);
joinGame(final String joinSecret);
spectateGame(final String spectateSecret);
joinRequest(final DiscordUser request);
```

Call any of these events similar to the example, all must be a callback.

The event fields are public because JNA does not like private fields...

Once you have your events defined, you are able to receive events with the following code:

```java
DiscordRPC.runCallbacks(() ->
        {
            // run anything you want, this is also where you would want to call updatePresence();
        }, 1000L);
```

You are able to control how often you want to receive callbacks from Discord with the last argument of `runCallbacks`

Specify any long value in MS (for the example above, I used 1000ms for an update every second)

---

## Updating / Clearing Presences

You are able to both update & clear the discord rich presence (why else would I make this?)

Example: (this should be put in the callback in the `runCallbacks` method)

### Update Presence

```java
final DiscordRichPresence presence = new DiscordRichPresence();
presence.details = "Crazy code!";
presence.state = "Some state";
DiscordRPC.updatePresence(presence);
```

### Clear Presence
```java
DiscordRPC.clearPresence();
```

---

## Shutting down

You are able to completely shutdown & start fresh with the `shutdown` method

Example:

```java
DiscordRPC.shutdown();
```

This method will halt the callback thread (meaning your code in the `runCallbacks` method will stop) and will cease connection with the Discord RPC

---
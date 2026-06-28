# Add a new track

As a user, you should be able to add a new track to an artist's catalog,
capturing attributes such as track title, genre, length, etc.

## Analysis

Let's assume that a track in this context is a specific performance for a given
composition. Then there is a many-to-many relation between `Artist` and `Track`:
- a track can be used as part of at least one musical release;
- same track can be performed by different artists;
- same composition can have several variations;
- composer and performer may not be the same persons.

### Release side

Since the time of physical releasing, a track belongs to one of the forms of pieces like 
album, single or EP. This practice is continued nowadays.

My initial view was to build chain like following:

```
    Release -> Edition
```

where `Release` is catching common and high-level part of release ie type, genre,
release artist(s); and `Edition` carries more specific details: form, total duration,
label and release location.

For online music catalog `Edition` looks like unnecessary complication, so I decided
to merge it.

### Performance side

At the same time I know that a title of the track also can be misleading, as several tracks
with same title:
- have a different duration ie radio edit of `Shine On You Crazy Diamond`;
- be performed live or in studio ie `Metallica` releasing every big concert;
- feature other `Artists` and so on

So, in hope to have a room to catch it I split it into:

```
    Composition -> Performance
```

where `Composition` carries information about who composed it, and `Performance` 
collects information about who and where (planned) is performing.

### Track

In my domain `Track` is an item which belongs to the `Release` domain and acts as a link to a specific
`Performance`. 

## Implementation

Between:
- each domain own creation;
- single entry with nested items;
- command like approach;

decided to go with the last one, which open at least ability to submit everything
in the scope of single transaction, and have it rolled back at once.

See `reqs/add_release.json` for an example of how to create a new release with a track assigned.
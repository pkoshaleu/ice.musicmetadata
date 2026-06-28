# Fetch Artist Tracks

As a user, you should be able to fetch all tracks associated with a specific artist.

## Analysis & Implementation

As domain already has some taste of separation between principal and appears-on
work (see `Composition` vs `Performance`), I'd prefer to have it added to the
most of end-points.

All main endpoints (`compositions`, `performances`, `releases`) support filtering by
`Artist` id. In addition to this `performances` and `releases` will do filtering
by principal role ie for case Main Artist credentials:

By default, search is by appearance:
```
$ http localhost:8080/releases\?artist=00000000-0000-7000-0010-000000000002
[
    {
        ...
        "title": "Mr. Bad Guy"
    },
    {
        ...
        "title": "Radio Ga Ga"
    },
    {
        ...
        "title": "That Works"
    }
]
```

Or more strict principal search:
```
$ http localhost:8080/releases\?artist=00000000-0000-7000-0010-000000000002\&role=principal
[
    {
        ...
        "title": "Mr. Bad Guy"
    }
]
```

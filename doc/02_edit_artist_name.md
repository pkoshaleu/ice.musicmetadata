# Edit Artist Name

As a user, you should be able to edit an artist's name to accommodate instances
where artists have multiple aliases.

## Analysis

Let's assume that edit operation means add another alias to the artist's name. 

So, aliases can be used in the following cases:
- spelling mismatch between native and localized name,
- because romanization of non-latin names could be done in different way,
- presence of numbers or punctuation in the artist name,
- misspelling.

All mentioned cases will be handled via `variation` of the `Artist` domain. For IRL project 
I'd like to include at least `location` to the variation entry, but for sake of simplicity for 
now I will use just an array of strings.

So, `variation` can be used for general search by artist's name.

One exceptional case must be handled differently. When an artist decides to rename officially 
eg `Polka Tulk Blues Band` to `Black Sabbath`, `Relation` has to be used to track these changes.
I believe all work done under previous name should stay connected to the previous name.

## Implementation (old and removed)

`variation` is a part of `Artist` domain:

```
$ http localhost:8080/artists/00000000-0000-7000-0000-000000000001                          
HTTP/1.1 200 

{
    "id": "00000000-0000-7000-0000-000000000001",
    "title": "Queen",
    "type": "COLLECTIVE",
    "variations": [
        "Queen UK"
    ]
}
```

To add a new variation user has to submit it as JSON to the following endpoint:

```
➜ http POST localhost:8080/artists/00000000-0000-7000-0000-000000000001/variations variation="Queen Band" 
HTTP/1.1 200 

{
    "id": "00000000-0000-7000-0000-000000000001",
    "title": "Queen",
    "type": "COLLECTIVE",
    "variations": [
        "Queen UK",
        "Queen Band"
    ]
}
```

## Implementation

User has to submit `addVariation` command (payload in `reqs\add_variation.json`):

```
$ http POST localhost:8080/catalog/update < doc/reqs/add_variation.json
HTTP/1.1 200 

{
    "aliases": {}
}
```
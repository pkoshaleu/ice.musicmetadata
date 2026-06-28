# Domain description

## Artist

__Entity credited as a creator and/or performer__

An `Artist` can represent:
- a person with specific stage name;
- a band or a collective.

`Artist` is a public identity ie stage name. A real person may have one or more entries in this domain.

Rules:
- should have title;
- should have type, one of;
- should have variations (see `02_edit_artist_name.md`)
- should have validity period (TODO: not implemented, only script).

## Relation (not implemented ¯\(°_o)/¯)

__Directed relation from one `Artist` to another `Artist`__

Catching connections of the following types:
- (person) member of (band or collective);
- (artist) renamed to (artist).

Rules:
- should have clear direction (member) -> (band) or (early) -> (later);
- should have validity period.

## `Composition`

Original piece of music in broad definition, which may has melody and lyric. Or may have not.
Any artist connected with composition has to be considered as creator of it.

Rules:
- should have at least one artist connected;
- should have a title;

## `Performace`

Specific execution of `Composition`, live or in studio.

Rules:
- any artist connected with composition has to be considered as performer of it;
- may have different to parent `Composition` title;
- should have duration (stored as seconds)
- may have genre(s) assigned;

## `Release`

Published to public music release, physically or digitally. Catch generic information about release.

- may have release artist or artist=0 as a sign of Various;
- should have a title;
- should have a type: Album, Single, Box

## `Edition` (not implemented, merged with Release ¯\(°_o)/¯)

Specific execution of `Release`

- may have different title;
- may have location;

## `[type]Credit`
Link between `[type]` (one of Composition, Performance, Release) and Artist;

## `Track`
Link between `Edition` and `Performace`

may have coordinates as {Alpha} or {digit.digits}

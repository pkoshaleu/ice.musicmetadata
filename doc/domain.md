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

Original piece of music in the broad sense, which may have a melody and lyrics. Or may have neither.
Any artist connected with a composition has to be considered a creator of it.

Rules:
- should have at least one artist connected;
- should have a title;

## `Performance`

Specific execution of `Composition`, live or in studio.

Rules:
- any artist connected with a performance has to be considered a performer of it;
- may have different to parent `Composition` title;
- should have duration (stored as seconds)
- may have genre(s) assigned;

## `Release`

Music released to the public, physically or digitally. Captures generic information about the release.

- may have release artist or artist=0 as a sign of Various;
- should have a title;
- should have a type: Album, Single, EP, Live

## `Edition` (not implemented, merged with Release ¯\(°_o)/¯)

Specific execution of `Release`

- may have different title;
- may have location;

## `[type]Credit`
Link between `[type]` (one of `Composition`, `Performance`, `Release`) and `Artist`, carrying a role.

The role's meaning depends on the layer:
- `Composition` — authorship, e.g. `Composer`;
- `Performance` — `Main Artist` (principal act) or `Performer`, `Featured` etc;
- `Release` — billing, e.g. `Main Artist`.

`Main Artist` is the principal credit; the search API exposes it via the logical filter `role=principal`.

## `Track`
Link between `Release` and `Performance`

- may have coordinates as {Alpha} or {digit.digits} (not implemented)

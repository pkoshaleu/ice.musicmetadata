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

## Relation

__Directed relation from one `Artist` to another `Artist`__

Catching connections of the following types:
- (person) member of (band or collective);
- (artist) renamed to (artist).

Rules:
- should have clear direction (member) -> (band) or (early) -> (later);
- should have validity period.

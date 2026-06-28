# Music Metadata Catalogue Assignment

## Goal

Design and implement a system that provides the following user experiences:

- Add a New Track;
- Edit Artist Name;
- Fetch Artist Tracks;
- Artist of the Day.

## Design

See `doc` folder.

## Building and Execution

### Prerequisites
- Java 25.x
- Maven 3.9.x
- Docker

`httpie` is used in the documentation to illustrate interaction with the provided API.

### Start it

Run `db/runme.sh` first to start a local copy of PG with test data, or use your own and execute the
contents of `db/sql` in order.

Run it with maven:
```
mvn spring-boot:run
```

## Known Issues
- No unit tests, nil, at all.
- Logging is also non-existent.
- Almost nothing is implemented in the API except what's really required.
- Positions are ignored for updates.
- Validation may be broken for updates.

## What to Do Next
- Unit testing at least domain operations.
- Integration tests for repositories.
- More data to assess which indices are needed.

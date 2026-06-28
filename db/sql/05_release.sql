CREATE TABLE music_release
(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    release_type TEXT NOT NULL,
    release_date DATE NOT NULL,
    genres JSONB NOT NULL DEFAULT '[]'::jsonb,

    CONSTRAINT release_title_non_empty CHECK (btrim(title) <> ''),
    CONSTRAINT release_title_len CHECK (char_length(title) <= 255),
    CONSTRAINT release_genres_type CHECK (jsonb_typeof(genres) = 'array')
);

INSERT INTO music_release (id, title, release_type, release_date, genres)
VALUES
(u('50', '1'), 'Radio Ga Ga' , 'SINGLE', '1984-01-23', '["pop-rock"]'::jsonb),
(u('50', '2'), 'That Works' , 'ALBUM', '1984-02-20', '["pop-rock"]'::jsonb),
(u('50', '3'), 'Mr. Bad Guy' , 'ALBUM', '1985-04-29', '["synth-pop"]'::jsonb);

CREATE TABLE music_release_credit
(
    id UUID PRIMARY KEY,
    release_id UUID NOT NULL REFERENCES music_release(id),
    position INT NOT NULL,
    artist_id UUID NOT NULL REFERENCES artist(id),
    role TEXT NOT NULL,

    CONSTRAINT composition_credit_role_len CHECK (char_length(role) <= 255)
);

CREATE INDEX music_release_credit_artist_id_idx ON music_release_credit (artist_id);

INSERT INTO music_release_credit (id, release_id, position, artist_id, role)
VALUES
    (u('51', '1'), u('50', '1'), 1, u('10', '1'), 'Main Artist'),
    (u('51', '2'), u('50', '2'), 1, u('10', '1'), 'Main Artist'),
    (u('51', '3'), u('50', '3'), 1, u('10', '2'), 'Main Artist');

CREATE TABLE music_release_track
(
    id UUID PRIMARY KEY,
    release_id UUID NOT NULL REFERENCES music_release(id),
    position INT NOT NULL,
    performance_id UUID NOT NULL REFERENCES performance(id)
);

CREATE INDEX music_release_track_performance_id_idx ON music_release_track (performance_id);

INSERT INTO music_release_track (id, release_id, position, performance_id)
VALUES
    (u('52', '1'), u('50', '1'), 1, u('40', '1')),
    (u('52', '2'), u('50', '2'), 1, u('40', '1')),
    (u('52', '3'), u('50', '2'), 2, u('40', '2')),
    (u('52', '4'), u('50', '2'), 3, u('40', '3')),
    (u('52', '5'), u('50', '3'), 1, u('40', '4'));
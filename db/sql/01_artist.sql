CREATE TABLE artist
(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    artist_type TEXT NOT NULL,
    variations JSONB NOT NULL DEFAULT '[]'::jsonb,

    CONSTRAINT artist_title_non_empty CHECK (btrim(title) <> ''),
    CONSTRAINT artist_title_len CHECK (char_length(title) <= 255),
    CONSTRAINT artist_type CHECK (artist_type IN ('PERSON', 'COLLECTIVE')),
    CONSTRAINT artist_variation_type CHECK (jsonb_typeof(variations) = 'array')
);

INSERT INTO artist (id, title, artist_type, variations)
VALUES
    (u('10', '1'), 'Queen', 'COLLECTIVE', '["Queen UK", "Queen Band"]'::jsonb),
    (u('10', '2'), 'Freddie Mercury', 'PERSON', '["Farrokh Bulsara", "Freddie"]'::jsonb),
    (u('10', '3'), 'Brian May', 'PERSON', '[]'::jsonb),
    (u('10', '4'), 'Roger Taylor', 'PERSON', '[]'::jsonb),
    (u('10', '5'), 'John Deacon', 'PERSON', '[]'::jsonb);

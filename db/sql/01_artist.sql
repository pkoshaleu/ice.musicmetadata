CREATE TABLE artist
(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    artist_type TEXT NOT NULL,
    variations JSONB NOT NULL DEFAULT '[]'::jsonb,
    valid_from DATE,
    valid_until DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT artist_title_non_empty CHECK (btrim(title) <> ''),
    CONSTRAINT artist_title_len CHECK (char_length(title) <= 255),
    CONSTRAINT artist_type CHECK (artist_type IN ('PERSON', 'COLLECTIVE')),
    CONSTRAINT artist_variation_type CHECK (jsonb_typeof(variations) = 'array'),
    CONSTRAINT artist_validity CHECK (valid_until IS NULL OR valid_from IS NULL OR valid_until >= valid_from)
);

INSERT INTO artist (id, title, artist_type, variations, valid_from, valid_until)
VALUES
    ('00000000-0000-7000-0000-000000000001', 'Queen', 'COLLECTIVE', '["Queen UK", "Queen Band"]'::jsonb, '1970-01-01', NULL),
    ('00000000-0000-7000-0000-000000000002', 'Freddie Mercury', 'PERSON', '["Farrokh Bulsara", "Freddie"]'::jsonb, '1946-09-05', '1991-11-24'),
    ('00000000-0000-7000-0000-000000000003', 'Brian May', 'PERSON', '[]'::jsonb, '1947-07-19', NULL),
    ('00000000-0000-7000-0000-000000000004', 'Roger Taylor', 'PERSON', '[]'::jsonb, '1949-07-26', NULL),
    ('00000000-0000-7000-0000-000000000005', 'John Deacon', 'PERSON', '[]'::jsonb, '1951-08-19', NULL);

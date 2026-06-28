CREATE TABLE performance
(
    id UUID PRIMARY KEY,
    composition_id UUID NOT NULL REFERENCES composition(id),
    title TEXT NOT NULL,
    duration INT NOT NULL,
    lang TEXT NOT NULL,
    genres JSONB NOT NULL DEFAULT '[]'::jsonb,

    CONSTRAINT performance_title_non_empty CHECK (btrim(title) <> ''),
    CONSTRAINT performance_title_len CHECK (char_length(title) <= 255),
    CONSTRAINT performance_language CHECK (char_length(lang) = 2),
    CONSTRAINT performance_duration CHECK (duration > 0),
    CONSTRAINT performance_genres_type CHECK (jsonb_typeof(genres) = 'array')
);

INSERT INTO performance (id, composition_id, title, duration, lang, genres)
VALUES
    (u('40', '1'),u('30', '1'),'Radio Ga Ga', 348, 'EN', '["pop-rock"]'::jsonb),
    (u('40', '2'),u('30', '1'),'Radio Ga Ga - Radio Edit', 263, 'EN', '["pop-rock"]'::jsonb),
    (u('40', '3'),u('30', '2'),'Don''t Stop Me Now', 209, 'EN', '["power pop"]'::jsonb),
    (u('40', '4'),u('30', '3'),'Living on My Own', 203,'EN', '["pop", "dance-pop"]'::jsonb);

CREATE TABLE performance_credit
(
    id UUID PRIMARY KEY,
    performance_id UUID NOT NULL REFERENCES performance(id),
    position INT NOT NULL,
    artist_id UUID NOT NULL REFERENCES artist(id),
    role TEXT NOT NULL,

    CONSTRAINT composition_credit_role_len CHECK (char_length(role) <= 255)
);

INSERT INTO performance_credit (id, performance_id, position, artist_id, role)
VALUES
    (u('41', '1'), u('40', '1'), 1, u('10', '1'), 'Main Artist'),
    (u('41', '2'), u('40', '2'), 1, u('10', '1'), 'Main Artist'),
    (u('41', '3'), u('40', '3'), 1, u('10', '1'), 'Main Artist'),
    (u('41', '4'), u('40', '4'), 1, u('10', '2'), 'Main Artist');

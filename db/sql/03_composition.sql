CREATE TABLE composition
(
    id UUID PRIMARY KEY,
    title TEXT NOT NULL,
    lang TEXT NOT NULL,

    CONSTRAINT composition_title_non_empty CHECK (btrim(title) <> ''),
    CONSTRAINT composition_title_len CHECK (char_length(title) <= 255),
    CONSTRAINT composition_language CHECK (char_length(lang) = 2)
);

INSERT INTO composition (id, title, lang)
VALUES
    (u('30', '1'), 'Radio Ga Ga', 'EN'),
    (u('30', '2'), 'Don''t Stop Me Now', 'EN'),
    (u('30', '3'), 'Living on My Own', 'EN');

CREATE TABLE composition_credit
(
    id UUID PRIMARY KEY,
    composition_id UUID NOT NULL REFERENCES composition(id),
    position INT NOT NULL,
    artist_id UUID NOT NULL REFERENCES artist(id),
    role TEXT NOT NULL,

    CONSTRAINT composition_credit_role_len CHECK (char_length(role) <= 255)
);

CREATE INDEX composition_credit_artist_id_idx ON composition_credit (artist_id);

INSERT INTO composition_credit (id, composition_id, position, artist_id, role)
VALUES
    (u('31', '1'), u('30', '1'), 1, u('10', '4'), 'Composer'),
    (u('31', '2'), u('30', '2'), 1, u('10', '2'), 'Composer'),
    (u('31', '3'), u('30', '3'), 1, u('10', '2'), 'Composer');

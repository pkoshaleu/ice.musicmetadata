CREATE FUNCTION u(p1 text, p2 text) RETURNS uuid
    LANGUAGE sql IMMUTABLE AS
$$ SELECT ('00000000-0000-7000-' || lpad(p1, 4, '0') || '-' || lpad(p2, 12, '0'))::uuid $$;
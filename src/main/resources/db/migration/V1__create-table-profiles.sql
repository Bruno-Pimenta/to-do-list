CREATE TABLE profiles (
    id SERIAL PRIMARY KEY,
    user_name TEXT NOT NULL,
    e_mail TEXT NOT NULL UNIQUE,
    password_hash TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL
);


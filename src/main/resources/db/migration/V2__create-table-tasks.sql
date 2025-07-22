CREATE TABLE tasks (
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    description TEXT,
    task_date TIMESTAMP NOT NULL,
    is_completed BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP NOT NULL,
    profile_id INTEGER NOT NULL,
    CONSTRAINT fk_profile FOREIGN KEY (profile_id) REFERENCES profiles(id)
);


CREATE TABLE IF NOT EXISTS project_users (
    project_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES project(id),
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES employee(id),
    PRIMARY KEY (project_id, user_id)
);

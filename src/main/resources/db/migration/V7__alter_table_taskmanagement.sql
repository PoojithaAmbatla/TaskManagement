ALTER TABLE taskmanagement ADD COLUMN project_id BIGINT;
ALTER TABLE taskmanagement ADD COLUMN user_id BIGINT;
ALTER TABLE taskmanagement ADD CONSTRAINT fk_project FOREIGN KEY (project_id) REFERENCES project(id);
ALTER TABLE taskmanagement ADD CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES employee(id);

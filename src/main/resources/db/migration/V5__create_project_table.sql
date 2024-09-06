CREATE TABLE IF NOT EXISTS project (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(300) NOT NULL,
    status VARCHAR(100) NOT NULL,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(id)
);
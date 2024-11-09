CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE usuario
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    nome        VARCHAR(255) NOT NULL,
    email       VARCHAR(255) NOT NULL,
    senha       VARCHAR(255) NOT NULL,
    tipo_usuario VARCHAR(255) NOT NULL
)
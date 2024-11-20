CREATE TABLE pauta
(
    id              UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo          VARCHAR(255) NOT NULL,
    descricao       VARCHAR(255) NOT NULL,
    reuniao_id      UUID NOT NULL,
    FOREIGN KEY     (reuniao_id) REFERENCES reuniao(id) ON DELETE CASCADE
)
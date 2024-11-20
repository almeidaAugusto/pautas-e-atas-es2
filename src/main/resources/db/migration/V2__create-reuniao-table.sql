CREATE TABLE reuniao
(
    id          UUID DEFAULT gen_random_uuid() PRIMARY KEY,
    titulo       VARCHAR(255) NOT NULL,
    dataHora       TIMESTAMP NOT NULL,
    local       VARCHAR(255) NOT NULL,
    ata         VARCHAR(255) NOT NULL
)
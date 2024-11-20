CREATE TABLE reuniao_membros (
                                 reuniao_id UUID NOT NULL,
                                 membros_id UUID NOT NULL,
                                 PRIMARY KEY (reuniao_id, membros_id),
                                 FOREIGN KEY (reuniao_id) REFERENCES reuniao(id),
                                 FOREIGN KEY (membros_id) REFERENCES membros_participantes(id)
)
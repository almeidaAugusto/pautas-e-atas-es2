ALTER TABLE membros_participantes
ADD COLUMN reuniao_id UUID ,
ADD CONSTRAINT fk_reuniao FOREIGN KEY (reuniao_id) REFERENCES reuniao(id) ON DELETE CASCADE;


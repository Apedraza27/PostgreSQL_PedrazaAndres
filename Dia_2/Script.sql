create table Persona(
    id serial,
    nombre varchar(225),
    apellido varchar(225),
    municipio_nacimiento varchar(225),
    municipio_domicilio varchar(225),
    primary key (id)
);

create table Region(
    id SERIAL primary key not null,
    region varchar(250),
    departamento varchar(250),
    codigo_departamento varchar(250),
    municipio varchar(250),
    codigo_municipio varchar(250)
);

select * from Region;

--- ## 1 ## ---
-- crear una vista que muestre las regiones con sus respectivos departamentos.
-- en esta vista generar una columna que muestre la cantidad de municipios por cada departamento

create view Region_Departamento_Municipios as
select 
    region,
    departamento,
    COUNT(municipio) as cantidad_municipios
from Region
group by region, departamento;

select * from Region_Departamento_Municipios;


--- ## 2 ## ---
-- Crear una vista que muestre los departamentos con sus respectivos municipios.
-- en esta vista generar la columna de código de municipio completo, esto es, 
-- código de departamento concatenado con el código de municipio.

create view Departamento_Municipio_Codigos as
select 
    departamento,
    municipio,
    (codigo_departamento || codigo_municipio) as codigo_municipio_completo
from Region;

select * from Departamento_Municipio_Codigos;



--- ## 3 ## ---
-- Agregar dos columnas a la tabla municipios que permitan llevar el conteo de personas que viven 
-- y trabajan en cada municipio, y con base en esas columnas, implementar un disiparador que actualice 
-- esos conteos toda vez que se agregue, modifique o elimine un dato de municipio de nacimiento y/o de domicilio

ALTER TABLE Places 
ADD COLUMN personas_viven INT DEFAULT 0,
ADD COLUMN personas_trabajan INT DEFAULT 0;

CREATE OR REPLACE FUNCTION actualizar_conteo_personas() 
RETURNS TRIGGER AS $$
BEGIN
    IF (TG_OP = 'DELETE') THEN
        UPDATE Places
        SET 
            personas_viven = personas_viven - (CASE WHEN OLD.municipio_domicilio IS NOT NULL THEN 1 ELSE 0 END),
            personas_trabajan = personas_trabajan - (CASE WHEN OLD.municipio_nacimiento IS NOT NULL THEN 1 ELSE 0 END)
        WHERE municipio = OLD.municipio_domicilio OR municipio = OLD.municipio_nacimiento;
    
    ELSIF (TG_OP = 'INSERT') THEN
        UPDATE Places
        SET 
            personas_viven = personas_viven + (CASE WHEN NEW.municipio_domicilio IS NOT NULL THEN 1 ELSE 0 END),
            personas_trabajan = personas_trabajan + (CASE WHEN NEW.municipio_nacimiento IS NOT NULL THEN 1 ELSE 0 END)
        WHERE municipio = NEW.municipio_domicilio OR municipio = NEW.municipio_nacimiento;
    
    ELSIF (TG_OP = 'UPDATE') THEN
        UPDATE Places
        SET 
            personas_viven = personas_viven - (CASE WHEN OLD.municipio_domicilio IS NOT NULL THEN 1 ELSE 0 END),
            personas_trabajan = personas_trabajan - (CASE WHEN OLD.municipio_nacimiento IS NOT NULL THEN 1 ELSE 0 END)
        WHERE municipio = OLD.municipio_domicilio OR municipio = OLD.municipio_nacimiento;
        
        UPDATE Places
        SET 
            personas_viven = personas_viven + (CASE WHEN NEW.municipio_domicilio IS NOT NULL THEN 1 ELSE 0 END),
            personas_trabajan = personas_trabajan + (CASE WHEN NEW.municipio_nacimiento IS NOT NULL THEN 1 ELSE 0 END)
        WHERE municipio = NEW.municipio_domicilio OR municipio = NEW.municipio_nacimiento;
    END IF;

    RETURN NULL; 
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_actualizar_conteo_personas
AFTER INSERT OR UPDATE OR DELETE ON People
FOR EACH ROW
EXECUTE FUNCTION actualizar_conteo_personas();

INSERT INTO Places (region, departamento, codigo_departamento, municipio, codigo_municipio) VALUES
('Santander', 'Santander', '68', 'Girón', '307'),
('Santander', 'Santander', '68', 'Bucaramanga', '001'),
('Antioquia', 'Antioquia', '05', 'Medellín', '001'),
('Antioquia', 'Antioquia', '05', 'Envigado', '002');

INSERT INTO People (nombre, apellido, municipio_nacimiento, municipio_domicilio) VALUES
('Carlos', 'Pérez', 'Girón', 'Medellín'),
('Ana', 'Gómez', 'Medellín', 'Bucaramanga'),
('Luis', 'Rodríguez', 'Envigado', 'Envigado');

SELECT * FROM Places;

-- Cambiar el domicilio de 'Carlos Pérez' de 'Medellín' a 'Envigado'
UPDATE People 
SET municipio_domicilio = 'Envigado'
WHERE nombre = 'Carlos' AND apellido = 'Pérez';

-- Eliminar el registro de 'Ana Gómez'
DELETE FROM People 
WHERE nombre = 'Ana' AND apellido = 'Gómez';


--- ## 4 ## ---
-- agregar las columnas de conteos a la vista que muestra la lista de departamentos y municipios (modificar vista).

CREATE OR REPLACE VIEW Departamento_Municipio_Codigos AS
SELECT 
    departamento,
    municipio,
    (codigo_departamento || codigo_municipio) AS codigo_municipio_completo,
    personas_viven,
    personas_trabajan
FROM Places;

SELECT * FROM Departamento_Municipio_Codigos;
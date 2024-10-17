DELIMITER $$
CREATE PROCEDURE agregarSeguro (
	IN auxIDSeguro int(11),
    IN auxDescripcion VARCHAR(100),
    IN auxTipoSeguro VARCHAR(100),
    IN auxCostoContratacion bigint(20),
    IN auxCostoMaximoAsegurado bigint(20)
)
BEGIN
    INSERT INTO seguros (IDSeguro,Descripcion, TipoSeguro,CostoContratacion,CostomaximoAsegurado) 
    VALUES (auxIDSeguro,auxDescripcion, auxTipoSeguro,auxCostoContratacion,auxCostoMaximoAsegurado);
END$$
DELIMITER ;




DELIMITER $$
CREATE PROCEDURE listarSeguros ()
BEGIN
    SELECT IDSeguro,Descripcion, TipoSeguro,CostoContratacion,CostomaximoAsegurado FROM seguros;
END$$
DELIMITER ;




USE tech_challenge;

-- drop das SPs
DROP procedure IF EXISTS seed_tipo_pagamento;
DROP procedure IF EXISTS seed_categoria;

-- alterando delimitador
DELIMITER $$

-- SP para tipo de pagamento
CREATE PROCEDURE `seed_tipo_pagamento` ()
BEGIN
	DECLARE count_nome DECIMAL DEFAULT 0;

	SELECT COUNT(nome) INTO count_nome FROM tipo_pagamento;

	    IF count_nome = 0 THEN
		INSERT INTO tipo_pagamento (nome) VALUES ('QR Code');
	    END IF;
END$$

-- SP para categoria
CREATE PROCEDURE `seed_categoria` ()
BEGIN
	DECLARE count_nome DECIMAL DEFAULT 0;

	SELECT COUNT(nome) INTO count_nome FROM categoria;

	    IF count_nome = 0 THEN
		INSERT INTO categoria (nome) VALUES ('Lanche');
		INSERT INTO categoria (nome) VALUES ('Acompanhamento');
		INSERT INTO categoria (nome) VALUES ('Bebida');
		INSERT INTO categoria (nome) VALUES ('Sobremesa');
	    END IF;
END$$

-- alterando delimitador
DELIMITER ;

-- chamando SPs
CALL seed_tipo_pagamento();
CALL seed_categoria();

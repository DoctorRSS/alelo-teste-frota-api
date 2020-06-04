CREATE TABLE vehicle (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	plate VARCHAR(8) NOT NULL,
	model VARCHAR(50) NOT NULL,
	manufacturer VARCHAR(50),
	color VARCHAR(20),
	status BOOLEAN NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('AAA-1111', 'Classe A200', 'Mercedez Benz', 'cinza', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('BBB-1111', '323i', 'BMW', 'azul', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('AAA-2222', 'Lancer Evolution X', 'Mitsubish', 'branco', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('BBB-2222', 'Enzo', 'Ferrari', 'vermelho', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('CCC-1111', 'Gallardo', 'Lamborghini', 'laranja', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('DDD-4444', 'Huracan', 'Lamborghini', 'preto', false);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('EEE-2222', 'Corvette ZR1', 'GM', 'vermelho', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('FFF-2222', 'Camaro', 'GM', 'amarelo', false);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('BBB-9999', '911 Turbo', 'Porsche', 'branco', false);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('CCC-0000', 'Panamera', 'Porsche', 'marrom', true);
INSERT INTO vehicle (plate, model, manufacturer, color, status) values ('LLL-7777', 'A4', 'Audi', 'preto', true);


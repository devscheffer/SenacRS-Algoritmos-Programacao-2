CREATE TABLE categorias (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	nome VARCHAR(100) NOT NULL 
);

CREATE TABLE produtos (
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT ,
	nome VARCHAR(100) NOT NULL ,
	preco DOUBLE DEFAULT 0.0 ,
	quantidade DOUBLE ,
	codCategoria INT NOT NULL ,
	FOREIGN KEY (codCategoria) REFERENCES categorias(id)
);
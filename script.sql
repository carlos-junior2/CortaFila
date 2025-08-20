use corta_fila;

CREATE TABLE usuario (
  id int NOT NULL PRIMARY KEY AUTO_INCREMENT,
  nome varchar(100) NOT NULL,
  username varchar(20) NOT NULL UNIQUE,
  email varchar(100) NOT NULL UNIQUE,
  senha varchar(100) NOT NULL,
  role varchar(20) NOT NULL
);

create table barbearias (
	id  INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(100) NOT NULL,
    descricao TEXT,
	email VARCHAR(100) UNIQUE,
    imagem_patch VARCHAR(255),
	criado_em DATETIME,
	atualizado_em DATETIME
);

CREATE TABLE enderecos (
	id INT PRIMARY KEY AUTO_INCREMENT,
    barbearia_id INT,
	logradouro VARCHAR(255),
    bairro VARCHAR(100),
	cidade VARCHAR(100),
	estado VARCHAR(2),
    cep VARCHAR(10),
    ponto_de_referencia VARCHAR(255),
  	telefone VARCHAR(20),
    FOREIGN KEY(barbearia_id) REFERENCES barbearias(id)
);

CREATE TABLE barbeiros (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_barbearia INT NOT NULL,
    criado_em DATETIME,
	atualizado_em DATETIME,

    -- Garantir que um mesmo usuário não seja barbeiro duplicado na mesma barbearia
    UNIQUE KEY uq_usuario_barbearia (id_usuario, id_barbearia),

    -- Relacionamentos
    CONSTRAINT fk_barbeiro_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_barbeiro_barbearia
        FOREIGN KEY (id_barbearia) REFERENCES barbearias(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE tipo_servico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL,
    descricao VARCHAR(255),
    criado_em DATETIME,
	atualizado_em DATETIME
);

CREATE TABLE barbeiro_servico (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_barbeiro INT NOT NULL,
    id_tipo_servico INT NOT NULL,
    preco DECIMAL(10,2) NOT NULL,
    duracao_min INT NOT NULL,
    criado_em DATETIME,
	atualizado_em DATETIME,
    CONSTRAINT fk_barbeiro
        FOREIGN KEY (id_barbeiro)
        REFERENCES barbeiros(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_tipo_servico
        FOREIGN KEY (id_tipo_servico)
        REFERENCES tipo_servico(id)
        ON DELETE CASCADE,
    CONSTRAINT uq_barbeiro_servico UNIQUE (id_barbeiro, id_tipo_servico)
);

CREATE TABLE horario_trabalho (
    id INT PRIMARY KEY AUTO_INCREMENT,
    id_barbeiro INT NOT NULL,
    dia_semana VARCHAR(20) NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fim TIME NOT NULL,

    CONSTRAINT fk_horario_barbeiro FOREIGN KEY (id_barbeiro)
        REFERENCES barbeiros(id) ON DELETE CASCADE
);

create table agendamentos(
	id INT PRIMARY KEY AUTO_INCREMENT,
    id_barbeiro INT NOT NULL,
    id_usuario INT,
    id_barbeiro_servico INT NOT NULL,
    data DATE NOT NULL,
    horario TIME NOT NULL,
    criado_em DATETIME,
    atualizado_em DATETIME,

    CONSTRAINT fk_agendamento_barbeiro
        FOREIGN KEY (id_barbeiro) REFERENCES barbeiros(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE,

    CONSTRAINT fk_agendamento_usuario
        FOREIGN KEY (id_usuario) REFERENCES usuario(id)
        ON DELETE SET NULL
        ON UPDATE CASCADE,

    CONSTRAINT fk_agendamento_servico
        FOREIGN KEY (id_barbeiro_servico) REFERENCES barbeiro_servico(id)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
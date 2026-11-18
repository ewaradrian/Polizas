-- Script SQL Definitivo (3FN) - Sistema de Gestión de Pólizas
CREATE DATABASE IF NOT EXISTS gestor_polizas
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE gestor_polizas;

CREATE TABLE IF NOT EXISTS CLIENTE (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    telefono VARCHAR(15) NOT NULL,
    email VARCHAR(100) NOT NULL,
    rfc VARCHAR(13) NOT NULL,
    CONSTRAINT pk_cliente PRIMARY KEY (id),
    CONSTRAINT uk_cliente_email UNIQUE (email),
    CONSTRAINT uk_cliente_rfc UNIQUE (rfc)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS MARCA (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(50) NOT NULL,
    CONSTRAINT pk_marca PRIMARY KEY (id),
    CONSTRAINT uk_marca_nombre UNIQUE (nombre)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS VEHICULO (
    id INT AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idMarca INT NOT NULL,
    modelo VARCHAR(60) NOT NULL,
    anio INT NOT NULL,
    placas VARCHAR(15) NOT NULL,
    numeroSerie VARCHAR(30) NOT NULL,
    CONSTRAINT pk_vehiculo PRIMARY KEY (id),
    CONSTRAINT uk_vehiculo_placas UNIQUE (placas),
    CONSTRAINT uk_vehiculo_serie UNIQUE (numeroSerie),
    CONSTRAINT chk_vehiculo_anio CHECK (anio >= 1950),
    CONSTRAINT fk_vehiculo_cliente FOREIGN KEY (idCliente)
        REFERENCES CLIENTE(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_vehiculo_marca FOREIGN KEY (idMarca)
        REFERENCES MARCA(id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS ASEGURADORA (
    id INT AUTO_INCREMENT,
    nombre VARCHAR(80) NOT NULL,
    telefonoContacto VARCHAR(15) NOT NULL,
    CONSTRAINT pk_aseguradora PRIMARY KEY (id),
    CONSTRAINT uk_aseguradora_nombre UNIQUE (nombre)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS COBERTURA (
    id INT AUTO_INCREMENT,
    tipo VARCHAR(50) NOT NULL,
    descripcion TEXT NOT NULL,
    CONSTRAINT pk_cobertura PRIMARY KEY (id),
    CONSTRAINT uk_cobertura_tipo UNIQUE (tipo)
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS POLIZA (
    id INT AUTO_INCREMENT,
    idCliente INT NOT NULL,
    idVehiculo INT NOT NULL,
    idAseguradora INT NOT NULL,
    idCobertura INT NOT NULL,
    numeroPoliza VARCHAR(30) NOT NULL,
    fechaInicio DATE NOT NULL,
    fechaFin DATE NOT NULL,
    estado ENUM('Activa', 'Por vencer', 'Vencida', 'Cancelada') NOT NULL DEFAULT 'Activa',
    CONSTRAINT pk_poliza PRIMARY KEY (id),
    CONSTRAINT uk_poliza_numero UNIQUE (numeroPoliza),
    CONSTRAINT chk_poliza_fechas CHECK (fechaFin > fechaInicio),
    CONSTRAINT fk_poliza_cliente FOREIGN KEY (idCliente)
        REFERENCES CLIENTE(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_poliza_vehiculo FOREIGN KEY (idVehiculo)
        REFERENCES VEHICULO(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_poliza_aseguradora FOREIGN KEY (idAseguradora)
        REFERENCES ASEGURADORA(id) ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_poliza_cobertura FOREIGN KEY (idCobertura)
        REFERENCES COBERTURA(id) ON UPDATE CASCADE ON DELETE RESTRICT
) ENGINE=InnoDB;

CREATE TABLE IF NOT EXISTS PAGO (
    id INT AUTO_INCREMENT,
    idPoliza INT NOT NULL,
    fechaPago DATE NOT NULL,
    monto DECIMAL(10, 2) NOT NULL,
    metodoPago ENUM('Efectivo', 'Transferencia', 'Tarjeta Crédito', 'Tarjeta Débito') NOT NULL,
    CONSTRAINT pk_pago PRIMARY KEY (id),
    CONSTRAINT chk_pago_monto CHECK (monto > 0),
    CONSTRAINT fk_pago_poliza FOREIGN KEY (idPoliza)
        REFERENCES POLIZA(id) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB;

CREATE USER 'aluno'@'%' IDENTIFIED BY 'segredo';

GRANT ALL PRIVILEGES ON treino_java_spring.* TO 'aluno'@'%';

FLUSH PRIVILEGES;

SHOW GRANTS FOR 'aluno'@'%';

CREATE TABLE roles (
    id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
    name VARCHAR(30) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY user_role (name)
);

create table users (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	username VARCHAR(50) NOT NULL,
	password VARCHAR(100) NOT NULL,
	is_active BOOLEAN NOT NULL DEFAULT TRUE,
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    UNIQUE KEY uq_users_username (username)
)

CREATE TABLE user_roles (
    user_id INT UNSIGNED NOT NULL,
    role_id TINYINT UNSIGNED NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id)
);

create table status (
	id TINYINT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	status_name VARCHAR(100) NOT NULL,
	UNIQUE KEY uq_status_name (status_name)
)

create table plagues (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	plague_name VARCHAR(100) NOT NULL,
	UNIQUE KEY uq_plague_name (plague_name)
)

create table customer_status (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	status_name VARCHAR(100) NOT NULL,
	UNIQUE KEY uq_customer_status_name (status_name)
)

create table services (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	service_name VARCHAR(100) NOT NULL,
	UNIQUE KEY uq_service_name (service_name)
)

create table customers (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	status_id INT UNSIGNED,
	FOREIGN KEY (status_id) REFERENCES customer_status(id),
	name VARCHAR(100) NOT NULL,
	cpf CHAR(11),
	cnpj CHAR(14),
	phone CHAR(13) NOT NULL,
	email VARCHAR(100) NOT NULL,
	created_by INT UNSIGNED NOT NULL,
    edited_by INT UNSIGNED NULL,
	UNIQUE KEY uq_customer_name (name),
	created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON
UPDATE
	CURRENT_TIMESTAMP,
	CONSTRAINT fk_cusomer_created_by
    	FOREIGN KEY (created_by) REFERENCES users(id),
	CONSTRAINT fk_cusomer_edited_by
        FOREIGN KEY (edited_by) REFERENCES users(id),
	CONSTRAINT chk_customer_document
    CHECK (
	    (cpf IS NOT NULL
		AND cnpj IS NULL)
	OR
	    (cnpj IS NOT NULL
		AND cpf IS NULL)
	)
);

CREATE TABLE properties (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    customer_id INT UNSIGNED NOT NULL,
    nickname VARCHAR(80) NULL,
    cep CHAR(8) NOT NULL,
    street VARCHAR(120) NOT NULL,
    number VARCHAR(20) NOT NULL,
    complement VARCHAR(80) NULL,
    district VARCHAR(80) NULL,
    city VARCHAR(80) NOT NULL,
    state CHAR(2) NOT NULL,
    country VARCHAR(80) NOT NULL DEFAULT 'Brasil',
    formatted_address VARCHAR(255) NULL,
    google_place_id VARCHAR(150) NULL,
    latitude DECIMAL(10,8) NULL,
    longitude DECIMAL(11,8) NULL,
    property_type ENUM('PUBLICO', 'PRIVADO') NOT NULL,
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    CONSTRAINT fk_properties_customer
        FOREIGN KEY (customer_id) REFERENCES customers(id),
    UNIQUE KEY uq_properties_place_id (google_place_id)
);

CREATE TABLE schedules (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    property_id INT UNSIGNED NOT NULL,
    plague_id INT UNSIGNED NOT NULL,
    service_id INT UNSIGNED NOT NULL,
    created_by INT UNSIGNED NOT NULL,
    assigned_technician_id INT UNSIGNED NULL,
    status_id TINYINT UNSIGNED NOT NULL,
    scheduled_start DATETIME NOT NULL,
    scheduled_end DATETIME NULL,
    completed_at DATETIME NULL,
    notes TEXT NULL,
    deleted_at DATETIME NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    FOREIGN KEY (plague_id) REFERENCES plagues(id),
    FOREIGN KEY (service_id) REFERENCES services(id),
    CONSTRAINT fk_schedules_property
        FOREIGN KEY (property_id) REFERENCES properties(id),
    CONSTRAINT fk_schedules_created_by
        FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_schedules_technician
        FOREIGN KEY (assigned_technician_id) REFERENCES users(id),
    CONSTRAINT fk_schedules_status
        FOREIGN KEY (status_id) REFERENCES status(id)
);

CREATE TABLE history (
    id INT UNSIGNED NOT NULL AUTO_INCREMENT,
    schedule_id INT UNSIGNED NOT NULL,
    property_id INT UNSIGNED NOT NULL,
    plague_id INT UNSIGNED NOT NULL,
    service_id INT UNSIGNED NOT NULL,
    technician_id INT UNSIGNED NULL,
    status_id TINYINT UNSIGNED NOT NULL,
    execution_date DATETIME NOT NULL,
    notes TEXT NULL,
    certificate_url VARCHAR(255) NULL,
    created_by INT UNSIGNED NOT NULL,
    edited_by INT UNSIGNED NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    PRIMARY KEY (id),
    UNIQUE KEY uq_history_schedule (schedule_id),
    CONSTRAINT fk_history_schedule
        FOREIGN KEY (schedule_id) REFERENCES schedules(id),
    CONSTRAINT fk_history_property
        FOREIGN KEY (property_id) REFERENCES properties(id),
    CONSTRAINT fk_history_plague
        FOREIGN KEY (plague_id) REFERENCES plagues(id),
    CONSTRAINT fk_history_service
        FOREIGN KEY (service_id) REFERENCES services(id),
    CONSTRAINT fk_history_technician
        FOREIGN KEY (technician_id) REFERENCES users(id),
    CONSTRAINT fk_history_status
        FOREIGN KEY (status_id) REFERENCES status(id),
    CONSTRAINT fk_history_created_by
        FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_history_edited_by
        FOREIGN KEY (edited_by) REFERENCES users(id)
);

create table audit_log (
	id INT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY (id),
	user_id INT UNSIGNED,
	FOREIGN KEY (user_id) REFERENCES users(id),
	operation VARCHAR(100) NOT NULL,
	method ENUM('GET', 'POST', 'PUT', 'DELETE') NOT NULL,
	`table` ENUM('users', 'status', 'plagues', 'customer_status', 'services', 'customers', 'properties') NOT NULL,
	created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP(),
	created_by INT UNSIGNED NOT NULL,
	CONSTRAINT fk_audit_created_by
        FOREIGN KEY (created_by) REFERENCES users(id)
)

CREATE TABLE refresh_tokens (
    id INT NOT NULL AUTO_INCREMENT,
    PRIMARY KEY (id),
    user_id INT UNSIGNED,
    FOREIGN KEY (user_id) REFERENCES users(id),
    token VARCHAR(512) NOT NULL UNIQUE,
    expires_at TIMESTAMP NOT NULL,
    revoked BOOLEAN NOT NULL DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_refresh_tokens_user FOREIGN KEY (user_id) REFERENCES users(id)
);


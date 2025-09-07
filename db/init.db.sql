CREATE TABLE users
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    username      VARCHAR(50) UNIQUE  NOT NULL,
    email         VARCHAR(100) UNIQUE NOT NULL,
    phone         VARCHAR(20) UNIQUE,
    password      VARCHAR(255)        NOT NULL,
    status        VARCHAR(20) DEFAULT 'ACTIVE', -- ACTIVE, INACTIVE, LOCKED
    created_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP   DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    last_login_at TIMESTAMP
);

CREATE TABLE roleEnums
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(50) UNIQUE NOT NULL, -- ví dụ: ADMIN, USER
    name        VARCHAR(100)       NOT NULL,
    description TEXT
);

CREATE TABLE permissions
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    code        VARCHAR(100) UNIQUE NOT NULL,
    name        VARCHAR(100)        NOT NULL,
    description TEXT
);

CREATE TABLE user_roles
(
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (role_id) REFERENCES roleEnums (id)
);


CREATE TABLE role_permissions
(
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    FOREIGN KEY (role_id) REFERENCES roleEnums (id),
    FOREIGN KEY (permission_id) REFERENCES permissions (id)
);

CREATE TABLE user_sessions
(
    id            BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id       BIGINT NOT NULL,
    device_info   VARCHAR(255),
    ip_address    VARCHAR(50),
    refresh_token VARCHAR(255) UNIQUE,
    expired_at    TIMESTAMP,
    created_at    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);

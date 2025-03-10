-- Criação da tabela 'users'
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

-- Criação da tabela 'roles'
CREATE TABLE roles (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) UNIQUE NOT NULL
);

-- Criação da tabela 'user_roles' para o relacionamento muitos-para-muitos entre 'users' e 'roles'
CREATE TABLE user_roles (
    user_id BIGINT,
    role_id BIGINT,
    PRIMARY KEY (user_id, role_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

-- Criação da tabela 'product' com a chave estrangeira para 'users'
CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT,
    price DECIMAL(10, 2),
    observation VARCHAR(255),
    user_id BIGINT,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Criação da tabela 'comments' com chaves estrangeiras para 'product' e 'users'
CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    product_id BIGINT,
    user_id BIGINT,
    FOREIGN KEY (product_id) REFERENCES product(id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

-- Inserção de dados iniciais na tabela 'users' (senhas já hashadas com bcrypt)
INSERT INTO users (name, email, password) VALUES
('Alice Johnson', 'alice.johnson@example.com', '$2a$12$G9f5O3cUzBXB4Z8MKnU98eB9e7cz7kUfpHK21fTxBk5X.c4tw7I/u'),
('Bob Smith', 'bob.smith@example.com', '$2a$12$pOw5/Uckktw/RM6yW97x1e3YQmlEZ6iy.a3HZ5hL/c4JcXl1wnJS2'),
('Charlie Brown', 'charlie.brown@example.com', '$2a$12$5yM6Hh8gFfKUjAcVkt5mveXX6rD.FbwIJU6r/MQ5.1NYcUqW3mC2W'),
('David Wilson', 'david.wilson@example.com', '$2a$12$zBGGAmkJx2Sz/eS3RYoO6OCJ69T6fEIVTNRDEJmE5H5JL8ZDpdtj2'),
('Emma Davis', 'emma.davis@example.com', '$2a$12$QPT5G5/n53os6R2oJ66Eze.PB/5AVzXZSoDbmhC/ExlT1n6qIdUBC');

-- Inserção de dados iniciais na tabela 'roles'
INSERT INTO roles (name) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

-- Inserção de dados iniciais na tabela 'user_roles'
INSERT INTO user_roles (user_id, role_id) VALUES
(1, 1),
(2, 2),
(3, 2),
(4, 2),
(5, 2);

-- Inserção de dados iniciais na tabela 'product'
INSERT INTO product (name, quantity, price, observation, user_id) VALUES
('Laptop', 10, 999.99, 'High performance laptop', 1),
('Smartphone', 20, 499.99, 'Latest model smartphone', 2),
('Tablet', 15, 299.99, '10-inch screen tablet', 3),
('Smartwatch', 25, 199.99, 'Fitness tracking smartwatch', 4),
('Headphones', 50, 89.99, 'Noise-cancelling headphones', 5);

-- Inserção de dados iniciais na tabela 'comments'
INSERT INTO comments (text, product_id, user_id) VALUES
('Great product!', 1, 1),
('Very satisfied with this purchase.', 2, 2),
('Could be better.', 3, 3),
('Excellent quality!', 4, 4),
('Not worth the price.', 5, 5);
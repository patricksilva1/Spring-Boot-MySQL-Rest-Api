CREATE TABLE people (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    cpf VARCHAR(20),
    phone VARCHAR(20),
    address VARCHAR(255),
    city VARCHAR(100),
    state VARCHAR(100),
    zipCode VARCHAR(20),
    country VARCHAR(100),
    gender VARCHAR(20),
    dateOfBirth VARCHAR(20),
    password VARCHAR(255) NOT NULL DEFAULT ''
);

CREATE TABLE product (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT,
    price DECIMAL(10, 2),
    observation VARCHAR(255),
    people_id BIGINT,
    FOREIGN KEY (people_id) REFERENCES people(id)
);

INSERT INTO people (name, email, cpf, phone, address, city, state, zipCode, country, gender, dateOfBirth, password) VALUES
('Alice Johnson', 'alice.johnson@example.com', '12345678901', '111111111', 'Rua A, 100', 'Cidade A', 'Estado A', '00001', 'País A', 'F', '1990-01-01', 'senhaAlice'),
('Bob Smith', 'bob.smith@example.com', '98765432100', '222222222', 'Rua B, 200', 'Cidade B', 'Estado B', '00002', 'País B', 'M', '1985-05-05', 'senhaBob'),
('Charlie Brown', 'charlie.brown@example.com', '11122233344', '333333333', 'Rua C, 300', 'Cidade C', 'Estado C', '00003', 'País C', 'M', '1978-03-15', 'senhaCharlie'),
('David Wilson', 'david.wilson@example.com', '55566677788', '444444444', 'Rua D, 400', 'Cidade D', 'Estado D', '00004', 'País D', 'M', '1992-12-10', 'senhaDavid'),
('Emma Davis', 'emma.davis@example.com', '99988877766', '555555555', 'Rua E, 500', 'Cidade E', 'Estado E', '00005', 'País E', 'F', '1995-07-20', 'senhaEmma');

INSERT INTO product (name, quantity, price, observation, people_id) VALUES
('Laptop', 10, 999.99, 'High performance laptop', 1),
('Smartphone', 20, 499.99, 'Latest model smartphone', 2),
('Tablet', 15, 299.99, '10-inch screen tablet', 3),
('Smartwatch', 25, 199.99, 'Fitness tracking smartwatch', 4),
('Headphones', 50, 89.99, 'Noise-cancelling headphones', 5);

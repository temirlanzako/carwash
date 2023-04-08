CREATE TABLE BaseModel
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE City
(
    id     BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name   VARCHAR(255),
    region VARCHAR(255),
    code   VARCHAR(255)
);

CREATE TABLE VehicleType
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    type VARCHAR(255)
);

CREATE TABLE VehicleWashType
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name VARCHAR(255)
);

CREATE TABLE Permission
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name VARCHAR(255)
);

CREATE TABLE User
(
    id       BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    email    VARCHAR(255),
    password VARCHAR(255)
);

CREATE TABLE Client
(
    id      BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name    VARCHAR(255),
    surname VARCHAR(255),
    phone   VARCHAR(255),
    city_id BIGINT REFERENCES City (id) ON DELETE SET NULL,
    user_id BIGINT REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE Company
(
    id       BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name     VARCHAR(255),
    address  VARCHAR(255),
    phone    VARCHAR(255),
    capacity SMALLINT,
    user_id  BIGINT REFERENCES User (id) ON DELETE CASCADE
);

CREATE TABLE Company_VehicleType
(
    company_id     BIGINT REFERENCES Company (id),
    vehicleType_id BIGINT REFERENCES VehicleType (id),
    PRIMARY KEY (company_id, vehicleType_id)
);

CREATE TABLE Company_VehicleWashType
(
    company_id      BIGINT REFERENCES Company (id),
    serviceTypes_id BIGINT REFERENCES VehicleWashType (id),
    PRIMARY KEY (company_id, serviceTypes_id)
);

CREATE TABLE Company_City
(
    company_id BIGINT REFERENCES Company (id),
    city_id    BIGINT REFERENCES City (id),
    PRIMARY KEY (company_id, city_id)
);

CREATE TABLE Order
(
    id             BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    client_id      BIGINT REFERENCES Client (id),
    company_id     BIGINT REFERENCES Company (id),
    serviceType_id BIGINT REFERENCES VehicleWashType (id),
    vehicleType_id BIGINT REFERENCES VehicleType (id),
    city_id        BIGINT REFERENCES City (id),
    date           DATE,
    isCompleted    BOOLEAN
);

CREATE TABLE Review
(
    id         BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    client_id  BIGINT REFERENCES Client (id),
    company_id BIGINT REFERENCES Company (id),
    review     TEXT,
    date       DATE
);
CREATE TABLE BaseModel
(
    id BIGINT AUTO_INCREMENT PRIMARY KEY
);

CREATE TABLE City
(
    id     BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name   VARCHAR(255) NOT NULL,
    region VARCHAR(255) NOT NULL,
    code   VARCHAR(255) NOT NULL
);

CREATE TABLE VehicleType
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    type VARCHAR(255) NOT NULL
);

CREATE TABLE VehicleWashType
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE Permission
(
    id   BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name VARCHAR(255) NOT NULL
);

CREATE TABLE User
(
    id       BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    email    VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
CREATE TABLE UserPermission

(
    user_id BIGINT REFERENCES User (id),
    permission_id BIGINT REFERENCES Permission (id),
    PRIMARY KEY (user_id, permission_id)
);

CREATE TABLE Client
(
    id      BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name    VARCHAR(255)                                  NOT NULL,
    surname VARCHAR(255)                                  NOT NULL,
    phone   VARCHAR(255)                                  NOT NULL UNIQUE,
    city_id BIGINT                                        REFERENCES City (id) ON DELETE SET NULL,
    user_id BIGINT REFERENCES User (id) ON DELETE CASCADE NOT NULL
);

CREATE TABLE Company
(
    id             BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    name           VARCHAR(255)                                  NOT NULL,
    address        VARCHAR(255)                                  NOT NULL,
    phone          VARCHAR(255)                                  NOT NULL UNIQUE,
    capacity       SMALLINT                                      NOT NULL CHECK (capacity >= 0),
    availableBoxes SMALLINT                                      NOT NULL CHECK (availableBoxes <= capacity),
    user_id        BIGINT REFERENCES User (id) ON DELETE CASCADE NOT NULL
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

CREATE TABLE OrderBase
(
    id             BIGINT PRIMARY KEY REFERENCES BaseModel (id),
    client_id      BIGINT REFERENCES Client (id)          NOT NULL,
    company_id     BIGINT REFERENCES Company (id)         NOT NULL,
    serviceType_id BIGINT REFERENCES VehicleWashType (id) NOT NULL,
    vehicleType_id BIGINT REFERENCES VehicleType (id)     NOT NULL,
    city_id        BIGINT REFERENCES City (id),
    date           DATE,
    isCompleted    BOOLEAN
);

CREATE TABLE Rating
(
    id BIGINT PRIMARY KEY REFERENCES BaseModel(id),
    grade INT NOT NULL,
    company_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (company_id) REFERENCES company(id)
);


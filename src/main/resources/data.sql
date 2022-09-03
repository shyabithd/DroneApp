CREATE TABLE DRONE (
    serial_number VARCHAR(100),
    drone_model VARCHAR(20),
    weight INT,
    battery_capacity INT,
    drone_state VARCHAR(30)
);

CREATE TABLE PACKAGE (
    name VARCHAR(100),
    weight INT,
    code VARCHAR(20),
    img_name VARCHAR(100),
    drone_serial_number VARCHAR(100),
    timestamp VARCHAR(30)
);

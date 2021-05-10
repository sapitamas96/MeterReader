INSERT INTO meter_reading(client_id, meter_reading.month, consumption, meter_reading.year)
VALUES ('Fj8VJPcYnF', 'December', 17, 2020),
       ('Fj8VJPcYnF', 'January', 10.5, 2021),
       ('Fj8VJPcYnF', 'February', 15, 2021),
       ('Fj8VJPcYnF', 'March', 14, 2021),
       ('Fj8VJPcYnF', 'April', 16.5, 2021),
       ('Fj8VJPcYnF', 'May', 12, 2021);

INSERT INTO meter_reading(client_id, meter_reading.month, consumption, meter_reading.year)
VALUES ('ePmXU7j3H5', 'September', 12, 2020),
       ('ePmXU7j3H5', 'October', 14, 2020),
       ('ePmXU7j3H5', 'November', 17.5, 2020),
       ('ePmXU7j3H5', 'December', 17, 2020),
       ('ePmXU7j3H5', 'January', 11, 2021),
       ('ePmXU7j3H5', 'February', 13, 2021),
       ('ePmXU7j3H5', 'March', 14.5, 2021),
       ('ePmXU7j3H5', 'April', 16, 2021),
       ('ePmXU7j3H5', 'May', 13.5, 2021);

INSERT INTO client(ip_address, client_id)
VALUES ('8.8.8.8', 'Fj8VJPcYnF'),
       ('8.8.4.4', 'ePmXU7j3H5');
INSERT INTO cosmetic_service (name, price) VALUES ('Nyomásterápia', 3000);
INSERT INTO cosmetic_service (name, price) VALUES ('Mezopen', 15000);
INSERT INTO cosmetic_service (name, price) VALUES ('Mezoterápiás testkezelés', 15000);
INSERT INTO cosmetic_service (name, price) VALUES ('Derma Clear kezelés problémás bőrre', 11000);
INSERT INTO cosmetic_service (name, price) VALUES ('Recorvery növényi őssektes kezelés', 12000);

INSERT INTO city (name, postal_code) VALUES ('Budapest',1038);
INSERT INTO city (name, postal_code) VALUES ('Budapest',1111);
INSERT INTO city (name, postal_code) VALUES ('Budapest',1122);
INSERT INTO city (name, postal_code) VALUES ('Miskolc',3500);
INSERT INTO city (name, postal_code) VALUES ('Szentendre',2000);

INSERT INTO customer (first_name, last_name, address_id, phone_Number, email)
VALUES ('János', 'Bíró', 1, '06706113333', 'birojanos@gmail.com');
INSERT INTO customer (first_name, last_name, address_id, phone_Number, email)
VALUES ('Zsolt', 'Zemen', 2, '06709823366', 'zemenzsolt@gmail.com');

INSERT INTO address (city_id, street, house_number, customer_id) VALUES (4,'Vörösmarty utca', 1, 1);
INSERT INTO address (city_id, street, house_number, customer_id) VALUES (3,'Ady utca', 5, 2);

INSERT INTO appointment (appointment_date_start, appointment_date_end, customer_id, cosmetic_service_id) VALUES ('2021-07-14 13:00:00', '2021-07-14 14:00:00', 1, 1);
INSERT INTO appointment (appointment_date_start, appointment_date_end, customer_id, cosmetic_service_id) VALUES ('2021-07-15 10:00:00', '2021-07-14 12:00:00', 2, 1);
INSERT INTO appointment (appointment_date_start, appointment_date_end, customer_id, cosmetic_service_id) VALUES ('2021-07-17 09:00:00', '2021-07-14 11:00:00', 2, 2);
INSERT INTO appointment (appointment_date_start, appointment_date_end, customer_id, cosmetic_service_id) VALUES ('2021-07-18 09:00:00', '2021-07-14 11:00:00', 1, 4);


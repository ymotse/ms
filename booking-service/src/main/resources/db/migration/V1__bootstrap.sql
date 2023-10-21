
CREATE SEQUENCE IF NOT EXISTS seq_booking_id
    START WITH 1
    INCREMENT BY 1
    NO CYCLE
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
    ;

CREATE TABLE IF NOT EXISTS booking (
    id INT default seq_booking_id.nextval PRIMARY KEY,
    description VARCHAR NOT NULL,
    value_per_day NUMBER(8,2) NOT NULL,
    currency VARCHAR NOT NULL
);

INSERT INTO booking (description, value_per_day, currency) VALUES('Xpto Hotel', 34, 'BRL');
INSERT INTO booking (description, value_per_day, currency) VALUES('ZigZag Resort', 121, 'USD');
INSERT INTO booking (description, value_per_day, currency) VALUES('Foo Hostel', 18, 'EUR');
INSERT INTO booking (description, value_per_day, currency) VALUES('Wtf Hotel', 45, 'BRL');
INSERT INTO booking (description, value_per_day, currency) VALUES('Xiss Hotel', 78, 'USD');

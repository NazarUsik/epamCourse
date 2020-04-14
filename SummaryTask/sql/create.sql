SET NAMES utf8;

DROP DATABASE IF EXISTS final_project_db;
CREATE DATABASE final_project_db CHARACTER SET utf8 COLLATE utf8_bin;
USE final_project_db;

CREATE TABLE Train
(
    id      int(10) NOT NULL AUTO_INCREMENT,
    type_id int(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Carriage
(
    id                    int(10) NOT NULL AUTO_INCREMENT,
    type_id               int(10) NOT NULL,
    count_seats           int(10) NOT NULL,
    count_available_seats int(10),
    haveRestaurant        boolean NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);


CREATE TABLE Train_composition
(
    train_id        int(10) NOT NULL,
    carriage_id     int(10) NOT NULL UNIQUE,
    carriage_number int(10) NOT NULL
);

CREATE TABLE Station
(
    id   int(10)      NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Schedule
(
    id             int(10)       NOT NULL AUTO_INCREMENT,
    departure_time timestamp(10) NOT NULL,
    arrival_time   timestamp(10) NOT NULL,
    parking_time   int(10)       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Train_route
(
    id                   int(10) NOT NULL AUTO_INCREMENT,
    train_id             int(10),
    departure_station_id int(10) NOT NULL,
    arrival_station_id   int(10) NOT NULL,
    schedule_id          int(10) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Intermediate_stations
(
    route_id    int(10) NOT NULL,
    schedule_id int(10) NOT NULL,
    station_id  int(10) NOT NULL UNIQUE,
    PRIMARY KEY (schedule_id)
);

CREATE TABLE Seats
(
    id          int(10) NOT NULL AUTO_INCREMENT,
    carriage_id int(10) NOT NULL,
    number      int(10) NOT NULL,
    available   boolean,
    PRIMARY KEY (id),
    INDEX (id)
);

CREATE TABLE Ticket
(
    id          int(10) NOT NULL AUTO_INCREMENT,
    route_id    int     not null,
    price       float   NOT NULL,
    carriage_id int(10),
    seat_id     int(10),
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE TicketsList
(
    user_id          int(10) NOT NULL,
    ticket_id        int(10) NOT NULL,
    ticket_status_id int(10)
);

CREATE TABLE User
(
    id         int(10)      NOT NULL AUTO_INCREMENT,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    email      varchar(255) NOT NULL UNIQUE,
    password   varchar(255) NOT NULL,
    role_id    int(1)       NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Role
(
    id   int(1)       NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Train_type
(
    id   int(10)      NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Carriage_type
(
    id   int(10)      NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);

CREATE TABLE Status
(
    id   int(10)      NOT NULL AUTO_INCREMENT,
    name varchar(255) NOT NULL UNIQUE,
    PRIMARY KEY (id),
    UNIQUE INDEX (id)
);


ALTER TABLE Train_composition
    ADD CONSTRAINT FKTrain_comp579423 FOREIGN KEY (carriage_id) REFERENCES Carriage (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE Train_composition
    ADD CONSTRAINT FKTrain_comp754659 FOREIGN KEY (train_id) REFERENCES Train (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE Train_route
    ADD CONSTRAINT FKTrain_rout202874 FOREIGN KEY (departure_station_id) REFERENCES Station (id)
        ON UPDATE Restrict
        ON DELETE Restrict;

ALTER TABLE Train_route
    ADD CONSTRAINT FKTrain_rout177624 FOREIGN KEY (arrival_station_id) REFERENCES Station (id)
        ON UPDATE Restrict
        ON DELETE Restrict;

ALTER TABLE Train_route
    ADD CONSTRAINT FKTrain_rout291038 FOREIGN KEY (schedule_id) REFERENCES Schedule (id)
        ON UPDATE Restrict
        ON DELETE Restrict;

ALTER TABLE Train_route
    ADD CONSTRAINT FKTrain_rout118488 FOREIGN KEY (train_id) REFERENCES Train (id)
        ON UPDATE Cascade
        ON DELETE Set null;

ALTER TABLE Intermediate_stations
    ADD CONSTRAINT FKIntermediate902119 FOREIGN KEY (route_id) REFERENCES Train_route (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE Intermediate_stations
    ADD CONSTRAINT FKIntermediate593866 FOREIGN KEY (station_id) REFERENCES Station (id)
        ON UPDATE Restrict
        ON DELETE Restrict;

ALTER TABLE Seats
    ADD CONSTRAINT FKSeats393219 FOREIGN KEY (carriage_id) REFERENCES Carriage (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE Ticket
    ADD CONSTRAINT FKTicket512153 FOREIGN KEY (route_id) REFERENCES train_route (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE Ticket
    ADD CONSTRAINT FKTicket786644 FOREIGN KEY (seat_id) REFERENCES Seats (id)
        ON UPDATE Cascade
        ON DELETE SET NULL;

ALTER TABLE Ticket
    ADD CONSTRAINT FKTicket542354 FOREIGN KEY (carriage_id) REFERENCES Carriage (id)
        ON DELETE SET NULL
        ON UPDATE CASCADE;

ALTER TABLE TicketsList
    ADD CONSTRAINT FKTicketsLis530419 FOREIGN KEY (ticket_id) REFERENCES Ticket (id)
        ON UPDATE Cascade
        ON DELETE Cascade;

ALTER TABLE TicketsList
    ADD CONSTRAINT FKUser306639 FOREIGN KEY (user_id) REFERENCES User (id)
        ON UPDATE Cascade
        ON DELETE CASCADE;

ALTER TABLE Intermediate_stations
    ADD CONSTRAINT FKIntermediate317484 FOREIGN KEY (schedule_id) REFERENCES Schedule (id)
        ON UPDATE Restrict
        ON DELETE Restrict;

ALTER TABLE User
    ADD CONSTRAINT FKUser87814 FOREIGN KEY (role_id) REFERENCES Role (id)
        ON UPDATE Restrict
        ON DELETE Cascade;

ALTER TABLE Train
    ADD CONSTRAINT FKTrain629609 FOREIGN KEY (type_id) REFERENCES Train_type (id)
        ON UPDATE Restrict
        ON DELETE Cascade;

ALTER TABLE Carriage
    ADD CONSTRAINT FKCarriage265687 FOREIGN KEY (type_id) REFERENCES Carriage_type (id)
        ON UPDATE Restrict
        ON DELETE Cascade;

ALTER TABLE TicketsList
    ADD CONSTRAINT FKTicketsLis798723 FOREIGN KEY (ticket_status_id) REFERENCES Status (id)
        ON UPDATE Restrict
        ON DELETE Set null;

INSERT INTO Role
VALUES (DEFAULT, 'admin'),
       (DEFAULT, 'user');

INSERT INTO User
VALUES (DEFAULT, 'Nazar', 'Usik', 'usik.nazar@gmail.com', 'nazar50510', 1),
       (DEFAULT, 'User', 'User', 'user.example@gmail.com', 'user12345', 2);

INSERT INTO Carriage_type
VALUES (DEFAULT, 'lux'),
       (DEFAULT, 'coupe'),
       (DEFAULT, 'platskart'),
       (DEFAULT, 'seat place');

INSERT INTO Carriage
VALUES (DEFAULT, 1, 10, 10, true),
       (DEFAULT, 1, 10, 10, true),
       (DEFAULT, 1, 10, 10, false),
       (DEFAULT, 1, 10, 10, false),
       (DEFAULT, 2, 35, 35, true),
       (DEFAULT, 2, 35, 35, true),
       (DEFAULT, 2, 35, 35, false),
       (DEFAULT, 2, 35, 35, false),
       (DEFAULT, 2, 35, 35, false),
       (DEFAULT, 2, 35, 35, false),
       (DEFAULT, 2, 35, 35, false),
       (DEFAULT, 3, 54, 54, true),
       (DEFAULT, 3, 54, 54, true),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 3, 54, 54, false),
       (DEFAULT, 4, 65, 65, true),
       (DEFAULT, 4, 65, 65, false),
       (DEFAULT, 4, 65, 65, false),
       (DEFAULT, 4, 65, 65, false),
       (DEFAULT, 4, 65, 65, false);

#Insert into Seats
#
# public static void main(String[] s) throws SQLException, ClassNotFoundException {
#   Connection conn = MySQLConnectionUtils.getMySQLConnection
#                 ("localhost", "final_project_db", "root", "admin");
#
#   ResultSet res = conn.createStatement().executeQuery("SELECT count_seats FROM Carriage;");
#
#   int i = 1;
#   while (res.next()) {
#       int number = 0;
#       int sum = res.getInt(1);
#       for (int j = 0; j < sum; j++) {
#           PreparedStatement statement = conn.prepareStatement("INSERT INTO seats VALUES (DEFAULT, ?, ?, true)");
#           statement.setInt(1, i);
#           statement.setInt(2, ++number);
#           statement.execute();
#       }
#       i++;
#   }
# }

INSERT INTO Train_type
VALUES (DEFAULT, 'electric'),
       (DEFAULT, 'steam'),
       (DEFAULT, 'diesel');

INSERT INTO Train
VALUES (DEFAULT, 1),
       (DEFAULT, 2),
       (DEFAULT, 3);

-- #INSERT INTO Train_composition
-- #
-- # public static void main(String[] s) throws SQLException, ClassNotFoundException {
-- #   Connection conn = MySQLConnectionUtils.getMySQLConnection
-- #                 ("localhost", "final_project_db", "root", "admin");
-- #
-- #    ResultSet res = conn.createStatement().executeQuery("SELECT id FROM train;");
-- #
-- #
-- #   int numb = 1;
-- #   int train_id = 1;
-- #   while (res.next()) {
-- #       for (int i = 0; i < 10; i++) {
-- #           PreparedStatement statement = conn.prepareStatement("INSERT INTO train_composition VALUES (?, ?, ?)");
-- #           statement.setInt(1, train_id);
-- #           statement.setInt(2, numb++);
-- #           statement.setInt(3, i + 1);
-- #           statement.execute();
-- #       }
-- #       train_id++;
-- #   }
-- # }

INSERT INTO Train_composition
VALUES (3, 31, 11);

INSERT INTO Station
VALUES (DEFAULT, 'Kharkov'),
       (DEFAULT, 'Kiev'),
       (DEFAULT, 'Zaporozhye'),
       (DEFAULT, 'Lviv'),
       (DEFAULT, 'Vinnitsa'),
       (DEFAULT, 'Lozovay'),
       (DEFAULT, 'Pavlograd');

INSERT INTO Schedule
VALUES (DEFAULT, '2020-09-24 15:13', '2020-09-25 11:01', 1185),
       (DEFAULT, '2020-09-25 00:24', '2020-09-25 00:54', 551),
       (DEFAULT, '2020-09-25 04:05', '2020-09-25 04:10', 772),
       (DEFAULT, '2020-04-14 23:41', '2020-04-15 04:18', 277),
       (DEFAULT, '2020-04-15 01:35', '2020-04-15 01:37', 114),
       (DEFAULT, '2020-05-15 02:38', '2020-04-15 02:40', 177);

INSERT INTO Train_route
VALUES (DEFAULT, 3, 1, 4, 1),
       (DEFAULT, 1, 1, 3, 4);

INSERT INTO Intermediate_stations
VALUES (1, 2, 2),
       (1, 3, 5),
       (2, 5, 6),
       (2, 6, 7);

INSERT INTO Ticket
VALUES (DEFAULT, 1, 223.5, 31, 10),
       (DEFAULT, 2, 195, 31, 227);

INSERT INTO Status
VALUES (DEFAULT, 'Paid'),
       (DEFAULT, 'Not active');

INSERT INTO TicketsList
VALUES (2, 1, 1);



#####################################################
#Select all rout and her intermediate station       #
#                                                   #
#####################################################

SELECT dep.name          Departure_station,
       s.departure_time  Departure_time,
       ar.name           Arrival_station,
       s.arrival_time    Arrival_time,
       im.name           Intermediate_Station,
       si.departure_time Departure_time,
       si.arrival_time   Arrival_station
FROM train_route tr
         join intermediate_stations i on tr.id = i.route_id
         join schedule s on tr.schedule_id = s.id
         join schedule si on i.schedule_id = si.id
         join station dep on tr.departure_station_id = dep.id
         join station ar on tr.arrival_station_id = ar.id
         join station im on i.station_id = im.id;

######################################################
#                                                    #
#                                                    #
######################################################

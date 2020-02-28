USE CashDBv2
CREATE TABLE T_cash
(ID_cash INT PRIMARY KEY Identity,
ID_type_cash INT NOT NULL,
Type_cash VARCHAR NOT NULL,
Max_capacity INT NOT NULL,
Current_capacity INT NOT NULL,
FK_machine INT NOT NULL,
CONSTRAINT FK_MACHINE FOREIGN KEY (FK_machine) REFERENCES T_machines(ID_machine)
)
CREATE TABLE T_machines
(ID_machine INT PRIMARY KEY Identity,
ID_parking INT NOT NULL)

INSERT INTO T_cash(ID_type_cash,Type_cash,Max_capacity,Current_capacity,FK_machine)
VALUES (2,'coinIn2',1000,800,1),
(3,'coinIn3',1000,900,1),
(4,'coinIn4',1000,0,1),
(5,'coinIn5',1000,0,1),
(6,'coinIn6',1000,0,1),
(7,'coinOut2',1000,1000,1),
(8,'coinOut3',1000,150,1),
(9,'coinOut4',1000,200,1),
(10,'coinOut5',1000,1000,1),
(11,'coinOut6',1000,1000,1),
(12,'note1',200,0,1),
(13,'note2',200,161,1),
(14,'note3',200,160,1),
(15,'note4',200,120,1),
(2,'coinIn2',1000,800,2),
(3,'coinIn3',1000,653,2),
(4,'coinIn4',1000,691,2),
(5,'coinIn5',1000,398,2),
(6,'coinIn6',1000,0,2),
(7,'coinOut2',1000,1000,2),
(8,'coinOut3',1000,1000,2),
(9,'coinOut4',1000,200,2),
(10,'coinOut5',1000,400,2),
(11,'coinOut6',1000,400,2),
(12,'note1',200,100,2),
(13,'note2',200,100,2),
(14,'note3',200,100,2),
(15,'note4',200,100,2),
(2,'coinIn2',1000,50,3),
(3,'coinIn3',1000,50,3),
(4,'coinIn4',1000,1000,3),
(5,'coinIn5',1000,50,3),
(6,'coinIn6',1000,50,3),
(7,'coinOut2',1000,400,3),
(8,'coinOut3',1000,400,3),
(9,'coinOut4',1000,400,3),
(10,'coinOut5',1000,400,3),
(11,'coinOut6',1000,400,3),
(12,'note1',200,50,3),
(13,'note2',200,50,3),
(14,'note3',200,50,3),
(15,'note4',200,50,3)

INSERT INTO T_machines(ID_parking)
VALUES (1),(2),(3)
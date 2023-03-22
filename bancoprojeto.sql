CREATE TABLE supplier (
  Id int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE product (
  Id int(10) NOT NULL AUTO_INCREMENT,
  Name varchar(60) NOT NULL,
  Price double NOT NULL,
  Section varchar(60) NOT NULL,
  Supplier_id int(11) NOT NULL,
  PRIMARY KEY (Id),
  FOREIGN KEY (Supplier_id) REFERENCES supplier (id)
);

INSERT INTO supplier (Name) VALUES 
  ('MC Química'),
  ('Braskem'),
  ('Clariant');
  
INSERT INTO product (Name, Price, Section, Supplier_id) VALUES
('Alcohol', 12, 'Solvent', 1),
('Acetone', 7.9, 'Solvent', 1),
('Ether', 16, 'Solvent', 1),
('Sodium Chloride', 13, 'Salt', 2),
('Hydrochloric Acid', 35, 'Acid', 1),
('Lithium Hydroxide', 49.5, 'Alkali', 3);
 
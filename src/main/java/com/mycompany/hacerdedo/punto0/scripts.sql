CREATE TABLE users(
	ci integer PRIMARY KEY UNIQUE,
	name VARCHAR(80) NOT NULL,
	surname VARCHAR(80) NOT NULL,
	telephone VARCHAR(9) NOT NULL,
	password VARCHAR(100) NOT NULL,
	rating numeric(2,1) NOT NULL DEFAULT 0.0,
	type VARCHAR(8) NOT NULL DEFAULT 'Pasajero'
);

CREATE TABLE friends(
	ci_user integer NOT NULL,
	ci_friend integer NOT NULL,
	PRIMARY KEY (ci_user, ci_friend),
	FOREIGN KEY(ci_user) REFERENCES users(ci),
	FOREIGN KEY(ci_friend) REFERENCES users(ci)
);

CREATE TABLE conveyances(
	matricula VARCHAR(8) NOT NULL UNIQUE,
	ci_owner integer NOT NULL,
	model VARCHAR(50),
	color VARCHAR(15),
	type VARCHAR(5) NOT NULL,
	number_seats integer NOT NULL,
	PRIMARY KEY(matricula, ci_owner),
	FOREIGN KEY(ci_owner) REFERENCES users(ci)
);

CREATE TABLE travels(
	ci_driver integer NOT NULL,
	ci_passenger integer NOT NULL,
	date_ini TIMESTAMP NOT NULL,
	date_end TIMESTAMP,
	matricula_vehicle VARCHAR(8) NOT NULL,
	seats_available integer NOT NULL,
	companions integer NOT NULL,
	PRIMARY KEY(ci_driver, ci_passenger, date_ini),
	FOREIGN KEY(ci_driver) REFERENCES users(ci),
	FOREIGN KEY(ci_passenger) REFERENCES users(ci),
	FOREIGN KEY(matricula_vehicle) REFERENCES conveyances(matricula)
);

insert into users values(123,'user','surnme','212312','pass',0.0,'Pasajero');
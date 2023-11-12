CREATE TABLE `employee` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `name` varchar(255) DEFAULT NULL,
                            `age` int DEFAULT NULL,
                            `sex` int DEFAULT NULL,
                            PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

CREATE TABLE `employee_temp` (
                                 `id` int NOT NULL AUTO_INCREMENT,
                                 `name` varchar(255) DEFAULT NULL,
                                 `age` int DEFAULT NULL,
                                 `sex` int DEFAULT NULL,
                                 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
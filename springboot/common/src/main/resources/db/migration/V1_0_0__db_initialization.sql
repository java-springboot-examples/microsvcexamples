CREATE TABLE IF NOT EXISTS `customer` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `firstname` VARCHAR(50) NOT NULL,
    `lastname` VARCHAR(50) NOT NULL,
    `sin` VARCHAR(10) NULL,
    `dob` DATE NOT NULL,
    `created` DATETIME NOT NULL
) ENGINE = InnoDB;

INSERT INTO `customer` (`firstname`, `lastname`, `dob`, `created`) VALUES ('Mary', 'Hopkins', '1965-07-29', CURRENT_TIMESTAMP);
INSERT INTO `customer` (`firstname`, `lastname`, `dob`, `created`) VALUES ('John', 'Smith', '1970-10-20', CURRENT_TIMESTAMP);
INSERT INTO `customer` (`firstname`, `lastname`, `dob`, `created`) VALUES ('David', 'King', '1955-02-11', CURRENT_TIMESTAMP);


CREATE TABLE IF NOT EXISTS `account` (
    `id` VARCHAR(20) PRIMARY KEY,
    `customer` INT NOT NULL,
    `type` ENUM ('CHECKING','SAVING') NOT NULL,
    `currency` CHAR(3) NOT NULL default 'CAD',
    `created` DATETIME NOT NULL,
    INDEX idx_customer (`customer`),
    FOREIGN KEY (`customer`) REFERENCES customer(`id`)
) ENGINE = InnoDB;

INSERT INTO `account` (`id`, `customer`, `type`, `created`) VALUES ('12345678-0001', '1', 'CHECKING', CURRENT_TIMESTAMP);
INSERT INTO `account` (`id`, `customer`, `type`, `currency`, `created`) VALUES ('87654321-0002', '2', 'SAVING', 'USD', CURRENT_TIMESTAMP);
INSERT INTO `account` (`id`, `customer`, `type`, `currency`, `created`) VALUES ('87654321-0003', '2', 'CHECKING', 'CAD', CURRENT_TIMESTAMP);

CREATE TABLE IF NOT EXISTS `transaction` (
    `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `account` VARCHAR(20) NOT NULL,
    `amount` FLOAT NOT NULL,
    `time` DATETIME NOT NULL,
    INDEX idx_account (`account`),
    FOREIGN KEY (`account`) REFERENCES account(`id`)
) ENGINE = InnoDB;

INSERT INTO `transaction` (`account`, `amount`, `time`) VALUES ('12345678-0001', '200.0', CURRENT_TIMESTAMP);
INSERT INTO `transaction` (`account`, `amount`, `time`) VALUES ('12345678-0001', '-100.0', CURRENT_TIMESTAMP);
INSERT INTO `transaction` (`account`, `amount`, `time`) VALUES ('87654321-0002', '-300.0', CURRENT_TIMESTAMP);
INSERT INTO `transaction` (`account`, `amount`, `time`) VALUES ('87654321-0003', '-400.0', CURRENT_TIMESTAMP);
INSERT INTO `transaction` (`account`, `amount`, `time`) VALUES ('87654321-0003', '200.0', CURRENT_TIMESTAMP);

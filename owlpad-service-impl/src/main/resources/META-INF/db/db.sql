/*
drop schema if exists owlpad_configdb;
create schema owlpad_configdb;
use owlpad_configdb;

create table `layout` (
  id int(11) NOT NULL AUTO_INCREMENT,
  layout_type varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

create table `configuration` (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  layout_id int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_conf` FOREIGN KEY (`layout_id`) REFERENCES `layout` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `region` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `selector` varchar(255) DEFAULT NULL,
  `layout_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reg` (`layout_id`),
  CONSTRAINT `FK_reg` FOREIGN KEY (`layout_id`) REFERENCES `layout` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

*/
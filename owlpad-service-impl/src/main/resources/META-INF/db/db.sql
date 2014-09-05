drop schema if exists owlpaddb;
create schema owlpaddb;
use owlpaddb;

create table `layout` (
  id int(11) NOT NULL AUTO_INCREMENT,
  header_region varchar(45) DEFAULT NULL,
  left_region varchar(45) DEFAULT NULL,
  right_region varchar(45) DEFAULT NULL,
  footer_region varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

create table `configuration` (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(45) DEFAULT NULL,
  layout_id int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_conf` (`layout_id`),
  CONSTRAINT `fk_conf` FOREIGN KEY (`layout_id`) REFERENCES `layout` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

insert into layout (id,header_region,left_region,right_region,footer_region)
values (1,'.navbar-default', '.gridRegion', '.previewRegion', '.footer');

insert into configuration (id,name,layout_id) values (1,'default',1);

insert into layout (id,layout_type) values (1,'TWO_COLUMN');
insert into configuration (id,name,layout_id) values (1,'default',1);
insert into region (id,name,selector,layout_id) values (1,'header','.navbar-default',1);
insert into region (id,name,selector,layout_id) values (2,'left','.gridRegion',1);
insert into region (id,name,selector,layout_id) values (3,'right','.previewRegion',1);
insert into region (id,name,selector,layout_id) values (4,'footer','.footer',1);
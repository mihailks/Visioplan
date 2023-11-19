
INSERT INTO `buildings` VALUES (1,'45 Bd Raspail, 75006 Paris','Paris','France','https://res.cloudinary.com/daqpjk60x/image/upload/v1700420511/Lutetia_sia81r.jpg','Lutetia'),(2,'Gentsestraat 79, 8530 Harelbeke','Harelbeke','Belgium',NULL,'Varbel'),(3,'София, ж.к. Младост 4, бул. Александър Малинов №78 ПК: 1799','Sofia','Bugaria',NULL,'Softuni'),(4,'бул. „Пещерско шосе“ 80, 4002 Западна промишлена зона, Пловдив','Plovdiv','Bugaria',NULL,'УСБАЛАГ \"Селена\"'),(5,'Отдих и Култура 1М, 4015 Западна промишлена зона, Пловдив','Plovdiv','Bugaria',NULL,'Bugaria'),(6,'4MPX+PX, 4001 Западна промишлена зона, Пловдив','Plovdiv','Bugaria',NULL,'Западен парк резидънс 1 и 2'),(7,'Magnolia Residence, ул. „Рая“ 17, 4000 Западна промишлена зона, Пловдив','Plovdiv','Bugaria',NULL,'Plovdiv'),(8,'6, ул. „Лев Толстой“ str, 4017 Пловдив','Plovdiv','Bugaria',NULL,'Hotel Imperial Plovdiv'),(9,'ул. „Братя Миладинови“, 8800 Сливен Център, Сливен','Sliven','Bugaria',NULL,'6, ул. „Лев Толстой“ str, 4017 Пловдив'),(10,'ул. „Предел“ 1, 8800 Сливен Център, Сливен','Sliven','Bugaria',NULL,'Hotel Kredo'),(11,'бул. „Цар Освободител“ 6, 8800 Сливен Център, Сливен','Sliven','Bugaria',NULL,'Hotel Хотел Парк Централ'),(13,'Mестност \"Карандила, ж.к. Сини камъни, 8800 Сливен','Sliven','Bugaria',NULL,'Комплекс \"Сините камъни\"'),(14,'ул. „Проф. д-р Иван Странски“ 20, 1000 ж.к. Малинова долина, София','Sofia','Bugaria',NULL,'Кауфланд София-Малинова долина'),(15,'ул. „Владичина ливада“ 2, 1000 кв. Горубляне, София','Sofia','Bugaria',NULL,'Кауфланд София Горубляне'),(16,'fdghfdgh','dfghfdgh','dfghfgh','test','fdghfdgh'),(17,'dfghfdgh','dfghfdhg','dfghfdh','test','fghfgh'),(18,'sfdsd','fsdffddf','fsd','test','sfadf'),(19,'dfgsdfg','sdfgsdfg','sdfgsdfg','http://res.cloudinary.com/daqpjk60x/image/upload/v1698837590/td2d23qsxsfagj6xv69c.png','dsfgdsfg'),(20,'testAdrresdsdf23','Sliwen','Bulgariq','http://res.cloudinary.com/daqpjk60x/image/upload/v1699340097/eio4huz2nqw9hkfgrl25.png','testName1'),(21,'кладенче 42','Сливен','България','http://res.cloudinary.com/daqpjk60x/image/upload/v1699474770/nxp3ycfqrx1ruj5o69p1.png','Зорница'),(22,'Sliwen','Sliwen','Sliwen','http://res.cloudinary.com/daqpjk60x/image/upload/v1699954308/dymscmue8lgicuwxoltw.png','testBuilding');

INSERT INTO `companies` VALUES (1,'ul. \"Vasil Levski\" 272, 4003 Severna Promishlena Zona, Plovdiv','Plovdiv','Bulgaria','plovdiv@ataro.bg','Ataro Clima','+359 7001 1618','https://www.ataro.bg/en'),(2,'93B, Simeonovsko shosse, Blvd. entrance B, 2 nd floor','Sofia','Bulgaria','office@pipesystem.bg','Pipe System','+359 878 252 655','https://www.pipesystem.bg/'),(3,'бул. д-р Петър Дертлиев 129.','Sofia','Bulgaria','office@raicommerce.bg','Raicommerce','+359 02 9251444','https://www.raicommerce.bg/'),(4,'11, place de l\'Europe Campus Pierre-Berger, bât. D 78140 Vélizy-Villacoublay','Paris','France','office@eiffage.fr','Eiffage','+33 (0) 1 34 65 89 89','https://www.eiffage.com/en/'),(5,'Pl. de la Bastille, 75004 Paris','Paris','France','contact@sfica.fr','Sfica','+33 01 44 92 89 00','https://www.sfica.fr/'),(6,'The Alrov Tower 46 Rothschild Blvd.','Tel-Aviv','Israel','info@alrov.co.il','Alrov','+972-3-7147777','https://www.alrov.co.il/'),(7,'20 rue des Jardins 92600 Asnières-sur-Seine','Paris','France','contact@bouygues.fr','Bouygues ','+33 01 86 65 29 80','https://www.bouygues-es.fr/'),(8,'София, ж.к. Младост 4, бул. Александър Малинов №78 ПК: 1799','Sofia','Bulgaria','university@softuni.bg','Softuni','+ 359 899 55 55 92','https://softuni.bg/'),(10,'Centre','Sliven','Sliven','support@visioplan.com','Visiolan','+395 886 997 993','https://www.visioplan.com/');

INSERT INTO `buildings_companies` VALUES (4,1),(8,1),(11,1),(13,1),(14,1),(1,2),(9,2),(13,2),(2,3),(7,3),(11,3),(14,3),(3,4),(4,4),(5,4),(6,4),(11,4),(15,4),(1,5),(2,5),(4,5),(7,5),(10,5),(15,5),(1,6),(10,6),(13,6),(3,7),(5,7),(6,7),(9,7),(11,7),(9,8),(10,8),(13,8);

INSERT INTO `floors` VALUES (1,NULL,'SS3',1),(3,NULL,'SS2',1),(4,NULL,'SS1',1),(5,NULL,'RDC',1),(6,NULL,'ENT',1),(7,NULL,'R-1',1),(8,NULL,'R-2',1),(9,NULL,'R-3',1),(10,NULL,'R-4',1),(11,NULL,'R-5',1),(12,NULL,'R-6',1),(13,NULL,'R-7',1),(14,NULL,'TOIT',1),(15,NULL,'1',2),(16,NULL,'2',2),(17,NULL,'3',2),(18,NULL,'4',2),(19,NULL,'1',3),(20,NULL,'2',3),(21,NULL,'3',3),(22,NULL,'4',3),(31,NULL,'1',4),(32,NULL,'2',4),(33,NULL,'3',4),(34,NULL,'4',4),(35,NULL,'5',4),(36,NULL,'6',4),(37,NULL,'7',4),(38,NULL,'8',4),(39,NULL,'1',5),(40,NULL,'2',5),(41,NULL,'3',5),(42,NULL,'4',5),(43,NULL,'5',5),(81,NULL,'1',6),(82,NULL,'2',6),(83,NULL,'3',6),(84,NULL,'1',7),(85,NULL,'2',7),(86,NULL,'3',7),(87,NULL,'4',7),(88,NULL,'5',7),(89,NULL,'6',7),(90,NULL,'1',8),(91,NULL,'2',8),(92,NULL,'3',8),(93,NULL,'4',8),(94,NULL,'5',8),(95,NULL,'6',8),(96,NULL,'7',8),(97,NULL,'8',8),(98,NULL,'9',8),(99,NULL,'10',8),(100,NULL,'1',9),(101,NULL,'2',9),(102,NULL,'3',9),(103,NULL,'4',9),(104,NULL,'5',9),(105,NULL,'6',9),(106,NULL,'1',10),(107,NULL,'2',10),(108,NULL,'3',10),(109,NULL,'4',10),(110,NULL,'5',10),(111,NULL,'6',10),(112,NULL,'1',11),(113,NULL,'2',11),(114,NULL,'3',11),(115,NULL,'4',11),(116,NULL,'5',11),(117,NULL,'1',13),(118,NULL,'2',13),(119,NULL,'3',13),(120,NULL,'4',13),(121,NULL,'5',13),(122,NULL,'1',14),(123,NULL,'2',14),(124,NULL,'1',15),(125,NULL,'2',15),(126,NULL,'3',15);

INSERT INTO `users` VALUES (1,'misho@abv.bg','Mihail','Slavov','f69983b003545b40f424d1604bb27ace9a9a99cab0ad1ff1bec8f918c309ed934009d2f49a078809f921562a42424b5e','misho',10),(2,'gosho@abv.bg','Gosho','Goshev','97cfca8cda2e239d1c861251db1fdaf86fc55aedceb120fa1e8d35ef432d89ce382e75fc6f66a6681d30cc7efa5405e1','gosho',4),(3,'Amy Hays@gmail.com','Amy','Hays','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','mmm',7),(4,'Eve Durham@gmail.com','Eve','Durham','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Amy',3),(5,'Roderick Drake@gmail.com','Roderick','Drake','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Eve',8),(10,'Dora Whitney@gmail.com','Dora','Whitney','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Dollie',7),(11,'Andreas Calderon@gmail.com','Andreas','Calderon','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Dora',5),(12,'Jacklyn Stone@gmail.com','Jacklyn','Stone','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Andreas',7),(13,'Lenard Christian@gmail.com','Lenard','Christian','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Jacklyn',8),(14,'Omer Vance@gmail.com','Omer','Vance','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Lenard',5),(15,'Misty Tate@gmail.com','Misty','Tate','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Omer',1),(16,'Felicia Sexton@gmail.com','Felicia','Sexton','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Misty',2),(17,'Pablo Frederick@gmail.com','Pablo','Frederick','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Felicia',4),(18,'Liliana George@gmail.com','Liliana','George','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Pablo',8),(19,'Amos Everett@gmail.com','Amos','Everett','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Liliana',7),(20,'Carolyn Ashley@gmail.com','Carolyn','Ashley','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Amos',5),(21,'Josefa Waller@gmail.com','Josefa','Waller','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Carolyn',7),(22,'Katrina Schroeder@gmail.com','Katrina','Schroeder','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Josefa',8),(23,'Quintin Mccarthy@gmail.com','Quintin','Mccarthy','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Katrina',5),(24,'Ruthie Chandler@gmail.com','Ruthie','Chandler','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Quintin',1),(25,'Sidney Gibson@gmail.com','Sidney','Gibson','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Ruthie',2),(26,'Nellie Kidd@gmail.com','Nellie','Kidd','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Sidney',4),(27,'Mark Schaefer@gmail.com','Mark','Schaefer','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Nellie',8),(28,'Shaun Pittman@gmail.com','Shaun','Pittman','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Mark',8),(29,'Erin Stein@gmail.com','Erin','Stein','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Shaun',2),(30,'Loyd Scott@gmail.com','Loyd','Scott','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Erin',6),(31,'Carly Ross@gmail.com','Carly','Ross','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Loyd',5),(32,'Manual Brock@gmail.com','Manual','Brock','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Carly',3),(33,'Lucas Singh@gmail.com','Lucas','Singh','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Manual',7),(34,'Hosea Vincent@gmail.com','Hosea','Vincent','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Lucas',5),(35,'Lynn Chambers@gmail.com','Lynn','Chambers','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Hosea',7),(36,'Margarito Bowers@gmail.com','Margarito','Bowers','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Lynn',4),(37,'Jaime Pacheco@gmail.com','Jaime','Pacheco','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Margarito',8),(38,'Rashad Molina@gmail.com','Rashad','Molina','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Jaime',7),(39,'Randy Hebert@gmail.com','Randy','Hebert','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Rashad',5),(40,'Beryl Yoder@gmail.com','Beryl','Yoder','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Randy',7),(41,'Maynard Lynch@gmail.com','Maynard','Lynch','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Beryl',8),(42,'Louisa Mack@gmail.com','Louisa','Mack','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Maynard',5),(43,'Shon Adams@gmail.com','Shon','Adams','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Louisa',1),(44,'Derrick Martinez@gmail.com','Derrick','Martinez','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Shon',2),(45,'Margo Gay@gmail.com','Margo','Gay','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Derrick',4),(46,'Octavio Beltran@gmail.com','Octavio','Beltran','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Margo',8),(47,'Eugenio Hansen@gmail.com','Eugenio','Hansen','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Octavio',7),(48,'Jenna Bray@gmail.com','Jenna','Bray','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Eugenio',5),(49,'Burt Hampton@gmail.com','Burt','Hampton','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Jenna',7),(50,'Edwin Brewer@gmail.com','Edwin','Brewer','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Burt',8),(51,'Jacqueline Pratt@gmail.com','Jacqueline','Pratt','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Edwin',5),(52,'Rhea Horton@gmail.com','Rhea','Horton','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Jacqueline',1),(53,'Mark Carly@gmail.com','Mark','Carly','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Markio',2),(54,'Eugenio Adams@gmail.com','Eugenio','Adams','23a7282c8c34d411ab31dc4507d20c43bd6797061c0ec2a759ecebe1aa848ac1bd1b512c15b9cdb04dec4d1867f68ea4','Eugeniov',4),(69,'test1@abv.bg','test1','test1','ca41b5f5d90d6af26829172c82f8956b59e1dab844512bdd9efac0c88681b5dbe6cc6132e8d6c784e3a5a7c34214dd51','test1',5),(84,'srjfgn','srjfgn','srjfgn','5772fb42933dfc23d6ce6152531f6ada604dd595575cd8797b9a04ddc6e372ac26939e4d08ef4f6b08e0a2cf248a0f7e','srjfgn',2),(89,'test3@abv.bg','test3','test3L','4f58e4ee0d86d31e23e3bb4d0f0a660664dec65e9d247fc265eb85f524dce82e6c01d7c7fddd6147e67a4008463fe1a1','test3',4),(90,'finaltest@gmail.com','finaltest','finaltestL','37c3da8cff36d836d8f70e51c825fef6ffbe539ef295a25b15d5794fdf0029c522adb04c37d65ac001bd6361638b6136','finaltest',1);

insert into roles (id, role)
values  (1, 'ADMIN'),
        (2, 'USER');

INSERT INTO `users_roles` VALUES (11,2),(12,2),(13,2),(15,2),(16,2),(17,2),(18,2),(19,2),(20,2),(21,2),(22,2),(23,2),(24,2),(25,2),(26,2),(27,2),(28,2),(29,2),(30,2),(31,2),(32,2),(33,2),(34,2),(35,2),(36,2),(37,2),(38,2),(39,2),(40,2),(41,2),(42,2),(43,2),(44,2),(45,2),(46,2),(47,2),(48,2),(49,2),(50,2),(51,2),(52,2),(53,2),(54,2),(5,2),(14,2),(14,1),(1,2),(1,1),(3,2),(3,1),(69,2),(69,1),(84,2),(84,1),(89,2),(90,2),(10,2),(4,2),(2,2);

INSERT INTO `files` VALUES (2,'DETAIL','DWG','TEST_FILE_NAME','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test',1,1),(3,'DETAIL','DWG','TEST_FILE_NAME1','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test1',1,2),(7,'DETAIL','DWG','TEST_FILE_NAME5','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test5',1,3),(9,'DETAIL','DWG','TEST_FILE_NAME7','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test7',1,1),(11,'DETAIL','DWG','TEST_FILE_NAME9','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test9',1,1),(15,'DETAIL','DWG','TEST_FILE_NAME12','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test12',1,2),(16,'DETAIL','DWG','TEST_FILE_NAME13','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test13',1,3),(25,'SECTION','DWG','asdssrrrrr','ARCHITECTURAL','INSURANCE','2023-11-06 19:49:06.158159','url',92,4),(27,'SITE_PLAN','RVT','fghffg','PLUMBING','FRONT_PAGE','2023-11-07 06:53:15.614798','bf4a19ae-f8df-43ac-9e03-311b26f5c684',12,5),(28,'SITE_PLAN','DWG','dfdfgfd','MECHANICAL','CALCULATION','2023-11-07 07:13:39.011361','9faeebe3-275c-4dc9-a19e-1d17fbe6853c',82,1),(29,'SITE_PLAN','DWG','sadfsdfgdsfgdf','MECHANICAL','PERMIT','2023-11-07 07:34:26.123497','/exe_ata_04_de_sev_ss3_2011_0 (1).pdf',42,2),(30,'OTHER','XLSX','sdrfthjgtdhyjm77','PLUMBING','INSURANCE','2023-11-07 07:56:59.971095','https://www.dropbox.com/scl/fi/jrekfn8vmejom42k2bjsv/EXE_ATA_04_DE_SEV_SS3_2011_0-1.pdf',21,3),(31,'SECTION','PDF','stherthne56j5e6tyhs4re5t','MECHANICAL','CERTIFICATE','2023-11-07 10:43:43.809341','https://www.dropbox.com/scl/fi/0nj3vnubj8hikclzobvqr/horizontalna-mreja.pdf\\?raw=1',34,4),(32,'SECTION','PDF','sdfgsdfdsfg','MECHANICAL','NOT_SPECIFIED','2023-11-14 17:20:43.440251','https://www.dropbox.com/scl/fi/epnvzr5i9yobudxc2b5hq/techmart.pdf',40,5),(33,'DETAIL','DWG','TEST_FILE_NAME21','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test21',1,1),(35,'DETAIL','DWG','TEST_FILE_NAME29','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test22',1,1),(36,'DETAIL','DWG','TEST_FILE_NAME22','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test23',1,1),(37,'DETAIL','DWG','TEST_FILE_NAME23','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test24',1,1),(38,'DETAIL','DWG','TEST_FILE_NAME24','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test25',1,1),(39,'DETAIL','DWG','TEST_FILE_NAME25','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test26',1,1),(40,'DETAIL','DWG','TEST_FILE_NAME26','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test27',1,1),(41,'DETAIL','DWG','TEST_FILE_NAME27','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test28',1,1),(42,'DETAIL','DWG','TEST_FILE_NAME28','PLUMBING','NOT_SPECIFIED','2023-11-02 13:57:24.000000','https://test29',1,1);

INSERT INTO `comments` VALUES (1,'2023-11-02 14:13:17.000000','no its not ok',1,2),(2,'2023-11-02 14:13:17.000000','no its not ok',1,3),(5,'2023-11-02 14:13:17.000000','no its not ok',3,7),(6,'2023-11-02 14:13:17.000000','no its not ok',1,7),(7,'2023-11-02 14:13:17.000000','no its not ok',2,2),(8,'2023-11-15 18:04:41.852813','this is the test comment',1,3),(10,'2023-11-15 21:01:19.705472','top, the best',69,7),(12,'2023-11-16 07:52:23.656748','sdfgdsfg',1,9),(13,'2023-11-16 07:53:30.906082','zde\\fhxgh',1,11),(14,'2023-11-16 07:55:41.462500','asdfasdfasdf',1,11),(15,'2023-11-16 08:05:03.546093','sfgsdfgdfsg',1,9),(16,'2023-11-16 08:05:56.112131','asdfasdadsf',1,9),(17,'2023-11-16 08:08:12.896069','fsdfs',1,9),(21,'2023-11-17 20:54:27.275746','test if it still works',1,25),(22,'2023-11-18 16:17:40.151304','sdfghsdfgdfg',1,15);

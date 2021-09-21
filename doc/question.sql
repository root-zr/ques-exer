/*
SQLyog Job Agent v13.1.1 (64 bit) Copyright(c) Webyog Inc. All Rights Reserved.


MySQL - 5.7.30-log : Database - question
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`question` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `question`;

/*Table structure for table `java` */

DROP TABLE IF EXISTS `java`;

CREATE TABLE `java` (
  `id` varchar(10) DEFAULT NULL,
  `problem` text,
  `answer` varchar(255) DEFAULT NULL,
  `ch1` varchar(255) DEFAULT NULL,
  `ch2` varchar(255) DEFAULT NULL,
  `ch3` varchar(255) DEFAULT NULL,
  `ch4` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `java` */

insert  into `java` values 
('4','下列哪个叙述是正确的？','D','成员变量的名字不可以和局部变量的名字相同。','方法的参数的名字可以和方法中声明的局部变量的名字相同。','成员变量没有默认值。','局部变量没有默认值。'),
('6','下列哪个叙述是正确的？\n\n\n\n','D','final 类可以有子类','abstract类中只可以有abstract方法','abstract类中可以有非abstract方法，但该方法不可以用final修饰','不可以同时用final和abstract修饰同一个方法\n'),
('7','下列程序中注释的哪两个代码（A，B，C，D）是错误的（无法通过编译）？class Father {\n   private int money =12;\n   float height;\n   int seeMoney(){\n      return money ;           //A\n   } \n}\nclass Son extends Father {\n   int height;\n   int lookMoney() {\n      int m = seeMoney();    //B\n      return m;             \n   }\n}\nclass E {\n   public static void main(String args[]) {\n      Son erzi = new Son();\n      erzi.money = 300;      //C\n      erzi.height = 1.78F;  //D\n   }\n}\n','D','A','B','C','D'),
('11','class Hello {\n     Hello(int m){\n     }\n     int Hello() {\n         return 20;\n     }\n     hello() {\n     }\n}\n','  ','A?Hello??2?????','B?Hello??int Hello()????????','C?Hello????????','D?Hello????????????hello?????????????????'),
('12','public class Dog {\n     Dog(int m){ \n     }\n     Dog(double  m){ \n     }\n     int Dog(int m){ \n        return 23;\n     }\n     void Dog(double m){\n     }\n}\n',' A','A?Dog(int m)?Dog(double m)???????????','B?int Dog(int m)?void Dog(double m)????????????','C?Dog???????????????????????','D?Dog??3??????'),
('10','class Hello {\n     Hello(int m){\n     }\n     int Hello() {\n         return 20;\n     }\n     hello() {\n     }\n}\n','  ','Hello??2?????','Hello??int Hello()????????','Hello????????','Hello????????????hello?????????????????'),
('10','???????????\n\n\n','  ','???????????','abstract????????abstract?','???????????????????????','??????????????????????????'),
('11','??????????11111','  ','???1?????','???2?????','???3?????','???4?????'),
('11','???????????\n11111\n\n','  ','???????????','abstract????????abstract?','???????????????????????','??????????????????????????'),
('11','class Hello {\n     Hello(int m){\n     }\n     int Hello() {\n         return 20;\n     }\n     hello() {\n     }\n}\n','  ','A?Hello','B?Hello??int Hello()','C?Hello????????','D?Hello????????????hello?????????????????');

/*Table structure for table `managepassword` */

DROP TABLE IF EXISTS `managepassword`;

CREATE TABLE `managepassword` (
  `id` varchar(10) NOT NULL,
  `password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `managepassword` */

insert  into `managepassword` values 
('001','123');

/*Table structure for table `userpassword` */

DROP TABLE IF EXISTS `userpassword`;

CREATE TABLE `userpassword` (
  `Id` varchar(10) NOT NULL,
  `Password` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `userpassword` */

insert  into `userpassword` values 
('201800001','123'),
('20180001','123'),
('2018001','123'),
('2018002','123'),
('2018003','123'),
('201802','123');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

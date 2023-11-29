use newcodecreateddatabase;

-- procedure to updateEmpSalary 
drop procedure if exists updateEmpSalary;
DELIMITER $$
CREATE PROCEDURE updateEmpSalary(IN id INT,IN incRate DECIMAL(4,2))
BEGIN
UPDATE employee 
   SET salary = salary*(1+incRate)
   WHERE empId=id;
END$$ 
DELIMITER ;
SET SQL_SAFE_UPDATES = 0;
CALL `newcodecreateddatabase`.`updateEmpSalary`(101, 10);

-- procedure to the count of total no. of count countEmp
DROP PROCEDURE IF EXISTS countEmp;
DELIMITER $$
CREATE PROCEDURE countEmp (OUT count INT)
BEGIN
DECLARE id INT;
   DECLARE name VARCHAR(20);
   DECLARE salary DECIMAL(10,4);
   
DECLARE finished INT DEFAULT 0;
   DECLARE c CURSOR FOR SELECT * FROM employee;
   DECLARE CONTINUE HANDLER FOR NOT FOUND
   BEGIN
 SET finished=1;
END;
SET count=0;
   OPEN c;
   l:LOOP
 FETCH c INTO id,name,salary;
       IF finished=1 THEN
  LEAVE l;
 END IF;
       SET count=count+1;
END LOOP;
   CLOSE c;
END$$
DELIMITER ;

CALL countEmp(@count);
select @count;


-- Counter parameter Procedure. like input is 3 then output will be 4
DROP PROCEDURE IF EXISTS incCounter;
DELIMITER $$
CREATE PROCEDURE incCounter(INOUT counter INT)
BEGIN
SET counter=counter+1;
END$$
DELIMITER ;

-- procedure to increment the salary by some percent.
DROP PROCEDURE IF EXISTS salaryMap;
DELIMITER $$
CREATE PROCEDURE salaryMap(In id INT, IN percent INT, OUT New_salary INT, OUT Old_salary INT)
BEGIN
select salary into Old_salary from employee where empid = id;
update employee SET salary = salary * (percent/100 +1);
select salary into New_salary from employee where empid = id;
END$$
DELIMITER ;

call salaryMap(101, 10, @new_sal, @old_sal);
select @new_sal, @old_sal;
-- select salary as Old_salary from employee where empid = 101;

-- isprime function
Drop Function IF EXISTS isPrime;
DELIMITER $$
CREATE FUNCTION isPrime(x INT) RETURNS varchar(40)
DETERMINISTIC
BEGIN
DECLARE n INT DEFAULT 2;
   loop1:WHILE n<=SQRT(x) DO
 IF x%n=0 THEN
  RETURN "NOt Prime";
 END IF;
       SET n=n+1;
END WHILE;
   RETURN "Prime";
END$$
DELIMITER ;

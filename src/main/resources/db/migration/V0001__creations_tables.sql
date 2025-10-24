CREATE TABLE t_student(
   st_id INT AUTO_INCREMENT PRIMARY KEY,
   st_name VARCHAR(55),
   st_code VARCHAR(55)
);

CREATE TABLE t_history(
   hi_id INT AUTO_INCREMENT PRIMARY KEY,
   hi_module VARCHAR(55),
   hi_identifier INT,
   hi_title VARCHAR(255),
   hi_description TEXT
);
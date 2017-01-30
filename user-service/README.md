# How to run the user service ?

```
$mvn clean package
$java -jar ./target/user-service.jar
```

And go to URL http://localhost:9001/user

* ต้องมีการสร้าง Database ก่อน โดยสามารถสร้างโดยใช้ MySQL WorkBench หรือ command ของ MySQL โดยใช้คำสั่ง 'CREATE DATABASE <ชื่อDB>'
* ไฟล์ UserController.java มีการเพิ่ม @CrossOrigin @Controller

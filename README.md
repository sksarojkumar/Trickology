# Trickology   
Multiple Choice Question Application. Running over locally connected server through WIFI.
The application can used for taking test of your employee.

# Prerequisite   
You need to install following software:
* Java 7
* Oracle 10g database  

and create a *three table* named:  
1. LOGIN `(for authentication)`  
```
USERNAME VARCHAR2 NOT NULL ENABLE
PASSWORD VARCHAR2 NOT NULL ENABLE
```
2. MCQ `(for store your questions)`  
```
QNO NUMBER NOT NULL ENABLE
QUESTION VARCHAR2
A VARCHAR2
B VARCHAR2
C VARCHAR2
D VARCHAR2
ANSWER VARCHAR2
```
3. USER_RECORD `(for record user answers)`  
```
USER_NAME VARCHAR2
Q1 VARCHAR2
Q2 VARCHAR2
Q3 VARCHAR2
Q4 VARCHAR2
Q5 VARCHAR2
Q6 VARCHAR2
Q7 VARCHAR2
Q8 VARCHAR2
Q9 VARCHAR2
Q10 VARCHAR2
Q11 VARCHAR2
Q12 VARCHAR2
Q13 VARCHAR2
Q14 VARCHAR2
Q15 VARCHAR2
Q16 VARCHAR2
Q17 VARCHAR2
Q18 VARCHAR2
Q19 VARCHAR2
Q20 VARCHAR2
Q21 VARCHAR2
Q22 VARCHAR2
Q23 VARCHAR2
Q24 VARCHAR2
Q25 VARCHAR2
Q26 VARCHAR2
Q27 VARCHAR2
Q28 VARCHAR2
Q29 VARCHAR2
Q30 VARCHAR2
SCORE NUMBER
FTIME VARCHAR2
CANS NUMBER
WANS NUMBER
```
you need to modify *Database* login *PASSWORD* accordingly:
```
conn=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","USERNAME","PASSWORD");
```
The default *Admin* password is *cad614* .

# Running The Application
To run, you have to start the *server*.  
To do this, you only need to execute a *java file*
```
java appserver
```
Then double click on *Trickology.exe*  
Registration:  
![Registration](https://github.com/sksarojkumar/Trickology/tree/master/pics/md/screenshort1.png)  
Login:  
![Login](https://github.com/sksarojkumar/Trickology/tree/master/pics/md/screenshort2.png)  
Instructions:  
![Instructions](https://github.com/sksarojkumar/Trickology/tree/master/pics/md/screenshort3.png)  
Landing page:  
![MCQ page](https://github.com/sksarojkumar/Trickology/tree/master/pics/md/screenshort4.png)  
Result:  
![Result](https://github.com/sksarojkumar/Trickology/tree/master/pics/md/screenshort5.png)  
# Built With:  
* Swing and AWT Framework
* Thread
* Socket

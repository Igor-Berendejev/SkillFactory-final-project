# SkillFactory-final-project

API URL: http://localhost:8080/v1/balance
Current REST API version allows to:
1. Check account balance by user id. URL: /{userId}
![image](https://user-images.githubusercontent.com/90723839/190002973-419e6cb0-55da-4488-8e34-cb505036264d.png)

2. Add money on users account. URL: /put/{userId}. Amount must be passed in the body of request
![image](https://user-images.githubusercontent.com/90723839/190003330-f006fb83-10f8-4432-882e-17fd99992291.png)


3. Withdraw money from users account. URL: /withdraw/{userId}. Amount must be passed in the body of request
![image](https://user-images.githubusercontent.com/90723839/190003845-d2b73015-8047-473a-9927-01a2797df86b.png)

![image](https://user-images.githubusercontent.com/90723839/190003954-6cbf812f-a942-492f-950a-94744de7cb0b.png)


Persistance layer tests are using TestContainers. Make sure to start Docker before running the test.

Database structure:

![image](https://user-images.githubusercontent.com/90723839/190004924-986ff971-7900-4bfd-a5d9-42b2d986300c.png)


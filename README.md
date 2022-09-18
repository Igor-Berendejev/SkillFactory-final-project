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

4. Get list of transactions for a specified balance id and period. If no period specified full list of transactions will be retrieved.
![image](https://user-images.githubusercontent.com/90723839/190901190-397d62b1-1e9e-4e70-a248-cbacf18561d5.png)

![image](https://user-images.githubusercontent.com/90723839/190901211-162e2acc-f913-4a40-939d-a9751d36cbd6.png)


Persistance layer tests are using TestContainers. Make sure to start Docker before running the test.

Database structure:

![image](https://user-images.githubusercontent.com/90723839/190901348-4fe62738-b7c7-4173-99aa-c309045867d0.png)



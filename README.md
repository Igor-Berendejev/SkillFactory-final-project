# SkillFactory-final-project

API URL: http://localhost:8080/v1/balance
Current REST API version allows to:
1. Check account balance by user id and balance id. URL: /{userId}/{balanceId}
![image](https://user-images.githubusercontent.com/90723839/193452028-3978fda0-8934-4eda-9519-d0fb4b2abdbb.png)

![image](https://user-images.githubusercontent.com/90723839/193452074-b2ddcb2d-a26d-441a-b085-2718ee645ccd.png)


2. Add money on users account. URL: /put/{userId}. Amount must be passed in the body of request
![image](https://user-images.githubusercontent.com/90723839/190003330-f006fb83-10f8-4432-882e-17fd99992291.png)


3. Withdraw money from users account. URL: /withdraw/{userId}. Amount must be passed in the body of request
![image](https://user-images.githubusercontent.com/90723839/190003845-d2b73015-8047-473a-9927-01a2797df86b.png)

![image](https://user-images.githubusercontent.com/90723839/190003954-6cbf812f-a942-492f-950a-94744de7cb0b.png)

4. Get list of transactions for a specified balance id and period. If no period specified full list of transactions will be retrieved.
URL: /v1/transactions/{balanceId}. Start date and end date must be passed as request parameters.
![image](https://user-images.githubusercontent.com/90723839/190901190-397d62b1-1e9e-4e70-a248-cbacf18561d5.png)

![image](https://user-images.githubusercontent.com/90723839/190901211-162e2acc-f913-4a40-939d-a9751d36cbd6.png)

5. Make money transfers between accounts. URL: /v1/transfer. Balance ids must be passed as request parameters, amount must be passed in the body of request
![image](https://user-images.githubusercontent.com/90723839/193453109-c3aebc7b-cf98-45a3-ab2d-1bbc6ea41e29.png)

![image](https://user-images.githubusercontent.com/90723839/193453122-1a4c2d9b-57fd-4bc5-8e0f-a1ea6fa376a1.png)


Persistance layer tests are using TestContainers. Make sure to start Docker before running the test.

Database structure:

![image](https://user-images.githubusercontent.com/90723839/193453156-1f54675e-0baf-4459-811c-b5e1984f0d3e.png)



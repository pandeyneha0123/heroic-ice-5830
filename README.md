# heroic-ice-5830

# Payment-Wallet-Services


 <p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/easyPay%20logo.png.png" width="600" height="400" essibility text">
</p>
 
EasyPay is a secure and reliable web service that provides users with the ability to manage their digital wallets. The service is built using Java, Spring Boot, MySQL, and Hibernate.

The purpose of this project is to create a user-friendly platform that allows users to store, send, and receive digital currency securely. The wallet service provides users with the ability to view their balance, transaction history, and wallet details. The service also includes a secure authentication system to protect users' sensitive information.

The project has several objectives, including:

Providing a RESTful web service for managing digital wallets
Implementing a secure and reliable payment system for processing transactions
Enabling users to send and receive digital currency to and from other wallet users
Providing detailed transaction history and balance details
Implementing robust security measures to protect users' sensitive information
The key features of the Wallet Service include wallet management, transaction management, and user management. The service supports different types of digital currency, such as Bitcoin and Ethereum(Feature scope).

The technologies used in the project include Java, Spring Boot, MySQL, Hibernate, REST APIs, and JSON. The project follows best practices in software development and includes robust testing and debugging procedures to ensure the service's quality and reliability.

The project team includes developers, designers, and testers who work together to ensure that the project meets the requirements of stakeholders and end-users. The project is scheduled to be completed within five days and includes a risk management plan to identify and mitigate potential risks.

In conclusion, the EasyPay wallet service is a secure and reliable web service that provides users with the ability to manage their digital wallets. It is built using modern technologies and follows best practices in software development to ensure its quality and reliability.






      
# Funcionality of the application:
- Two-factor authentication (2FA) - Adding an extra layer of security to the login process by requiring users to enter a unique code generated by an authenticator app on their phone or sent via SMS.

- Multisignature wallets - Allowing multiple users to control a single wallet, which increases the security of the wallet by requiring multiple users to sign off on transactions.

- Integration with external wallets - Allowing users to connect their wallets from external services such as Coinbase or Binance, to transfer funds to their digital wallets within the service.

- Automatic transaction alerts - Sending notifications to users when a transaction is initiated or completed, providing users with real-time updates on their wallet's activity.

- Transaction limits - Setting limits on the amount of digital currency that can be transferred in a single transaction or over a specific period of time to prevent fraud and ensure compliance with regulatory requirements.

- Cold storage - Storing users' digital currency in an offline "cold" storage wallet, which provides additional security by making it less vulnerable to hacking attempts.

- API access - Providing an API that developers can use to integrate the wallet service into their applications or websites, allowing them to accept payments in digital currency.

- Automated tax reporting - Generating reports for users that show their transaction history and any capital gains or losses, simplifying the tax reporting process.

- Social media integration - Allowing users to share their wallet activity on social media platforms such as Twitter or Facebook, which could increase the adoption of the service and generate more interest in digital currency.

- Peer-to-peer payments - Enabling users to send and receive digital currency directly to and from other users of the wallet service, without the need for a traditional financial institution as an intermediary.

- These are just a few potential functionalities that could be added to the wallet service. The actual features implemented would depend on the project requirements, stakeholder needs, and other considerations.

# Tech Stack
- Java
- Hibernate
- Spring Framework
- Spring Sequrity
- Spring Data JPA
- MySQL
- Maven
- Swagger UI



# Services

- Account service
- Transaction Service
- Customer Service
- Bill Payement Service
- Benificiary Service 
- Wallet service


# Installation & Run
 - if you want to run the API server, you should have to update the database configuration inside the application.properties file and Update the port number, username and password as per your local database configuration.


```
    server.port=8088

    spring.datasource.url=jdbc:mysql://localhost:3306/easyapp;
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=root
```

# API Root Endpoint
```
https://localhost:8080/
```
```
http://localhost:8888/swagger-ui/
```
# ER Diagram


 <p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot%202023-04-02%20185621.png" width="1200" height="600" essibility text">
</p>

# Login-logout

<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_112319.png" width="1200" height="200" essibility text">
</p>
                                                                                                                                                  
# Wallet Controller

<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_113917.png" width="1200" height="300" essibility text">
</p>

# trscaction Controller

<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_113805.png" width="1200" height="300" essibility text">
</p>



# Customer Controller
<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_111738.png" width="1200" height="300" essibility text">
</p>


# Bank Cobtroller

<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_110338.png" width="1200" height="300" essibility text">
</p>

# Bill Payment
                                                                                                                                                  
<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_110349.png" width="1200" height="300" essibility text">
</p>

# Benificairy Controller
                                                                                                                                                                                                                   
<p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_110359.png" width="1200" height="300" essibility text">
</p>

# Schema
 <p align="center">
  <img src="https://github.com/pandeyneha0123/heroic-ice-5830/blob/main/Screenshot_20230403_110608.png" width="1200" height="300" essibility text">
</p>

# Collaborators

- Neha Pandey (fw20_0039)
- Amit Chakraborty (fw22_0458)
- Prince kumar (fp03_275)
- Rohit Kumar (fw19_0011)


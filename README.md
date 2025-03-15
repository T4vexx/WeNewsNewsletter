# WeNews
A Java project that incorporates all the main pillars of object-oriented programming, featuring a UI built with JavaFX.

## 📌 Dependencies
To run this program, you must have a **PostgreSQL database** running on your machine.

Once the database is set up and running, you need to edit **line 37** of the code with your database credentials:

```java
public sistema() { con = DBFun.connectToDb("DatabaseName", "postgres", "DatabasePassword"); }
```

The project was developed using **Java 20**.

## ✨ Features
WeNews is designed to function as an **online news portal** with the following capabilities:

✅ **User Management**
- Register and create new accounts
- View user profile information

✅ **News & Content Access**
- Free users can read a **partial** version of news articles
- Subscribed users have **full access** to all news articles
- Users can **comment** on news articles

✅ **Content Creation & Editing**
- **Journalists** can write news articles
- **Writers** can publish opinion articles
- **Editors** have permissions to **edit any** article or news post

✅ **Subscription System**
- Users can purchase a **subscription** to access full content
- Subscriptions can be **renewed** within the system

## 🎨 UI Preview
<div align="center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/273ed1f1-cf40-4140-a10a-2fbf86b4206b" />
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/2a922c89-a4c3-46ed-8730-9d5d4c290367" />
</div>
<div align="center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/e09776c6-c805-4f30-b907-fbe441ac5460" />
</div>
<div align="center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/f73f41af-4772-4a89-b91e-1fbeb0315509" />
</div>
<div align="center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/e793e04f-050a-4023-bc6c-2f30f11def51" />
</div>
<div align="center">
  <img height="500em" src="https://github.com/T4vexx/WeNewsNewsletter/assets/68335367/229e20a0-5c4b-4616-994b-4bab65959b5e" />
</div>

## 🚀 Getting Started

### 1️⃣ Prerequisites
Before running WeNews, make sure you have:
- **Java 20** installed
- **PostgreSQL** database running
- **JavaFX** dependencies set up in your project

### 2️⃣ Installation & Execution

#### Clone the Repository:
```bash
git clone https://github.com/yourusername/WeNews.git
cd WeNews
```

#### Set Up the Database:
1. Open **PostgreSQL** and create a new database
2. Update **line 37** of `sistema.java` with your database credentials

#### Run the Application:
```bash
mvn clean install
mvn javafx:run
```
Or if using an IDE like IntelliJ IDEA, simply **run the main class**.

## 💡 Future Improvements
- Implement API support for external news sources
- Enhance the subscription system with multiple payment methods
- Improve UI/UX with additional JavaFX animations

## 🤝 Contributions
If you have suggestions or improvements, feel free to **open an issue** or submit a **pull request**! 😊



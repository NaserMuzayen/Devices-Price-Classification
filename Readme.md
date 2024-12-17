# **Devices Price Classification System**

This project predicts device price ranges based on their specifications using **Machine Learning** and provides a RESTful API built with **Spring Boot** and **FastAPI**.

---

## **Features**
- Predict the price range of a device using its input features.
- RESTful API endpoints for managing devices and predicting prices.
- Supports both Python (ML model) and Java Spring Boot (backend).

---

## **Technologies Used**

### **Python (ML Model)**
- **Python 3.10**
- **FastAPI** - For serving the ML model as a RESTful API.
- **Scikit-learn** - Machine Learning library.
- **MongoDB** - For data storage.

### **Spring Boot (Backend)**
- **Java 23.0.1**
- **Apache Maven 3.9.9**
- **MongoDB** - For persisting devices and predictions.

---

## **Run Locally**
### **1. Python ML Model**

#### **Pre-requisites**:
Go to the project directory


Create virtual environment


```bash
  python3 -m venv venv
```

Activate virtual environment

on windows
```bash
  source venv/Scripts/activate
```
on mac
```bash
  source venv/bin/activate
```
Install dependencies

```bash
  pip install -r requirements.txt
```
#### Import test data to mongodb

```bash
  python data_importer.py
```

#### Start the server

```bash
  python main.py
```


#### Swagger link
```bash
  http://localhost:8000/docs
```


### **2. Spring Boot Backend**
Setup Steps:
Go to the project directory

on mac use this commmand
```bash
  brew install libomp  
```

#### Build the Project
Run the following Maven command:

```bash
  mvn clean install
```

#### Start the server

```bash
  mvn spring-boot:run
```
#### Swagger link
```bash
  http://localhost:8080/swagger-ui/index.html
```

## Endpoints Overview
### 1. FastAPI (Python ML Model):
POST /predict: Predict the price range of a device based on its features.

### 2.Spring Boot (Backend):
POST /api/devices: Add a new device.</br>
GET /api/devices: Get all devices.</br>
GET /api/devices/{id}: Retrieve details of a specific device.</br>
POST /api/predict/{deviceId}: Call the ML model API to predict the price and store it.</br>


# 🗂️ Customer Data Management

A production-ready **RESTful backend** built with **Micronaut + Java 21 + PostgreSQL**, deployed on **Oracle Cloud Free Tier** with HTTPS via DuckDNS + Nginx + Let's Encrypt.

Supports a **React (Vercel)** frontend and an **Android** mobile app.

---

## 🌐 Live Deployment

| Service | URL |
|---|---|
| 🚀 API Base URL | `https://customer-data-management.duckdns.org` |
| 📖 Swagger UI | `https://customer-data-management.duckdns.org/swagger-ui/` |

---

## 🏗️ Tech Stack

| Layer | Technology |
|---|---|
| **Framework** | Micronaut 4.9.3 |
| **Language** | Java 21 |
| **Database** | PostgreSQL 16 |
| **Build Tool** | Gradle + Shadow JAR |
| **Runtime** | Docker + Docker Compose |
| **Server** | Oracle Cloud Free Tier — Ampere A1 (ARM64), Ubuntu 22.04 |
| **HTTPS** | Nginx reverse proxy + Let's Encrypt (via DuckDNS) |
| **ORM** | Hibernate JPA (auto DDL — tables created on startup) |
| **API Docs** | Swagger / OpenAPI 3 |

---

## 📁 Project Structure

```
Customer-Data-Management/
├── src/
│   └── main/
│       ├── java/com/dm/customer/
│       │   ├── controller/          # REST controllers
│       │   │   ├── CDMController.java
│       │   │   └── LoginRegisterController.java
│       │   ├── model/               # JPA entities (DB tables)
│       │   │   ├── CDMModel.java
│       │   │   └── LoginRegisterModel.java
│       │   ├── repository/          # Data access layer
│       │   ├── service/             # Business logic
│       │   ├── dto/                 # Request/Response objects
│       │   ├── util/                # Validators, enums, logger
│       │   └── exception/           # Custom API responses
│       └── resources/
│           └── application.yml      # App config (port, DB, Swagger)
├── Dockerfile                       # Multi-stage build (Gradle → JRE)
├── docker-compose.yml               # App + PostgreSQL stack
└── build.gradle                     # Dependencies & build config
```

---

## 🔌 API Reference

### Base URL
```
https://customer-data-management.duckdns.org
```

---

### 🔐 Authentication

#### Register a new user
```http
POST /user/register
```
**Request Body:**
```json
{
  "userName": "rupesh",
  "userEmail": "rupesh@example.com",
  "password": "yourpassword"
}
```

---

#### Login
```http
POST /user/login
```
**Request Body:**
```json
{
  "userName": "rupesh",
  "password": "yourpassword"
}
```

---

### 👤 Customer Management

#### Create a customer
```http
POST /customer/data/post
```
**Request Body:**
```json
{
  "customerName": "John Doe",
  "customerMobileNumber": "9876543210",
  "customerPanCardNumber": "ABCDE1234F",
  "customerActiveStatus": "ACTIVE",
  "customerAddress": "123 Main Street, Mumbai",
  "createdDate": "2024-01-15",
  "createdBy": "admin",
  "updatedDate": "2024-01-15",
  "updatedBy": "admin"
}
```

> **Validations applied:**
> - Mobile number must be valid (10 digits)
> - PAN card must follow format: `ABCDE1234F`
> - Mobile number and PAN card must be unique
> - Active status must be `ACTIVE` or `INACTIVE`
> - All fields are required

---

#### Get all customers
```http
GET /customer/data/get
```
Returns a list of all customer records.

---

#### Search customers
```http
POST /customer/data/search
```
**Request Body** — pass any field(s) to filter by:
```json
{
  "customerName": "John",
  "customerActiveStatus": "ACTIVE"
}
```
Returns matching customers. All fields are optional — only the provided ones are used as filters.

---

#### Update a customer
```http
PUT /customer/data/update/{id}
```
Replace `{id}` with the `customerId` of the record to update.

**Request Body:** Same fields as Create (all required).

---

#### Delete a customer
```http
DELETE /customer/data/delete/{id}
```
Replace `{id}` with the `customerId` to delete. Returns 400 if the customer does not exist.

---

### 📊 Response Format

**Success:**
```json
{
  "status": "SUCCESS",
  "message": "Customer data validated and added successfully"
}
```

**Failure (validation error):**
```json
{
  "status": "FAILURE",
  "errors": [
    "Customer mobile number is invalid",
    "PAN card number already exists"
  ]
}
```

---

## 🗄️ Database Schema

Tables are **auto-created by Hibernate** on first startup — no SQL scripts needed.

### `customer` table

| Column | Type | Notes |
|---|---|---|
| `customer_id` | integer | Primary key, auto-increment |
| `customer_name` | varchar | Required |
| `customer_mobile_number` | varchar | Unique, validated |
| `customer_pan_card_number` | varchar | Unique, format validated |
| `customer_active_status` | varchar | `ACTIVE` or `INACTIVE` |
| `customer_address` | varchar | Required |
| `created_date` | varchar | Required |
| `created_by` | varchar | Required |
| `updated_date` | varchar | Required |
| `updated_by` | varchar | Required |

### `user_details` table

| Column | Type | Notes |
|---|---|---|
| `user_id` | integer | Primary key, auto-increment |
| `user_name` | varchar | Login username |
| `user_email` | varchar | User email |
| `password` | varchar | User password |

---

## 🚀 Local Development Setup

### Prerequisites
- Java 21
- Docker + Docker Compose
- Git

### 1. Clone the repository

```bash
git clone https://github.com/rupesh9698/Customer-Data-Management.git
cd Customer-Data-Management
```

### 2. Run with Docker Compose

```bash
docker compose up -d --build
```

This starts two containers:
- **app** — Micronaut backend on port `8080`
- **db** — PostgreSQL 16 on port `5432`

Database tables are created automatically on first run.

### 3. Verify it's running

```bash
# Check containers
docker compose ps

# View logs
docker compose logs -f app
```

Look for:
```
Startup completed in ...ms. Server Running: http://0.0.0.0:8080
```

### 4. Test an endpoint

```bash
curl -X POST http://localhost:8080/user/register \
  -H "Content-Type: application/json" \
  -d '{"userName":"testuser","userEmail":"test@test.com","password":"test123"}'
```

### 5. Open Swagger UI

```
http://localhost:8080/swagger-ui/
```

---

## ⚙️ Configuration

App configuration lives in `src/main/resources/application.yml`.

Environment variables override the defaults (used in Docker):

| Variable | Default | Description |
|---|---|---|
| `DATASOURCES_DEFAULT_URL` | `jdbc:postgresql://localhost:5432/Customer_Data_Management` | JDBC connection URL |
| `DATASOURCES_DEFAULT_USERNAME` | `postgres` | DB username |
| `DATASOURCES_DEFAULT_PASSWORD` | `1234` | DB password |

---

## ☁️ Oracle Cloud Deployment Guide

The app is deployed on **Oracle Cloud Free Tier** — fully free, forever.

### Infrastructure
- **Instance:** Ampere A1 Flex — 1 OCPU, 6 GB RAM (ARM64)
- **OS:** Ubuntu 22.04
- **Domain:** DuckDNS (free subdomain)
- **SSL:** Let's Encrypt via Certbot
- **Proxy:** Nginx

### Deployment Architecture

```
Internet
   │
   ▼
DuckDNS Domain (HTTPS :443)
   │
   ▼
Nginx Reverse Proxy  ──────────────── Let's Encrypt SSL
   │
   ▼
Micronaut App (localhost:8080)
   │
   ▼
PostgreSQL 16 (Docker container)
```

### Steps to Deploy

**1. Open ports in OCI Security List** (OCI Console → Networking → VCN → Security List)

Add Ingress Rules for TCP ports: `80`, `443`, `8080`

**2. SSH into your instance**

```bash
chmod 400 your-key.key
ssh -i your-key.key ubuntu@<PUBLIC_IP>
```

**3. Install Docker**

```bash
sudo apt update
sudo apt install -y ca-certificates curl gnupg
sudo install -m 0755 -d /etc/apt/keyrings
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | \
  sudo gpg --dearmor -o /etc/apt/keyrings/docker.gpg
sudo chmod a+r /etc/apt/keyrings/docker.gpg
echo "deb [arch=$(dpkg --print-architecture) signed-by=/etc/apt/keyrings/docker.gpg] \
  https://download.docker.com/linux/ubuntu $(. /etc/os-release && echo "$VERSION_CODENAME") stable" | \
  sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
sudo apt update
sudo apt install -y docker-ce docker-ce-cli containerd.io docker-compose-plugin
sudo usermod -aG docker ubuntu && newgrp docker
```

**4. Open firewall ports (iptables — required on OCI)**

```bash
sudo iptables -I INPUT -p tcp --dport 80 -j ACCEPT
sudo iptables -I INPUT -p tcp --dport 443 -j ACCEPT
sudo iptables -I INPUT -p tcp --dport 8080 -j ACCEPT
sudo apt install -y iptables-persistent
sudo netfilter-persistent save
```

> ⚠️ OCI Ubuntu uses iptables directly — `ufw` does not work on Oracle Cloud instances.

**5. Upload and run the project**

```bash
# From local machine
scp -i your-key.key Customer-Data-Management-master.zip ubuntu@<IP>:~/

# On server
unzip Customer-Data-Management-master.zip
cd Customer-Data-Management-master
docker compose up -d --build
```

**6. Install Nginx + SSL**

```bash
sudo apt install -y nginx certbot python3-certbot-nginx

# Configure reverse proxy
sudo nano /etc/nginx/sites-available/customer-api
```

Paste:
```nginx
server {
    listen 80;
    server_name customer-data-management.duckdns.org;

    location / {
        proxy_pass http://localhost:8080;
        proxy_set_header Host $host;
        proxy_set_header X-Real-IP $remote_addr;
        proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
        proxy_set_header X-Forwarded-Proto $scheme;
    }
}
```

```bash
sudo ln -s /etc/nginx/sites-available/customer-api /etc/nginx/sites-enabled/
sudo nginx -t && sudo systemctl reload nginx

# Install SSL certificate
sudo certbot --nginx -d customer-data-management.duckdns.org
```

**7. Verify**

```bash
curl https://customer-data-management.duckdns.org/customer/data/get
```

---

## 🔧 Useful Docker Commands

```bash
# View running containers
docker compose ps

# View live app logs
docker compose logs -f app

# Restart the app
docker compose restart app

# Rebuild and redeploy after code changes
docker compose up -d --build

# Stop everything
docker compose down

# Stop and wipe database volume (fresh start)
docker compose down -v
```

---

## 📱 Client Integration

### React / Web (Vercel)
Set your API base URL environment variable:
```
REACT_APP_API_BASE_URL=https://customer-data-management.duckdns.org
```

### Android
No special configuration needed — HTTPS works out of the box on all Android API levels.

For **HTTP** (local dev only), add to `res/xml/network_security_config.xml`:
```xml
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="false">10.0.2.2</domain>
    </domain-config>
</network-security-config>
```

---

## 📄 License

This project is open source and available under the [MIT License](LICENSE).

---

## 👨‍💻 Author

**Rupesh** — [github.com/rupesh9698](https://github.com/rupesh9698)

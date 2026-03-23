# TASK-march-13
**Texnologiyalar**
Java 21 & Spring Boot 3.x

Spring Data JPA (Hibernate)

PostgreSQL (WSL / Ubuntu mühitində quraşdırılmış)

Liquibase (Database migration)

MapStruct (DTO Mapping)

Lombok (Kodun optimallaşdırılması)

WSL 2 (Windows Subsystem for Linux)


**🚀 Necə İşə Salmalı? (How to Run)**
1.Layihəni klonlayın:

Bash
git clone https://github.com/fuadhajiyev/product-management-api.git


**2.PostgreSQL-i (WSL) başladın:**
Terminalda PostgreSQL servisinin aktiv olduğuna əmin olun:

Bash
sudo service postgresql start

**3.Baza yaradın:**
psql terminalına daxil olaraq product_db bazasını yaradın:

SQL
CREATE DATABASE product_db;


**4.Application Properties:**
src/main/resources/application.properties faylında baza istifadəçi adınızı və şifrənizi daxil edin:

Properties
spring.datasource.url=jdbc:postgresql://localhost:5432/product_db
spring.datasource.username=sizin_istifadeci
spring.datasource.password=sizin_sifre


**5.Proyekti işə salın:**

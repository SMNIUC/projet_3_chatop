# ChaTop - Backend

ChaTop est une application de mise en relation pour la location saisonnière. Ce dépôt contient le code source de l'API Backend développée avec **Java**, **Spring Boot** et **Spring Security**.

## Prérequis techniques

Avant de commencer, assurez-vous d'avoir installé les outils suivants sur votre machine :

*   **Java SDK 25** (ou version compatible configurée dans le `pom.xml`)
*   **Maven** (ou utilisez le wrapper `mvnw` inclus)
*   **MySQL** (Serveur de base de données)
*   Un IDE Java (IntelliJ IDEA, Eclipse, ou VS Code)

---

## 1. Configuration de la Base de Données

Le projet utilise une base de données MySQL nommée `chatop`.

### Étape 1.1 : Création de la base

Connectez-vous à votre serveur MySQL et exécutez les commandes SQL suivantes pour créer la base de données et l'utilisateur (optionnel si vous utilisez root) :
```
sql
CREATE DATABASE chatop;
```
Si vous souhaitez créer un utilisateur dédié (recommandé pour correspondre aux variables d'environnement par défaut) :
```
sql
CREATE USER 'user'@'localhost' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON chatop.* TO 'user'@'localhost';
FLUSH PRIVILEGES;
```
### Étape 1.2 : Structure des tables

Assurez-vous que le schéma de base de données correspond aux entités JPA (`User`, `Rental`, `Message`). Hibernate est configuré avec `spring.jpa.hibernate.ddl-auto=update` (ou similaire par défaut), ce qui peut générer les tables au démarrage. Cependant, pour un environnement de production ou propre, voici le schéma attendu :
```
sql
USE chatop;

CREATE TABLE `users` (
`id` integer PRIMARY KEY AUTO_INCREMENT,
`email` varchar(255),
`name` varchar(255),
`password` varchar(255),
`created_at` timestamp,
`updated_at` timestamp
);

CREATE TABLE `rentals` (
`id` integer PRIMARY KEY AUTO_INCREMENT,
`name` varchar(255),
`surface` numeric,
`price` numeric,
`picture` varchar(255),
`description` varchar(2000),
`owner_id` integer,
`created_at` timestamp,
`updated_at` timestamp,
FOREIGN KEY (`owner_id`) REFERENCES `users` (`id`)
);

CREATE TABLE `messages` (
`id` integer PRIMARY KEY AUTO_INCREMENT,
`rental_id` integer,
`user_id` integer,
`message` varchar(2000),
`created_at` timestamp,
`updated_at` timestamp,
FOREIGN KEY (`rental_id`) REFERENCES `rentals` (`id`),
FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
);
```
---

## 2. Installation et Configuration du Projet

### Étape 2.1 : Cloner le dépôt
```
bash
git clone https://github.com/votre-username/ChaTop_backend.git
cd ChaTop_backend
```
### Étape 2.2 : Variables d'Environnement

Le fichier `application.properties` utilise des variables d'environnement pour les identifiants de la base de données afin de ne pas exposer de mots de passe en clair.

Vous devez définir les variables d'environnement suivantes sur votre système ou dans votre configuration de lancement IDE :

*   `DB_USER` : Votre nom d'utilisateur MySQL (ex: `root` ou `user`)
*   `DB_PASSWORD` : Votre mot de passe MySQL

**Exemple de configuration dans IntelliJ IDEA :**
1.  Allez dans **Run/Debug Configurations**.
2.  Sélectionnez la configuration **ChatopApplication**.
3.  Dans le champ **Environment variables**, ajoutez : `DB_USER=root;DB_PASSWORD=votre_mot_de_passe`.

---

## 3. Lancement de l'application

### Via le terminal (Maven Wrapper)

À la racine du projet, exécutez :
```
bash
./mvnw spring-boot:run
```
*(Sur Windows, utilisez `mvnw.cmd spring-boot:run`)*

### Via l'IDE

Ouvrez la classe principale `com.openclassrooms.chatop.ChatopApplication` et cliquez sur le bouton **Run**.

Le serveur démarrera par défaut sur le port **8080**.
L'URL de base de l'API est : `http://localhost:8080/api`

---

## 4. Documentation de l'API (Swagger)

Une fois l'application lancée, vous pouvez accéder à la documentation interactive Swagger UI pour tester les endpoints directement depuis votre navigateur.

*   **URL Swagger UI :** [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
*   **URL JSON OpenAPI :** [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

### Authentification dans Swagger

La plupart des routes sont protégées par JWT. Pour les tester :
1.  Utilisez l'endpoint `/api/auth/register` ou `/api/auth/login` pour obtenir un token.
2.  Copiez le token (sans le préfixe "Bearer ", juste la chaîne de caractères).
3.  Dans Swagger, cliquez sur le bouton **Authorize** (cadenas) en haut à droite.
4.  Collez le token dans le champ "Value" et validez.

---

## 5. Architecture et Technologies

*   **Langage :** Java 25
*   **Framework :** Spring Boot 3.x
*   **Sécurité :** Spring Security (JWT avec clés RSA asymétriques)
*   **Base de données :** MySQL
*   **ORM :** Spring Data JPA / Hibernate
*   **Documentation :** SpringDoc OpenAPI (Swagger)
*   **Outils :** Lombok, Maven

Les clés RSA pour la signature des tokens JWT se trouvent dans `src/main/resources/keys/`.

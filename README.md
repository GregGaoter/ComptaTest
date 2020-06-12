# Projet de test d'un ERP

## Projet

### Présentation

Ce projet consiste en la réalisations des tests d'une application de facturation et de comptabilité développée en Java avec le framwork Spring (configuration XML).

### Contexte

L'équipe de développement est en train de réaliser un système de facturation et de comptabilité pour un client. Le développement a débuté depuis quelques temps et nous devons commencer à vérifier que l'application fonctionne correctement, qu'elle répond bien aux règles de gestion et les respecte.

### Objectif

L'objectif de ce projet est de réaliser 2 types de tests :

- des **tests unitaires** : leurs objectifs sont de valider les règles de gestion unitaires de chaque "composant" de l'application
- des **tests d'intégration** : leurs objectifs sont de valider la bonne interaction entre les différents composants de l'application

Les tests sont implémentés et automatisés à l'aide de :

- Maven
- JUnit
- AssertJ
- Mockito
- Travis CI
- SonarCloud

Les tests sont lancés via le build Maven.

Les tests d'intégration font l'objet de profils Maven spécifiques.

## Résultats SonarCloud

![image-20200612121952459](C:\Users\gregg\AppData\Roaming\Typora\typora-user-images\image-20200612121952459.png)

## Organisation du répertoire

*   Modules Maven :
    *   `myerp-business` : code source de la couche *business*
    *   `myerp-consumer` : code source de la couche *consumer*
    *   `myerp-model` : code source de la couche *model*
    *   `myerp-technical` : code source de la couche *technical*
*   `doc` : documentation
*   `docker` : répertoire relatifs aux conteneurs _docker_ utiles pour le projet
    *   `dev` : environnement de développement


## Environnement de développement

Les composants nécessaires lors du développement sont disponibles via des conteneurs _docker_.
L'environnement de développement est assemblé grâce à _docker-compose_
(cf docker/dev/docker-compose.yml).

Il comporte :

*   une base de données _PostgreSQL_ contenant un jeu de données de démo (`postgresql://127.0.0.1:9032/db_myerp`)

### Lancement

    cd docker/dev
    docker-compose up


### Arrêt

    cd docker/dev
    docker-compose stop


### Remise à zero

    cd docker/dev
    docker-compose stop
    docker-compose rm -v
    docker-compose up

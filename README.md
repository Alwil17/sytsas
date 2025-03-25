# Gestion de Salle de Sport API

Ce projet est une API de gestion de salle de sport développée avec **Spring Boot** et **MongoDB**. La documentation de l'API est générée automatiquement grâce à **Swagger**. Le diagramme de classes, réalisé en PlantUML, présente les principales entités et relations utilisées dans l'application.

## Fonctionnalités

- **Gestion des salles de sport** : Création, mise à jour et suppression de salles.
- **Gestion des équipements** : Association d'équipements aux salles de sport.
- **Gestion des abonnements et adhérents** : Suivi des abonnements des membres aux salles.
- **Gestion des entraîneurs et de leurs carrières** : Attribution d'entraîneurs aux salles et gestion de leurs horaires de disponibilité.
- **Documentation interactive** : Interface Swagger pour tester et documenter l’API.

## Diagramme UML

Le diagramme suivant décrit les relations entre les principales classes de l'application :

```plantuml
@startuml

class SalleSport {
    + numero_salle : int
    + adresse_salle : String
    + capacite : int
}

class Equipement {
    + num_equip : int
    + nom_equip : String
    + fonction_equip : String
    + quantite : int
}

class Abonnement {
    + date_debut : DateTime
    + date_fin : DateTime
    + actif : bool
}

class Adherent {
    + num_membre : int
    + poids : double
}

class Carriere {
    + date_debut : DateTime
    + date_fin : DateTime
}

class Horaire {
    + debut : double
    + fin : double
}

class Disponibilite {
    + date_dispo : Date
}

class Personne {
    + matricule : int
    + nom : String
    + prenom : String
    + adresse : String
    + telephone : int
    + courriel : String
}

class Entraineur {
    + num_coach : int
    + specialite : String
    + date_emb : DateTime
    + sal_base : double
}

'SalleSport possède plusieurs Equipements
SalleSport "1" -- "1..*" Equipement

'Relation many-to-many entre SalleSport et Adherent avec Abonnement comme table composite
SalleSport "1..*" -- "1..*" Adherent : Abonnement

'Relation many-to-many entre SalleSport et Entraineur avec Carriere comme table composite
SalleSport "1..*" -- "1..*" Entraineur : Carriere

'Relation many-to-many entre Entraineur et Horaire avec Disponibilite comme table composite
Entraineur "1..*" -- "1..*" Horaire : Disponibilite

'Héritage de Personne par Adherent et Entraineur
Personne <|-- Adherent
Personne <|-- Entraineur

@enduml

# DevLog — Séance 1 : Versionning, Maven et POO

## Ce que j'ai compris de Git et GitHub

Git est l'outil de versionnement qui fonctionne localement sur ma machine : il garde
l'historique du projet (commits, branches, tags) même sans connexion Internet.
GitHub est la plateforme distante qui héberge ce dépôt et permet de le partager,
de le sauvegarder, et de visualiser l'historique en ligne. Git gère le versionnement,
GitHub héberge et facilite la collaboration autour de ce même dépôt.

## Les branches créées et leur rôle

- **main** : branche stable, ne contient aucun développement direct. Sert uniquement
  à recevoir les fusions validées (via `develop`).
- **develop** : branche d'intégration. Reçoit les fusions de chaque `feature/*` une
  fois le travail terminé et vérifié.
- **feature/00-tooling** : mise en place de l'environnement (Git, Maven, .gitignore,
  README). Fusionnée puis supprimée après intégration.
- **feature/01-domain** : modélisation du domaine (AbstractEntity, Salle, Reservation,
  enums, diagramme UML). Fusionnée puis supprimée après intégration.
- **feature/02-memory-basics** : ajout des données de démonstration en mémoire.
  Fusionnée puis supprimée après intégration.

## Les tags posés et leur signification

- **v0.1.0** : outillage Git et Maven initialisé, projet compilable.
- **v0.2.0** : modèle objet du domaine terminé (entités + relations + diagramme).
- **v0.3.0** : premières données en mémoire disponibles (salles + réservations).

Chaque tag marque un état du projet qui compile et qui correspond à un incrément
fonctionnel validé, pas simplement la fin d'une période de travail.

## Ce que Maven apporte au projet

Maven standardise la structure du projet (`src/main/java`, `src/test/java`) et
automatise les tâches répétitives : compilation (`mvn compile`), vérification
(`mvn test`), packaging (`mvn package`). Le fichier `pom.xml` décrit le projet
(groupId, artifactId, version Java) sans avoir à gérer manuellement un classpath.

## Diagramme de classes

Voir le fichier [domaine.mdj](domaine.mdj) (ouvrable avec StarUML). Il représente :
AbstractEntity (abstract)
|
+---- Salle (final)
|
+---- Reservation (final)

Salle 1 -------- 0..* Reservation

## Pourquoi AbstractEntity est abstraite

`AbstractEntity` ne représente aucune entité métier concrète : elle sert uniquement
de base commune, portant l'identifiant (`id`) partagé par toutes les entités du
domaine. La déclarer `abstract` empêche de l'instancier directement — seules ses
sous-classes concrètes (`Salle`, `Reservation`) ont un sens métier réel.

## Pourquoi Salle et Reservation sont final

Le domaine ne prévoit aucun sous-type de ces entités : il n'y a pas de notion de
"sous-catégorie de Salle" ou de "sous-catégorie de Reservation" à gérer. Les
déclarer `final` empêche un héritage non justifié et protège la cohérence du
modèle métier.

## Relation entre Salle et Reservation

C'est une association, pas un héritage : une `Reservation` n'est pas une `Salle`
(le test "est un" ne fonctionne pas). Une `Salle` peut recevoir plusieurs
`Reservation` (cardinalité **1 — 0..\***), et chaque `Reservation` concerne une
seule `Salle`.

## Difficulté rencontrée et résolution

En travaillant sur l'Incrément 1, j'ai d'abord committé mes premiers fichiers
(.gitignore, pom.xml, README) directement sur la branche `develop`, sans passer
par `feature/00-tooling` comme l'impose la stratégie. Je m'en suis rendu compte
en vérifiant `git branch` et en comparant avec la stratégie imposée par le support.

Comme le projet était encore petit, j'ai choisi de reconstruire l'historique
proprement plutôt que de laisser cet écart : suppression du dossier `.git`,
réinitialisation, puis recréation des trois incréments (`feature/00-tooling`,
`feature/01-domain`, `feature/02-memory-basics`) avec des commits atomiques,
fusionnés avec `--no-ff` et tagués (`v0.1.0`, `v0.2.0`, `v0.3.0`), avant de
republier l'historique sur GitHub avec `git push --force`.

Cette difficulté m'a fait comprendre concrètement pourquoi la discipline de
travail (toujours créer une branche `feature/*` avant de coder, même pour de
petites étapes) fait partie intégrante du résultat attendu, au même titre que
le code Java lui-même.
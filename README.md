# <center> SYM_Labo1</center>

note : pour ajouter l'image sur la carte sd, il faut d'abord ajouter la path AppData\Local\Android\Sdk\platform-tools aux variables d'environnement et apres se placer ou est l'image et faire adb push <nom_image> /sdcard/Download/nom_image. Voilà je sais pas si c'est ce qu'il faut faire mais yolo
#### Question 1

Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue supplémentaire ?

Pour ajouter blablabla

blabla on créé de nouveaux dossiers à l'intérieur de res/. Chaque dossier suit un code de format spécifique, devant respecter la règle suivante:

    <type de ressource >-b+<code de langue >[+<code de pays>]


On peut alors se retrouver avec l'exemple suivant:

cc, cv

  cf android doc


Si une traduction est manquante dans la langue par défaut ou une langue supplémentaire**, l'application lève une exception, stopant l'execution du programme.

#### Question 2

Dans l’exemple fourni, sur le dialogue pop-up, nous affichons l’icône android.R.drawable.ic_dialog_alert , disponible dans le SDK Android mais qui n’est pas très bien adapté visuellement à notre utilisation. Nous souhaitons la remplacer avec notre propre icône, veuillez indiquer comment procéder. Dans quel(s) dossier(s) devons-nous ajouter cette image ? Décrivez brièvement la logique derrière la gestion des ressources de type « image » sur Android.

Nous devons créer un nouveau dossier dans res, selon la configuration suivante:

    <nom_ressource>-<qualificateur_config>

Le dossier <strong><nom_ressource></strong> correspondra aux images par défauts.

<strong><qualificateur_config></strong>

Permettra de qualifier pour chaque configuration l'image à utiliser

Pour remplacer l'icoe par notre image, nous avons créé un dossier blabla blabla

https://developer.android.com/guide/topics/resources/providing-resources
#### Question 3

Lorsque le login est réussi, vous êtes censé chaîner une autre Activity en utilisant un Intent. Si je presse le bouton "Back" de l'interface Android, que puis-je constater ? Comment faire pour que l'application se comporte de manière plus logique ? Veuillez discuter de la logique derrière les activités Android.

L'application se met en pause. Nous souhaiterions que le bouton back agisse à l'intérieur de l'application. De ce fait, il faudrait ajouter le code suivant dans l'activité qui chaîne la principale:

pour restarter l'activité principale
pour arrêter l'activité courante

#### Question 4

On pourrait imaginer une situation où cette seconde Activity fournit un résultat (par exemple l’IMEI ou une autre chaîne de caractères) que nous voudrions récupérer dans l'Activity de départ. Comment procéder ?

Nous procédons comme nous l'avons fait pour passer l'adresse mail en chaînant l'activité principale à la seconde avec le code suivant:


#### Question 5

Vous noterez que la méthode getDeviceId() du TelephonyManager, permettant d’obtenir l’IMEI du téléphone, est dépréciée depuis la version 26 de l’API. Veuillez discuter de ce que cela implique lors du développement et de présenter une façon d’en tenir compte avec un exemple de code.

Deprecier mais nouvelle méthode. Implique une conception de maintenance du système pour pouvoir bkabla
cf donnée, toute les versions doivent être..

portabilité.

#### Question 6

Dans l’activité de login, en plaçant le téléphone (ou l’émulateur) en mode paysage (landscape), nous constatons que les 2 champs de saisie ainsi que le bouton s’étendent sur toute la largeur de l’écran. Veuillez réaliser un layout spécifique au mode paysage qui permet un affichage mieux adapté et indiquer comment faire pour qu’il soit utilisé automatiquement à l’exécution.



Android a prévu une
#### Question 7

Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.
BIM. C'est fait.
#### Question 8

Implémenter dans votre code les méthodes onCreate(), onStart(), onResume(), onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécution dans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Si vous aviez (par exemple) une connexion Bluetooth (ou des connexions bases de données, ou des capteurs activés) ouverte dans votre Activity, que faudrait-il peut-être faire, à votre avis (nous ne vous demandons pas de code ici) ?

La méthode onCreate() est appelée pour créer l'activité et la configurer. On l'appel par exemple à l'appui de l'icône dans le menu pincipal de l'OS?????
La méthode onStart() est appelée pour démarrer l'activité.
La méthode onPause() permet de mettre en pause l'application. Cela est le cas par exemple lors du vérouillage de l'écran, d'appels, d'appui sur le bouton home.
La méthode onStop() permet de stoper l'activité,
La méthode onDestroy() permet de détruire l'activité

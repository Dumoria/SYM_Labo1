Lagha Oussame, Jobin Simon,
 Thomas Benjamin

# <center> SYM_Labo1</center>



###### 1)   <strong>Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue supplémentaire ?</strong>

<p style="text-align:justify;">
En premier lieu, il faut définir une langue par défaut. Pour cela, il faut créer un fichier <strong>strings.xml</strong> dans le dossier <strong>res/values/</strong> contenant l'ensemble des chaînes à afficher.
Pour ajouter de nouvelle langue à notre application, on crééera des fichiers dont le chemin d'accès est de la forme:</p>

    res/values-<code-pays>/strings.xml  
<p style="text-align:justify;">
et qui contiendra l'ensemble des traductions dans la langue correspondante.
Exemple:</p>

    res/values-fr/strings.xml

<p style="text-align:justify;">
Si une traduction est manquante, l'application affichera le texte dans la langue par défaut et si le texte manque dans la langue par défaut, l'application crashera.
Le changement de langue s'effectue dans les réglages du téléphone et s'applique à l'ensemble des applications. </p>

###### 2)   <strong>Dans l’exemple fourni, sur le dialogue pop-up, nous affichons l’icône android.R.drawable.ic_dialog_alert , disponible dans le SDK Android mais qui n’est pas très bien adapté visuellement à notre utilisation. Nous souhaitons la remplacer avec notre propre icône, veuillez indiquer comment procéder. Dans quel(s) dossier(s) devons-nous ajouter cette image ? Décrivez brièvement la logique derrière la gestion des ressources de type « image » sur Android.</strong>
<p style="text-align:justify;">
Nous devons ajouter l'image souhaitée dans le dossier <strong>res/drawable/</strong>. L'image sera alors accessible via la ligne de code suivante:</p>

    R.drawable.nom_image

<p style="text-align:justify;">
Android prend en compte également la gestion des différents formats de densité de pixel (ou dpi), ceci pour pouvoir assurer un affichage optimisé pour chaque type d'ecran.
Il suffit donc de placer l'image souhaitée redimensionnée dans chacun des sous-dossier dpi pour afficher notre image au bon format sur l'ensemble des écrans.</p>

###### 3)   <strong>Lorsque le login est réussi, vous êtes censé chaîner une autre Activity en utilisant un Intent. Si je presse le bouton "Back" de l'interface Android, que puis-je constater ? Comment faire pour que l'application se comporte de manière plus logique ? Veuillez discuter de la logique derrière les activités Android.</strong>
<p style="text-align:justify;">
L'application se ferme.
Androit gère les activités via une pile, dans laquelle nous retrouvons l'ensemble des activités en tâche de fond. Une simple pression du bouton back permet de retourner à la dernière activité de la pile.
Comme nous avions détruite l'activité principale, l'application fermait automatiquement au lieu de reprendre sur celle-ci. Pour parvenir à un comportement logique, nous devons donc supprimer la ligne suivante de l'activité principale: </p>

    finish();

Et ajouter le code suivant à la seconde activité:

    @Override
    public void onBackPressed() {
      Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
      startActivity(intent);
      finish();
    }


###### 4)   <strong>On pourrait imaginer une situation où cette seconde Activity fournit un résultat (par exemple l’IMEI ou une autre chaîne de caractères) que nous voudrions récupérer dans l'Activity de départ. Comment procéder ?</strong>
<p style="text-align:justify;">
Nous procédons comme nous l'avons fait pour passer l'adresse mail en chaînant l'activité principale à la seconde. Pour cela, il faut ajouter dans la seconde activité:</p>

    TelephonyManager tm = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    String imei = tm.getDeviceId();

    Intent intent = new Intent(LoginDisplayActivity.this, MainActivity.class);
    intent.putExtra("IMEI", imei);
    startActivity(intent);
    finish();


Nous pouvons alors facilement récupérer l'IMEI en ajoutant au fichier MainActivity.java:

    Intent intent = getIntent();
	TextView imei;

	if (intent != null) {
		imei.setText(intent.getStringExtra("IMEI"));
	}
###### 5)   <strong>Vous noterez que la méthode getDeviceId() du TelephonyManager, permettant d’obtenir l’IMEI du téléphone, est dépréciée depuis la version 26 de l’API. Veuillez discuter de ce que cela implique lors du développement et de présenter une façon d’en tenir compte avec un exemple de code.</strong>
<p style="text-align:justify;">
Certaines méthodes deviennent dépréciée au fil du temps, que ce soit pour des raisons de sécurité, de performances ou de compabilité.
Il est donc important lors du développement d'une application de penser à la maintenance de l'application afin que celle-ci corresponde aux standards imposé par Android. Dans le cas contraire, notre application pourrait être supprimée du store.
On doit également penser à la portabilité de l'application, entre les différents appareils, présent et à venir, et de leur système d'exploitation, en prévoyant d'utiliser l'une ou l'autre méthode en fonction de la version de l'appareil en question. Ceci s'illustre à travers le code suivant:</p>

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String imei = telephonyMgr.getImei();       //Pour les nouvelles versions
    } else {
                String imei = telephonyMgr.getDeviceId();   //Pour les anciennes versions
    }


###### 6)   <strong>Dans l’activité de login, en plaçant le téléphone (ou l’émulateur) en mode paysage (landscape), nous constatons que les 2 champs de saisie ainsi que le bouton s’étendent sur toute la largeur de l’écran. Veuillez réaliser un layout spécifique au mode paysage qui permet un affichage mieux adapté et indiquer comment faire pour qu’il soit utilisé automatiquement à l’exécution.</strong>



Android gère les deux types d'affichages en mettant dans deux dossiers différents les ressources à afficher.
Le dossier suivant permet de gérer l'affichage en mode vertical:

    res/layout-port/
Le dossier suivant permet de gérer l'affichage en mode paysage:

    res/layout-land/

Il nous suffit donc de créer le dossier en question et d'y mettre les ressources souhaitées pour que notre application utilise automatiquement le bon format en fonction de l'orientation de l'appareil.

###### 7) <strong>Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.</strong>
BIM. C'est fait.
###### 8)  <strong>Implémenter dans votre code les méthodes onCreate(), onStart(), onResume(), onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécution dans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Si vous aviez (par exemple) une connexion Bluetooth (ou des connexions bases de données, ou des capteurs activés) ouverte dans votre Activity, que faudrait-il peut-être faire, à votre avis (nous ne vous demandons pas de code ici) ?</strong>

La méthode onCreate() est appelée pour créer l'activité et la configurer. On l'appel par exemple à l'appui de l'icône dans le menu pincipal de l'OS?????
La méthode onStart() est appelée pour démarrer l'activité.
La méthode onPause() permet de mettre en pause l'application. Cela est le cas par exemple lors du vérouillage de l'écran, d'appels, d'appui sur le bouton home.
La méthode onStop() permet de stoper l'activité,
La méthode onDestroy() permet de détruire l'activité

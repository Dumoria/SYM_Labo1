Lagha Oussame, Jobin Simon,
 Thomas Benjamin

# <center> SYM_Labo1</center>



###### 1)   <strong>Comment organiser les textes pour obtenir une application multi-langues (français, allemand, italien, langue par défaut : anglais) ? Que se passe-t-il si une traduction est manquante dans la langue par défaut ou dans une langue supplémentaire ?</strong>

<p style="text-align:justify;">
En premier lieu, il faut définir une langue par défaut. Pour cela, il faut créer un fichier <strong>strings.xml</strong> dans le dossier <strong>res/values/</strong> contenant l'ensemble des chaînes à afficher.
Pour ajouter de nouvelle langue à notre application, on créera des fichiers dont le chemin d'accès est de la forme:</p>

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
Android prend en compte également la gestion des différents formats de densité de pixel (ou dpi), ceci pour pouvoir assurer un affichage optimisé pour chaque type d'écran.
Il suffit donc de placer l'image souhaitée dans chacun des sous-dossier dpi pour afficher notre image au bon format sur l'ensemble des écrans.</p>

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
Certaines méthodes deviennent dépréciées au fil du temps, que ce soit pour des raisons de sécurité, de performances ou de compatibilité.
Il est donc important lors du développement d'une application de penser à la maintenance de l'application afin que celle-ci corresponde aux standards imposés par Android. Dans le cas contraire, notre application pourrait être supprimée du store.
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

<p style="text-align:justify;">Il nous suffit donc de créer le dossier en question et d'y mettre les ressources souhaitées pour que notre application utilise automatiquement le bon format en fonction de l'orientation de l'appareil.</p>

###### 7) <strong>Le layout de l’interface utilisateur de l’activité de login qui vous a été fourni a été réalisé avec un LinearLayout à la racine. Nous vous demandons de réaliser un layout équivalent utilisant cette fois-ci un RelativeLayout.</strong>
BIM. C'est fait.
###### 8)  <strong>Implémenter dans votre code les méthodes onCreate(), onStart(), onResume(), onPause(), onStop(), etc... qui marquent le cycle de vie d'une application Android, et tracez leur exécution dans le logcat. Décrivez brièvement à quelles occasions ces méthodes sont invoquées. Si vous aviez (par exemple) une connexion Bluetooth (ou des connexions bases de données, ou des capteurs activés) ouverte dans votre Activity, que faudrait-il peut-être faire, à votre avis (nous ne vous demandons pas de code ici) ?</strong>
<p style="text-align:justify;">Il faudrait surcharger les méthodes suivantes afin d'assurer un fonctionnement correcte et sécurisé de notre application. Il faudrait par exemple être sûr que notre activité ferme la connexion Bluetooth avant de se terminer pour éviter des problèmes  de connexion non-authorisée.
La méthode <strong>onCreate()</strong> est appelée pour créer l'activité et la configurer.
La méthode <strong>onStart()</strong> est appelée pour démarrer l'activité. Elle va se charger de rendre visible l'activité et la prépare à passer en premier plan et devenir interactive. Cette méthode est appelée après la création de l'activité.
La méthode <strong>onResume()</strong> est appelée lorsque l'activité est dans son état de reprise et est en premier plan. C'est dans cet état que l'utilisateur peut interagir avec l'application. L'application reste dans cet état tant que rien ne fait perdre le focus sur cette-dernière, comme le ferait par exemple un appel.
La méthode <strong>onPause()</strong> permet de mettre en pause l'activité. C'est cette dernière qui est appelée dès que l'utilisateur souhaite quitter l'activité, qui passera en background sans pour autant être détruite.
La méthode <strong>onStop()</strong> permet de stopper l'activité. Elle est appelée dès que l'activité n'est plus visible pour l'utilisateur, par exemple lorsque l'activité a fini son exécution et est sur le point d'être terminée.
La méthode <strong>onDestroy()</strong> est appelée avant la destruction de l'activité. Ceci arrive par exemple lors d'un changement de configuration. L'activité est alors temporairement détruite par le système.

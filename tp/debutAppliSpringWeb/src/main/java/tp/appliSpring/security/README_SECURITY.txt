/rest/** ==> Api REST sécurisée avec JWT (soit généré par ce seveur (partie generic.standalone), soit généré par Outh2 )
/site/** ==> partie générant directement html (Thymeleaf ou jsp)
/**      ==> autres parties ==> ressources statiques

=======
dans la plupart des projets modernes, plus de partie site(jsp ou thymeleaf)
et api rest sécurisée via Oauth2/OIDC (avec partie generic.standalone inutile).

====
profiles de l'application:
* par défaut : sécurité pas activée.
* withSecurity : sécurité en mode oauth2/OIDC
* withSecurity,withoutOAuth2 :  sécurité en mode autonome (JWT généré par ce serveur) pour partie "rest" /ou "site"

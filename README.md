# SOB-DouniaPol

## Autors
- Dounia Lakdimi
- Pol Pujol

## Índex
1. [Introducció](#introducció)
2. [Estructura de la Pràctica](#estructura-de-la-pràctica)
3. [Decisions de Disseny](#decisions-de-disseny)
5. [Conclusions](#conclusions)
6. [Manual d’Instal·lació](#manual-dinstal·lació)

## Introducció
En aquesta pràctica hem desenvolupat una aplicació web a partir d'una API Web creada en entregues anteriors. L’objectiu principal és construir una plataforma web que permeti als usuaris explorar i llogar videojocs disponibles.

L’aplicació consta de dues parts: una pública i una altra privada. La part pública és accessible per a tothom i la part privada només per als usuaris registrats. Els usuaris poden explorar una llista de videojocs disponibles. Quan se selecciona un videojoc, es mostra informació com la seva localització, preu, tipus, descripció, etc.

A la part privada, els usuaris registrats poden realitzar lloguers de videojocs. Per accedir a aquesta àrea privada, han d’estar autenticats utilitzant un formulari que requereix nom i contrasenya.

En resum, aquesta pràctica ens proporciona l’oportunitat d’aplicar conceptes clau de desenvolupament web, com la creació d’interfícies d’usuari i la implementació d’autenticació i gestió de dades en una base de dades.

## Estructura de la Pràctica
- **Web Pages**: Aquí es troben totes les pàgines web com `LlistatJocs`, `DetallsJocs`, `Carrito`, `ConfirmarLloguer`, etc.
- **Web Pages/WEB-INF**: Informació de configuració de l’aplicació web com la configuració de sessions, arxius d’inici, pàgines d’error, etc.
- **Web Pages/WEB-INF/views**: Aquí tenim les implementacions de les vistes `login-form` i `login-success`.
- **Web Pages/resources/css**: Implementacions dels estils.
- **Web Pages/resources/img**: Imatges JPEG.
- **Web Pages/resources/js**: Implementacions JavaScript.
- **Source Packages/deim.urv.cat.homework2.controller**: Inclou els controladors de l’aplicació.
- **Source Packages/deim.urv.cat.homework2.model**: Inclou els CDI beans que conformen el model de l’aplicació web.
- **Source Packages/deim.urv.cat.homework2.service**: Inclou les implementacions dels diferents serveis d’accés a la capa de dades implementada a la pràctica com a recursos REST.

- ## Decisions de Disseny
Pel que fa a les decisions de disseny, hem optat per diferents estils per a cada pàgina web implementada.

- **Part Pública**: Hem optat per un disseny centrat tant verticalment com horitzontalment amb un fons blanc per utilitzar ombres i vores per ressaltar i donar profunditat als elements de la pàgina. Això permet que la interfície sigui el més visible possible i neta.
  
- **Inici de Sessió**: Hem dissenyat una operació `@Secured` `userLogin` a la façana d’usuaris de la primera pràctica. S’introdueix el nom i la contrasenya als headers i retorna codi 200 si l’usuari està autenticat. Després d’implementar aquesta operació, vam implementar un client Java amb la funció `login` que rep nom i contrasenya per paràmetre, forma la URL de login i guarda les credencials al bean `@SessionScoped UsuariCredencialsBean`.

Finalment, vam implementar el controlador associat a la vista `login-form` que rep per injecció el servei i una instància de `UsuariCredencialsBean`. Aquí es fa la crida a `login` per verificar les dades i guardar les credencials.

### Exemples de Visualització de les Pàgines Web
- `LlistatJocs.jsp`
- `DetallsJocs.jsp`
- `login-form.jsp`

- ## Conclusions
Aquesta pràctica ens ha permès aplicar i consolidar els nostres coneixements en el desenvolupament d’aplicacions web utilitzant tecnologies Java EE, com JSP, JSTL i Bootstrap, així com la implementació de sistemes d’autenticació segurs i la gestió de dades en una base de dades.

### Passos detallats d'instal·lació

1. Un cop instal·lada la pràctica 1 i la pràctica 2, cal fer un Clean and Build de Homework1 i executar-la. S’ha de tenir en compte que la base de dades té el nom de `sob_grup_18`.
2. Un cop executada la primera pràctica, fer Clean and Build de la segona i executar-la. Es redirigirà a la pàgina de LogIn on l’usuari pot introduir les dades o prémer el botó de Llista Jocs per mirar els videojocs de forma pública.

3. **Configuració de la Base de Dades**:
   - Assegureu-vos que la base de dades `sob_grup_18` està creada i en execució.
   - Importeu les taules necessàries per al funcionament de l'aplicació. Podeu trobar els scripts SQL necessaris a la carpeta `sql` del projecte.

4. **Configuració de l'Entorn de Desenvolupament**:
   - Assegureu-vos de tenir instal·lada una JDK compatible (per exemple, JDK 8 o superior).
   - Configureu el vostre IDE (IntelliJ IDEA, Eclipse, NetBeans, etc.) amb les variables d'entorn necessàries.

5. **Desplegament de l'Aplicació**:
   - Utilitzeu un servidor d'aplicacions com Apache Tomcat.
   - Desplegueu l'arxiu WAR generat després del Clean and Build al vostre servidor d'aplicacions.

6. **Inici de l'Aplicació**:
   - Accediu a la URL del servidor on heu desplegat l'aplicació. Per exemple, `http://localhost:8080/Pràctica2`.
   - La pàgina principal hauria de ser la pàgina de LogIn on els usuaris poden iniciar sessió o navegar per la llista de jocs.


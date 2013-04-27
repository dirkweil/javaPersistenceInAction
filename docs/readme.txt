Java Persistence in Action
==========================

Dies sind die Demoprojekte zu meinen Sessions auf den Konferenzen W-JAX 12 und JAX 13:

a) W-JAX 12: Java Persistence in Action - Einsatz von JPA 2.x in Java-EE-Anwendungen

   Hierzu gehören die Teilprojekte java-persistence-in-action-detached und java-persistence-in-action-attached. Es handelt sich
   dabei um Java-EE-6-Webanwendungen, die bspw. auf einem JBoss 7.1.x deployt werden können. Im Server wird eine Datasource mit
   dem JNDI-Namen "jdbc/beantrial" erwartet. Ein Beispiel-Deployment-File dazu ist beantrial-ds.xml.

b) JAX 13: Java Persistence in Action - Features jenseits des Entry-Levels

   Die beiden Teilprojekte java-persistence-in-action-advanced und java-persistence-in-action-v2_1 werden in diesem Vortrag
   angesprochen. Es sind Java-SE-Projekte, die neben den betrachteten Entity-Klassen i. W. aus Unit-Tests bestehen. Diese
   können bspw. in der genutzten IDE direkt ausgeführt werden. Über die Maven-Profiled ist einstellbar, welcher Provider
   genutzt werden soll (Default: EclipseLink) und ob statt der In-Memory-DB eine File-basierte DB geutzt wird.
   

Alle Projekte sind Maven-Projekte. Sie können bspw. in Eclipse importiert werden, wenn dort ein entsprechendes Plugin installiert
ist.

   
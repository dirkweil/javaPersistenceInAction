package de.gedoplan.beantrial.jpia.repository;

import de.gedoplan.beantrial.jpia.interceptor.LogEntityManager;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

/**
 * Repository-Master.
 *
 * Diese Klasse bietet Repository-übergreifende Methoden (derzeit nur {@link #saveAll()}) an.
 *
 * @author dw
 */
@ApplicationScoped
@LogEntityManager
public class RepositoryMaster implements Serializable
{
  @Inject
  EntityManager entityManager;

  // TALKABOUT A8 Spezielle Methode zur Propagierung aller Änderungen in die DB

  /**
   * Alle Änderungen abspeichern.
   */
  @Transactional
  public void saveAll()
  {
    // Ein Application Managed Entity Manager nimmt nicht automatisch an Transaktionen teil. Daher hier an TX anbinden. Ansonsten
    // würden Änderungen in den gemanagten Entities nicht in der DB abgelegt
    this.entityManager.joinTransaction();

    // Dies sollte eigentlich unnötig sein, da alle Änderungen ohnehin bei TX-Ende abgespeichert werden. Bei einigen
    // Server-Versionen war dies aber nicht der Fall, daher zur Sicherheit explizit aufrufen.
    this.entityManager.flush();
  }
}

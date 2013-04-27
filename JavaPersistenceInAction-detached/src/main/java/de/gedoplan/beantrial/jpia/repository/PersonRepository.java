package de.gedoplan.beantrial.jpia.repository;

import de.gedoplan.baselibs.enterprise.interceptor.Transactional;
import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.beantrial.jpia.entity.Person;
import de.gedoplan.beantrial.jpia.interceptor.LogEntityManager;

// TALKABOUT D2) PersonRepository bietet DB-Funktionalität für Person an

/**
 * DB-Zugriffsklasse für {@link Person}.
 * 
 * @author dw
 */
// @Stateless
@Transactional
// @Transactional aus Seam oder Deltaspike geht nicht, da Basisklassenmethoden nicht intercepted werden (Bug / Feature?)
@LogEntityManager
public class PersonRepository extends SingleIdEntityRepository<Integer, Person>
{
  // TALKABOUT D6) Wegen Detached-Mode ist eine spezielle Methode zum Finden inkl. Nachladen von Lazy-Attributen vorhanden

  /**
   * Person anhand ihrer ID finden und Lazy-Attribute nachladen.
   * 
   * @param id ID
   * @return gefundene Person oder <code>null</code>
   */
  public Person findByIdEager(Integer id)
  {
    Person person = findById(id);
    if (person != null)
    {
      person.loadLazyAttributes();
    }
    return person;
  }

}

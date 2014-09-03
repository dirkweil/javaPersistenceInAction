package de.gedoplan.beantrial.jpia.repository;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.beantrial.jpia.entity.Person;
import de.gedoplan.beantrial.jpia.interceptor.LogEntityManager;

import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

// TALKABOUT D2) PersonRepository bietet DB-Funktionalität für Person an

/**
 * DB-Zugriffsklasse für {@link Person}.
 *
 * @author dw
 */
// @Stateless
@Transactional
@LogEntityManager
public class PersonRepository extends SingleIdEntityRepository<Integer, Person>
{
  // TALKABOUT D6) Wegen Detached-Mode ist eine spezielle Methode zum Finden inkl. Nachladen von Lazy-Attributen vorhanden

  /**
   * Person anhand ihrer ID finden und Lazy-Attribute telNummern und hobbies nachladen.
   *
   * @param id ID
   * @return gefundene Person oder <code>null</code>
   */
  public Person findByIdIncludingTelAndHobbies(Integer id)
  {
    // JPA 2.1: Entity Graph als Load Graph anfordern
    Map<String, Object> loadTelAndHobbies = new HashMap<>();
    loadTelAndHobbies.put("javax.persistence.loadgraph", this.entityManager.getEntityGraph("Person.TelAndHobbies"));
    Person person = this.entityManager.find(Person.class, id, loadTelAndHobbies);

    // Vor JPA 2.1: Nachladen der betroffenen Attribute durch (beliebigen) Zugriff
    //    if (person != null)
    //    {
    //      person.getTelefonNummern().size();
    //      person.getHobbies().size();
    //    }

    return person;
  }

}

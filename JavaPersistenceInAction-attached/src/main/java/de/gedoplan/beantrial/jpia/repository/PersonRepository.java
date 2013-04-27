package de.gedoplan.beantrial.jpia.repository;

import de.gedoplan.baselibs.persistence.repository.SingleIdEntityRepository;
import de.gedoplan.beantrial.jpia.entity.Person;
import de.gedoplan.beantrial.jpia.interceptor.LogEntityManager;

//TALKABOUT A2) PersonRepository bietet DB-Funktionalität für Person an

/**
 * DB-Zugriffsklasse für {@link Person}.
 * 
 * Die Methoden dürfen ohne TX aufgerufen werden. Die Änderungen werden aber erst durch Verknüpfung des EntityManagers mit einer
 * Transaktion und Commit in die DB propagiert.
 * 
 * @author dw
 */
@LogEntityManager
public class PersonRepository extends SingleIdEntityRepository<Integer, Person>
{
}

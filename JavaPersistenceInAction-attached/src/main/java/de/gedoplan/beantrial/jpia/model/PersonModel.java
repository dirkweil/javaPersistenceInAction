package de.gedoplan.beantrial.jpia.model;

import de.gedoplan.baselibs.faces.validation.FacesValidationHelper;
import de.gedoplan.beantrial.jpia.entity.Person;
import de.gedoplan.beantrial.jpia.interceptor.LogEntityManagerInterceptor;
import de.gedoplan.beantrial.jpia.repository.PersonRepository;
import de.gedoplan.beantrial.jpia.repository.RepositoryMaster;
import de.gedoplan.beantrial.jpia.service.ConversationService;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.persistence.Persistence;

import org.apache.commons.logging.Log;

//TALKABOUT A3) PersonModel ist die JSF-Bean zur Unterstützung der Views view/personXXX.xhtml

/**
 * Präsentationsmodel für Person-bezogene Views.
 * 
 * @author dw
 */
@Model
@ConversationScoped
public class PersonModel implements Serializable
{
  @Inject
  PersonRepository      personRepository;

  @Inject
  RepositoryMaster      repositoryMaster;

  @Inject
  ConversationService   conversationService;

  @Inject
  FacesValidationHelper facesValidationHelper;

  @Inject
  Log                   logger;

  List<Person>          personen;

  Person                person;

  /**
   * Alle Personen aus der DB liefern.
   * 
   * @return Personen
   */
  public List<Person> getPersonen()
  {
    if (this.personen == null)
    {
      this.logger.debug("Loading all persons from db");

      this.personen = this.personRepository.findAll();

      for (Person person : this.personen)
      {
        logPerson("  ", person);
      }
    }

    return this.personen;
  }

  /**
   * Aktuell bearbeitete Person liefern.
   * 
   * @return Person
   */
  public Person getPerson()
  {
    return this.person;
  }

  /**
   * Aktionsmethode zum Editieren einer Person.
   * 
   * @param person Person
   * @return "edit"
   */
  public String edit(Person person)
  {
    this.conversationService.beginConversation();

    // TALKABOUT A5) Person ist vom EM gemanagt - keine besondere Behandlung nötig

    this.person = person;

    logPerson("Edit", this.person);

    return "edit";
  }

  /**
   * Aktionsmethode zum Erzeugen und Editieren einer neuen Person.
   * 
   * @return "edit"
   */
  public String create()
  {
    this.conversationService.beginConversation();

    this.person = new Person(null, null);
    this.logger.debug("Edit new person");

    return "edit";
  }

  /**
   * Aktionsmethode zum Löschen einer Person.
   * 
   * @param person Person
   */
  public void delete(Person person)
  {
    logPerson("Delete", person);

    this.personRepository.remove(person);
    this.repositoryMaster.saveAll();

    this.personen = null;
  }

  /**
   * Aktionsmethode zum Speichern der aktuell bearbeiteten Person.
   * 
   * @return "ok", falls gespeichert, <code>null</code> im Fehlerfall
   */
  public String save()
  {
    if (this.person != null)
    {
      if (!this.facesValidationHelper.validate(this.person))
      {
        return null;
      }
    }

    logPerson("Save", this.person);

    try
    {
      // TALKABOUT A6) Person ist gemanagt oder transient; persist kann immer genutzt werden

      this.personRepository.persist(this.person);

      // TALKABOUT A7) Änderungen speichern durch spezielle Methode

      this.repositoryMaster.saveAll();

      this.conversationService.endConversation();
      this.personen = null;

      return "ok";
    }
    catch (Exception e) // CHECKSTYLE:IGNORE
    {
      if (this.facesValidationHelper.convertToFacesMessages(e) == 0)
      {
        throw e;
      }
    }

    return null;
  }

  /**
   * Aktionsmethode zum Abbrechen der Bearbeitung der aktuellen Person.
   * 
   * @return "ok"
   */
  public String cancel()
  {
    this.conversationService.endConversation();
    this.personen = null;

    return "ok";
  }

  private void logPerson(String action, Person person)
  {
    Object attachedEM = LogEntityManagerInterceptor.getAttachedEntityManager(person);
    boolean telefonNummernLoaded = Persistence.getPersistenceUtil().isLoaded(person, "telefonNummern");
    boolean hobbiesLoaded = Persistence.getPersistenceUtil().isLoaded(person, "hobbies");
    this.logger.debug(action + " " + person.toDebugString() + " (attachedEM=" + attachedEM + ", telefonNummernLoaded=" + telefonNummernLoaded + ", hobbiesLoaded=" + hobbiesLoaded + ")");
  }
}

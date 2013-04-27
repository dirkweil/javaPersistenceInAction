package de.gedoplan.beantrial.jpia.entity.optlock;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Person.TABLE_NAME)
//
// EclipseLink: Alle Spaltenwerte für Optimistic Locking vergleichen
@org.eclipse.persistence.annotations.OptimisticLocking(type = org.eclipse.persistence.annotations.OptimisticLockingType.ALL_COLUMNS)
//
// Hibernate: Alle Spaltenwerte für Optimistic Locking vergleichen (setzt Dynamic Update voraus)
@org.hibernate.annotations.OptimisticLocking(type = org.hibernate.annotations.OptimisticLockType.ALL)
@org.hibernate.annotations.DynamicUpdate(true)
//
// OpenJPA: Alle Spaltenwerte für Optimistic Locking vergleichen
@org.apache.openjpa.persistence.jdbc.VersionStrategy("state-comparison")
public class Person extends GeneratedLongIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_PERSON";

  /**
   * Name.
   */
  private String             name;

  /**
   * Vorname.
   */
  private String             vorname;

  /**
   * Konstruktor.
   * 
   * @param name
   * @param vorname
   */
  public Person(String name, String vorname)
  {
    this.name = name;
    this.vorname = vorname;
  }

  /**
   * Konstruktor.
   */
  protected Person()
  {
  }

  /**
   * Wert liefern: {@link #name}.
   * 
   * @return Wert
   */
  public String getName()
  {
    return this.name;
  }

  /**
   * Wert setzen: {@link #name}.
   * 
   * @param name Wert
   */
  public void setName(String name)
  {
    this.name = name;
  }

  /**
   * Wert liefern: {@link #vorname}.
   * 
   * @return Wert
   */
  public String getVorname()
  {
    return this.vorname;
  }

  /**
   * Wert setzen: {@link #vorname}.
   * 
   * @param vorname Wert
   */
  public void setVorname(String vorname)
  {
    this.vorname = vorname;
  }

}

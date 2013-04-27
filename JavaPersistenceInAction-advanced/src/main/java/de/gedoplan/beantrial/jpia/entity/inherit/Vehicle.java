package de.gedoplan.beantrial.jpia.entity.inherit;

import de.gedoplan.baselibs.persistence.entity.StringIdEntity;
import de.gedoplan.beantrial.jpia.eclipselink.VehicleClassExtractor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Access(AccessType.FIELD)
@Table(name = Vehicle.TABLE_NAME)
//
// EclipseLink: Klasse zur Ermittlung des konkreten Typs gelesener Objekte hinzufügen
// (s. a. Customizer bei den abgeleiteten Klassen)
@org.eclipse.persistence.annotations.ClassExtractor(VehicleClassExtractor.class)
//
// Hibernate: SQL-Fragment zur Ermittelung des Diskriminators angegen
@org.hibernate.annotations.DiscriminatorFormula("case substring(id,1,2) when 'C.' then 'Car' when 'L.' then 'Lorry' when 'S.' then 'Ship' end")
//
// OpenJPA: Strategie-Klasse anmelden
@org.apache.openjpa.persistence.jdbc.DiscriminatorStrategy("de.gedoplan.beantrial.jpia.openjpa.VehicleDiscriminatorStrategy")
public abstract class Vehicle extends StringIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_VEHICLE";

  /**
   * Name.
   */
  private String             name;

  /**
   * Konstruktor.
   */
  protected Vehicle()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param name
   */
  protected Vehicle(String id, String name)
  {
    super(id);
    this.name = name;
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

}

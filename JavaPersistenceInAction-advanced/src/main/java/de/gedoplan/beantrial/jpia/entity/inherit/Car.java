package de.gedoplan.beantrial.jpia.entity.inherit;

import de.gedoplan.beantrial.jpia.eclipselink.CarCustomizer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
//
// EclipseLink: Mittels Customizer Bedingung für Queries konfigurieren, die Enträge dieser Klasse und ihrer Subklassen liefern
@org.eclipse.persistence.annotations.Customizer(CarCustomizer.class)
public class Car extends Vehicle
{
  /**
   * Präfix der ID.
   */
  public static final String ID_PREFIX = "C.";

  /**
   * Anzahl Türen.
   */
  private int                noOfDoors;

  /**
   * Konstruktor.
   */
  protected Car()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param name
   * @param noOfDoors
   */
  public Car(String id, String name, int noOfDoors)
  {
    super(id, name);
    this.noOfDoors = noOfDoors;
  }

  /**
   * Wert liefern: {@link #noOfDoors}.
   * 
   * @return Wert
   */
  public int getNoOfDoors()
  {
    return this.noOfDoors;
  }

  /**
   * Wert setzen: {@link #noOfDoors}.
   * 
   * @param noOfDoors Wert
   */
  public void setNoOfDoors(int noOfDoors)
  {
    this.noOfDoors = noOfDoors;
  }
}

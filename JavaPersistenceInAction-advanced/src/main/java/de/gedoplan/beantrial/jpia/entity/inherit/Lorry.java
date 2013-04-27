package de.gedoplan.beantrial.jpia.entity.inherit;

import de.gedoplan.beantrial.jpia.eclipselink.LorryCustomizer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
//
// EclipseLink: Mittels Customizer Bedingung für Queries konfigurieren, die Enträge dieser Klasse und ihrer Subklassen liefern
@org.eclipse.persistence.annotations.Customizer(LorryCustomizer.class)
public class Lorry extends Car
{
  /**
   * Präfix der ID.
   */
  public static final String ID_PREFIX = "L.";

  /**
   * Nutzlast.
   */
  private double             payLoad;

  /**
   * Konstruktor.
   */
  protected Lorry()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param name
   * @param noOfDoors
   */
  public Lorry(String id, String name, int noOfDoors, double payLoad)
  {
    super(id, name, noOfDoors);
    this.payLoad = payLoad;
  }

  /**
   * Wert liefern: {@link #payLoad}.
   * 
   * @return Wert
   */
  public double getPayLoad()
  {
    return this.payLoad;
  }

  /**
   * Wert setzen: {@link #payLoad}.
   * 
   * @param payLoad Wert
   */
  public void setPayLoad(double payLoad)
  {
    this.payLoad = payLoad;
  }

}

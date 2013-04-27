package de.gedoplan.beantrial.jpia.entity.inherit;

import de.gedoplan.beantrial.jpia.eclipselink.ShipCustomizer;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;

@Entity
@Access(AccessType.FIELD)
//
// EclipseLink: Mittels Customizer Bedingung für Queries konfigurieren, die Enträge dieser Klasse und ihrer Subklassen liefern
@org.eclipse.persistence.annotations.Customizer(ShipCustomizer.class)
public class Ship extends Vehicle
{
  /**
   * Präfix der ID.
   */
  public static final String ID_PREFIX = "S.";

  /**
   * Bruttoraumzahl.
   */
  private double             brz;

  /**
   * Konstruktor.
   */
  protected Ship()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param id
   * @param name
   * @param brz
   */
  public Ship(String id, String name, int brz)
  {
    super(id, name);
    this.brz = brz;
  }

  /**
   * Wert liefern: {@link #brz}.
   * 
   * @return Wert
   */
  public double getBrz()
  {
    return this.brz;
  }

  /**
   * Wert setzen: {@link #brz}.
   * 
   * @param brz Wert
   */
  public void setBrz(double brz)
  {
    this.brz = brz;
  }

}

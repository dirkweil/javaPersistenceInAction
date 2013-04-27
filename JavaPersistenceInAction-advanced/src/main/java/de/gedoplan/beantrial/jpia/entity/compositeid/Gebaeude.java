package de.gedoplan.beantrial.jpia.entity.compositeid;

import de.gedoplan.baselibs.persistence.entity.GeneratedLongIdEntity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Entity-Klasse für Gebäude.
 * 
 * @author dw
 */
@Entity
@Access(AccessType.FIELD)
@Table(name = Gebaeude.TABLE_NAME)
public class Gebaeude extends GeneratedLongIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_GEBAEUDE";

  /**
   * Adresse.
   */
  private String             adresse;

  /**
   * Konstruktor.
   */
  public Gebaeude()
  {
  }

  /**
   * Konstruktor.
   * 
   * @param adresse Adresse
   */
  public Gebaeude(String adresse)
  {
    this.adresse = adresse;
  }

  /**
   * Wert liefern: {@link #adresse}.
   * 
   * @return Wert
   */
  public String getAdresse()
  {
    return this.adresse;
  }

  /**
   * Wert setzen: {@link #adresse}.
   * 
   * @param adresse Wert
   */
  public void setAdresse(String adresse)
  {
    this.adresse = adresse;
  }

}

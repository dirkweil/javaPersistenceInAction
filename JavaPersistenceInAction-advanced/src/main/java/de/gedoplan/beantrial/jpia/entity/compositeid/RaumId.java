package de.gedoplan.beantrial.jpia.entity.compositeid;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID-Klasse zu {@link Raum}.
 * 
 * @author dw
 */
@Embeddable
public class RaumId implements Serializable
{
  /**
   * ID des Gebäudes.
   */
  @Column(name = "GEBAEUDE_ID")
  private Long   gebaeudeId;

  /**
   * Raumnummer.
   */
  @Column(name = "RAUM_NUMMER")
  private String raumNummer;

  /**
   * Konstruktor.
   * 
   * @param gebaeudeId ID des Gebäudes
   * @param raumNummer Gebäudeflügel-Kennzeichen
   */
  public RaumId(Long gebaeudeId, String raumNummer)
  {
    this.gebaeudeId = gebaeudeId;
    this.raumNummer = raumNummer;
  }

  /**
   * Konstruktor.
   */
  public RaumId()
  {
  }

  /**
   * Wert liefern: {@link #gebaeudeId}.
   * 
   * @return Wert
   */
  public Long getGebaeudeId()
  {
    return this.gebaeudeId;
  }

  /**
   * Wert setzen: {@link #gebaeudeId}.
   * 
   * @param gebaeudeId Wert
   */
  public void setGebaeudeId(Long gebaeudeId)
  {
    this.gebaeudeId = gebaeudeId;
  }

  /**
   * Wert liefern: {@link #raumNummer}.
   * 
   * @return Wert
   */
  public String getRaumNummer()
  {
    return this.raumNummer;
  }

  /**
   * Wert setzen: {@link #raumNummer}.
   * 
   * @param raumNummer Wert
   */
  public void setRaumNummer(String raumNummer)
  {
    this.raumNummer = raumNummer;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode()
  {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((this.raumNummer == null) ? 0 : this.raumNummer.hashCode());
    result = prime * result + ((this.gebaeudeId == null) ? 0 : this.gebaeudeId.hashCode());
    return result;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj)
  {
    if (this == obj)
    {
      return true;
    }
    if (obj == null)
    {
      return false;
    }
    if (getClass() != obj.getClass())
    {
      return false;
    }
    RaumId other = (RaumId) obj;
    if (this.raumNummer == null)
    {
      if (other.raumNummer != null)
      {
        return false;
      }
    }
    else if (!this.raumNummer.equals(other.raumNummer))
    {
      return false;
    }
    if (this.gebaeudeId == null)
    {
      if (other.gebaeudeId != null)
      {
        return false;
      }
    }
    else if (!this.gebaeudeId.equals(other.gebaeudeId))
    {
      return false;
    }
    return true;
  }

  /**
   * {@inheritDoc}
   * 
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString()
  {
    return "FluegelId [gebaeudeId=" + this.gebaeudeId + ", raumNummer=" + this.raumNummer + "]";
  }

}

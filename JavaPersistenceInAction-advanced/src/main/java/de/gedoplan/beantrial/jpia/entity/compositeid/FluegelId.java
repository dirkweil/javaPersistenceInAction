package de.gedoplan.beantrial.jpia.entity.compositeid;

import java.io.Serializable;

/**
 * ID-Klasse zu {@link Fluegel}.
 * 
 * @author dw
 */
public class FluegelId implements Serializable
{
  /**
   * ID des Gebäudes.
   */
  private Long   gebaeude; // muss namensgleich zu Entity-Attribut sein!

  /**
   * Gebäudeflügel-Kennzeichen.
   */
  private String fluegelKz;

  /**
   * Konstruktor.
   * 
   * @param gebaeudeId ID des Gebäudes
   * @param fluegelKz Gebäudeflügel-Kennzeichen
   */
  public FluegelId(Long gebaeudeId, String fluegelKz)
  {
    this.gebaeude = gebaeudeId;
    this.fluegelKz = fluegelKz;
  }

  /**
   * Konstruktor.
   */
  public FluegelId()
  {
  }

  /**
   * Wert liefern: {@link #gebaeude}.
   * 
   * @return Wert
   */
  public Long getGebaeudeId()
  {
    return this.gebaeude;
  }

  /**
   * Wert setzen: {@link #gebaeude}.
   * 
   * @param gebaeudeId Wert
   */
  public void setGebaeudeId(Long gebaeudeId)
  {
    this.gebaeude = gebaeudeId;
  }

  /**
   * Wert liefern: {@link #fluegelKz}.
   * 
   * @return Wert
   */
  public String getFluegelKz()
  {
    return this.fluegelKz;
  }

  /**
   * Wert setzen: {@link #fluegelKz}.
   * 
   * @param fluegelKz Wert
   */
  public void setFluegelKz(String fluegelKz)
  {
    this.fluegelKz = fluegelKz;
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
    result = prime * result + ((this.fluegelKz == null) ? 0 : this.fluegelKz.hashCode());
    result = prime * result + ((this.gebaeude == null) ? 0 : this.gebaeude.hashCode());
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
    FluegelId other = (FluegelId) obj;
    if (this.fluegelKz == null)
    {
      if (other.fluegelKz != null)
      {
        return false;
      }
    }
    else if (!this.fluegelKz.equals(other.fluegelKz))
    {
      return false;
    }
    if (this.gebaeude == null)
    {
      if (other.gebaeude != null)
      {
        return false;
      }
    }
    else if (!this.gebaeude.equals(other.gebaeude))
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
    return "FluegelId [gebaeudeId=" + this.gebaeude + ", fluegelKz=" + this.fluegelKz + "]";
  }

}

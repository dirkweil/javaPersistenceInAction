package de.gedoplan.beantrial.jpia.entity.compositeid;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * ID-Klasse zu {@link Flur}.
 * 
 * @author dw
 */
@Embeddable
public class FlurId implements Serializable
{
  /**
   * ID des Gebäudes.
   */
  private Long   gebaeude; // muss gleichnamig zum Feld der Entity sein

  /**
   * Gebäudeflügel-Kennzeichen.
   */
  private String fluegelKz;

  /**
   * Stockwerk-Nummer.
   */
  private int    stock;

  /**
   * Konstruktor.
   * 
   * @param gebaeudeId
   * @param fluegelKz
   * @param stock
   */
  public FlurId(Long gebaeudeId, String fluegelKz, int stock)
  {
    this.gebaeude = gebaeudeId;
    this.fluegelKz = fluegelKz;
    this.stock = stock;
  }

  /**
   * Konstruktor.
   */
  public FlurId()
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
   * Wert liefern: {@link #stock}.
   * 
   * @return Wert
   */
  public int getStock()
  {
    return this.stock;
  }

  /**
   * Wert setzen: {@link #stock}.
   * 
   * @param stock Wert
   */
  public void setStock(int stock)
  {
    this.stock = stock;
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
    result = prime * result + this.stock;
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
    FlurId other = (FlurId) obj;
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
    if (this.stock != other.stock)
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
    return "FlurId [gebaeudeId=" + this.gebaeude + ", fluegelKz=" + this.fluegelKz + ", stock=" + this.stock + "]";
  }

}

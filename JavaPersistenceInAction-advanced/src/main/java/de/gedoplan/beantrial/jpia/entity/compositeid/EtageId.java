package de.gedoplan.beantrial.jpia.entity.compositeid;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ID-Klasse zu {@link Etage}.
 * 
 * @author dw
 */
@Embeddable
public class EtageId implements Serializable
{
  /**
   * ID des Gebäudes.
   */
  @Column(name = "GEBAEUDE_ID")
  private Long gebaeudeId;

  /**
   * Stockwerk-Nummer.
   */
  private int  stock;

  /**
   * Konstruktor.
   * 
   * @param gebaeudeId ID des Gebäudes
   * @param stock Stockwerk-Nummer
   */
  public EtageId(Long gebaeudeId, int stock)
  {
    this.gebaeudeId = gebaeudeId;
    this.stock = stock;
  }

  /**
   * Konstruktor.
   */
  public EtageId()
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
    result = prime * result + ((this.gebaeudeId == null) ? 0 : this.gebaeudeId.hashCode());
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
    EtageId other = (EtageId) obj;
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
    return "EtageId [gebaeudeId=" + this.gebaeudeId + ", stock=" + this.stock + "]";
  }

}

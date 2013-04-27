package de.gedoplan.beantrial.jpia.entity.performance;

import de.gedoplan.baselibs.persistence.entity.GeneratedIntegerIdEntity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = Group.TABLE_NAME)
public class Group extends GeneratedIntegerIdEntity
{
  /**
   * Tabellenname.
   */
  public static final String TABLE_NAME = "JPIA_GROUP";

  /**
   * Name.
   */
  private String             name;

  /**
   * Berechtigungen.
   */
  @OneToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH })
  @JoinColumn(name = "GROUP_ID")
  //
  // Variante 1: Permissions bei Bedarf mit einem SELECT holen, wobei die Group-IDs in einer IN-Klausel mit max. 10 Einträgen
  // spezifiziert werden; siehe auch Hinweise bei User.groups
  // @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.IN, size = 10)
  @org.hibernate.annotations.BatchSize(size = 10)
  //
  // Variante 2: Permissions bei Bedarf mit einem SELECT holen, wobei die Group-IDs per Subselect spezifiziert werden; siehe auch
  // Hinweise bei User.groups
  @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.EXISTS)
  // @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SUBSELECT)
  //
  // Variante 3: Permissions bei Bedarf mit einem SELECT holen, wobei die Group-IDs per Join spezifiziert werden; siehe auch
  // Hinweise bei User.groups
  // @org.eclipse.persistence.annotations.BatchFetch(value = org.eclipse.persistence.annotations.BatchFetchType.JOIN)
  @org.apache.openjpa.persistence.jdbc.EagerFetchMode(org.apache.openjpa.persistence.jdbc.FetchMode.PARALLEL)
  private Set<Permission>    permissions;

  /**
   * Konstruktor.
   * 
   * @param name
   */
  public Group(String name)
  {
    this.name = name;

    this.permissions = new HashSet<>();
  }

  /**
   * Konstruktor.
   */
  protected Group()
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
   * Wert liefern: {@link #permissions}.
   * 
   * @return Wert
   */
  public Set<Permission> getPermissions()
  {
    return this.permissions;
  }

  public boolean deepEquals(Group otherGroup)
  {
    if (!this.equals(otherGroup))
    {
      return false;
    }

    if (this.name == null)
    {
      if (otherGroup.name != null)
      {
        return false;
      }
    }
    else if (!this.name.equals(otherGroup.name))
    {
      return false;
    }

    if (this.permissions == null)
    {
      if (otherGroup.permissions != null)
      {
        return false;
      }
    }
    else
    {
      if (this.permissions.size() != otherGroup.permissions.size())
      {
        return false;
      }

      // TODO: Dieser Vergleich ist ineffizient programmiert
      permissionLoop: for (Permission permission : this.permissions)
      {
        for (Permission otherPermission : otherGroup.permissions)
        {
          if (permission.equals(otherPermission))
          {
            if (!permission.deepEquals(otherPermission))
            {
              return false;
            }

            continue permissionLoop;
          }
        }
      }
    }

    return true;
  }

}

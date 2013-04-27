package de.gedoplan.beantrial.jpia.entity.convert;

public enum Taetigkeit
{
  PROGRAMMIERUNG
  {
    @Override
    public String getKuerzel()
    {
      return "81";
    }
  },
  BERATUNG
  {
    @Override
    public String getKuerzel()
    {
      return "40";
    }
  },
  SCHULUNG
  {
    @Override
    public String getKuerzel()
    {
      return "60";
    }
  };

  public abstract String getKuerzel();

  public static Taetigkeit valueOfKuerzel(String kuerzel)
  {
    for (Taetigkeit taetigkeit : Taetigkeit.values())
    {
      if (taetigkeit.getKuerzel().equals(kuerzel))
      {
        return taetigkeit;
      }
    }

    throw new IllegalArgumentException("Ungueltiges Kuerzel: " + kuerzel);
  }

}

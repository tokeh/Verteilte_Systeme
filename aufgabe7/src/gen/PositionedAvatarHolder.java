package gen;

/**
* aufgabe7/gen/PositionedAvatarHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Sonntag, 9. Juni 2013 14:58 Uhr MESZ
*/

public final class PositionedAvatarHolder implements org.omg.CORBA.portable.Streamable
{
  public gen.PositionedAvatar value = null;

  public PositionedAvatarHolder ()
  {
  }

  public PositionedAvatarHolder (gen.PositionedAvatar initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = gen.PositionedAvatarHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    gen.PositionedAvatarHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return gen.PositionedAvatarHelper.type ();
  }

}

package gen;


/**
* aufgabe7/gen/FolksHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Sonntag, 9. Juni 2013 14:58 Uhr MESZ
*/

public final class FolksHolder implements org.omg.CORBA.portable.Streamable
{
  public gen.PositionedAvatar value[] = null;

  public FolksHolder ()
  {
  }

  public FolksHolder (gen.PositionedAvatar[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = gen.FolksHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    gen.FolksHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return gen.FolksHelper.type ();
  }

}

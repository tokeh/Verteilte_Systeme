package gen;

/**
* aufgabe7/gen/CorbaForumViewHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Sonntag, 9. Juni 2013 14:58 Uhr MESZ
*/

public final class CorbaForumViewHolder implements org.omg.CORBA.portable.Streamable
{
  public gen.CorbaForumView value = null;

  public CorbaForumViewHolder ()
  {
  }

  public CorbaForumViewHolder (gen.CorbaForumView initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = gen.CorbaForumViewHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    gen.CorbaForumViewHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return gen.CorbaForumViewHelper.type ();
  }

}

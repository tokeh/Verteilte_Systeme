package gen;


/**
* aufgabe7/gen/PositionedAvatar.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Sonntag, 9. Juni 2013 14:58 Uhr MESZ
*/

public final class PositionedAvatar implements org.omg.CORBA.portable.IDLEntity
{
  public String name = null;
  public gen.Position position = null;

  public PositionedAvatar ()
  {
  } // ctor

  public PositionedAvatar (String _name, gen.Position _position)
  {
    name = _name;
    position = _position;
  } // ctor

} // class PositionedAvatar

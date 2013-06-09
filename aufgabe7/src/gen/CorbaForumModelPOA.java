package gen;


/**
* aufgabe7/gen/CorbaForumModelPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from forum.idl
* Sonntag, 9. Juni 2013 14:58 Uhr MESZ
*/

public abstract class CorbaForumModelPOA extends org.omg.PortableServer.Servant
 implements gen.CorbaForumModelOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("registerView", new java.lang.Integer (0));
    _methods.put ("deregisterView", new java.lang.Integer (1));
    _methods.put ("moveNorth", new java.lang.Integer (2));
    _methods.put ("moveEast", new java.lang.Integer (3));
    _methods.put ("moveSouth", new java.lang.Integer (4));
    _methods.put ("moveWest", new java.lang.Integer (5));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // aufgabe7/gen/CorbaForumModel/registerView
       {
         try {
           String name = in.read_string ();
           gen.CorbaForumView view = gen.CorbaForumViewHelper.read (in);
           this.registerView (name, view);
           out = $rh.createReply();
         } catch (gen.AlreadyBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.AlreadyBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 1:  // aufgabe7/gen/CorbaForumModel/deregisterView
       {
         try {
           String name = in.read_string ();
           this.deregisterView (name);
           out = $rh.createReply();
         } catch (gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 2:  // aufgabe7/gen/CorbaForumModel/moveNorth
       {
         try {
           String name = in.read_string ();
           this.moveNorth (name);
           out = $rh.createReply();
         } catch (gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 3:  // aufgabe7/gen/CorbaForumModel/moveEast
       {
         try {
           String name = in.read_string ();
           this.moveEast (name);
           out = $rh.createReply();
         } catch (gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // aufgabe7/gen/CorbaForumModel/moveSouth
       {
         try {
           String name = in.read_string ();
           this.moveSouth (name);
           out = $rh.createReply();
         } catch (gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // aufgabe7/gen/CorbaForumModel/moveWest
       {
         try {
           String name = in.read_string ();
           this.moveWest (name);
           out = $rh.createReply();
         } catch (gen.NotBoundException $ex) {
           out = $rh.createExceptionReply ();
           gen.NotBoundExceptionHelper.write (out, $ex);
         }
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:aufgabe7/gen/CorbaForumModel:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public CorbaForumModel _this() 
  {
    return CorbaForumModelHelper.narrow(
    super._this_object());
  }

  public CorbaForumModel _this(org.omg.CORBA.ORB orb) 
  {
    return CorbaForumModelHelper.narrow(
    super._this_object(orb));
  }


} // class CorbaForumModelPOA

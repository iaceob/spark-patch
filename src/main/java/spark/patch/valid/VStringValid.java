package spark.patch.valid;


import spark.patch.qm.Qm;
import spark.patch.ret.R;
import spark.patch.thr.ValidException;

public class VStringValid extends VObjectValid<VStringValid> {


  protected VStringValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    super(qm, k, existsMode, valueMode);
  }

  public VStringValid email(String message) throws ValidException {
    return this.email(R.err(message));
  }

  public VStringValid email(R r) throws ValidException {
    if (super.skipExists()) return this;
    super.blank(r);
    String value = super._value().string();
    boolean match = value.matches("^[a-z0-9]+([._\\\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$");
    if (!match)
      throw new ValidException(r);
    return this;
  }

}

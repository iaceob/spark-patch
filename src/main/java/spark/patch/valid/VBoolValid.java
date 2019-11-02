package spark.patch.valid;


import spark.patch.qm.Qm;
import spark.patch.ret.R;
import spark.patch.thr.ValidException;

public class VBoolValid extends VObjectValid<VBoolValid> {

  protected VBoolValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    super(qm, k, existsMode, valueMode);
  }

  public VBoolValid is(String message) throws ValidException {
    return this.is(R.err(message));
  }

  public VBoolValid is(R r) throws ValidException {
    if (super.skipExists()) return this;
    super.blank(r);
    String v = super._value().string();
    if (!(v.equals("true") ||
      v.equals("false") ||
      v.equals("1") ||
      v.equals("0")))
      throw new ValidException(r);
    return this;
  }

}

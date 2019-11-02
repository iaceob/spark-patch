package spark.patch.valid;

import io.enoa.toolkit.is.Is;
import spark.patch.qm.Qm;
import spark.patch.ret.R;
import spark.patch.thr.ValidException;

public class VNumberValid extends VObjectValid<VNumberValid> {


  protected VNumberValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    super(qm, k, existsMode, valueMode);
  }

  public VNumberValid is(String message) throws ValidException {
    return this.is(R.err(message));
  }

  public VNumberValid is(R r) throws ValidException {
    if (super.skipExists()) return this;
    super.blank(r);
    if (Is.not().number(super._value().string()))
      throw new ValidException(r);
    return this;
  }

}

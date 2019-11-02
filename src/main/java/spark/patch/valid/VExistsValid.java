package spark.patch.valid;

import io.enoa.toolkit.is.Is;
import spark.patch.qm.Qm;
import spark.patch.thr.ValidException;

public class VExistsValid extends VObjectValid<VExistsValid> {


  protected VExistsValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    super(qm, k, existsMode, valueMode);
  }

  public VExistsValid yes(Valid.IValidThen then) throws ValidException {
    if (Is.truthy(super._value().string()))
      then.then();
    return this;
  }

  public VExistsValid no(Valid.IValidThen then) throws ValidException {
    if (Is.not().truthy(super._value().string()))
      then.then();
    return this;
  }

  public VStringValid string() {
    return new VStringValid(super.qm(), super.k(), true, false);
  }

  public VNumberValid number() {
    return new VNumberValid(this.qm(), super.k(), true, false);
  }

  public VBoolValid bool() {
    return new VBoolValid(super.qm(), super.k(), true, false);
  }

}

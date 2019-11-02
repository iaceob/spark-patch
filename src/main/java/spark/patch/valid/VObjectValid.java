package spark.patch.valid;

import io.enoa.toolkit.is.Is;
import io.enoa.toolkit.value.EnoaValue;
import spark.patch.qm.Qm;
import spark.patch.ret.R;
import spark.patch.thr.ValidException;

public class VObjectValid<T extends VObjectValid> {

  private Qm qm;
  private String k;
  private boolean existsMode;
  private boolean valueMode;

//  protected enum VMode {
//    KEY,
//    EXISTS,
//    VALUE
//  }

  protected VObjectValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    this.qm = qm;
    this.k = k;
    this.existsMode = existsMode;
    this.valueMode = valueMode;
  }

  protected Qm qm() {
    return this.qm;
  }

  protected String k() {
    return this.k;
  }

//  protected VMode vmode() {
//    return this.vmode;
//  }

  protected boolean existsMode() {
    return this.existsMode;
  }

  protected boolean valueMode() {
    return this.valueMode;
  }

  boolean skipExists() {
    return this.existsMode && Is.not().truthy(this._value().string());
  }

  EnoaValue _value() {
    if (this.valueMode)
      return EnoaValue.with(this.k);
    return this.qm.value(this.k);
  }

  public T blank(String message) throws ValidException {
    this.blank(R.err(message));
    return (T) this;
  }

  public T blank(R r) throws ValidException {
    boolean blany = Is.not().truthy(this._value().string());
    if (!blany)
      return (T) this;
    if (!this.existsMode)
      throw new ValidException(r);
    return (T) this;
  }

}

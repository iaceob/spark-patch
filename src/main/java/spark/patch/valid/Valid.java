package spark.patch.valid;

import spark.Request;
import spark.patch.qm.Qm;
import spark.patch.ret.R;
import spark.patch.thr.ValidException;

public class Valid {


  public interface IValidThen {
    void then() throws ValidException;
  }

  private Qm qm;

  public Valid(Qm qm) {
    this.qm = qm;
  }

  public static Valid with(Request request) {
    return new Valid(Qm.with(request));
  }

  public Qm qm() {
    return this.qm;
  }

  public VStringValid string(String k) {
    return new VStringValid(this.qm, k, false, false);
  }

  public VNumberValid number(String k) {
    return new VNumberValid(this.qm, k, false, false);
  }

  public VBoolValid bool(String k) {
    return new VBoolValid(this.qm, k, false, false);
  }

  public VExistsValid exists(String k) {
    return new VExistsValid(this.qm, k, true, false);
  }

  public VValueValid value(String v) {
    return new VValueValid(this.qm, v, false, true);
  }

  public void yes(boolean yes, String message) throws ValidException {
    this.yes(yes, R.err(message));
  }

  public void yes(boolean yes, R r) throws ValidException {
    if (yes)
      throw new ValidException(r);
  }

  public void no(boolean no, String message) throws ValidException {
    this.yes(!no, message);
  }

  public void no(boolean no, R r) throws ValidException {
    this.yes(!no, r);
  }

}

package spark.patch.valid;


import spark.patch.qm.Qm;

public class VValueValid extends VObjectValid {


  protected VValueValid(Qm qm, String k, boolean existsMode, boolean valueMode) {
    super(qm, k, existsMode, valueMode);
  }


  public VStringValid string() {
    return new VStringValid(super.qm(), super.k(), false, true);
  }

  public VNumberValid number() {
    return new VNumberValid(this.qm(), super.k(), false, true);
  }

  public VBoolValid bool() {
    return new VBoolValid(super.qm(), super.k(), false, true);
  }

  public VExistsValid exists() {
    return new VExistsValid(super.qm(), super.k(), true, true);
  }
}

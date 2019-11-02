package spark.patch.thr;

import io.enoa.toolkit.prop.PropKit;
import io.enoa.toolkit.sys.ThrowableKit;
import spark.patch.ret.R;

public class ValidException extends Exception {

  private R r;

  private ValidException() {
    super();
  }

  private ValidException(Throwable cause) {
    super(cause);
  }

  private ValidException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }


  public ValidException(String message) {
    this(R.err(message));
  }

  public ValidException(String message, Throwable thr) {
    this(R.err(message), thr);
  }

  public ValidException(R r) {
    super(r.message());
    this.r = r;
  }

  public ValidException(R r, Throwable thr) {
    super(r.message(), thr);
    this.r = r;
    if (PropKit.bool("hn.debug", false))
      this.r.addition("_trace", ThrowableKit.string(thr));
  }

  public static ValidException with(String message) {
    return with(R.err(message));
  }

  public static ValidException with(R r) {
    return new ValidException(r);
  }

  public static void throwWith(String message) throws ValidException {
    throwWith(R.err(message));
  }

  public static void throwWith(R r) throws ValidException {
    throw new ValidException(r);
  }

  public R r() {
    return this.r;
  }
}

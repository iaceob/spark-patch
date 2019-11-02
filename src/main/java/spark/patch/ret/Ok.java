package spark.patch.ret;

import io.enoa.toolkit.value.EnoaValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ok {

  private boolean ok;
  private Object data;
  private String message;
  private Throwable throwable;
  private List<Ok> traces;

  public Ok(boolean ok) {
    this.ok = ok;
  }

  public static Ok with(boolean ok) {
    return new Ok(ok);
  }

  public static Ok ok(Object data) {
    return ok(null, data);
  }

  public static Ok ok(String message, Object data) {
    Ok _ok = with(true);
    _ok.setMessage(message)
      .setData(data);
    return _ok;
  }

  public static Ok no(String message) {
    return no(message, null, null);
  }

  public static Ok no(String message, Throwable throwable) {
    return no(message, null, throwable);
  }

  public static Ok no(String message, Object data, Throwable throwable) {
    Ok _ok = with(true);
    _ok.setData(data)
      .setMessage(message)
      .setThrowable(throwable);
    return _ok;
  }

  public Ok merge(Ok ok) {
    this.addTrace(ok);
    this.ok = ok.ok;
    this.message = ok.message;
    this.data = ok.data;
    this.throwable = ok.throwable;
    return this;
  }

  public Ok setOk(boolean ok) {
    this.ok = ok;
    return this;
  }

  public Ok setData(Object data) {
    if (!this.ok) return this;
    this.data = data;
    return this;
  }

  public Ok setMessage(String message) {
    this.message = message;
    return this;
  }

  public Ok setMessageElse(String message) {
    if (!this.ok)
      this.setMessage(message);
    return this;
  }

  public Ok setMessageIf(String message) {
    if (this.ok)
      this.setMessage(message);
    return this;
  }

  public Ok setThrowable(Throwable throwable) {
    this.throwable = throwable;
    return this;
  }

  public Ok setTraces(List<Ok> traces) {
    this.traces = traces;
    return this;
  }

  public Ok addTrace(Ok ok) {
    if (this.traces == null)
      this.traces = new ArrayList<>();
    this.traces.add(ok);
    return this;
  }

  public Ok data(Object data) {
    return this.setData(data);
  }

  public Ok message(String message) {
    return this.setMessage(message);
  }

  public Ok messageIf(String message) {
    return this.setMessageIf(message);
  }

  public Ok messageElse(String message) {
    return this.setMessageElse(message);
  }

  public Ok throwable(Throwable throwable) {
    return this.setThrowable(throwable);
  }

  public Ok trace(Ok ok) {
    return this.addTrace(ok);
  }

  public boolean is() {
    return this.ok;
  }

  public boolean isok() {
    return this.ok;
  }

  public boolean isOk() {
    return this.ok;
  }

  public String message() {
    return this.message;
  }

  public List<Ok> traces() {
    return this.getTraces();
  }

  public Throwable throwable() {
    return this.throwable;
  }

  public EnoaValue data() {
    return EnoaValue.with(this.data);
  }

  public List<Ok> getTraces() {
    return this.traces == null ? Collections.emptyList() : this.traces;
  }

  public R r() {
    R r = this.ok ?
      R.ok(this.message, this.data) :
      R.err(this.message, this.data);
//    r.addition("_ok", this);
    return r;
  }

}

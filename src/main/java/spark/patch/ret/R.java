package spark.patch.ret;

import io.enoa.toolkit.is.Is;
import io.enoa.toolkit.map.EoMap;
import io.enoa.toolkit.map.Kv;

import java.util.Map;

public class R implements EoMap<R> {

  private Kv kv;

  public R() {
    this.kv = Kv.create();
  }

  public static R ok() {
    return R.ok(null, null);
  }

  public static R ok(String message) {
    return R.ok(message, null);
  }

  public static R ok(Object data) {
    return R.ok(null, data);
  }

  public static R ok(String message, Object data) {
    R r = new R();
    r.set("err", 0);
    if (Is.truthy(message))
      r.set("message", message);
    if (data != null)
      r.set("data", data);
    return r;
  }

  public static R err(Object data) {
    return err(-1, null, data);
  }

  public static R err(String message) {
    return err(null, message, null);
  }

  public static R err(Integer code, Object data) {
    return err(code, null, data);
  }

  public static R err(String message, Object data) {
    return err(null, message, data);
  }

  public static R err(Integer code, String message, Object data) {
    R r = new R();
    r.set("err", 1);
    if (code != null)
      r.set("code", code);
    if (Is.truthy(message))
      r.set("message", message);
    if (data != null)
      r.set("data", data);
    return r;
  }

  public boolean okx() {
    return this.isok();
  }

  public boolean isok() {
    return this.kv.integer("err", 1) == 0;
  }

  public Integer code() {
    return this.kv.integer("code");
  }

  public String message() {
    return this.kv.string("message");
  }

  public <T> T data() {
    return this.kv.as("data");
  }

  public <T> R addition(String key, T value) {
    Kv addition = (Kv) this.kv.computeIfAbsent("addition", _k -> new Kv());
    addition.set(key, value);
    return this;
  }

  public <T> T addition(String key) {
    Kv addition = this.kv.as("addition");
    return addition == null ? null : addition.as(key);
  }

  @Override
  public Map<String, Object> map() {
    return this.kv.map();
  }

  @Override
  public Object get(Object key) {
    return this.kv.get(key);
  }

}

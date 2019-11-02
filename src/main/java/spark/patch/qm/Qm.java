package spark.patch.qm;

import io.enoa.toolkit.convert.ConvertKit;
import io.enoa.toolkit.is.Is;
import io.enoa.toolkit.map.FastKv;
import spark.Request;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Qm implements FastKv {

  private Map<String, String[]> qm;
  private Request request;

  public Qm(Request request) {
    this.request = request;
//    this.qm = request.queryMap();
    Map<String, String[]> map = new HashMap<>();
    try {
      Map<String, String[]> qmap = request.queryMap().toMap();
      qmap.forEach(map::put);
    } catch (Exception ignored) {
    }
    try {
      request.queryParams().forEach(key -> map.put(key, request.queryParamsValues(key)));
    } catch (Exception ignored) {
    }
    this.qm = map;
  }

  public static Qm with(Request request) {
    return new Qm(request);
  }

  public Request request() {
    return this.request;
  }

  public Map<String, String[]> qm() {
    return this.qm;
  }

  @Override
  public Object origin(String key) {
    String[] vals = this.qm.get(key);
    return Is.empty(vals) ? null : vals[0];
  }

  @Override
  public boolean exists(String key) {
    return this.qm.containsKey(key);
  }

  public Set<String> safeSetString(String key) {
    String[] values = this.qm.get(key);
    if (Is.empty(values))
      return Collections.emptySet();
    return this.safeSet(values);
  }

  public Set<Integer> safeSetInteger(String key) {
    return this.safeSetString(key)
      .stream()
      .filter(Is::number)
      .map(ConvertKit::integer)
      .collect(Collectors.toSet());
  }


  private Set<String> safeSet(String[] arrs) {
    if (Is.empty(arrs))
      return Collections.emptySet();
    return Stream.of(arrs)
      .filter(Objects::nonNull)
      .filter(Is::truthy)
      .collect(Collectors.toSet());
  }
}

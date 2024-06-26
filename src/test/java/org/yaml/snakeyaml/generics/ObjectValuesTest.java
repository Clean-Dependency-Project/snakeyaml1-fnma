/**
 * Copyright (c) 2008, SnakeYAML
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package org.yaml.snakeyaml.generics;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;
import java.util.Map;
import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class ObjectValuesTest extends TestCase {

  public void testObjectValues() {
    ObjectValues ov = new ObjectValues();
    Integer obj = Integer.valueOf(131313);
    ov.setObject(obj);
    final Map<String, Map<Integer, Object>> prop2values =
        new HashMap<String, Map<Integer, Object>>();

    final String[] props = {"prop1", "prop2", "prop3"};
    for (String name : props) {
      Map<Integer, Object> values = new HashMap<Integer, Object>();
      prop2values.put(name, values);
      for (int i = 0; i < 3; i++) {
        values.put(i, name + i);
      }
    }

    ov.setValues(prop2values);
    ov.setPossible(props);

    Yaml dumper = new Yaml(new DumperOptions());
    String dumpedStr = dumper.dumpAsMap(ov);
    Yaml loader = new Yaml(new DumperOptions());
    ObjectValues ov2 = loader.loadAs(dumpedStr, ObjectValues.class);

    assertEquals(ov.getObject(), ov2.getObject());
    assertEquals(ov.getValues(), ov2.getValues());
    assertArrayEquals(ov.getPossible(), ov2.getPossible());
    ov.getPossible()[0] = ov2.getPossible()[0];
  }

  @SuppressWarnings("unchecked")
  public void testObjectValuesWithParam() {
    ObjectValuesWithParam<String, Integer> ov = new ObjectValuesWithParam<String, Integer>();
    Integer obj = Integer.valueOf(131313);
    ov.setObject(obj);
    final Map<String, Map<Integer, Object>> prop2values =
        new HashMap<String, Map<Integer, Object>>();

    final String[] props = {"prop1", "prop2", "prop3"};
    for (String name : props) {
      Map<Integer, Object> values = new HashMap<Integer, Object>();
      prop2values.put(name, values);
      for (int i = 0; i < 3; i++) {
        values.put(i, name + i);
      }
    }

    ov.setValues(prop2values);
    ov.setPossible(props);

    Yaml dumper = new Yaml(new DumperOptions());
    String dumpedStr = dumper.dumpAsMap(ov);
    Yaml loader = new Yaml(new DumperOptions());
    ObjectValuesWithParam<String, Integer> ov2 =
        loader.loadAs(dumpedStr, new ObjectValuesWithParam<String, Integer>().getClass());

    assertEquals(ov.getObject(), ov2.getObject());
    assertEquals(ov.getValues(), ov2.getValues());
    assertArrayEquals(ov.getPossible(), ov2.getPossible());
    // This actually FAILS. Use of GenericArrays is ..... no words.
    // assertEquals(ov.getPossible()[0], ov2.getPossible()[0]);
    try {
      ov2.getPossible();
    } catch (Exception e) {
      boolean java8 = e.getMessage().startsWith("[Ljava.lang.Object");
      boolean java9 = e.getMessage().startsWith("java.base/[Ljava.lang.Object");
      boolean java11 = e.getMessage()
          .startsWith("class [Ljava.lang.Object; cannot be cast to class [Ljava.lang.String;");
      assertTrue(e.getMessage(), java8 || java9 || java11);
    }
  }
}

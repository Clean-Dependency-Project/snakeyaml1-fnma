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
package org.yaml.snakeyaml.issues.issue58;

import java.util.ArrayList;
import junit.framework.TestCase;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.ArrayList;


public class NullValueDumperTest extends TestCase {

  public static class Foo {

    private ArrayList<Object> bar = new ArrayList<Object>();

    public ArrayList<Object> getBar() {
      return bar;
    }

    public void setBar(ArrayList<Object> bar) {
      this.bar = bar;
    }
  }

  public void testListElement() {
    final Foo foo = new Foo();
    foo.bar.add(1);
    foo.bar.add("A");
    foo.bar.add(3.14);
    Yaml yaml = new Yaml(new SafeConstructor());
    assertEquals("bar:\n- 1\n- A\n- 3.14\n", yaml.dumpAsMap(foo));
  }

  public void testNullListElement() {
    final Foo foo = new Foo();

    foo.bar.add(1);
    foo.bar.add("A");
    foo.bar.add(null);
    foo.bar.add(3.14);
    Yaml yaml = new Yaml(new SafeConstructor());
    assertEquals("bar:\n- 1\n- A\n- null\n- 3.14\n", yaml.dumpAsMap(foo));
    assertEquals(
        "!!org.yaml.snakeyaml.issues.issue58.NullValueDumperTest$Foo\nbar: [1, A, null, 3.14]\n",
        new Yaml(new SafeConstructor()).dump(foo));
  }
}

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
package examples;

import java.util.Map;
import java.util.TreeMap;
import junit.framework.TestCase;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import org.yaml.snakeyaml.constructor.Constructor;

public class CustomMapExampleTest extends TestCase {

  public void testMap() {
    Yaml yaml = new Yaml(new CustomConstructor());
    @SuppressWarnings("unchecked")
    Map<Integer, String> data = yaml.load("{2: '222', 1: '111', 3: '333'}");
    assertTrue(data instanceof TreeMap);
    Object[] keys = data.keySet().toArray();
    // must be sorted
    assertEquals(Integer.valueOf(1), keys[0]);
    assertEquals(Integer.valueOf(2), keys[1]);
    assertEquals(Integer.valueOf(3), keys[2]);
  }

  class CustomConstructor extends Constructor {

    public CustomConstructor() {
      super(new LoaderOptions());
    }

    @Override
    protected Map<Object, Object> createDefaultMap(int initSize) {
      return new TreeMap<Object, Object>();
    }
  }
}

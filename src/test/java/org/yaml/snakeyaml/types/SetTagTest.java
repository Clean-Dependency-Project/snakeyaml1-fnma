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
package org.yaml.snakeyaml.types;

import java.util.Map;
import java.util.Set;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.YamlDocument;

/**
 * @see <a href="http://yaml.org/type/set.html">set</a>
 */
public class SetTagTest extends AbstractTest {

  @SuppressWarnings("unchecked")
  public void testSet() {
    YamlDocument document = new YamlDocument("types/set.yaml");
    Map<String, Set<String>> map = (Map<String, Set<String>>) document.getNativeData();
    assertEquals(2, map.size());
    Set<String> set1 = map.get("baseball players");
    assertEquals(3, set1.size());
    assertTrue(set1.contains("Mark McGwire"));
    assertTrue(set1.contains("Sammy Sosa"));
    assertTrue(set1.contains("Ken Griffey"));
    //
    Set<String> set2 = map.get("baseball teams");
    assertEquals(3, set2.size());
    assertTrue(set2.contains("Boston Red Sox"));
    assertTrue(set2.contains("Detroit Tigers"));
    assertTrue(set2.contains("New York Yankees"));
  }

  public void testSetNull() {
    Yaml yaml = new Yaml(new LoaderOptions());
    Set<Object> set = yaml.load("!!set {1, 2, null}");
    assertTrue(set.contains(1));
    assertTrue(set.contains(2));
    assertTrue(set.contains(null));
    assertEquals(set.size(), 3);
  }
}

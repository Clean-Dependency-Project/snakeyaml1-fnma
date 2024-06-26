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
package org.yaml.snakeyaml.issues.issue100;

import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Util;
import org.yaml.snakeyaml.Yaml;

public class MergeMapsTest extends TestCase {

  @SuppressWarnings("unchecked")
  public void testExplicitMergeTag() {
    String input = Util.getLocalResource("issues/issue100-2.yaml");
    // System.out.println(input);
    Yaml yaml = new Yaml(new LoaderOptions());
    Map<String, ?> list = yaml.load(input);
    List<Map<?, ?>> result = (List<Map<?, ?>>) list.get("result");
    Map<?, ?> first = result.iterator().next();
    for (Map<?, ?> data : result) {
      // System.out.println(data);
      assertEquals("Size must coinside.", first.size(), data.size());
      assertEquals(first, data);
    }
  }
}

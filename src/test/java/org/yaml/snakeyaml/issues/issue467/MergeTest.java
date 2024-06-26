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
package org.yaml.snakeyaml.issues.issue467;

import junit.framework.TestCase;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class MergeTest extends TestCase {

  public void testMergeAsScalar() {
    Yaml loader = new Yaml(new SafeConstructor());
    String data =
        "test-list:\n" + " - &1\n" + "   a: 1\n" + "   b: 2\n" + " - &2 <<: *1\n" + " - <<: *2";
    try {
      loader.load(data);
      fail();
    } catch (Exception e) {
      assertTrue(e.getMessage()
          .contains("expected a mapping or list of mappings for merging, but found scalar"));
    }
  }
}

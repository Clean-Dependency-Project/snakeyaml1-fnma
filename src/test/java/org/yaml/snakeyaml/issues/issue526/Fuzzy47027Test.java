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
package org.yaml.snakeyaml.issues.issue526;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Util;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.error.YAMLException;

// OSS-Fuzz - 47027
public class Fuzzy47027Test {

  @Test
  public void parseOpenUnmatchedSequences_47027() {
    try {
      Yaml yaml = new Yaml(new SafeConstructor());
      String strYaml = Util.getLocalResource("fuzzer/YamlFuzzer-5427149240139776");
      yaml.load(strYaml);
      fail("Should report invalid YAML");
    } catch (YAMLException e) {
      assertEquals("Nesting Depth exceeded max 50", e.getMessage());
    }
  }

  @Test
  public void setCustomLimit100() {
    try {
      LoaderOptions options = new LoaderOptions();
      options.setNestingDepthLimit(100);
      Yaml yaml = new Yaml(options);
      String strYaml = Util.getLocalResource("fuzzer/YamlFuzzer-5427149240139776");
      yaml.load(strYaml);
      fail("Should report invalid YAML");
    } catch (YAMLException e) {
      assertEquals("Nesting Depth exceeded max 100", e.getMessage());
    }
  }
}

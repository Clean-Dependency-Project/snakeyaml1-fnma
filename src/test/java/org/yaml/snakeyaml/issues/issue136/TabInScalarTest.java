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
package org.yaml.snakeyaml.issues.issue136;

import junit.framework.TestCase;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class TabInScalarTest extends TestCase {

  public void testTab() {
    String data = new Yaml(new SafeConstructor()).load("L\tD");
    assertEquals("L\tD", data);
  }

  public void testNoTab() {
    String data = new Yaml(new SafeConstructor()).load("L D");
    assertEquals("L D", data);
  }

  public void testTabDoubleQuotes() {
    String data = new Yaml(new SafeConstructor()).load("\"L\tD\"");
    // System.out.println(data);
    assertEquals("L\tD", data);
  }

  public void testTabSingleQuotes() {
    String data = new Yaml(new SafeConstructor()).load("'L\tD'");
    // System.out.println(data);
    assertEquals("L\tD", data);
  }

  public void testDumpTab() {
    String data = new Yaml(new SafeConstructor()).dump("L\tD");
    // System.out.println(data);
    assertEquals("\"L\\tD\"\n", data);
  }
}

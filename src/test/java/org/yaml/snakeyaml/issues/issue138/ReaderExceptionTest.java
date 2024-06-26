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
package org.yaml.snakeyaml.issues.issue138;

import junit.framework.TestCase;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.reader.ReaderException;

public class ReaderExceptionTest extends TestCase {

  public void testGetters() {
    try {
      new Yaml(new SafeConstructor()).load("012\u0019");
      fail();
    } catch (ReaderException e) {
      assertEquals(3, e.getPosition());
      assertEquals("'string'", e.getName());
      assertEquals(0x19, e.getCodePoint());
    }
  }
}

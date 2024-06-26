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

import java.beans.IntrospectionException;
import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class BirdTest extends TestCase {

  public void testHome() throws IntrospectionException {
    Bird bird = new Bird();
    bird.setName("Eagle");
    Nest home = new Nest();
    home = new Nest();
    home.setHeight(3);
    bird.setHome(home);
    Yaml yaml = new Yaml(new DumperOptions());
    String output = yaml.dumpAsMap(bird);
    Bird parsed;
    String javaVendor = System.getProperty("java.vm.name");
    Yaml loader = new Yaml(new DumperOptions());
    // no global tags
    System.out.println("java.vm.name: " + javaVendor);
    assertEquals("no global tags must be emitted.", "home:\n  height: 3\nname: Eagle\n", output);
    parsed = loader.loadAs(output, Bird.class);
    assertEquals(bird.getName(), parsed.getName());
    assertEquals(bird.getHome().getHeight(), parsed.getHome().getHeight());
  }
}

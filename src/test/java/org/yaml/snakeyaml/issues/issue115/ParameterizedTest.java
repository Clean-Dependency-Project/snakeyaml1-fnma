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
package org.yaml.snakeyaml.issues.issue115;

import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class ParameterizedTest extends TestCase {

  public void testAsStandalone() {
    Parameterized<Integer> parm = new Parameterized<Integer>();
    parm.t = 3;
    Yaml yaml = new Yaml(new DumperOptions());
    String result = yaml.dump(parm);
    assertEquals("!!org.yaml.snakeyaml.issues.issue115.Parameterized {t: 3}\n", result);
    @SuppressWarnings("unchecked")
    // load
    Parameterized<Integer> parmParsed = yaml.load(result);
    assertEquals(Integer.valueOf(3), parmParsed.t);
  }

  public void testAsJavaBeanProperty() {
    Yaml yaml = new Yaml(new DumperOptions());
    Issue issue = new Issue();
    Parameterized<Integer> parm = new Parameterized<Integer>();
    parm.t = 555;
    issue.parm = parm;
    String result = yaml.dump(issue);
    assertEquals("!!org.yaml.snakeyaml.issues.issue115.Issue\nparm: {t: 555}\n", result);
    // load
    Issue issueParsed = yaml.load(result);
    assertEquals(Integer.valueOf(555), issueParsed.parm.t);
  }
}


class Issue {

  public Parameterized<Integer> parm = new Parameterized<Integer>();
}


class Parameterized<T> {

  public T t;
}

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
package org.yaml.snakeyaml.issues.issue193;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import org.yaml.snakeyaml.extensions.compactnotation.CompactConstructor;
import org.yaml.snakeyaml.constructor.SafeConstructor;


public class TestYaml {

  public static abstract class BeanA {

    public abstract Object getId();

  }

  public static class BeanA1 extends BeanA {

    private Long id;

    // @Override
    public Long getId() {
      return id;
    }

    public void setId(Long id) {
      this.id = id;
    }

    private String name;

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

  }

  public static void main(String[] args) {

    BeanA1 b = new BeanA1();
    b.setId(2L);
    b.setName("name1");
    Yaml yaml = new Yaml(new SafeConstructor());
    String dump = yaml.dump(b);

    dump = "!!org.yaml.snakeyaml.issues.issue193.TestYaml$BeanA1 {id: 2, name: name1}";

    yaml.load(dump);
  }
}

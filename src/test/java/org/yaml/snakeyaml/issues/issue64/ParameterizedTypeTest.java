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
package org.yaml.snakeyaml.issues.issue64;

import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.ScalarNode;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Represent;
import org.yaml.snakeyaml.representer.Representer;

import java.util.LinkedList;
import java.util.List;

public class ParameterizedTypeTest extends TestCase {

  public void testRepresenter() {
    Yaml yaml = new Yaml(new ClassConstructor(), new ClassRepresenter());

    String methodName = "testMethod";
    List<Class<?>> argTypes = new LinkedList<Class<?>>();
    argTypes.add(String.class);
    argTypes.add(Integer.class);
    argTypes.add(Boolean.class);
    MethodDesc methodDesc = new MethodDesc(methodName, argTypes);

    String out = yaml.dump(methodDesc);
    // System.out.println(out);
    assertEquals(
        "!!org.yaml.snakeyaml.issues.issue64.MethodDesc\nargTypes: [!clazz 'String', !clazz 'Integer', !clazz 'Boolean']\nname: testMethod\n",
        out);
    MethodDesc parsed = yaml.load(out);
    assertEquals(methodName, parsed.getName());
    List<Class<?>> argTypes2 = parsed.getArgTypes();
    assertEquals(3, argTypes2.size());
    assertEquals(argTypes, argTypes2);
  }

  static class ClassRepresenter extends Representer {

    public ClassRepresenter() {
      super(new DumperOptions());
      this.representers.put(Class.class, new RepresentClass());
    }

    private class RepresentClass implements Represent {

      public Node representData(Object data) {
        Class<?> clazz = (Class<?>) data;
        return representScalar(new Tag("!clazz"), clazz.getSimpleName());
      }
    }
  }

  static class ClassConstructor extends Constructor {

    public ClassConstructor() {
      super(new LoaderOptions());
      this.yamlConstructors.put(new Tag("!clazz"), new ConstructClass());
    }

    private class ConstructClass extends AbstractConstruct {

      public Object construct(Node node) {
        String clazz = constructScalar((ScalarNode) node);
        try {
          return Class.forName("java.lang." + clazz);
        } catch (ClassNotFoundException e) {
          throw new RuntimeException(e);
        }
      }
    }
  }
}

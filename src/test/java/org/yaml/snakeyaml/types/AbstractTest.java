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
import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;

public abstract class AbstractTest extends TestCase {

  @SuppressWarnings("unchecked")
  protected Map<String, Object> getMap(String data) {
    Yaml yaml = new Yaml(new LoaderOptions());
    Map<String, Object> nativeData = yaml.load(data);
    return nativeData;
  }

  protected Object load(String data) {
    Yaml yaml = new Yaml(new LoaderOptions());
    Object obj = yaml.load(data);
    return obj;
  }

  protected String dump(Object data) {
    Yaml yaml = new Yaml(new DumperOptions());
    return yaml.dump(data);
  }

  protected Object getMapValue(String data, String key) {
    Map<String, Object> nativeData = getMap(data);
    return nativeData.get(key);
  }
}

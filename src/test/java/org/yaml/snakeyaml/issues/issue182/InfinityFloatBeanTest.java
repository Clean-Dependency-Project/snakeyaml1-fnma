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
package org.yaml.snakeyaml.issues.issue182;

import junit.framework.TestCase;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

public class InfinityFloatBeanTest extends TestCase {

  public void testInfinityFloatBean() throws Exception {

    InfinityFloatBean bean = new InfinityFloatBean();
    bean.infinityFloat = Float.POSITIVE_INFINITY;
    bean.infinityFloatObject = Float.POSITIVE_INFINITY;

    Yaml yaml = new Yaml(new DumperOptions());
    String yamled = yaml.dump(bean);
    InfinityFloatBean loadedBean = yaml.loadAs(yamled, InfinityFloatBean.class);
    assertEquals(Float.POSITIVE_INFINITY, loadedBean.infinityFloat);
    assertEquals(Float.POSITIVE_INFINITY, loadedBean.infinityFloatObject);
  }
}

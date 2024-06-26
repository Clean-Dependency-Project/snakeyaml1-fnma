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

public class ParameterizedJavaBeanTest extends TestCase {

  public void testAsStandalone() {
    ParameterizedBean<Integer, String> bean = new ParameterizedBean<Integer, String>();
    bean.setK(13);
    bean.setV("ID47");
    Yaml yaml = new Yaml(new DumperOptions());
    String result = yaml.dump(bean);
    assertEquals("!!org.yaml.snakeyaml.issues.issue115.ParameterizedBean {k: 13, v: ID47}\n",
        result);
    // load
    @SuppressWarnings("unchecked")
    ParameterizedBean<Integer, String> beanParsed = yaml.load(result);
    assertEquals(Integer.valueOf(13), beanParsed.getK());
    assertEquals("ID47", beanParsed.getV());
  }

  public void testAsJavaBeanProperty() {
    Yaml yaml = new Yaml(new DumperOptions());
    IssueBean issue = new IssueBean();
    ParameterizedBean<Integer, String> bean = new ParameterizedBean<Integer, String>();
    bean.setK(432);
    bean.setV("Val432");
    issue.setBean(bean);
    String result = yaml.dump(issue);
    assertEquals("!!org.yaml.snakeyaml.issues.issue115.IssueBean\nbean: {k: 432, v: Val432}\n",
        result);
    // load
    IssueBean issueParsed = yaml.load(result);
    assertEquals(Integer.valueOf(432), issueParsed.getBean().getK());
    assertEquals("Val432", issueParsed.getBean().getV());
  }
}

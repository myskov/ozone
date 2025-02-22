/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.hdds.conf;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.StringWriter;
import org.junit.jupiter.api.Test;

/**
 * Test the utility which loads/writes the config file fragments.
 */
public class TestConfigFileAppender {

  @Test
  public void testInit() {
    ConfigFileAppender appender = new ConfigFileAppender();

    appender.init();

    appender.addConfig("hadoop.scm.enabled", "true", "desc",
        new ConfigTag[] {ConfigTag.OZONE, ConfigTag.SECURITY});

    StringWriter builder = new StringWriter();
    appender.write(builder);

    assertThat(builder.toString())
        .contains("<name>hadoop.scm.enabled</name>")
        .contains("<tag>OZONE, SECURITY</tag>");
  }
}

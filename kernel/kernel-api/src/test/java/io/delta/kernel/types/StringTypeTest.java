/*
 * Copyright (2024) The Delta Lake Project Authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.delta.kernel.types;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class StringTypeTest {

  private static Stream<Arguments> equalsCases() {
    return Stream.of(
        Arguments.of(StringType.STRING, StringType.STRING, true),
        Arguments.of(StringType.STRING, new StringType("sPark.UTF8_bINary"), true),
        Arguments.of(StringType.STRING, new StringType("SPARK.UTF8_LCASE"), false),
        Arguments.of(new StringType("ICU.UNICODE"), new StringType("SPARK.UTF8_LCASE"), false),
        Arguments.of(new StringType("ICU.UNICODE"), new StringType("ICU.UNICODE_CI"), false),
        Arguments.of(new StringType("ICU.UNICODE_CI"), new StringType("icU.uniCODe_Ci"), true));
  }

  @ParameterizedTest
  @MethodSource("equalsCases")
  public void checkEquals(StringType left, StringType right, boolean expected) {
    assertThat(left.equals(right)).isEqualTo(expected);
  }

  @Test
  public void isUTF8BinaryCollated() {
    assertThat(StringType.STRING.isUTF8BinaryCollated()).isTrue();
    assertThat(new StringType("sPark.UTF8_bINary").isUTF8BinaryCollated()).isTrue();
    assertThat(new StringType("SPARK.UTF8_LCASE").isUTF8BinaryCollated()).isFalse();
    assertThat(new StringType("ICU.UNICODE.72.2").isUTF8BinaryCollated()).isFalse();
    assertThat(new StringType("ICU.UNICODE_CI").isUTF8BinaryCollated()).isFalse();
  }

  @Test
  public void stringRepresentation() {
    assertThat(StringType.STRING.toString()).isEqualTo("string");
    assertThat(new StringType("sPark.UTF8_bINary").toString()).isEqualTo("string");
    assertThat(new StringType("SPARK.UTF8_LCASE").toString())
        .isEqualTo("string collate UTF8_LCASE");
    assertThat(new StringType("ICU.uNICoDE.72.2").toString()).isEqualTo("string collate UNICODE");
    assertThat(new StringType("ICU.UNICODE_CI").toString()).isEqualTo("string collate UNICODE_CI");
  }
}

/*
 * Copyright (2025) The Delta Lake Project Authors.
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
package io.delta.kernel.internal.metrics;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class CounterTest {

  @Test
  public void counterClass() {
    Counter counter = new Counter();
    assertThat(counter.value()).isEqualTo(0);
    counter.increment(0);
    assertThat(counter.value()).isEqualTo(0);
    counter.increment();
    assertThat(counter.value()).isEqualTo(1);
    counter.increment();
    assertThat(counter.value()).isEqualTo(2);
    counter.increment(10);
    assertThat(counter.value()).isEqualTo(12);
    counter.reset();
    assertThat(counter.value()).isEqualTo(0);
    counter.increment();
    assertThat(counter.value()).isEqualTo(1);
  }

  @Test
  public void counterToStringRepresentation() {
    Counter counter = new Counter();
    counter.increment(42);

    assertThat(counter.toString()).isEqualTo("Counter(42)");
  }
}

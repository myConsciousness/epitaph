/*
 * Copyright 2020 Kato Shinya.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package org.thinkit.common.regex.catalog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.jupiter.api.Test;

/**
 * The test class for the {@link RegexOption} .
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
final class RegexOptionTest {

    /**
     * The expected tags
     */
    private static final Map<Integer, Integer> EXPECTED_TAGS = new HashMap<>() {

        /**
         * Serial Verison UID
         */
        private static final long serialVersionUID = -6830944756614534215L;

        {
            put(0, Pattern.UNIX_LINES);
            put(1, Pattern.CASE_INSENSITIVE);
            put(2, Pattern.COMMENTS);
            put(3, Pattern.MULTILINE);
            put(4, Pattern.LITERAL);
            put(5, Pattern.DOTALL);
            put(6, Pattern.UNICODE_CASE);
            put(7, Pattern.CANON_EQ);
            put(8, Pattern.UNICODE_CHARACTER_CLASS);
        }
    };

    @Test
    void testCodeValues() {

        int expected = 0;

        for (RegexOption regexOption : RegexOption.values()) {
            assertEquals(expected++, regexOption.getCode());
        }
    }

    @Test
    void testTagValues() {

        int code = 0;

        for (RegexOption regexOption : RegexOption.values()) {
            assertEquals(EXPECTED_TAGS.get(code++), regexOption.getTag());
        }
    }
}

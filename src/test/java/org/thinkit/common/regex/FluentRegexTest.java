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

package org.thinkit.common.regex;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.thinkit.common.regex.catalog.RegexPattern;

/**
 * The test class for the {@link FluentRegex} .
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
final class FluentRegexTest {

    @Nested
    class TestBuilder {

        @Test
        void testWhenPatternMethodDoesNotCalled() {
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> FluentRegex.builder().input("").build());
            assertEquals("The regex pattern is required", exception.getMessage());
        }

        @Test
        void testWhenInputMethodDoesNotCalled() {
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> FluentRegex.builder().pattern(RegexPattern.ALPHABET).build());
            assertEquals("The input is required", exception.getMessage());
        }
    }

    @Nested
    class TestEmailAddressPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp", "test@my.email.jp" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { " test@gmail.com", "test@something.co.jp ", "testmy.email.jp", "tes t@my.email.jp",
                "test @my.email.jp", "test@my.ema il.jp", "にほんご@メールアドレス.日本" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { " test@gmail.com", "test@something.co.jp ", "testmy.email.jp", "tes t@my.email.jp",
                "test @my.email.jp", "test@my.ema il.jp", "にほんご@メールアドレス.日本" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { " test@gmail.com", "test@something.co.jp ", "testmy.email.jp", "tes t@my.email.jp",
                "test @my.email.jp", "test@my.ema il.jp", "にほんご@メールアドレス.日本" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDomainNamePattern {

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123",
                "mkyong.com/users", "-mkyong.com", "mkyong-.com", "sub.-mkyong.com", "sub.mkyong-.com" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123",
                "mkyong.com/users", "-mkyong.com", "mkyong-.com", "sub.-mkyong.com", "sub.mkyong-.com" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123",
                "mkyong.com/users", "-mkyong.com", "mkyong-.com", "sub.-mkyong.com", "sub.mkyong-.com" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().matches());
        }
    }

    @Nested
    class TestWebUrlPattern {

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })

        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().matches());
        }
    }

    @Nested
    class TestUserIdPattern {

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "shinya-kato", "shinya/kato" })

        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "shinya-kato", "shinya/kato" })

        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "shinya-kato", "shinya/kato" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.USER_ID).input(parameter).build().matches());
        }
    }
}

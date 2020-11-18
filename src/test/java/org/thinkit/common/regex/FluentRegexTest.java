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

    @Nested
    class TestFixedPhoneJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testMatches(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })

        void testNotFind(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })
        void testNotMatch(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestFixedPhoneWithHypenJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })

        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter)
                    .build().matches());
        }
    }

    @Nested
    class TestCellPhoneJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000000001", "080000000001", "090000000001" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000000001", "080000000001", "090000000001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000000001", "080000000001", "090000000001" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestCellPhoneWithHypenJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00001", "080-0000-00001", "090-0000-00001" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00001", "080-0000-00001", "090-0000-00001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00001", "080-0000-00001", "090-0000-00001" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }
    }

    @Nested
    class TestPasswordPattern {

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@",
                "a1234567891234567891234567891234A" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@",
                "a1234567891234567891234567891234A" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@",
                "a1234567891234567891234567891234A" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDatePattern {

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDateWithHyphenPattern {

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotMatch(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDateWithSlashPattern {

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().matches());
        }
    }

    @Nested
    class TestPostCodeJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestXmlPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestIpAddressPattern {

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551",
                "255.255.255.2551:0000" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551",
                "255.255.255.2551:0000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551",
                "255.255.255.2551:0000" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().matches());
        }
    }

    @Nested
    class TestIpAddressWithPortPattern {

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testFind(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build()
                    .matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "256.255.255.255:00",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "2551.255.255.255:00",
                "255.2551.255.255:00", "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotFind(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "256.255.255.255:00",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "2551.255.255.255:00",
                "255.2551.255.255:00", "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "256.255.255.255:00",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "2551.255.255.255:00",
                "255.2551.255.255:00", "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build()
                    .matches());
        }
    }

    @Nested
    class TestNumericPattern {

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0test", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0test", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0test", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphanumericCharacterPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000-", "Test001?" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000-", "Test001?" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000-", "Test001?" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testMatches(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?" })
        void testNotFind(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?" })
        void testNotMatch(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetUpperCasePattern {

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testMatches(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?", "TESt" })
        void testNotFind(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?", "TESt" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?", "TESt" })
        void testNotMatch(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetLowerCasePattern {

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testFind(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testLookingAt(final String parameter) {
            assertTrue(FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testMatches(final String parameter) {
            assertTrue(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "test?", "tesT" })
        void testNotFind(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "test?", "tesT" })
        void testNotLookingAt(final String parameter) {
            assertFalse(FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "test?", "tesT" })
        void testNotMatch(final String parameter) {
            assertFalse(
                    FluentRegex.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().matches());
        }
    }
}

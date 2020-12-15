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
 * The test class for the {@link Epitaph} .
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
final class EpitaphTest {

    @Nested
    class TestBuilder {

        @Test
        void testWhenPatternMethodDoesNotCalled() {
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> Epitaph.builder().input("").build());
            assertEquals("The regex pattern is required", exception.getMessage());
        }

        @Test
        void testWhenInputMethodDoesNotCalled() {
            final IllegalStateException exception = assertThrows(IllegalStateException.class,
                    () -> Epitaph.builder().pattern(RegexPattern.ALPHABET).build());
            assertEquals("The input is required", exception.getMessage());
        }
    }

    @Nested
    class TestEmailAddressPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp", "test@my.email.jp" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@gmail.com", "test@something.co.jp", "test@my.email.jp" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "testmy.email.jp", "test @my.email.jp", "にほんご@メールアドレス.日本" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "testmy.email.jp", "test @my.email.jp", "にほんご@メールアドレス.日本" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { " test@gmail.com", "test@something.co.jp ", "testmy.email.jp", "tes t@my.email.jp",
                "test @my.email.jp", "test@my.ema il.jp", "にほんご@メールアドレス.日本" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.EMAIL_ADDRESS).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDomainNamePattern {

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "gmail.com", "www.google.com", "Google.COM", "mkyong123.com", "mkyong-info.com",
                "sub.mkyong.com", "mkyong.t.t.co" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123", "mkyong-.com",
                "" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123", "mkyong-.com",
                "" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "mkyong.t.t.c", "mkyong、com ", "mkyong", "mkyong.123", "mkyong.123",
                "mkyong.com/users", "-mkyong.com", "mkyong-.com", "sub.-mkyong.com", "sub.mkyong-.com" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DOMAIN_NAME).input(parameter).build().matches());
        }
    }

    @Nested
    class TestWebUrlPattern {

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "https://myconsciousness.github.io/", "http://myconsciousness.github.io/",
                "http://myconsciousness.github.io/test?something=something" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "myconsciousness.github.io/", "httpp://myconsciousness.github.io/",
                "ftp://myconsciousness.github.io/", "https://myconsciousness-github-io/",
                "smtp://myconsciousness.github.io/" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.WEB_URL).input(parameter).build().matches());
        }
    }

    @Nested
    class TestUserIdPattern {

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "shinya", "shinya_kato", "shinya.kato", "shinya0" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "" })

        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "テスト", "テスト", "試験", "!", "01234567", "shinya-kato", "shinya/kato" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.USER_ID).input(parameter).build().matches());
        }
    }

    @Nested
    class TestFixedPhoneJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000000000" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })

        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "00-0000-0000", "12000000000" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestFixedPhoneWithHypenJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "00-0000-0000" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })

        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "テスト", "試験", "@", "0000000000", "12-0000-00000" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FIXED_LINE_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }
    }

    @Nested
    class TestCellPhoneJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000t000001", "080000t000001", "0900000t00001" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000t000001", "080000t000001", "0900000t00001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000", "test", "テスト", "試験", "@",
                "070000000001", "080000000001", "090000000001" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestCellPhoneWithHypenJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testFind(final String parameter) {
            assertTrue(
                    Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "070-0000-0000", "080-0000-0000", "090-0000-0000" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00t001", "080-0000-00t001", "090-0000-00t001" })
        void testNotFind(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00t001", "080-0000-00t001", "090-0000-00t001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "07000000000", "08000000000", "09000000000", "test", "テスト", "試験", "@",
                "070-0000-00001", "080-0000-00001", "090-0000-00001" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.CELL_PHONE_WITH_HYPHEN_JP).input(parameter).build()
                    .matches());
        }
    }

    @Nested
    class TestPasswordPattern {

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "a1234567A", "A1234567a", "a1234567A@", "a123456789123456789123456789123A" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@", "" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@", "" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "01234567", "012345678", "a12345678", "A12345678", "a12345678@",
                "a1234567891234567891234567891234A" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.PASSWORD).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDatePattern {

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201118" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "20201301", "20201232", "2020-01-01", "2020/01/01", "test", "2020test" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDateWithHyphenPattern {

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-11-18" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020-13-01", "2020-12-32", "20200101", "2020/01/01", "test", "2020-te-st" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_HYPHEN).input(parameter).build().matches());
        }
    }

    @Nested
    class TestDateWithSlashPattern {

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/11/18" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "2020/13/01", "2020/12/32", "20200101", "2020-01-01", "test", "2020/te/st" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.DATE_WITH_SLASH).input(parameter).build().matches());
        }
    }

    @Nested
    class TestPostCodeJpPattern {

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "100-0001" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "1000001", "100-000!", "100-t001" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.POST_CODE_JP).input(parameter).build().matches());
        }
    }

    @Nested
    class TestXmlPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.xml", "Test.xml", "test.XML", "Test.XML", "test.Xml", "test.XMl", "test.xMl",
                "test.xML", "test.xmL", "test.xMl", "test.XmL" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "!.xml", "!.xmls", "testxml", "てすと.xml", "テスト.xml", "試験.xml" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.XML_FILE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestIpAddressPattern {

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "255.255.255.255", "192.168.0.24" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "255.2561.255.255", "255.255.2561.255", "255.2551.255.255", "255.255.2551.255" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "255.2561.255.255", "255.255.2561.255", "255.2551.255.255", "255.255.2551.255" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551",
                "255.255.255.2551:0000" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS).input(parameter).build().matches());
        }
    }

    @Nested
    class TestIpAddressWithPortPattern {

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "192.168.0.24:0", "255.255.255.255:0", "255.255.255.255:09", "255.255.255.255:099",
                "255.255.255.255:0999", "255.255.255.255:09999" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "", "255.2551.255.255:00",
                "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "", "255.2551.255.255:00",
                "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "256.255.255.255", "255.256.255.255", "255.255.256.255", "255.255.255.256",
                "2551.255.255.255", "255.2551.255.255", "255.255.2551.255", "255.255.255.2551", "256.255.255.255:00",
                "255.256.255.255:00", "255.255.256.255:00", "255.255.255.256:00", "2551.255.255.255:00",
                "255.2551.255.255:00", "255.255.2551.255:00", "255.255.255.2551:00" })
        void testNotMatch(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.IP_ADDRESS_WITH_PORT).input(parameter).build().matches());
        }
    }

    @Nested
    class TestNumericPattern {

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0", "00000" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0test", "test", "テスト", "テスト", "試験", "!^@" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.NUMERIC).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphanumericPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "0000", "Test001" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "-@%&$#", "てすと", "テスト", "試験" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "", "-@%&$#", "てすと", "テスト", "試験" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000-", "Test001?" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHANUMERIC).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetPattern {

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "Test", "TEST" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000", "?", "" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "0000", "?", "" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetUpperCasePattern {

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", "0000", "@" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", "0000", "@" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "Test?", "TESt" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET_UPPER_CASE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestAlphabetLowerCasePattern {

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testLookingAt(final String parameter) {
            assertTrue(
                    Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST", "", "0000" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "TEST", "", "0000" })
        void testNotLookingAt(final String parameter) {
            assertFalse(
                    Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test@", "Test!", "0000", "test?", "tesT" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.ALPHABET_LOWER_CASE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestFtpUrlPattern {

        @ParameterizedTest
        @ValueSource(strings = { "ftp://example.com/pub/file.txt", "testftp://example.com/pub/file.txt",
                "ftp://example.com/pub/file.txt test", " ftp://example.com/pub/file.txt " })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "ftp://example.com/pub/file.txt", "ftp://example.com/pub/file.txt test" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "ftp://example.com/pub/file.txt", "ftp://example.com/pub/" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "ftp", "ftp://" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "ftp", "ftp://", "test ftp://example.com/pub/",
                " ftp://example.com/pub/file.txt " })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "ftp", "ftp://", "testftp://example.com/pub/file.txt" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.FTP_URL).input(parameter).build().matches());
        }
    }

    @Nested
    class TestJavaFilePattern {

        @ParameterizedTest
        @ValueSource(strings = { "test.java", " test.java", "test test.java", "test test.java ", "test.JAVA",
                " test.JAVA", "test test.JAVA", "test test.JAVA " })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.java", "test.java test", "test.JAVA", "test.JAVA test", "test.java test" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.java", "test.JAVA" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "java", ".java", "JAVA", ".JAVA", "てすと.java", "テスト.java",
                "試験.java" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "java", ".java", "JAVA", ".JAVA", " test.java",
                "test test.java", "てすと.java", "テスト.java", "試験.java" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "java", ".java", "JAVA", ".JAVA", " test.java",
                "test test.java", "test.java test", "てすと.java", "テスト.java", "試験.java" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.JAVA_FILE).input(parameter).build().matches());
        }
    }

    @Nested
    class TestTextFilePattern {

        @ParameterizedTest
        @ValueSource(strings = { "test.txt", " test.txt", "test test.txt", "test test.txt ", "test.TXT", " test.TXT",
                "test test.TXT", "test test.TXT ", "てすと.txt", "テスト.txt", "試験.txt", "てすとテスト試験test012.txt" })
        void testFind(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.txt", "test.txt test", "test.TXT", "test.TXT test", "test.txt test", "てすと.txt",
                "テスト.txt", "試験.txt", "てすとテスト試験test012.txt" })
        void testLookingAt(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test.txt", "test.TXT", "テスト.txt", "試験.txt", "てすとテスト試験test012.txt" })
        void testMatches(final String parameter) {
            assertTrue(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().matches());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "txt", ".txt", "TXT", ".TXT" })
        void testNotFind(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().find());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "txt", ".txt", "TXT", ".TXT", "test test.txt",
                "test test.TXT", "test テスト.txt", "test 試験.txt", "test てすとテスト試験test012.txt" })
        void testNotLookingAt(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().lookingAt());
        }

        @ParameterizedTest
        @ValueSource(strings = { "test", "", " ", "　", "0000", "txt", ".txt", "TXT", ".TXT", "test test.txt",
                "test test.TXT", "test テスト.txt", "test 試験.txt", "test てすとテスト試験test012.txt", "test.txt text",
                "test.TXT text", "テスト.txt text", "試験.txt text", "てすとテスト試験test012.txt text" })
        void testNotMatch(final String parameter) {
            assertFalse(Epitaph.builder().pattern(RegexPattern.TEXT_FILE).input(parameter).build().matches());
        }
    }
}

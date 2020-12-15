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

import java.util.regex.Pattern;

import org.thinkit.api.catalog.BiCatalog;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * The catalog that manages regex option.
 *
 * @author Kato Shinya
 * @since 1.0.0
 */
@RequiredArgsConstructor
public enum RegexOption implements BiCatalog<RegexOption, Integer> {

    /**
     * Enables Unix lines mode.
     *
     * <p>
     * In this mode, only the {@code '\n'} line terminator is recognized in the
     * behavior of {@code .}, {@code ^}, and {@code $}.
     *
     * <p>
     * Unix lines mode can also be enabled via the embedded flag
     * expression&nbsp;{@code (?d)}.
     */
    UNIX_LINES(0, Pattern.UNIX_LINES),

    /**
     * Enables case-insensitive matching.
     *
     * <p>
     * By default, case-insensitive matching assumes that only characters in the
     * US-ASCII charset are being matched. Unicode-aware case-insensitive matching
     * can be enabled by specifying the {@link #UNICODE_CASE} flag in conjunction
     * with this flag.
     *
     * <p>
     * Case-insensitive matching can also be enabled via the embedded flag
     * expression&nbsp;{@code (?i)}.
     *
     * <p>
     * Specifying this flag may impose a slight performance penalty.
     * </p>
     */
    CASE_INSENSITIVE(1, Pattern.CASE_INSENSITIVE),

    /**
     * Permits whitespace and comments in pattern.
     *
     * <p>
     * In this mode, whitespace is ignored, and embedded comments starting with
     * {@code #} are ignored until the end of a line.
     *
     * <p>
     * Comments mode can also be enabled via the embedded flag
     * expression&nbsp;{@code (?x)}.
     */
    COMMENTS(2, Pattern.COMMENTS),

    /**
     * Enables multiline mode.
     *
     * <p>
     * In multiline mode the expressions {@code ^} and {@code $} match just after or
     * just before, respectively, a line terminator or the end of the input
     * sequence. By default these expressions only match at the beginning and the
     * end of the entire input sequence.
     *
     * <p>
     * Multiline mode can also be enabled via the embedded flag
     * expression&nbsp;{@code (?m)}.
     * </p>
     */
    MULTILINE(3, Pattern.MULTILINE),

    /**
     * Enables literal parsing of the pattern.
     *
     * <p>
     * When this flag is specified then the input string that specifies the pattern
     * is treated as a sequence of literal characters. Metacharacters or escape
     * sequences in the input sequence will be given no special meaning.
     *
     * <p>
     * The flags CASE_INSENSITIVE and UNICODE_CASE retain their impact on matching
     * when used in conjunction with this flag. The other flags become superfluous.
     *
     * <p>
     * There is no embedded flag character for enabling literal parsing.
     */
    LITERAL(4, Pattern.LITERAL),

    /**
     * Enables dotall mode.
     *
     * <p>
     * In dotall mode, the expression {@code .} matches any character, including a
     * line terminator. By default this expression does not match line terminators.
     *
     * <p>
     * Dotall mode can also be enabled via the embedded flag
     * expression&nbsp;{@code (?s)}. (The {@code s} is a mnemonic for "single-line"
     * mode, which is what this is called in Perl.)
     * </p>
     */
    DOTALL(5, Pattern.DOTALL),

    /**
     * Enables Unicode-aware case folding.
     *
     * <p>
     * When this flag is specified then case-insensitive matching, when enabled by
     * the {@link #CASE_INSENSITIVE} flag, is done in a manner consistent with the
     * Unicode Standard. By default, case-insensitive matching assumes that only
     * characters in the US-ASCII charset are being matched.
     *
     * <p>
     * Unicode-aware case folding can also be enabled via the embedded flag
     * expression&nbsp;{@code (?u)}.
     *
     * <p>
     * Specifying this flag may impose a performance penalty.
     * </p>
     */
    UNICODE_CASE(6, Pattern.UNICODE_CASE),

    /**
     * Enables canonical equivalence.
     *
     * <p>
     * When this flag is specified then two characters will be considered to match
     * if, and only if, their full canonical decompositions match. The expression
     * <code>"a&#92;u030A"</code>, for example, will match the string
     * <code>"&#92;u00E5"</code> when this flag is specified. By default, matching
     * does not take canonical equivalence into account.
     *
     * <p>
     * There is no embedded flag character for enabling canonical equivalence.
     *
     * <p>
     * Specifying this flag may impose a performance penalty.
     * </p>
     */
    CANON_EQ(7, Pattern.CANON_EQ),

    /**
     * Enables the Unicode version of <i>Predefined character classes</i> and
     * <i>POSIX character classes</i>.
     *
     * <p>
     * When this flag is specified then the (US-ASCII only) <i>Predefined character
     * classes</i> and <i>POSIX character classes</i> are in conformance with
     * <a href="http://www.unicode.org/reports/tr18/"><i>Unicode Technical Standard
     * #18: Unicode Regular Expression</i></a> <i>Annex C: Compatibility
     * Properties</i>.
     * <p>
     * The UNICODE_CHARACTER_CLASS mode can also be enabled via the embedded flag
     * expression&nbsp;{@code (?U)}.
     * <p>
     * The flag implies UNICODE_CASE, that is, it enables Unicode-aware case
     * folding.
     * <p>
     * Specifying this flag may impose a performance penalty.
     * </p>
     */
    UNICODE_CHARACTER_CLASS(8, Pattern.UNICODE_CHARACTER_CLASS);

    /**
     * The code
     */
    @Getter
    private final int code;

    /**
     * The tag
     */
    @Getter
    private final Integer tag;
}

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

import java.util.regex.Matcher;

import org.thinkit.common.regex.catalog.RegexPattern;

import lombok.NonNull;

/**
 *
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
public final class FluentRegex {

    /**
     * The matcher
     */
    private Matcher matcher;

    /**
     * Default constructor
     */
    private FluentRegex() {
    }

    /**
     * The builder class for {@link FluentRegex} .
     */
    static class Builder {

        /**
         * The regex pattern
         */
        private RegexPattern regexPattern;

        /**
         * The input
         */
        private CharSequence input;

        /**
         * Defalut constructor
         */
        private Builder() {
        }

        /**
         * Sets the regex pattern.
         *
         * @param regexPattern The regex pattern
         * @return The instance of {@link Builder}
         *
         * @exception NullPointerException If {@code null} is passed as an argument
         */
        public Builder pattern(@NonNull RegexPattern regexPattern) {
            this.regexPattern = regexPattern;
            return this;
        }

        /**
         * Sets the input character sequence.
         *
         * @param input The input
         * @return The instance of {@link Builder}
         *
         * @exception NullPointerException If {@code null} is passed as an argument
         */
        public Builder input(@NonNull CharSequence input) {
            this.input = input;
            return this;
        }

        /**
         * Returns the new instance of {@link FluentRegex} .
         *
         * @return The new instance of {@link FluentRegex}
         */
        public FluentRegex build() {

            final FluentRegex fluentRegex = new FluentRegex();
            fluentRegex.matcher = this.regexPattern.getTag().matcher(this.input);

            return fluentRegex;
        }
    }

    /**
     * Attempts to find the next subsequence of the input sequence that matches the
     * pattern.
     *
     * <p>
     * This method starts at the beginning of this matcher's region, or, if a
     * previous invocation of the method was successful and the matcher has not
     * since been reset, at the first character not matched by the previous match.
     *
     * <p>
     * If the match succeeds then more information can be obtained via the
     * {@code start}, {@code end}, and {@code group} methods.
     * </p>
     *
     * @return {@code true} if, and only if, a subsequence of the input sequence
     *         matches this matcher's pattern
     */
    public boolean find() {
        return this.matcher.find();
    }

    /**
     * Attempts to match the input sequence, starting at the beginning of the
     * region, against the pattern.
     *
     * <p>
     * Like the {@link #matches matches} method, this method always starts at the
     * beginning of the region; unlike that method, it does not require that the
     * entire region be matched.
     *
     * <p>
     * If the match succeeds then more information can be obtained via the
     * {@code start}, {@code end}, and {@code group} methods.
     * </p>
     *
     * @return {@code true} if, and only if, a prefix of the input sequence matches
     *         this matcher's pattern
     */
    public boolean lookingAt() {
        return this.matcher.lookingAt();
    }

    /**
     * Attempts to match the entire region against the pattern.
     *
     * <p>
     * If the match succeeds then more information can be obtained via the
     * {@code start}, {@code end}, and {@code group} methods.
     * </p>
     *
     * @return {@code true} if, and only if, the entire region sequence matches this
     *         matcher's pattern
     */
    public boolean matches() {
        return this.matcher.matches();
    }

    /**
     * Replaces the first subsequence of the input sequence that matches the pattern
     * with the given replacement string.
     *
     * <p>
     * This method first resets this matcher. It then scans the input sequence
     * looking for a match of the pattern. Characters that are not part of the match
     * are appended directly to the result string; the match is replaced in the
     * result by the replacement string. The replacement string may contain
     * references to captured subsequences as in the {@link #appendReplacement
     * appendReplacement} method.
     *
     * <p>
     * Note that backslashes ({@code \}) and dollar signs ({@code $}) in the
     * replacement string may cause the results to be different than if it were
     * being treated as a literal replacement string. Dollar signs may be treated as
     * references to captured subsequences as described above, and backslashes are
     * used to escape literal characters in the replacement string.
     *
     * <p>
     * Given the regular expression {@code dog}, the input
     * {@code "zzzdogzzzdogzzz"}, and the replacement string {@code "cat"}, an
     * invocation of this method on a matcher for that expression would yield the
     * string {@code "zzzcatzzzdogzzz"}.
     * </p>
     *
     * <p>
     * Invoking this method changes this matcher's state. If the matcher is to be
     * used in further matching operations then it should first be reset.
     * </p>
     *
     * @param replacement The replacement string
     * @return The string constructed by replacing the first matching subsequence by
     *         the replacement string, substituting captured subsequences as needed
     *
     * @exception NullPointerException If {@code null} is passed as an argument
     */
    public String replaceFirst(@NonNull String replacement) {
        return this.matcher.replaceFirst(replacement);
    }
}

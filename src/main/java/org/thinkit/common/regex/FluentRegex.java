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

        public Builder pattern(@NonNull RegexPattern regexPattern) {
            this.regexPattern = regexPattern;
            return this;
        }

        public Builder input(@NonNull CharSequence input) {
            this.input = input;
            return this;
        }

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
}

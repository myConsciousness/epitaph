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

import java.util.ArrayList;
import java.util.Collection;

import org.thinkit.common.regex.catalog.RegexOption;

import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.ToString;

/**
 * The class that manages options for parsing regular expressions.
 * <p>
 * Additional parsing options can be retrieved by using the
 * {@link #getRegexOption()} method.
 *
 * @author Kato Shinya
 * @since 1.0
 * @version 1.0
 */
@ToString
@EqualsAndHashCode(callSuper = false)
public final class Option extends ArrayList<RegexOption> {

    /**
     * Serial Verison UID
     */
    private static final long serialVersionUID = 6490513068229795517L;

    /**
     * Constructs an empty list with an initial capacity of {@code 10} .
     */
    private Option() {
        super();
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the list
     *
     * @exception IllegalArgumentException If the specified initial capacity is
     *                                     negative
     */
    private Option(int initialCapacity) {
        super(initialCapacity);
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the
     * order they are returned by the collection's iterator.
     *
     * @param collection The collection whose elements are to be placed into this
     *                   list
     *
     * @exception NullPointerException If the specified collection is {@code null}
     */
    private Option(@NonNull Collection<? extends RegexOption> collection) {
        super(collection);
    }

    /**
     * Constructs an empty list with an initial capacity of {@code 10} .
     *
     * @return The new isntance of {@link Option}
     */
    public static Option of() {
        return new Option();
    }

    /**
     * Constructs an empty list with the specified initial capacity.
     *
     * @param initialCapacity The initial capacity of the list
     * @return The new isntance of {@link Option}
     *
     * @exception IllegalArgumentException If the specified initial capacity is
     *                                     negative
     */
    public static Option of(int initialCapacity) {
        return new Option(initialCapacity);
    }

    /**
     * Constructs a list containing the elements of the specified collection, in the
     * order they are returned by the collection's iterator.
     *
     * @param collection The collection whose elements are to be placed into this
     *                   list
     * @return The new isntance of {@link Option}
     *
     * @exception NullPointerException If the specified collection is {@code null}
     */
    public static Option of(@NonNull Collection<? extends RegexOption> collection) {
        return new Option(collection);
    }

    /**
     * Return the total of the regex options that were set.
     *
     * @return The total of the regex options
     */
    public int getRegexOption() {

        int option = 0;

        for (RegexOption RegexOption : this) {
            option |= RegexOption.getTag().intValue();
        }

        return option;
    }
}
